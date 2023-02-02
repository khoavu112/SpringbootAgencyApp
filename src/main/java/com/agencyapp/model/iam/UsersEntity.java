package com.agencyapp.model.iam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.agencyapp.model.messaging.ConversationsEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class UsersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String username;
	@Column
	private String first_name;
	@Column
	private String last_name;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String job_title;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String last_login_date;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String created;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String updated;
	@Column
	@JsonIgnore
	private String pic_user;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "agency_id")
	private AgenciesEntity agency;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "user")
//	private List<ConversationsEntity> conversations = new ArrayList<>();
//	
//	public List<ConversationsEntity> getConversations() {
//		return conversations;
//	}
//	public void setConversations(List<ConversationsEntity> conversations) {
//		this.conversations = conversations;
//	}
	public String getPic_user() {
		return pic_user;
	}
	public void setPic_user(String pic_user) {
		this.pic_user = pic_user;
	}
	public String getLast_login_date() {
		return last_login_date;
	}
	public void setLast_login_date(String last_login_date) {
		this.last_login_date = last_login_date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public AgenciesEntity getAgency() {
		return agency;
	}
	public void setAgency(AgenciesEntity agency) {
		this.agency = agency;
	}
	

}
