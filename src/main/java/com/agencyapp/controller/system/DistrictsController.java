package com.agencyapp.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.agencyapp.dto.ResponseObjectDTO;
import com.agencyapp.dto.system.DistrictsDTO;
import com.agencyapp.model.system.DistrictsEntity;
import com.agencyapp.service.IDistrictsService;

@RestController
public class DistrictsController {
	@Autowired
	private IDistrictsService iDistrictsService;
	// Get all districts
	@GetMapping(value = "/districts")
	public ResponseEntity<ResponseObjectDTO> getAllDistricts() {
		List<DistrictsDTO> districtsList = iDistrictsService.getAllDistricts();
		if (districtsList != null && districtsList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", districtsList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find districts", ""));
		}
	}
	// Get all districts by province id
	@GetMapping(value = "/districts/{id}")
	public ResponseEntity<ResponseObjectDTO> getDistrictsByProvinceID(@PathVariable("id") int id) {
		List<DistrictsDTO> districtsList = iDistrictsService.findByProvincesID(id);
		if (districtsList != null && districtsList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", districtsList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find province with id_districts = "+id, ""));
		}
	}
}
