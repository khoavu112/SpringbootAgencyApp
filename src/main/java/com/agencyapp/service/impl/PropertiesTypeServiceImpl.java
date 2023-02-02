package com.agencyapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agencyapp.dao.properties.PropertiesTypeDao;
import com.agencyapp.dto.properties.PropertiesTypesDTO;
import com.agencyapp.model.properties.PropertiesTypesEntity;
import com.agencyapp.service.IPropertiesTypeService;
@Service
public class PropertiesTypeServiceImpl implements IPropertiesTypeService{
	@Autowired
	private PropertiesTypeDao propertiesTypeDao;
	@Override
	public List<PropertiesTypesDTO> getAllPropertyTypes() {
		
		return propertiesTypeDao.findAllTypePropertiesDTO();
	}
	@Override
	public List<PropertiesTypesEntity> getAllPropertyTypesAndSubType() {
		return propertiesTypeDao.findAll();
	}

}
