package com.agencyapp.service.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agencyapp.dao.iam.AgenciesDao;
import com.agencyapp.dao.iam.User2Dao;
import com.agencyapp.dto.ReponseUserAgencyDTO;
import com.agencyapp.dto.iam.AgenciesDTOPO;
import com.agencyapp.dto.iam.User2DTO;
import com.agencyapp.model.GoogleDriveFileDTO;
import com.agencyapp.model.UserDTO;
import com.agencyapp.model.iam.AgenciesEntity;
import com.agencyapp.model.iam.UsersEntity;
import com.agencyapp.model.messaging.MessagesEntity;
import com.agencyapp.service.IGoogleDriveFile;
import com.agencyapp.service.IUsersService;

@Service
public class UsersServiceImpl implements IUsersService {
	private SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
	private final String URL_PREFIX = "https://drive.google.com/uc?id=";
	@Autowired
	private User2Dao userDao;
	@Autowired
	private AgenciesDao agenciesDao;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private IGoogleDriveFile iGoogleDriveFile;

	@Override
	public List<User2DTO> getAllUser(int page, int pagesize) {
		Pageable pageable = PageRequest.of(page, pagesize);
		List<UsersEntity> userEntityList = userDao.findAll(pageable).getContent();
		return convertToDTOList(userEntityList);
	}

	@Override
	public User2DTO getUserById(int id) {
		UsersEntity usersEntity = userDao.findByid(id);
		return convertToDTO(usersEntity);
	}

	@Override
	public User2DTO getUserDTO(String username) {
		UsersEntity usersEntity = userDao.findByUsername(username);
		return convertToDTO(usersEntity);
	}

	@Override
	public UsersEntity updateUser(int id, UsersEntity usersEntity, int agencyID) {
		AgenciesEntity agenciesEntity = agenciesDao.findByid(agencyID);
		if (usersEntity != null && agenciesEntity!= null) {
			UsersEntity userFound = userDao.findByid(id);
			if (userFound != null) {
				userFound.setAgency(agenciesEntity);
				userFound.setUsername(userFound.getUsername());
				userFound.setFirst_name(usersEntity.getFirst_name());
				userFound.setLast_name(usersEntity.getLast_name());
				userFound.setEmail(usersEntity.getEmail());
				userFound.setPassword(bcryptEncoder.encode(usersEntity.getPassword()));
				userFound.setJob_title(usersEntity.getJob_title());
				userFound.setLast_login_date(usersEntity.getLast_login_date());
				userFound.setCreated(usersEntity.getCreated());
				userFound.setUpdated(java.time.LocalDate.now().toString());
				userFound.setPic_user(usersEntity.getPic_user());
				return userDao.save(userFound);
			}
		}
		return null;
	}

	@Override
	public UsersEntity updateUserLoginDate(String username) {
		if (username != null) {
			UsersEntity userFound = userDao.findByUsername(username);
			if (userFound != null) {
				userFound.setLast_login_date(java.time.LocalDate.now().toString());
				return userDao.save(userFound);
			}
		}
		return null;
	}

	@Override
	public boolean deleteUser(int id) {
		if (userDao.findByid(id) != null) {
			userDao.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UsersEntity addUser(UsersEntity usersEntity, int agencyID) {
		AgenciesEntity agenciesEntity = agenciesDao.findByid(agencyID);
		if (userDao.findByUsername(usersEntity.getUsername()) == null && agenciesEntity != null) {
			usersEntity.setPassword(bcryptEncoder.encode(usersEntity.getPassword()));
			usersEntity.setAgency(agenciesEntity);
			usersEntity.setCreated(java.time.LocalDate.now().toString());
			return userDao.save(usersEntity);
		} else {
			return null;
		}
	}

	@Override
	public UsersEntity getUserEntityByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public List<User2DTO> convertToDTOList(List<UsersEntity> listEntity) {
		List<User2DTO> listDTO = new ArrayList<>();
		if (listEntity != null && listEntity.size() > 0) {
			for (int i = 0; i < listEntity.size(); i++) {
				User2DTO usersDTO = new User2DTO();
				usersDTO.setId(listEntity.get(i).getId());
				usersDTO.setAgencyName(listEntity.get(i).getAgency().getName());
				usersDTO.setUsername(listEntity.get(i).getUsername());
				usersDTO.setFirstName(listEntity.get(i).getFirst_name());
				usersDTO.setLastName(listEntity.get(i).getLast_name());
				usersDTO.setEmail(listEntity.get(i).getEmail());
				usersDTO.setPassword(listEntity.get(i).getPassword());
				usersDTO.setJobTitle(listEntity.get(i).getJob_title());
				usersDTO.setLastLoginDate(listEntity.get(i).getLast_login_date().toString());
				usersDTO.setCreated(listEntity.get(i).getCreated());
				usersDTO.setUpdated(listEntity.get(i).getUpdated());
				try {
					usersDTO.setPicUser(URL_PREFIX + getIdImageByNameImage(listEntity.get(i).getPic_user()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listDTO.add(usersDTO);
			}
		}
		return listDTO;
	}

	public User2DTO convertToDTO(UsersEntity userEntity) {
		User2DTO userDTO = new User2DTO();
		if (userEntity != null) {

			userDTO.setId(userEntity.getId());
			userDTO.setAgencyName(userEntity.getAgency().getName());
			userDTO.setUsername(userEntity.getUsername());
			userDTO.setFirstName(userEntity.getFirst_name());
			userDTO.setLastName(userEntity.getLast_name());
			userDTO.setEmail(userEntity.getEmail());
			userDTO.setPassword(userEntity.getPassword());
			userDTO.setJobTitle(userEntity.getJob_title());
			userDTO.setLastLoginDate(userEntity.getLast_login_date().toString());
			userDTO.setCreated(userEntity.getCreated());
			userDTO.setUpdated(userEntity.getUpdated());
			try {
				userDTO.setPicUser(URL_PREFIX + getIdImageByNameImage(userEntity.getPic_user()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return userDTO;
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
