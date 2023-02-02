package com.agencyapp.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.agencyapp.dto.ResponseObjectDTO;
import com.agencyapp.dto.system.ProvincesDTO;
import com.agencyapp.dto.system.WardsDTO;
import com.agencyapp.model.system.ProvincesEntity;
import com.agencyapp.service.IProvincesService;

@RestController
public class ProvincesController {
	@Autowired
	private IProvincesService iProvincesService;
	// Get all provinces
	@GetMapping(value = "/provinces")
	public ResponseEntity<ResponseObjectDTO> getAllProvinces() {
		List<ProvincesDTO> provincesList = iProvincesService.getAllProvinces();
		if (provincesList != null && provincesList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", provincesList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find countries", ""));
		}
	}
	// Get all provinces by country id
	@GetMapping(value = "/provinces/{id}")
	public ResponseEntity<ResponseObjectDTO> getProvincesByCountryID(@PathVariable("id") int id) {
		List<ProvincesDTO> provincesList = iProvincesService.findByCountryID(id);
		if (provincesList != null && provincesList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", provincesList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find province with id_country = "+id, ""));
		}
	}
}
