package com.agencyapp.controller.iam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.agencyapp.dto.ReponseUserAgencyDTO;
import com.agencyapp.dto.ResponseObjectDTO;
import com.agencyapp.dto.iam.User2DTO;
import com.agencyapp.model.iam.UsersEntity;
import com.agencyapp.model.messaging.MessagesEntity;
import com.agencyapp.service.IUsersService;

@RestController
public class UsersController {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private IUsersService iUserService;

	// Get all users @RequestParam("offset") Optional<Integer> page,@RequestParam("pagesize") Optional<Integer> pagesize
	@GetMapping(value = "/users")
	public ResponseEntity<ResponseObjectDTO> getAllUser(@RequestParam("page") Optional<Integer> page,@RequestParam("pagesize") Optional<Integer> pagesize) {
		List<User2DTO> userFoundList = iUserService.getAllUser(page.orElse(0), pagesize.orElse(5));
		if (!userFoundList.isEmpty() && !(userFoundList == null)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", userFoundList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find user", ""));
		}

	}

	// Return user data for given user’s id
	@GetMapping(value = "/users/{id}")
	public ResponseEntity<ResponseObjectDTO> getUserById(@PathVariable("id") int id) {
		User2DTO userFound = iUserService.getUserById(id);
		if (userFound != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", userFound));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find user with id = " + id, ""));
		}
	}

//	@GetMapping("/getInfo")
//    public List<ReponseUserAgencyDTO> getJoinInformation(){
//        return iUserService.getJoinInformation();
//    }
	// Get user data and related agency data
	@GetMapping("/usersInfo")
	public ResponseEntity<ResponseObjectDTO> getUSerDTO(HttpServletRequest request) {
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		User2DTO userFound = iUserService.getUserDTO(username);
		if (userFound != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", userFound));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find user with username = " + username, ""));
		}

	}
	@PostMapping(value = "/register")
	public ResponseEntity<ResponseObjectDTO> sendMessages(@RequestBody UsersEntity usersEntity,@RequestParam("agency_id") int agencyID) {
		UsersEntity userEntityCheck = iUserService.addUser(usersEntity, agencyID);
		if (usersEntity != null && userEntityCheck != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObjectDTO("ok", "Insert successfully", userEntityCheck));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObjectDTO("failed", "Cannot insert user", ""));
		}
	}

	@PutMapping(value = "/users/update/{id}")
	public ResponseEntity<ResponseObjectDTO> updateMessages(@PathVariable("id") int id,
			@RequestBody UsersEntity userEnity,@RequestParam("agency_id") int agencyID) {
		UsersEntity userUpdated = iUserService.updateUser(id, userEnity,agencyID);
		if (userUpdated != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Update successfully", userUpdated));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObjectDTO("failed", "Cannot update user id = " + id, ""));
		}
	}
	@DeleteMapping(value = "/users/delete/{id}")
	public ResponseEntity<ResponseObjectDTO> updateMessages(@PathVariable("id") int id) {
		if (iUserService.deleteUser(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObjectDTO("ok", "Delete successfully", ""));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObjectDTO("failed", "Cannot delete user id = " + id, ""));
		}
	}
}
