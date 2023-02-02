package com.agencyapp.service;

import java.util.List;

import com.agencyapp.dto.system.ProvincesDTO;
import com.agencyapp.dto.system.WardsDTO;
import com.agencyapp.model.system.ProvincesEntity;

public interface IProvincesService {
	public List<ProvincesDTO> getAllProvinces();
	List<ProvincesDTO> findByCountryID(int id);
}
