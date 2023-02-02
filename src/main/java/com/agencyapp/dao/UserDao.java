package com.agencyapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.agencyapp.model.iam.UsersEntity;

@Repository
public interface UserDao extends CrudRepository<UsersEntity, Integer> {
	UsersEntity findByUsername(String username);
}