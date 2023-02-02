package com.agencyapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agencyapp.dao.system.ProvincesDao;
import com.agencyapp.dto.system.ProvincesDTO;
import com.agencyapp.dto.system.WardsDTO;
import com.agencyapp.model.system.ProvincesEntity;
import com.agencyapp.service.IProvincesService;
@Service
public class ProvincesServiceImpl implements IProvincesService{
	@Autowired
	private ProvincesDao provincesDao;
	@Override
	public List<ProvincesDTO> getAllProvinces() {
		return provincesDao.findAllProvincesDTO();
	}
	@Override
	public List<ProvincesDTO> findByCountryID(int id) {		
		return provincesDao.findProvincesByCountryIdDTO(id);
	}

}
