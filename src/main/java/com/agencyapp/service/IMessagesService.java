package com.agencyapp.service;

import java.util.List;

import com.agencyapp.dto.messaging.MessagesDTO;
import com.agencyapp.dto.messaging.ResponseMessagesDTO;
import com.agencyapp.model.messaging.ConversationsEntity;
import com.agencyapp.model.messaging.MessagesEntity;
import com.agencyapp.model.messaging.ParticipantsEntity;

public interface IMessagesService {
	public List<MessagesDTO> getAllMessages(Integer page, Integer pagesize);

	public List<MessagesDTO> getAllMessageUnseenDTO(Integer page, Integer pagesize, int userId);

	public List<MessagesDTO> getAllMessageUnseenByConversationIdDTO(int conversationId, Integer page, Integer pagesize,
			int userId);

	public List<MessagesDTO> findByConversationsID(int id, Integer page, Integer pagesize);

	public MessagesEntity addMessages(MessagesEntity messagesEntity, int senderID, int conversationID);

	public MessagesEntity updateMessages(int id, MessagesEntity messagesEntity, int senderID, int conversationID);

	public boolean deleteMessages(int id);

	public ConversationsEntity addConversation(ConversationsEntity conversationsEntity);

	public boolean deleteConversation(int id);

	public ParticipantsEntity addParticipants(ParticipantsEntity participantsEntity);
	
}
