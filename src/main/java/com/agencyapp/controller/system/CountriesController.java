package com.agencyapp.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agencyapp.dto.ResponseObjectDTO;
import com.agencyapp.model.system.CountriesEntity;
import com.agencyapp.service.ICountriesService;

@RestController
public class CountriesController {
	@Autowired
	private ICountriesService iCountriesService;
	// Get all countries
	@GetMapping(value = "/countries")
	public ResponseEntity<ResponseObjectDTO> getAllAgency() {
		List<CountriesEntity> countriesList = iCountriesService.getAllCountries();
		if(countriesList != null && countriesList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObjectDTO("ok","Querry successfully",countriesList));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObjectDTO("failed","Cannot find countries",""));
		}
		
	}
}
