package com.agencyapp.controller.messaging;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agencyapp.config.JwtTokenUtil;
import com.agencyapp.dao.messaging.ParticipantsDao;
import com.agencyapp.dto.ResponseObjectDTO;
import com.agencyapp.dto.iam.User2DTO;
import com.agencyapp.dto.messaging.MessagesDTO;
import com.agencyapp.dto.messaging.ParticipantDTO;
import com.agencyapp.dto.messaging.ResponseMessagesDTO;
import com.agencyapp.model.iam.UsersEntity;
import com.agencyapp.model.messaging.ConversationsEntity;
import com.agencyapp.model.messaging.MessagesEntity;
import com.agencyapp.model.messaging.ParticipantsEntity;
import com.agencyapp.service.IMessagesService;
import com.agencyapp.service.IParticipantsService;
import com.agencyapp.service.IUsersService;

@RestController
public class MessagesController {
	@Autowired
	private IMessagesService iMessagesService;
	@Autowired
	private IParticipantsService iParticipantsService;
	@Autowired
	private ParticipantsDao participantsDao;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private IUsersService iUserService;

	// Get all messages
	@GetMapping(value = "/messages")
	public ResponseEntity<ResponseObjectDTO> getAllMessages(@RequestParam("page") Optional<Integer> page,@RequestParam("pagesize") Optional<Integer> pagesize) {
		List<MessagesDTO> messagesList = iMessagesService.getAllMessages(page.orElse(0),pagesize.orElse(5));
		if (messagesList != null && messagesList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", messagesList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find messages", ""));
		}
	}
	
	// Get all messages unseen
		@GetMapping(value = "/messagesUnseen")
		public ResponseEntity<ResponseObjectDTO> getAllMessagesUnseen(HttpServletRequest request,@RequestParam("page") Optional<Integer> page,@RequestParam("pagesize") Optional<Integer> pagesize) {
			final String requestTokenHeader = request.getHeader("Authorization");
			String jwtToken = requestTokenHeader.substring(7);
			String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			UsersEntity userEntity = iUserService.getUserEntityByUsername(username);
			List<MessagesDTO> messagesList = iMessagesService.getAllMessageUnseenDTO(page.orElse(0),pagesize.orElse(3),userEntity.getId());
			if (messagesList != null && messagesList.size() > 0) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObjectDTO("ok", "Querry successfully", messagesList));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObjectDTO("failed", "Cannot find messages", ""));
			}
		}
		
		// Get all messages unseen by conversation id
				@GetMapping(value = "/messagesUnseen/{id}")
				public ResponseEntity<ResponseObjectDTO> getAllMessagesUnseenByConversationId(HttpServletRequest request,@PathVariable("id") int id,@RequestParam("page") Optional<Integer> page,@RequestParam("pagesize") Optional<Integer> pagesize) {
					final String requestTokenHeader = request.getHeader("Authorization");
					String jwtToken = requestTokenHeader.substring(7);
					String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
					UsersEntity userEntity = iUserService.getUserEntityByUsername(username);
					List<MessagesDTO> messagesList = iMessagesService.getAllMessageUnseenByConversationIdDTO(id,page.orElse(0),pagesize.orElse(3),userEntity.getId());
					if (messagesList != null && messagesList.size() > 0) {
						return ResponseEntity.status(HttpStatus.OK)
								.body(new ResponseObjectDTO("ok", "Querry successfully", messagesList));
					} else {
						return ResponseEntity.status(HttpStatus.NOT_FOUND)
								.body(new ResponseObjectDTO("failed", "Cannot find messages", ""));
					}
				}

	// Get all messages by conversation id
	@GetMapping(value = "/messages/{id}")
	public ResponseEntity<ResponseObjectDTO> getMessagesByConversationsID(@PathVariable("id") int id,@RequestParam("page") Optional<Integer> page,@RequestParam("pagesize") Optional<Integer> pagesize) {
		List<MessagesDTO> messagesList = iMessagesService.findByConversationsID(id,page.orElse(0),pagesize.orElse(3));
		if (messagesList != null && messagesList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", messagesList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find messages with id_conversation = " + id, ""));
		}

	}

	@PostMapping(value = "/messages/send")
	public ResponseEntity<ResponseObjectDTO> sendMessages(@RequestBody MessagesEntity messagesEntity,@RequestParam("sender_id") int senderID, @RequestParam("conversation_id") int conversationID) {
		if (messagesEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObjectDTO("ok", "Insert successfully", iMessagesService.addMessages(messagesEntity,senderID, conversationID)));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObjectDTO("failed", "Cannot insert", ""));
		}
	}

	@PutMapping(value = "/messages/update/{id}")
	public ResponseEntity<ResponseObjectDTO> updateMessages(@PathVariable("id") int id,
			@RequestBody MessagesEntity messagesEntity,@RequestParam("sender_id") int senderID, @RequestParam("conversation_id") int conversationID) {
		MessagesEntity messagesUpdated = iMessagesService.updateMessages(id, messagesEntity,senderID, conversationID);
		if (messagesUpdated != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Update successfully", messagesUpdated));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObjectDTO("failed", "Cannot update message id= " + id, ""));
		}
	}

	@DeleteMapping("/messages/delete/{id}")
	public ResponseEntity<ResponseObjectDTO> deleteMessages(@PathVariable("id") int id) {
		if (iMessagesService.deleteMessages(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObjectDTO("ok", "Delete successfully", ""));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("failed", "Cannot delete message id= " + id, ""));
		}

	}

	@PostMapping(value = "/conversation/add")
	public ResponseEntity<ResponseObjectDTO> addConversation(@RequestBody ConversationsEntity conversationEntity) {
		if (conversationEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObjectDTO("ok", "Insert successfully", iMessagesService.addConversation(conversationEntity)));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObjectDTO("failed", "Cannot insert", ""));
		}
	}
	
	@DeleteMapping("/conversation/delete/{id}")
	public ResponseEntity<ResponseObjectDTO> deleteConversation(@PathVariable("id") int id) {
		if (iMessagesService.deleteConversation(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObjectDTO("ok", "Delete successfully", ""));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("failed", "Cannot delete message id= " + id, ""));
		}

	}

	@PostMapping(value = "/participant/add")
	public ResponseEntity<ResponseObjectDTO> addParticipant(@RequestBody ParticipantsEntity participantsEntity) {
		if (participantsEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObjectDTO("ok", "Insert successfully", iMessagesService.addParticipants(participantsEntity)));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObjectDTO("failed", "Cannot insert", ""));
		}
	}
	
	// 
	
	@GetMapping(value = "/participantByUserLogin")
	public ResponseEntity<ResponseObjectDTO> getParticipantByID(HttpServletRequest request) {
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		User2DTO userFound = iUserService.getUserDTO(username);
		List<ParticipantDTO> participantsEntity = iParticipantsService.getParticipantByUserLogin(userFound.getId());
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObjectDTO("ok", "Querry successfully", participantsEntity ));

	}

	
	
	// for testing
	@GetMapping(value = "/participants")
	public ResponseEntity<ResponseObjectDTO> getParticipantID() {

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObjectDTO("ok", "Querry successfully", participantsDao.findAll()));

	}

	@GetMapping(value = "/participants/{usersId}")
	public ResponseEntity<ResponseObjectDTO> getParticipantByUserID(@PathVariable("usersId") int usersId) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObjectDTO("ok", "Querry successfully", participantsDao.findByUserId(usersId)));

	}

	@DeleteMapping("/participants/delete/{usersId}")
	public ResponseEntity<ResponseObjectDTO> deleteparitcipant(@PathVariable("usersId") int usersId) {
		if (iParticipantsService.deleteParticipantsByUserID(usersId)) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObjectDTO("ok", "Delete successfully", ""));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("failed", "Cannot delete message user_id = " + usersId, ""));
		}

	}
}
