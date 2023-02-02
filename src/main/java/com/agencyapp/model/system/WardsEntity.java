package com.agencyapp.model.system;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.agencyapp.model.iam.AgenciesEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "wards")
public class WardsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "ward", fetch = FetchType.LAZY)
	private List<AgenciesEntity> agencies = new ArrayList<>();
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
	
}
