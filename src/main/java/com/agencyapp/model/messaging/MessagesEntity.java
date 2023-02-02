package com.agencyapp.model.messaging;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.agencyapp.model.iam.AgenciesEntity;
import com.agencyapp.model.iam.UsersEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "messages")
public class MessagesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_id")
	private UsersEntity user;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conversation_id")
	private ConversationsEntity conversation;
	@Column
	private String type;
	@Column
	private String message;
	@Column
	private String status;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String created;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String deleted;
	
	
	
	public UsersEntity getUser() {
		return user;
	}
	public void setUser(UsersEntity user) {
		this.user = user;
	}
	public ConversationsEntity getConversation() {
		return conversation;
	}
	public void setConversation(ConversationsEntity conversation) {
		this.conversation = conversation;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
}
