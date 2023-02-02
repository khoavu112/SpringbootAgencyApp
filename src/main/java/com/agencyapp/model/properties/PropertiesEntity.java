package com.agencyapp.model.properties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "properties")
@Table(name = "properties")
public class PropertiesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Integer user_id;
	@Column
	private boolean forSale;
	@Column
	private boolean forRentLong;
	@Column
	private boolean forRentShort;
	@Column
	private Long priceSale;
	@Column
	private Long priceRentLong;
	@Column
	private Long priceRentShort;
	@Column
	private Integer type_id;
	@Column
	private Integer subtype_id;
	@Column
	private String status;
	@Column
	private int floor;
	@Column
	private String description;
	@Column
	private String address1;
	@Column
	private String address2;
	@Column
	private Integer country_id;
	@Column
	private Integer province_id;
	@Column
	private Integer district_id;
	@Column
	private Integer ward_id;
	@Column
	private Long commission;
	@Column
	private int commission_pct;
	@Column
	private Long commission_list_agent;
	@Column
	private int commission_list_agent_pct;
	@Column
	private Long commission_sell_agent;
	@Column
	private int commission_sell_agent_pct;
	@Column
	private int beds;
	@Column
	private int baths;
	@Column
	private int built_space;
	@Column
	private int garden_space;
	@Column
	private int terrace_space;
	@Column
	private String currency;
	@Column
	private String dimension;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date created;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updated;
	
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer getSubtype_id() {
		return subtype_id;
	}
	public void setSubtype_id(Integer subtype_id) {
		this.subtype_id = subtype_id;
	}
	public Integer getCountry_id() {
		return country_id;
	}
	public void setCountry_id(Integer country_id) {
		this.country_id = country_id;
	}
	public Integer getProvince_id() {
		return province_id;
	}
	public void setProvince_id(Integer province_id) {
		this.province_id = province_id;
	}
	public Integer getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}
	public Integer getWard_id() {
		return ward_id;
	}
	public void setWard_id(Integer ward_id) {
		this.ward_id = ward_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
