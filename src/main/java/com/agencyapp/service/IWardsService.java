package com.agencyapp.service;

import java.util.List;

import com.agencyapp.dto.system.WardsDTO;
import com.agencyapp.model.system.WardsEntity;

public interface IWardsService {
	public List<WardsDTO> getAllWards();
	List<WardsDTO> findByDistrictsID(int id);
}
