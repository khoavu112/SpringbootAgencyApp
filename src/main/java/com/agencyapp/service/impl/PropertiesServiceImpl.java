package com.agencyapp.service.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.agencyapp.dao.iam.AgenciesDao;
import com.agencyapp.dao.iam.User2Dao;
import com.agencyapp.dao.messaging.ConversationsDao;
import com.agencyapp.dao.properties.PropertiesDao;
import com.agencyapp.dao.properties.PropertiesPicturesDao;
import com.agencyapp.dao.properties.PropertiesSubTypeDao;
import com.agencyapp.dao.properties.PropertiesTypeDao;
import com.agencyapp.dao.system.CountriesDao;
import com.agencyapp.dao.system.DistrictsDao;
import com.agencyapp.dao.system.ProvincesDao;
import com.agencyapp.dao.system.WardsDao;
import com.agencyapp.dto.messaging.ResponseGetConversationID;
import com.agencyapp.dto.properties.PropertiesDTOConv;
import com.agencyapp.dto.properties.ResponsePropertiesDTO;
import com.agencyapp.model.GoogleDriveFileDTO;
import com.agencyapp.model.iam.UsersEntity;
import com.agencyapp.model.messaging.MessagesEntity;
import com.agencyapp.model.properties.PropertiesEntity;
import com.agencyapp.model.properties.PropertiesPicturesEntity;
import com.agencyapp.service.IGoogleDriveFile;
import com.agencyapp.service.IPropertiesService;

@Service
public class PropertiesServiceImpl implements IPropertiesService {
	private final String URL_PREFIX = "https://drive.google.com/uc?id=";
	private SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
	@Autowired
	private PropertiesDao propertiesDao;
	@Autowired
	private AgenciesDao agenciesDao;
	@Autowired
	private User2Dao userDao;
	@Autowired
	private CountriesDao countriesDao;
	@Autowired
	private ProvincesDao provincesDao;
	@Autowired
	private DistrictsDao districtsDao;
	@Autowired
	private WardsDao wardsDao;
	@Autowired
	private PropertiesSubTypeDao propertiesSubTypeDao;
	@Autowired
	private PropertiesTypeDao propertiesTypeDao;
	@Autowired
	private PropertiesPicturesDao propertiesPicturesDao;
	@Autowired
	private IGoogleDriveFile iGoogleDriveFile;
	@Autowired
	private ConversationsDao conversationsDao;
	@Override
	public List<PropertiesDTOConv> getAllProperties(UsersEntity userEntity,Integer page, Integer pagesize) {
		Pageable pageable = PageRequest.of(page, pagesize);
		List<ResponsePropertiesDTO> listFound = propertiesDao.findAllProperties(pageable);
		return responsePropertiesDTOToDTO(listFound, userEntity);
	}

	@Override
	public List<PropertiesDTOConv> getAllPropertiesInLastCustomDaysService(UsersEntity userEntity,Integer page, Integer pagesize, int days) {
		Pageable pageable = PageRequest.of(page, pagesize);
		List<ResponsePropertiesDTO> listFound = propertiesDao.findAllPropertiesInLastCustomDays(pageable, days);
		return responsePropertiesDTOToDTO(listFound, userEntity);
	}

	@Override
	public List<PropertiesDTOConv> getAllPropertiesOfAgenciesInLastCustomDays(UsersEntity userEntity ,int userID, Integer page,
			Integer pagesize, int days) {
		Pageable pageable = PageRequest.of(page, pagesize);
		List<ResponsePropertiesDTO> listFound = propertiesDao.findAllPropertiesOfAgenciesInLastCustomDays(userID,
				pageable, days);
		return responsePropertiesDTOToDTO(listFound, userEntity);
	}

	@Override
	public PropertiesDTOConv getPropertiesById(UsersEntity userEntity,int id) {
		ResponsePropertiesDTO propertiesDTOresponse = propertiesDao.findPropertiesDTOById(id);
		List<ResponsePropertiesDTO> listFound = new ArrayList<>();
		listFound.add(propertiesDTOresponse);
		List<PropertiesDTOConv> convertedDTO = responsePropertiesDTOToDTO(listFound, userEntity);
		return convertedDTO.get(0);		
	}

	@Override
	public PropertiesEntity addProperties(PropertiesEntity propertiesEntity) {
		try {
			propertiesEntity.setCreated(fm.parse(fm.format(new Date())));
		} catch (ParseException e) {
			System.out.println("Parse date error!");
			e.printStackTrace();
		}
		return propertiesDao.save(propertiesEntity);
	}

	@Override
	public PropertiesEntity updateProperties(int id, PropertiesEntity propertiesEntity) {
		if (propertiesEntity != null) {
			PropertiesEntity propertiesFound = propertiesDao.findById(id);
			if (propertiesFound != null) {
				propertiesFound.setUser_id(propertiesEntity.getUser_id());
				propertiesFound.setForSale(propertiesEntity.isForSale());
				propertiesFound.setForRentLong(propertiesEntity.isForRentLong());
				propertiesFound.setForRentShort(propertiesEntity.isForRentShort());
				propertiesFound.setPriceSale(propertiesEntity.getPriceSale());
				propertiesFound.setPriceRentLong(propertiesEntity.getPriceRentLong());
				propertiesFound.setPriceRentShort(propertiesEntity.getPriceRentShort());
				propertiesFound.setType_id(propertiesEntity.getType_id());
				propertiesFound.setSubtype_id(propertiesEntity.getSubtype_id());
				propertiesFound.setStatus(propertiesEntity.getStatus());
				propertiesFound.setFloor(propertiesEntity.getFloor());
				propertiesFound.setDescription(propertiesEntity.getDescription());
				propertiesFound.setAddress1(propertiesEntity.getAddress1());
				propertiesFound.setAddress2(propertiesEntity.getAddress2());
				propertiesFound.setCountry_id(propertiesEntity.getCountry_id());
				propertiesFound.setProvince_id(propertiesEntity.getProvince_id());
				propertiesFound.setDistrict_id(propertiesEntity.getDistrict_id());
				propertiesFound.setWard_id(propertiesEntity.getWard_id());
				propertiesFound.setCommission(propertiesEntity.getCommission());
				propertiesFound.setCommission_pct(propertiesEntity.getCommission_pct());
				propertiesFound.setCommission_list_agent(propertiesEntity.getCommission_list_agent());
				propertiesFound.setCommission_list_agent_pct(propertiesEntity.getCommission_list_agent_pct());
				propertiesFound.setCommission_sell_agent(propertiesEntity.getCommission_sell_agent());
				propertiesFound.setCommission_sell_agent_pct(propertiesEntity.getCommission_sell_agent_pct());
				propertiesFound.setBeds(propertiesEntity.getBeds());
				propertiesFound.setBaths(propertiesEntity.getBaths());
				propertiesFound.setBuilt_space(propertiesEntity.getBuilt_space());
				propertiesFound.setGarden_space(propertiesEntity.getGarden_space());
				propertiesFound.setTerrace_space(propertiesEntity.getTerrace_space());
				propertiesFound.setCurrency(propertiesEntity.getCurrency());
				propertiesFound.setDimension(propertiesEntity.getDimension());
				propertiesFound.setCreated(propertiesEntity.getCreated());
				try {
					propertiesFound.setUpdated(fm.parse(fm.format(new Date())));
				} catch (ParseException e) {
					System.out.println("Parse date error!");
					e.printStackTrace();
				}

				return propertiesDao.save(propertiesFound);
			}
		}
		return null;
	}

	@Override
	public boolean deleteProperties(int id) {
		if (propertiesDao.findById(id) != null) {
			propertiesDao.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<PropertiesEntity> findByPrice(Long price) {
		return propertiesDao.findByPropertyByPrice(price);
	}

	@Override
	public List<PropertiesEntity> findByBaths(int baths) {
		return propertiesDao.findByBaths(baths);
	}

	@Override
	public List<PropertiesEntity> findByBeds(int beds) {
		return propertiesDao.findByBeds(beds);
	}

	@Override
	public List<PropertiesEntity> findByPropertyTypes(int propertyTypes) {
		return propertiesDao.findByTypeId(propertyTypes);
	}

	@Override
	public List<PropertiesEntity> findByParam(String param, int value) {
		return propertiesDao.findByPropertyByParam(param, value);
	}

	@Override
	public List<PropertiesDTOConv> findtestMultiFilter(UsersEntity userEntity, Integer minbaths, Integer maxbaths, Integer minbeds,
			Integer maxbeds, Long minprices, Long maxprices, String address, Integer type_id, Integer floor,
			Integer minareas, Integer maxareas, Integer user_id, Long minPricesRentLong, Long maxPricesRentLong,
			Long minPricesRentShort, Long maxPricesRentShort, Integer page, Integer pagesize, String sortBy,
			String sortOrder) {
		// Paging and sorting
		Pageable pageable = PageRequest.of(page, pagesize);
		if (sortBy != null && sortOrder != null) {
			if (sortOrder.equalsIgnoreCase("DESC")) {
				pageable = PageRequest.of(page, pagesize, Sort.by(sortBy).descending());
			} else {
				pageable = PageRequest.of(page, pagesize, Sort.by(sortBy).ascending());
			}
		} else if (sortBy != null && sortOrder == null) {
			pageable = PageRequest.of(page, pagesize, Sort.by(sortBy).ascending());
		}

		//
		List<PropertiesEntity> foundProperties = propertiesDao.findByMultiFilter(minbaths, maxbaths, minbeds, maxbeds,
				minprices, maxprices, address, type_id, floor, user_id, minPricesRentLong, maxPricesRentLong,
				minPricesRentShort, maxPricesRentShort, pageable);
		if (minareas != null && maxareas != null) {
//			List<PropertiesEntity> propertiesListFilterAreas = null;

			if (foundProperties != null && foundProperties.size() > 0) {
				for (int i = 0; i < foundProperties.size(); i++) {
					float areasProperties = getArea(foundProperties.get(i));
					if (areasProperties < minareas || areasProperties > maxareas) {
						foundProperties.remove(foundProperties.get(i));
						i--;
					}
				}
			}
		}
		List<PropertiesDTOConv> propertiesDTO = entityToDTO(foundProperties, userEntity);
		return propertiesDTO;
	}

	public List<PropertiesDTOConv> entityToDTO(List<PropertiesEntity> propertiesEntityList, UsersEntity userEntity) {
		List<PropertiesDTOConv> propertiesDTO = new ArrayList<>();
		if (propertiesEntityList != null && propertiesEntityList.size() > 0) {
			for (int i = 0; i < propertiesEntityList.size(); i++) {
				PropertiesDTOConv p = new PropertiesDTOConv();
				p.setId(propertiesEntityList.get(i).getId());
				p.setCurrent_username(userEntity.getUsername());
				p.setConversation_id(getConversation_id(userEntity.getId(), propertiesEntityList.get(i).getUser_id()));
				p.setUser_name(userDao.findByid(propertiesEntityList.get(i).getUser_id()).getUsername());
				p.setForSale(propertiesEntityList.get(i).isForSale());
				p.setForRentLong(propertiesEntityList.get(i).isForRentLong());
				p.setForRentShort(propertiesEntityList.get(i).isForRentShort());
				p.setPriceSale(propertiesEntityList.get(i).getPriceSale());
				p.setPriceRentLong(propertiesEntityList.get(i).getPriceRentLong());
				p.setPriceRentShort(propertiesEntityList.get(i).getPriceRentShort());
				p.setType_name(propertiesTypeDao.findNameByid(propertiesEntityList.get(i).getType_id()));
				p.setSubtype_name(propertiesSubTypeDao.findNameByid(propertiesEntityList.get(i).getSubtype_id()));
				p.setStatus(propertiesEntityList.get(i).getStatus());
				p.setFloor(propertiesEntityList.get(i).getFloor());
				p.setDescription(propertiesEntityList.get(i).getDescription());
				p.setAddress1(propertiesEntityList.get(i).getAddress1());
				p.setAddress2(propertiesEntityList.get(i).getAddress2());
				p.setCountry_name(countriesDao.findNameByid(propertiesEntityList.get(i).getCountry_id()));
				p.setProvince_name(provincesDao.findNameByid(propertiesEntityList.get(i).getProvince_id()));
				;
				p.setDistrict_name(districtsDao.findNameByid(propertiesEntityList.get(i).getDistrict_id()));
				p.setWard_name(wardsDao.findNameByid(propertiesEntityList.get(i).getWard_id()));
				p.setCommission(propertiesEntityList.get(i).getCommission());
				p.setCommission_pct(propertiesEntityList.get(i).getCommission_pct());
				p.setCommission_list_agent(propertiesEntityList.get(i).getCommission_list_agent());
				p.setCommission_list_agent_pct(propertiesEntityList.get(i).getCommission_list_agent_pct());
				p.setCommission_sell_agent(propertiesEntityList.get(i).getCommission_sell_agent());
				p.setCommission_sell_agent_pct(propertiesEntityList.get(i).getCommission_sell_agent_pct());
				p.setBeds(propertiesEntityList.get(i).getBeds());
				p.setBaths(propertiesEntityList.get(i).getBaths());
				p.setBuilt_space(propertiesEntityList.get(i).getBuilt_space());
				p.setGarden_space(propertiesEntityList.get(i).getGarden_space());
				p.setTerrace_space(propertiesEntityList.get(i).getTerrace_space());
				p.setCurrency(propertiesEntityList.get(i).getCurrency());
				p.setDimension(propertiesEntityList.get(i).getDimension());
				p.setCreated(propertiesEntityList.get(i).getCreated());
				p.setUpdated(propertiesEntityList.get(i).getUpdated());
				List<String> urlImage = getListURLImage(propertiesEntityList.get(i).getId());
				p.setPic_properties(urlImage);
				propertiesDTO.add(p);
			}
		}
		return propertiesDTO;
	}

	public List<PropertiesDTOConv> responsePropertiesDTOToDTO(List<ResponsePropertiesDTO> propertiesEntityList,UsersEntity userEntity) {
		List<PropertiesDTOConv> propertiesDTO = new ArrayList<>();
		if (propertiesEntityList != null && propertiesEntityList.size() > 0) {
			for (int i = 0; i < propertiesEntityList.size(); i++) {
				PropertiesDTOConv p = new PropertiesDTOConv();
				p.setId(propertiesEntityList.get(i).getId());
				p.setCurrent_username(userEntity.getUsername());
				p.setUser_name(propertiesEntityList.get(i).getUser_name());
				p.setConversation_id(getConversation_id(userEntity.getId(), userDao.findByUsername(propertiesEntityList.get(i).getUser_name()).getId()));
				p.setForSale(propertiesEntityList.get(i).getFor_sale());
				p.setForRentLong(propertiesEntityList.get(i).getFor_rent_long());
				p.setForRentShort(propertiesEntityList.get(i).getFor_rent_short());
				p.setPriceSale(propertiesEntityList.get(i).getPrice_sale());
				p.setPriceRentLong(propertiesEntityList.get(i).getPrice_rent_long());
				p.setPriceRentShort(propertiesEntityList.get(i).getPrice_rent_short());
				p.setType_name(propertiesEntityList.get(i).getType_name());
				p.setSubtype_name(propertiesEntityList.get(i).getSubtype_name());
				p.setStatus(propertiesEntityList.get(i).getStatus());
				p.setFloor(propertiesEntityList.get(i).getFloor());
				p.setDescription(propertiesEntityList.get(i).getDescription());
				p.setAddress1(propertiesEntityList.get(i).getAddress1());
				p.setAddress2(propertiesEntityList.get(i).getAddress2());
				p.setCountry_name(propertiesEntityList.get(i).getCountry_name());
				p.setProvince_name(propertiesEntityList.get(i).getProvince_name());
				p.setDistrict_name(propertiesEntityList.get(i).getDistrict_name());
				p.setWard_name(propertiesEntityList.get(i).getWard_name());
				p.setCommission(propertiesEntityList.get(i).getCommission());
				p.setCommission_pct(propertiesEntityList.get(i).getCommission_pct());
				p.setCommission_list_agent(propertiesEntityList.get(i).getCommission_list_agent());
				p.setCommission_list_agent_pct(propertiesEntityList.get(i).getCommission_list_agent_pct());
				p.setCommission_sell_agent(propertiesEntityList.get(i).getCommission_sell_agent());
				p.setCommission_sell_agent_pct(propertiesEntityList.get(i).getCommission_sell_agent_pct());
				p.setBeds(propertiesEntityList.get(i).getBeds());
				p.setBaths(propertiesEntityList.get(i).getBaths());
				p.setBuilt_space(propertiesEntityList.get(i).getBuilt_space());
				p.setGarden_space(propertiesEntityList.get(i).getGarden_space());
				p.setTerrace_space(propertiesEntityList.get(i).getTerrace_space());
				p.setCurrency(propertiesEntityList.get(i).getCurrency());
				p.setDimension(propertiesEntityList.get(i).getDimension());
				p.setCreated(propertiesEntityList.get(i).getCreated());
				p.setUpdated(propertiesEntityList.get(i).getUpdated());
				List<String> urlImage = getListURLImage(propertiesEntityList.get(i).getId());
				p.setPic_properties(urlImage);
				propertiesDTO.add(p);
			}
		}
		return propertiesDTO;
	}
	
	public Integer getConversation_id(int currentUserId, int propertiesUserID){
		Integer conversationId = null;
		ResponseGetConversationID responseGetConversationID = conversationsDao.findConversationIdBy2ID(currentUserId, propertiesUserID);
		if(responseGetConversationID != null) {
			conversationId = responseGetConversationID.getConversation_id();
		}
		responseGetConversationID = conversationsDao.findConversationIdBy2ID(propertiesUserID, currentUserId);
		if(responseGetConversationID != null) {
			conversationId = responseGetConversationID.getConversation_id();
		}
		return conversationId;
	}

	public float getArea(PropertiesEntity propertiesEntity) {
		float area;
		String dimension = propertiesEntity.getDimension();
		String[] splitDimension = dimension.split("x");
		float firstNumber = Float.parseFloat(splitDimension[0]);
		float secondNumber = Float.parseFloat(splitDimension[1].substring(0, splitDimension[1].length() - 1));
		area = firstNumber * secondNumber;
		return area;
	}

	public List<String> getListURLImage(int id) {
		List<String> urlImage = new ArrayList<>();
		List<PropertiesPicturesEntity> listEntity = propertiesPicturesDao.findByPropertiesId(id);
		if (listEntity.size() > 0 && listEntity != null) {
			for (int i = 0; i < listEntity.size(); i++) {
				String nameImage = listEntity.get(i).getImg_name();
				urlImage.add(URL_PREFIX + getIdImageByNameImage(nameImage));
			}
		}
		return urlImage;
	}

	public String getIdImageByNameImage(String imageName) {
		List<GoogleDriveFileDTO> listFound = new ArrayList<>();
		try {
			listFound = iGoogleDriveFile.getAllFile();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (GeneralSecurityException e) {

			e.printStackTrace();
		}

		for (int i = 0; i < listFound.size(); i++) {
			if (listFound.get(i).getName().equalsIgnoreCase(imageName)) {
				return listFound.get(i).getId().toString();
			}
		}

		return "";
	}

	@Override
	public List<PropertiesPicturesEntity> findPictureById(int id) {
		return propertiesPicturesDao.findByPropertiesId(id);
	}

}
