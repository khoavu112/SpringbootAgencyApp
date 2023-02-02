package com.agencyapp.service;

import java.util.List;

import com.agencyapp.dto.system.DistrictsDTO;
import com.agencyapp.model.system.DistrictsEntity;

public interface IDistrictsService {
	public List<DistrictsDTO> getAllDistricts();
	List<DistrictsDTO> findByProvincesID(int id);
}
