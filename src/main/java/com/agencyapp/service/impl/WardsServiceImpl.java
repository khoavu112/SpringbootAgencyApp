package com.agencyapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agencyapp.dao.system.WardsDao;
import com.agencyapp.dto.system.WardsDTO;
import com.agencyapp.model.system.WardsEntity;
import com.agencyapp.service.IWardsService;
@Service
public class WardsServiceImpl implements IWardsService{
	@Autowired
	private WardsDao wardsDao;
	@Override
	public List<WardsDTO> getAllWards() {
		return wardsDao.findAllWardsDTO();
	}

	@Override
	public List<WardsDTO> findByDistrictsID(int id) {
		return wardsDao.findWardsByDistrictIdDTO(id);
	}
}
