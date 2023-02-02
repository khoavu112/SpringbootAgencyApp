package com.agencyapp.service;

import java.util.List;

import com.agencyapp.dto.properties.PropertiesTypesDTO;
import com.agencyapp.model.properties.PropertiesTypesEntity;

public interface IPropertiesTypeService {
	public List<PropertiesTypesDTO> getAllPropertyTypes();
	public List<PropertiesTypesEntity> getAllPropertyTypesAndSubType();
}
