package com.agencyapp.model.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "property_subtypes")
public class PropertiesSubtypesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private Integer use_built_space;
	@Column
	private Integer use_terrace_space;
	@Column
	private Integer use_plot_space;
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
	public Integer getUse_built_space() {
		return use_built_space;
	}
	public void setUse_built_space(Integer use_built_space) {
		this.use_built_space = use_built_space;
	}
	public Integer getUse_terrace_space() {
		return use_terrace_space;
	}
	public void setUse_terrace_space(Integer use_terrace_space) {
		this.use_terrace_space = use_terrace_space;
	}
	public Integer getUse_plot_space() {
		return use_plot_space;
	}
	public void setUse_plot_space(Integer use_plot_space) {
		this.use_plot_space = use_plot_space;
	}
	
}
