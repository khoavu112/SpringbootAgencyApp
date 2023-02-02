package com.agencyapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agencyapp.dao.properties.PropertiesSubTypeDao;
import com.agencyapp.dto.properties.PropertiesSubTypeDTO;
import com.agencyapp.model.properties.PropertiesSubtypesEntity;
import com.agencyapp.service.IPropertiesSubTypeService;
@Service
public class PropertiesSubTypeServiceImpl implements IPropertiesSubTypeService{
@Autowired
	private PropertiesSubTypeDao propertiesSubTypeDao;
	@Override
	public List<PropertiesSubTypeDTO> getAllPropertySubTypes() {
		return propertiesSubTypeDao.findAllPropertiesSubTypeDTO();
	}
	@Override
	public List<PropertiesSubTypeDTO> findByPropertyTypeId(int id) {
		return propertiesSubTypeDao.findPropertiesSubTypeByTypeIdDTO(id);
	}
	
}
