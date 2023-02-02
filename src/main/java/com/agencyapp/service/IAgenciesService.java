package com.agencyapp.service;

import java.util.List;

import com.agencyapp.dto.iam.AgenciesDTOPO;

public interface IAgenciesService {
	public List<AgenciesDTOPO> getAllAgency(int page, int pagesize);
	public AgenciesDTOPO getAgencyById(int id);
}
