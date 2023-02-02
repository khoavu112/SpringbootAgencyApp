package com.agencyapp.dto;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

public interface ReponseUserAgencyDTO {

	Integer getId();

	@Value("#{target.agency_name}")
	String getAgency_name();

	String getUsername();

	String getFirst_name();

	String getLast_name();

	String getEmail();

	String getJob_title();
	
	String getLast_login_date();

	Date getCreated();

	Date getUpdated();
	String getPic_usere();

}
