package com.agencyapp.dto.messaging;

public interface ResponseMessagesDTO {
	public Integer getId();

	public String getSender();

	public String getConversation_name();

	public String getType();

	public String getMessage();

	public String getStatus();

	public String getCreated();
	
	public String getDeleted();

	public String getPic_user();
}
