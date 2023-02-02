package com.agencyapp.service;

import java.util.List;

import com.agencyapp.dto.properties.PropertiesDTOConv;
import com.agencyapp.dto.properties.ResponsePropertiesDTO;
import com.agencyapp.model.iam.UsersEntity;
import com.agencyapp.model.messaging.MessagesEntity;
import com.agencyapp.model.properties.PropertiesEntity;
import com.agencyapp.model.properties.PropertiesPicturesEntity;
import com.agencyapp.model.system.ProvincesEntity;

public interface IPropertiesService {
	public List<PropertiesDTOConv> getAllProperties(UsersEntity userEntity, Integer page, Integer pagesize);

	public PropertiesDTOConv getPropertiesById(UsersEntity userEntity,int id);

	public PropertiesEntity addProperties(PropertiesEntity propertiesEntity);

	public PropertiesEntity updateProperties(int id, PropertiesEntity propertiesEntity);

	public boolean deleteProperties(int id);
	
	public List<PropertiesDTOConv> getAllPropertiesInLastCustomDaysService(UsersEntity userEntity,Integer page,Integer pagesize,int days);
	
	public List<PropertiesDTOConv> getAllPropertiesOfAgenciesInLastCustomDays(UsersEntity userEntity,int userID,Integer page,Integer pagesize, int days);

	List<PropertiesEntity> findByPrice(Long price);

	List<PropertiesEntity> findByBaths(int baths);

	List<PropertiesEntity> findByBeds(int beds);

	List<PropertiesEntity> findByPropertyTypes(int propertyTypes);

	List<PropertiesEntity> findByParam(String param, int value);
	
	List<PropertiesPicturesEntity> findPictureById(int id);

	List<PropertiesDTOConv> findtestMultiFilter(UsersEntity userEntity,Integer minbaths, Integer maxbaths, Integer minbeds, Integer maxbeds,
			Long minprices, Long maxprices, String address, Integer type_id, Integer floor, Integer minareas,
			Integer maxareas, Integer user_id, Long minPricesRentLong, Long maxPricesRentLong,Long minPricesRentShort, Long maxPricesRentShort,Integer page,Integer pagesize,String sortBy,String sortOrder);
	
}
