package com.agencyapp.model.properties;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "property_types")
public class PropertiesTypesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@OneToMany(targetEntity = PropertiesSubtypesEntity.class,cascade = CascadeType.ALL)
	@JoinColumn(name ="property_type_id",referencedColumnName = "id")
	private List<PropertiesSubtypesEntity> propertySubtypesEntityList;
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
	public List<PropertiesSubtypesEntity> getPropertySubtypesEntityList() {
		return propertySubtypesEntityList;
	}
	public void setPropertySubtypesEntityList(List<PropertiesSubtypesEntity> propertySubtypesEntityList) {
		this.propertySubtypesEntityList = propertySubtypesEntityList;
	}
	
}
