package com.agencyapp.dto.properties;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface ResponsePropertiesDTO {
	public Integer getId();
	
	public String getUser_name();
	
	public boolean getFor_sale();
	
	public boolean getFor_rent_long();
	
	public boolean getFor_rent_short();
	
	public Long getPrice_sale();
	
	public Long getPrice_rent_long();
	
	public Long getPrice_rent_short();
	
	public String getType_name();
	
	public String getSubtype_name();
	
	public String getStatus();
	
	public int getFloor();
	
	public String getDescription();
	
	public String getAddress1();
	
	public String getAddress2();
	
	public String getCountry_name();
	
	public String getProvince_name();
	
	public String getDistrict_name();
	
	public String getWard_name();
	
	public Long getCommission();
	
	public int getCommission_pct();
	
	public Long getCommission_list_agent();
	
	public int getCommission_list_agent_pct();
	
	public Long getCommission_sell_agent();
	
	public int getCommission_sell_agent_pct();
	
	public int getBeds();
	
	public int getBaths();
	
	public int getBuilt_space();
	
	public int getGarden_space();
	
	public int getTerrace_space();
	
	public String getCurrency();	
	public String getDimension();
	
	public Date getCreated();
	public Date getUpdated();
	
	
}
