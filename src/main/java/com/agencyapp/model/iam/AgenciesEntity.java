package com.agencyapp.model.iam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.agencyapp.model.properties.PropertiesTypesEntity;
import com.agencyapp.model.system.CountriesEntity;
import com.agencyapp.model.system.DistrictsEntity;
import com.agencyapp.model.system.ProvincesEntity;
import com.agencyapp.model.system.WardsEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "agencies")
public class AgenciesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private String phone;
	@Column
	private String email;
	@Column
	private String email2;
	@Column
	private String website;
	@Column
	private String address1;
	@Column
	private String address2;	
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String created;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String updated;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private CountriesEntity country;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "district_id")
	private DistrictsEntity district;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "province_id")
	private ProvincesEntity province;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ward_id")
	private WardsEntity ward;
	
	@JsonIgnore
	@OneToMany(mappedBy = "agency")
	private List<UsersEntity> users = new ArrayList<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
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
	public List<UsersEntity> getUsers() {
		return users;
	}
	public void setUsers(List<UsersEntity> users) {
		this.users = users;
	}
	public CountriesEntity getCountry() {
		return country;
	}
	public void setCountry(CountriesEntity country) {
		this.country = country;
	}
	public DistrictsEntity getDistrict() {
		return district;
	}
	public void setDistrict(DistrictsEntity district) {
		this.district = district;
	}
	public ProvincesEntity getProvince() {
		return province;
	}
	public void setProvince(ProvincesEntity province) {
		this.province = province;
	}
	public WardsEntity getWard() {
		return ward;
	}
	public void setWard(WardsEntity ward) {
		this.ward = ward;
	}
	
	
}
