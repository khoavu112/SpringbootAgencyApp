package com.agencyapp.dao.messaging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agencyapp.model.messaging.ParticipantsEntity;

@Repository
public interface ParticipantsDao extends JpaRepository<ParticipantsEntity, Integer>{
	@Query(value = "DELETE from participants where user_id = :userIdP", nativeQuery = true)
	public void deleteParticipantByUserID(@Param("userIdP") int userID);
	public void deleteByUserId(int userId);
	public List<ParticipantsEntity> findByUserId(int userId);
	@Query(value = "SELECT * FROM participants as p WHERE p.conversation_id = :conIdP", nativeQuery = true)
	public ParticipantsEntity findByConversation_id(@Param("conIdP") int conId);
}
