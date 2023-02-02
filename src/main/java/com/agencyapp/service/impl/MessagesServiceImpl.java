package com.agencyapp.service.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.agencyapp.dao.iam.User2Dao;
import com.agencyapp.dao.messaging.ConversationsDao;
import com.agencyapp.dao.messaging.MessagesDao;
import com.agencyapp.dao.messaging.ParticipantsDao;
import com.agencyapp.dto.messaging.MessagesDTO;
import com.agencyapp.dto.messaging.ResponseMessagesDTO;
import com.agencyapp.model.GoogleDriveFileDTO;
import com.agencyapp.model.iam.UsersEntity;
import com.agencyapp.model.messaging.ConversationsEntity;
import com.agencyapp.model.messaging.MessagesEntity;
import com.agencyapp.model.messaging.ParticipantsEntity;
import com.agencyapp.service.IGoogleDriveFile;
import com.agencyapp.service.IMessagesService;

@Service
public class MessagesServiceImpl implements IMessagesService {
	private final String URL_PREFIX = "https://drive.google.com/uc?id=";
	private SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
	@Autowired
	private MessagesDao messagesDao;
	@Autowired
	private User2Dao userDao;
	@Autowired
	private ConversationsDao conversationsDao;
	@Autowired
	private ParticipantsDao participantsDao;
	@Autowired
	private IGoogleDriveFile iGoogleDriveFile;
	@Override
	public List<MessagesDTO> getAllMessages(Integer page, Integer pagesize) {
		Pageable pageable = PageRequest.of(page, pagesize, Sort.by("created").descending());
		List<ResponseMessagesDTO> listFound = messagesDao.findAllMessage(pageable);
		return convertStatusDTOResponse(listFound);
	}

	@Override
	public List<MessagesDTO> getAllMessageUnseenDTO(Integer page, Integer pagesize, int userId) {
		Pageable pageable = PageRequest.of(page, pagesize, Sort.by("created").descending());
		List<ResponseMessagesDTO> listFound = messagesDao.findAllMessageUnseenDTO("viewed", pageable, userId);
		List<MessagesDTO> convertedList = convertStatusDTOResponse(listFound);
		return convertedList;
		
	}

	@Override
	public List<MessagesDTO> getAllMessageUnseenByConversationIdDTO(int conversationId, Integer page,
			Integer pagesize, int userId) {
		Pageable pageable = PageRequest.of(page, pagesize, Sort.by("created").descending());
		List<ResponseMessagesDTO> listFound = messagesDao.findAllMessageUnseenByConversationIdDTO("viewed", conversationId, pageable, userId);
		List<MessagesDTO> convertedList = convertStatusDTOResponse(listFound);
		return convertedList;
	}

	@Override
	public List<MessagesDTO> findByConversationsID(int id,Integer page,
			Integer pagesize) {
		Pageable pageable = PageRequest.of(page, pagesize, Sort.by("created").ascending());
		List<ResponseMessagesDTO> listFound = messagesDao.findAllMessageByConversationIdDTO(id, pageable);
		List<MessagesDTO> convertedList = convertStatusDTOResponse(listFound);
		return convertedList;
	}

	@Override
	public MessagesEntity addMessages(MessagesEntity messagesEntity,int senderID,int conversationID) {
		UsersEntity userEntity = userDao.findByid(senderID);
		ConversationsEntity conversationsEntity = conversationsDao.findById(conversationID);
		if(messagesEntity != null && conversationsEntity != null && userEntity != null ) {
			messagesEntity.setConversation(conversationsEntity);
			messagesEntity.setUser(userEntity);
			messagesEntity.setCreated(fm.format(new Date()));
			return messagesDao.save(messagesEntity);
		}
		return null;
	}

	@Override
	public MessagesEntity updateMessages(int id, MessagesEntity messagesEntity,int senderID,int conversationID) {
		UsersEntity userEntity = userDao.findByid(senderID);
		ConversationsEntity conversationsEntity = conversationsDao.findById(conversationID);
		if (messagesEntity != null && userEntity != null && conversationsEntity != null) {
			MessagesEntity messagesFound = messagesDao.findById(id);
			if (messagesFound != null) {
				messagesFound.setUser(userEntity);
				messagesFound.setConversation(conversationsEntity);
				messagesFound.setType(messagesEntity.getType());
				messagesFound.setMessage(messagesEntity.getMessage());
				messagesFound.setStatus(messagesEntity.getStatus());
				messagesFound.setCreated(messagesEntity.getCreated());
				messagesFound.setDeleted(messagesEntity.getDeleted());
				return messagesDao.save(messagesFound);
			}
		}
		return null;
	}

	@Override
	public boolean deleteMessages(int id) {
		if (messagesDao.findById(id) != null) {
			messagesDao.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public ConversationsEntity addConversation(ConversationsEntity conversationsEntity) {
		if(conversationsEntity != null) {
			conversationsEntity.setCreated(fm.format(new Date()));
			return conversationsDao.save(conversationsEntity);
		}
		return null;
	}

	@Override
	public boolean deleteConversation(int id) {
		if(conversationsDao.findById(id) != null) {
			conversationsDao.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Override
	public ParticipantsEntity addParticipants(ParticipantsEntity participantsEntity) {
		if(participantsEntity != null) {
			participantsEntity.setCreated(fm.format(new Date()));
			return participantsDao.save(participantsEntity);
		}
		return null;
	}
	
	

	public List<MessagesDTO> convertStatusDTO(List<MessagesEntity> listMessageEntity) {
		List<MessagesDTO> listConvertedMessagesDTO = new ArrayList<>();
		if (listMessageEntity != null && listMessageEntity.size() > 0) {
			for (int i = 0; i < listMessageEntity.size(); i++) {
				MessagesDTO messDTO = new MessagesDTO();
				messDTO.setId(listMessageEntity.get(i).getId());
				messDTO.setSenderName(listMessageEntity.get(i).getUser().getUsername());
				messDTO.setConversationName(listMessageEntity.get(i).getConversation().getTitle());
				messDTO.setType(listMessageEntity.get(i).getType());
				messDTO.setMessage(listMessageEntity.get(i).getMessage());
				if (listMessageEntity.get(i).getStatus().equalsIgnoreCase("not_sent")) {
					messDTO.setStatus(0);
				} else if (listMessageEntity.get(i).getStatus().equalsIgnoreCase("sent")) {
					messDTO.setStatus(1);
				} else {
					messDTO.setStatus(2);
				}
				messDTO.setCreated(listMessageEntity.get(i).getCreated());
				messDTO.setDeleted(listMessageEntity.get(i).getDeleted());
				listConvertedMessagesDTO.add(messDTO);
			}
		}
		return listConvertedMessagesDTO;
	}
	
	public List<MessagesDTO> convertStatusDTOResponse(List<ResponseMessagesDTO> listMessageResonseDTO) {
		List<MessagesDTO> listConvertedMessagesDTO = new ArrayList<>();
		if (listMessageResonseDTO != null && listMessageResonseDTO.size() > 0) {
			for (int i = 0; i < listMessageResonseDTO.size(); i++) {
				MessagesDTO messDTO = new MessagesDTO();
				messDTO.setId(listMessageResonseDTO.get(i).getId());
				messDTO.setSenderName(listMessageResonseDTO.get(i).getSender());
				messDTO.setConversationName(listMessageResonseDTO.get(i).getConversation_name());
				messDTO.setType(listMessageResonseDTO.get(i).getType());
				messDTO.setMessage(listMessageResonseDTO.get(i).getMessage());
				if (listMessageResonseDTO.get(i).getStatus().equalsIgnoreCase("not_sent")) {
					messDTO.setStatus(0);
				} else if (listMessageResonseDTO.get(i).getStatus().equalsIgnoreCase("sent")) {
					messDTO.setStatus(1);
				} else {
					messDTO.setStatus(2);
				}
				messDTO.setCreated(listMessageResonseDTO.get(i).getCreated());
				messDTO.setDeleted(listMessageResonseDTO.get(i).getDeleted());
				try {
					messDTO.setPic_user(URL_PREFIX+ getIdImageByNameImage(listMessageResonseDTO.get(i).getPic_user()));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (GeneralSecurityException e) {					
					e.printStackTrace();
				}
				listConvertedMessagesDTO.add(messDTO);
			}
		}
		return listConvertedMessagesDTO;
	}
	public String getIdImageByNameImage(String imageName) throws IOException, GeneralSecurityException {
		List<GoogleDriveFileDTO> listFound = iGoogleDriveFile.getAllFile();
		for(int i = 0 ; i < listFound.size() ; i++) {
			if(listFound.get(i).getName().equalsIgnoreCase(imageName)) {
				return listFound.get(i).getId().toString();
			}
		}
		return "";
	}
}
