package com.agencyapp.controller.properties;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agencyapp.config.JwtTokenUtil;
import com.agencyapp.dao.properties.PropertiesDao;
import com.agencyapp.dao.properties.PropertiesPicturesDao;
import com.agencyapp.dto.ResponseObjectDTO;
import com.agencyapp.dto.properties.PropertiesDTOConv;
import com.agencyapp.dto.properties.PropertiesSubTypeDTO;
import com.agencyapp.dto.properties.PropertiesTypesDTO;
import com.agencyapp.dto.properties.ResponsePropertiesDTO;
import com.agencyapp.model.UserDTO;
import com.agencyapp.model.iam.UsersEntity;
import com.agencyapp.model.properties.PropertiesEntity;
import com.agencyapp.model.properties.PropertiesPicturesEntity;
import com.agencyapp.model.properties.PropertiesSubtypesEntity;
import com.agencyapp.model.properties.PropertiesTypesEntity;
import com.agencyapp.service.IPropertiesService;
import com.agencyapp.service.IPropertiesSubTypeService;
import com.agencyapp.service.IPropertiesTypeService;
import com.agencyapp.service.IUsersService;

import antlr.StringUtils;

@RestController
public class PropertiesController {
	@Autowired
	private IPropertiesTypeService iPropertyTypeService;
	@Autowired
	private IPropertiesSubTypeService iPropertySubTypeService;
	@Autowired
	private IPropertiesService iPropertiesService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private IUsersService iUserService;

	private Long MAX_VALUE_SEARCH = Long.MAX_VALUE;

	// Get all properties type
	@GetMapping(value = "/types")
	public ResponseEntity<ResponseObjectDTO> getAllPropertyTypesController() {
		List<PropertiesTypesDTO> propertyTypeList = iPropertyTypeService.getAllPropertyTypes();
		if (propertyTypeList != null && propertyTypeList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", propertyTypeList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find property type", ""));
		}

	}

	// Get all properties type && subtype
	@GetMapping(value = "/typesAndSubtype")
	public ResponseEntity<ResponseObjectDTO> getAllPropertyTypesAndSubType() {
		List<PropertiesTypesEntity> propertyTypeList = iPropertyTypeService.getAllPropertyTypesAndSubType();
		if (propertyTypeList != null && propertyTypeList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", propertyTypeList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find property type", ""));
		}

	}

	// Get all properties subtype
	@GetMapping(value = "/subtypes")
	public ResponseEntity<ResponseObjectDTO> getAllPropertySubTypesController() {
		List<PropertiesSubTypeDTO> propertySubTypeList = iPropertySubTypeService.getAllPropertySubTypes();
		if (propertySubTypeList != null && propertySubTypeList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", propertySubTypeList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find property type", ""));
		}
	}

	// Get all system subtypes from its parent property type
	@GetMapping(value = "/subtypesByPropertiesTypeID/{id}")
	public ResponseEntity<ResponseObjectDTO> getAllPropertySubTypesControllerByPropertiesTypeId(
			@PathVariable("id") int id) {
		List<PropertiesSubTypeDTO> propertySubTypeByPropertiesIdList = iPropertySubTypeService.findByPropertyTypeId(id);
		if (propertySubTypeByPropertiesIdList != null && propertySubTypeByPropertiesIdList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", propertySubTypeByPropertiesIdList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find property type", ""));
		}
	}

	// Get property by id
	@GetMapping(value = "/properties/{id}")
	public ResponseEntity<ResponseObjectDTO> getPropertiesById(HttpServletRequest request, @PathVariable("id") int id) {
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		UsersEntity userEntity = iUserService.getUserEntityByUsername(username);
		PropertiesDTOConv propertyfound = iPropertiesService.getPropertiesById(userEntity, id);
		if (propertyfound != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", propertyfound));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find property with id= " + id, ""));
		}
	}

	// Get all property
	@GetMapping(value = "/properties")
	public ResponseEntity<ResponseObjectDTO> getAllProperties(HttpServletRequest request,
			@RequestParam("page") Optional<Integer> page, @RequestParam("pagesize") Optional<Integer> pagesize) {
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		UsersEntity userEntity = iUserService.getUserEntityByUsername(username);
		List<PropertiesDTOConv> propertyfound = iPropertiesService.getAllProperties(userEntity, page.orElse(0),
				pagesize.orElse(5));
		if (propertyfound != null && propertyfound.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", propertyfound));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find property ", ""));
		}
	}

	@PostMapping(value = "/properties/add")
	public ResponseEntity<ResponseObjectDTO> addProperties(@RequestBody PropertiesEntity propertiesEntity) {
		if (propertiesEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObjectDTO("ok", "Insert successfully",
					iPropertiesService.addProperties(propertiesEntity)));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObjectDTO("failed", "Cannot insert property", ""));
		}
	}

	@PutMapping(value = "/properties/update/{id}")
	public ResponseEntity<ResponseObjectDTO> updateProperties(@PathVariable("id") int id,
			@RequestBody PropertiesEntity propertiesEntity) {
		PropertiesEntity propertiesUpdated = iPropertiesService.updateProperties(id, propertiesEntity);
		if (propertiesUpdated != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Update successfully", propertiesUpdated));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObjectDTO("failed", "Cannot update user id = " + id, ""));
		}
	}

	@DeleteMapping(value = "/properties/delete/{id}")
	public ResponseEntity<ResponseObjectDTO> deleteProperties(@PathVariable("id") int id) {
		if (iPropertiesService.deleteProperties(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObjectDTO("ok", "Delete successfully", ""));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObjectDTO("failed", "Cannot delete properties id = " + id, ""));
		}
	}

	// Test dynamic search
	@GetMapping(value = "/properties/search")
	public ResponseEntity<ResponseObjectDTO> searchDynamic(HttpServletRequest request,
			@RequestParam Map<String, String> customQuery, @RequestParam("page") Optional<Integer> page,
			@RequestParam("pagesize") Optional<Integer> pagesize) {
		Integer minbaths = null;
		Integer maxbaths = null;
		Integer minbeds = null;
		Integer maxbeds = null;
		Long minprices = null;
		Long maxprices = null;
		String address = null;
		Integer type_id = null;
		Integer floor = null;
		Integer minareas = null;
		Integer maxareas = null;
		Integer user_id = null;
		Long minPricesRentLong = null;
		Long maxPricesRentLong = null;
		Long minPricesRentShort = null;
		Long maxPricesRentShort = null;
		String sortBy = null;
		String sortOrder = null;
		String addressInfo = "";
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		UsersEntity userEntity = iUserService.getUserEntityByUsername(username);
		// Baths
		if (customQuery.containsKey("minbaths") && customQuery.containsKey("maxbaths")) {
			minbaths = Integer.valueOf(customQuery.get("minbaths"));
			maxbaths = Integer.valueOf(customQuery.get("maxbaths"));
		} else if (customQuery.containsKey("minbaths")) {
			minbaths = Integer.valueOf(customQuery.get("minbaths"));
			maxbaths = Integer.MAX_VALUE;
		} else if (customQuery.containsKey("maxbaths")) {
			minbaths = 0;
			maxbaths = Integer.valueOf(customQuery.get("maxbaths"));
		}
		// Beds
		if (customQuery.containsKey("minbeds") && customQuery.containsKey("maxbeds")) {
			minbeds = Integer.valueOf(customQuery.get("minbeds"));
			maxbeds = Integer.valueOf(customQuery.get("maxbeds"));
		} else if (customQuery.containsKey("minbeds")) {
			minbeds = Integer.valueOf(customQuery.get("minbeds"));
			maxbeds = Integer.MAX_VALUE;
		} else if (customQuery.containsKey("maxbeds")) {
			minbeds = 0;
			maxbeds = Integer.valueOf(customQuery.get("maxbeds"));
		}
		// Prices
		if (customQuery.containsKey("minprices") && customQuery.containsKey("maxprices")) {
			minprices = Long.valueOf(customQuery.get("minprices"));
			maxprices = Long.valueOf(customQuery.get("maxprices"));
		} else if (customQuery.containsKey("minprices")) {
			minprices = Long.valueOf(customQuery.get("minprices"));
			maxprices = MAX_VALUE_SEARCH;
		} else if (customQuery.containsKey("maxprices")) {
			minprices = 0l;
			maxprices = Long.valueOf(customQuery.get("maxprices"));
		}

		// Address
		if (customQuery.containsKey("address")) {
			addressInfo += ", " + customQuery.get("address");

		}
		// countries + districts...
		if (customQuery.containsKey("wards_name")) {
			addressInfo += ", " + customQuery.get("wards_name");
		}

		if (customQuery.containsKey("districts_name")) {
			addressInfo += ", " + customQuery.get("districts_name");
		}
		if (customQuery.containsKey("provinces_name")) {
			addressInfo += ", " + customQuery.get("provinces_name");
		}
		if (customQuery.containsKey("countries_name")) {
			addressInfo += ", " + customQuery.get("countries_name");
		}

		if (!addressInfo.equalsIgnoreCase("")) {
			addressInfo = addressInfo.substring(2);
			address = "%" + addressInfo.trim() + "%";
		}
		// Type
		if (customQuery.containsKey("type_id")) {
			type_id = Integer.valueOf(customQuery.get("type_id"));
		}
		// Floor
		if (customQuery.containsKey("floor")) {
			floor = Integer.valueOf(customQuery.get("floor"));
		}
		// Area
		if (customQuery.containsKey("minareas") && customQuery.containsKey("maxareas")) {
			minareas = Integer.valueOf(customQuery.get("minareas"));
			maxareas = Integer.valueOf(customQuery.get("maxareas"));
		} else if (customQuery.containsKey("minareas")) {
			minareas = Integer.valueOf(customQuery.get("minareas"));
			maxareas = Integer.MAX_VALUE;
		} else if (customQuery.containsKey("maxareas")) {
			minareas = 0;
			maxareas = Integer.valueOf(customQuery.get("maxareas"));
		}
		// Agency
		if (customQuery.containsKey("user_id")) {
			user_id = Integer.valueOf(customQuery.get("user_id"));
		}
		// Rent long
		if (customQuery.containsKey("minpricesrentlong") && customQuery.containsKey("maxpricesrentlong")) {
			minPricesRentLong = Long.valueOf(customQuery.get("minpricesrentlong"));
			maxPricesRentLong = Long.valueOf(customQuery.get("maxpricesrentlong"));
		} else if (customQuery.containsKey("minpricesrentlong")) {
			minPricesRentLong = Long.valueOf(customQuery.get("minpricesrentlong"));
			maxPricesRentLong = MAX_VALUE_SEARCH;
		} else if (customQuery.containsKey("maxpricesrentlong")) {
			minPricesRentLong = 0l;
			maxPricesRentLong = Long.valueOf(customQuery.get("maxpricesrentlong"));
		}
		// Rent short
		if (customQuery.containsKey("minpricesrentshort") && customQuery.containsKey("maxpricesrentshort")) {
			minPricesRentShort = Long.valueOf(customQuery.get("minpricesrentshort"));
			maxPricesRentShort = Long.valueOf(customQuery.get("maxpricesrentshort"));
		} else if (customQuery.containsKey("minpricesrentshort")) {
			minPricesRentShort = Long.valueOf(customQuery.get("minpricesrentshort"));
			maxPricesRentShort = MAX_VALUE_SEARCH;
		} else if (customQuery.containsKey("maxpricesrentshort")) {
			minPricesRentShort = 0l;
			maxPricesRentShort = Long.valueOf(customQuery.get("maxpricesrentshort"));
		}
		// sorting
		if (customQuery.containsKey("sortby") && customQuery.containsKey("sortorder")) {
			sortBy = customQuery.get("sortby");
			sortOrder = customQuery.get("sortorder");
		} else if (customQuery.containsKey("sortby")) {
			sortBy = customQuery.get("sortby");
		} else if (customQuery.containsKey("sortorder")) {
			sortOrder = customQuery.get("sortorder");
		}

		List<PropertiesDTOConv> propertyfound = iPropertiesService.findtestMultiFilter(userEntity, minbaths, maxbaths,
				minbeds, maxbeds, minprices, maxprices, address, type_id, floor, minareas, maxareas, user_id,
				minPricesRentLong, maxPricesRentLong, minPricesRentShort, maxPricesRentShort, page.orElse(0),
				pagesize.orElse(10), sortBy, sortOrder);

		if (propertyfound != null && propertyfound.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", propertyfound));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find property", ""));
		}
	}

	@GetMapping(value = "/propertiesLastCustomDays")
	public ResponseEntity<ResponseObjectDTO> getAllPropertiesInLastCustomDays(HttpServletRequest request,
			@RequestParam("page") Optional<Integer> page, @RequestParam("pagesize") Optional<Integer> pagesize,
			@RequestParam("days") Optional<Integer> days) {
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		UsersEntity userEntity = iUserService.getUserEntityByUsername(username);
		List<PropertiesDTOConv> propertyfound = iPropertiesService.getAllPropertiesInLastCustomDaysService(userEntity,
				page.orElse(0), pagesize.orElse(3), days.orElse(2));
		if (propertyfound != null && propertyfound.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", propertyfound));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find property ", ""));
		}
	}

	@GetMapping(value = "/propertiesOfAgenciesLastCustomDays")
	public ResponseEntity<ResponseObjectDTO> getAllPropertiesOfAgenciesInLastCustomDays(HttpServletRequest request,
			@RequestParam("page") Optional<Integer> page, @RequestParam("pagesize") Optional<Integer> pagesize,
			@RequestParam("days") Optional<Integer> days) {
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		UsersEntity userEntity = iUserService.getUserEntityByUsername(username);
		List<PropertiesDTOConv> propertyfound = iPropertiesService.getAllPropertiesOfAgenciesInLastCustomDays(
				userEntity, userEntity.getId(), page.orElse(0), pagesize.orElse(3), days.orElse(7));
		if (propertyfound != null && propertyfound.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", propertyfound));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find property ", ""));
		}
	}
//	@GetMapping(value = "/propertiesPicture/{id}")
//	public ResponseEntity<ResponseObjectDTO> getPropertiesPictureById(@PathVariable("id") int id) {
//		List<PropertiesPicturesEntity> listFound = iPropertiesService.findPictureById(id);
//		if (listFound != null) {
//			return ResponseEntity.status(HttpStatus.OK)
//					.body(new ResponseObjectDTO("ok", "Querry successfully", listFound));
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body(new ResponseObjectDTO("failed", "Cannot find property picture with id= " + id, ""));
//		}
//	}
}
