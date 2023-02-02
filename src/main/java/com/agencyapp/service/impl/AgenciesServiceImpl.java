package com.agencyapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agencyapp.dao.iam.AgenciesDao;
import com.agencyapp.dto.iam.AgenciesDTOPO;
import com.agencyapp.model.iam.AgenciesEntity;
import com.agencyapp.service.IAgenciesService;

@Service
public class AgenciesServiceImpl implements IAgenciesService {
	private static final Logger logger = LoggerFactory.getLogger(AgenciesServiceImpl.class);
	@Autowired
	private AgenciesDao agencyDao;
	@Autowired
	EntityManager em;

	@Override
	public List<AgenciesDTOPO> getAllAgency(int page, int pagesize) {
		Pageable pageable = PageRequest.of(page, pagesize);
		List<AgenciesEntity> listEntityAgencies = agencyDao.findAll(pageable).getContent();
		return convertToDTOList(listEntityAgencies);
	}

	@Override
	public AgenciesDTOPO getAgencyById(int id) {
		AgenciesEntity agencyEntity = agencyDao.findByid(id);
		return convertToDTO(agencyEntity);
	}

	public List<AgenciesDTOPO> convertToDTOList(List<AgenciesEntity> listEntity) {
		List<AgenciesDTOPO> listDTO = new ArrayList<>();
		if (listEntity != null && listEntity.size() > 0) {
			for (int i = 0; i < listEntity.size(); i++) {
				AgenciesDTOPO agenciesDTO = new AgenciesDTOPO();
				agenciesDTO.setId(listEntity.get(i).getId());
				agenciesDTO.setName(listEntity.get(i).getName());
				agenciesDTO.setPhone(listEntity.get(i).getPhone());
				agenciesDTO.setEmail(listEntity.get(i).getEmail());
				agenciesDTO.setEmail2(listEntity.get(i).getEmail2());
				agenciesDTO.setWebsite(listEntity.get(i).getWebsite());
				agenciesDTO.setAddress1(listEntity.get(i).getAddress1());
				agenciesDTO.setAddress2(listEntity.get(i).getAddress2());
				agenciesDTO.setCountryName(listEntity.get(i).getCountry().getName());
				agenciesDTO.setProvinceName(listEntity.get(i).getProvince().getName());
				agenciesDTO.setDistrictName(listEntity.get(i).getDistrict().getName());
				agenciesDTO.setWardName(listEntity.get(i).getWard().getName());
				agenciesDTO.setCreated(listEntity.get(i).getCreated());
				agenciesDTO.setUpdated(listEntity.get(i).getUpdated());
				listDTO.add(agenciesDTO);
			}
		}
		return listDTO;
	}

	public AgenciesDTOPO convertToDTO(AgenciesEntity entity) {
		AgenciesDTOPO agenciesDTO = new AgenciesDTOPO();
		if (entity != null) {
			agenciesDTO.setId(entity.getId());
			agenciesDTO.setName(entity.getName());
			agenciesDTO.setPhone(entity.getPhone());
			agenciesDTO.setEmail(entity.getEmail());
			agenciesDTO.setEmail2(entity.getEmail2());
			agenciesDTO.setWebsite(entity.getWebsite());
			agenciesDTO.setAddress1(entity.getAddress1());
			agenciesDTO.setAddress2(entity.getAddress2());
			agenciesDTO.setCountryName(entity.getCountry().getName());
			agenciesDTO.setProvinceName(entity.getProvince().getName());
			agenciesDTO.setDistrictName(entity.getDistrict().getName());
			agenciesDTO.setWardName(entity.getWard().getName());
			agenciesDTO.setCreated(entity.getCreated());
			agenciesDTO.setUpdated(entity.getUpdated());
		}
		return agenciesDTO;
	}
}
