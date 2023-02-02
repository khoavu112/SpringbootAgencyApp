package com.agencyapp.dao.messaging;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agencyapp.dto.messaging.ResponseMessagesDTO;
import com.agencyapp.model.messaging.MessagesEntity;
@Repository
public interface MessagesDao extends JpaRepository<MessagesEntity, Integer>{
	

	@Query(value = "SELECT m.id, u.username as sender, c.title as conversation_name, m.type, m.message, m.status, m.created, m.deleted,u.pic_user FROM messages as m INNER JOIN users as u on u.id = m.sender_id INNER JOIN conversations as c ON c.id = m.conversation_id", nativeQuery = true)
	public List<ResponseMessagesDTO> findAllMessage(Pageable pageable);
	
	public MessagesEntity findById(int id);
	
	@Query(value = "SELECT m.id, u.username as sender, c.title as conversation_name, m.type, m.message, m.status, m.created, m.deleted,u.pic_user FROM messages as m INNER JOIN users as u on u.id = m.sender_id INNER JOIN conversations as c ON c.id = m.conversation_id where m.conversation_id = :idP", nativeQuery = true)
	public List<ResponseMessagesDTO> findAllMessageByConversationIdDTO(@Param("idP") int id,Pageable pageable);
	
	@Query(value = "SELECT m.id, u.username as sender, c.title as conversation_name, m.type, m.message, m.status, m.created, m.deleted,u.pic_user FROM messages as m INNER JOIN users as u on u.id = m.sender_id INNER JOIN conversations as c ON c.id = m.conversation_id WHERE m.status not like :statusP AND m.sender_id != :curUserIdP", nativeQuery = true)
	public List<ResponseMessagesDTO> findAllMessageUnseenDTO(@Param("statusP") String status,Pageable pageable,@Param("curUserIdP") int curUserIdP);
	
	@Query(value = "SELECT m.id, u.username as sender, c.title as conversation_name, m.type, m.message, m.status, m.created, m.deleted,u.pic_user FROM messages as m INNER JOIN users as u on u.id = m.sender_id INNER JOIN conversations as c ON c.id = m.conversation_id WHERE m.status not like :statusP and m.conversation_id = :idP AND m.sender_id != :curUserIdP", nativeQuery = true)
	public List<ResponseMessagesDTO> findAllMessageUnseenByConversationIdDTO(@Param("statusP") String status, @Param("idP") int converId,Pageable pageable, @Param("curUserIdP") int curUserIdP);


	@Query(value = "SELECT * FROM messages as m WHERE m.conversation_id = :idConver ORDER BY m.created DESC LIMIT 1", nativeQuery = true)
	public MessagesEntity getLastMessageOfConver(@Param("idConver") int convId);
	
}

