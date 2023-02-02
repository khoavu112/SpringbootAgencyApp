package com.agencyapp.dto.properties;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PropertiesDTOConv {
	private Integer id;
	
	private Integer conversation_id;
	
	private String current_username;
	
	private String user_name;
	
	private boolean forSale;
	
	private boolean forRentLong;
	
	private boolean forRentShort;
	
	private Long priceSale;
	
	private Long priceRentLong;
	
	private Long priceRentShort;
	
	private String type_name;
	
	private String subtype_name;
	
	private String status;
	
	private int floor;
	
	private String description;
	
	private String address1;
	
	private String address2;
	
	private String country_name;
	
	private String province_name;
	
	private String district_name;
	
	private String ward_name;
	
	private Long commission;
	
	private int commission_pct;
	
	private Long commission_list_agent;
	
	private int commission_list_agent_pct;
	
	private Long commission_sell_agent;
	
	private int commission_sell_agent_pct;
	
	private int beds;
	
	private int baths;
	
	private int built_space;
	
	private int garden_space;
	
	private int terrace_space;
	
	private String currency;
	
	private String dimension;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date created;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updated;

	private List<String> pic_properties;
	
	public List<String> getPic_properties() {
		return pic_properties;
	}

	public void setPic_properties(List<String> pic_properties) {
		this.pic_properties = pic_properties;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getCurrent_username() {
		return current_username;
	}

	public void setCurrent_username(String current_username) {
		this.current_username = current_username;
	}

	public Integer getConversation_id() {
		return conversation_id;
	}

	public void setConversation_id(Integer conversation_id) {
		this.conversation_id = conversation_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public boolean isForSale() {
		return forSale;
	}

	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}

	public boolean isForRentLong() {
		return forRentLong;
	}

	public void setForRentLong(boolean forRentLong) {
		this.forRentLong = forRentLong;
	}

	public boolean isForRentShort() {
		return forRentShort;
	}

	public void setForRentShort(boolean forRentShort) {
		this.forRentShort = forRentShort;
	}

	public Long getPriceSale() {
		return priceSale;
	}

	public void setPriceSale(Long priceSale) {
		this.priceSale = priceSale;
	}

	public Long getPriceRentLong() {
		return priceRentLong;
	}

	public void setPriceRentLong(Long priceRentLong) {
		this.priceRentLong = priceRentLong;
	}

	public Long getPriceRentShort() {
		return priceRentShort;
	}

	public void setPriceRentShort(Long priceRentShort) {
		this.priceRentShort = priceRentShort;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getSubtype_name() {
		return subtype_name;
	}

	public void setSubtype_name(String subtype_name) {
		this.subtype_name = subtype_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public String getWard_name() {
		return ward_name;
	}

	public void setWard_name(String ward_name) {
		this.ward_name = ward_name;
	}

	public Long getCommission() {
		return commission;
	}

	public void setCommission(Long commission) {
		this.commission = commission;
	}

	public int getCommission_pct() {
		return commission_pct;
	}

	public void setCommission_pct(int commission_pct) {
		this.commission_pct = commission_pct;
	}

	public Long getCommission_list_agent() {
		return commission_list_agent;
	}

	public void setCommission_list_agent(Long commission_list_agent) {
		this.commission_list_agent = commission_list_agent;
	}

	public int getCommission_list_agent_pct() {
		return commission_list_agent_pct;
	}

	public void setCommission_list_agent_pct(int commission_list_agent_pct) {
		this.commission_list_agent_pct = commission_list_agent_pct;
	}

	public Long getCommission_sell_agent() {
		return commission_sell_agent;
	}

	public void setCommission_sell_agent(Long commission_sell_agent) {
		this.commission_sell_agent = commission_sell_agent;
	}

	public int getCommission_sell_agent_pct() {
		return commission_sell_agent_pct;
	}

	public void setCommission_sell_agent_pct(int commission_sell_agent_pct) {
		this.commission_sell_agent_pct = commission_sell_agent_pct;
	}

	public int getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}

	public int getBaths() {
		return baths;
	}

	public void setBaths(int baths) {
		this.baths = baths;
	}

	public int getBuilt_space() {
		return built_space;
	}

	public void setBuilt_space(int built_space) {
		this.built_space = built_space;
	}

	public int getGarden_space() {
		return garden_space;
	}

	public void setGarden_space(int garden_space) {
		this.garden_space = garden_space;
	}

	public int getTerrace_space() {
		return terrace_space;
	}

	public void setTerrace_space(int terrace_space) {
		this.terrace_space = terrace_space;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
}
