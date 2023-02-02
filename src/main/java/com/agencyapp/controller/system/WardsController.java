package com.agencyapp.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.agencyapp.dto.ResponseObjectDTO;
import com.agencyapp.dto.system.WardsDTO;
import com.agencyapp.model.system.WardsEntity;
import com.agencyapp.service.IWardsService;
@RestController
public class WardsController {
	@Autowired
	private IWardsService iWardsService;
	// Get all wards
	@GetMapping(value = "/wards")
	public ResponseEntity<ResponseObjectDTO> getAllProvinces() {
		List<WardsDTO> wardsList = iWardsService.getAllWards();
		if (wardsList != null && wardsList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", wardsList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find wards", ""));
		}
	}
	// Get all wards by provinces id
	@GetMapping(value = "/wards/{id}")
	public ResponseEntity<ResponseObjectDTO> getProvincesByCountryID(@PathVariable("id") int id) {
		List<WardsDTO> wardsList = iWardsService.findByDistrictsID(id);
		if (wardsList != null && wardsList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObjectDTO("ok", "Querry successfully", wardsList));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObjectDTO("failed", "Cannot find province with id_districts = "+id, ""));
		}
	}
}
