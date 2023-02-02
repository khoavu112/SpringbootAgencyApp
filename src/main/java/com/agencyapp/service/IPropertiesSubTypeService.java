package com.agencyapp.service;

import java.util.List;

import com.agencyapp.dto.properties.PropertiesSubTypeDTO;
import com.agencyapp.model.properties.*;

public interface IPropertiesSubTypeService {
	public List<PropertiesSubTypeDTO> getAllPropertySubTypes();
	public List<PropertiesSubTypeDTO> findByPropertyTypeId(int id);
}
