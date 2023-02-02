package com.agencyapp.dao.messaging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agencyapp.dto.messaging.ResponseGetConversationID;
import com.agencyapp.model.messaging.ConversationsEntity;

@Repository
public interface ConversationsDao extends JpaRepository<ConversationsEntity, Integer>{
	public ConversationsEntity findById(int id);
	
	@Query(value = "SELECT * FROM conversations as c WHERE c.creator_id = :userId", nativeQuery = true)
	public List<ConversationsEntity> findByUserId(@Param("userId") int userId);
	
	@Query(value = "SELECT c.id as conversation_id, c.creator_id, p.user_id FROM conversations as c INNER JOIN participants as p on c.id = p.conversation_id  WHERE c.creator_id = :idCurrentUser and p.user_id = :idProUser", nativeQuery = true)
	public ResponseGetConversationID findConversationIdBy2ID(@Param("idCurrentUser") int idCurrentUser, @Param("idProUser") int idProUser);
}
