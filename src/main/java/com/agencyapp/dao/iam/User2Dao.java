package com.agencyapp.dao.iam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agencyapp.model.iam.UsersEntity;

@Repository
public interface User2Dao extends JpaRepository<UsersEntity, Integer>{
	public UsersEntity findByid(int id);
	public UsersEntity findByUsername(String username);
	
}
