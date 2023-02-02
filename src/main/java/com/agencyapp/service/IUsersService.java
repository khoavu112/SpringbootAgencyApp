package com.agencyapp.service;

import java.util.List;

import com.agencyapp.dto.iam.User2DTO;
import com.agencyapp.model.iam.UsersEntity;

public interface IUsersService {
	public List<User2DTO> getAllUser(int page, int pagesize);
	public UsersEntity getUserEntityByUsername(String username);
	public User2DTO getUserById(int id);
	public User2DTO getUserDTO(String username);
	public UsersEntity updateUser(int id, UsersEntity usersEntity,int agencyID);
	public boolean deleteUser(int id);
	public UsersEntity addUser(UsersEntity usersEntity, int agencyID);
	public UsersEntity updateUserLoginDate(String username);
}
