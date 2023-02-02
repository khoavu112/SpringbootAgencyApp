package com.agencyapp.dto.iam;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface AgenciesDTO {
	public Integer getId();
	public String getName();
	public String getPhone();
	public String getEmail();
	public String getEmail2();
	public String getWebsite();
	public String getAddress1();
	public String getAddress2();
	public String getCountry_name();
	public String getProvince_name();
	public String getDistrict_name();
	public String getWard_name();
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getCreated();
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getUpdated();
}
