package com.agencyapp.model.system;

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

import com.agencyapp.model.properties.PropertiesSubtypesEntity;
import com.agencyapp.model.properties.PropertiesTypesEntity;


@Entity
@Table(name = "languages")
public class LanguageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String category;
	@Column
	private String text_en;
	@Column
	private String text_vi;
	@OneToMany(targetEntity = PropertiesTypesEntity.class,cascade = CascadeType.ALL)
	@JoinColumn(name ="language_id",referencedColumnName = "id")
	private List<PropertiesTypesEntity> propertyTypesList;
	
	@OneToMany(targetEntity = PropertiesSubtypesEntity.class,cascade = CascadeType.ALL)
	@JoinColumn(name ="language_id",referencedColumnName = "id")
	private List<PropertiesSubtypesEntity> propertySubtypesEntityList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getText_en() {
		return text_en;
	}

	public void setText_en(String text_en) {
		this.text_en = text_en;
	}

	public String getText_vi() {
		return text_vi;
	}

	public void setText_vi(String text_vi) {
		this.text_vi = text_vi;
	}

	public List<PropertiesTypesEntity> getPropertyTypesList() {
		return propertyTypesList;
	}

	public void setPropertyTypesList(List<PropertiesTypesEntity> propertyTypesList) {
		this.propertyTypesList = propertyTypesList;
	}

	public List<PropertiesSubtypesEntity> getPropertySubtypesEntityList() {
		return propertySubtypesEntityList;
	}

	public void setPropertySubtypesEntityList(List<PropertiesSubtypesEntity> propertySubtypesEntityList) {
		this.propertySubtypesEntityList = propertySubtypesEntityList;
	}
	
}
