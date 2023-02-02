package com.agencyapp.controller.iam;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agencyapp.dto.ResponseObjectDTO;
import com.agencyapp.dto.iam.AgenciesDTOPO;
import com.agencyapp.service.IAgenciesService;

@RestController
public class AgenciesController {
	@Autowired
	private IAgenciesService iAgencyService;
	// Get all agencies
	@GetMapping(value = "/agencies")
	public ResponseEntity<ResponseObjectDTO> getAllAgency(@RequestParam("page") Optional<Integer> page,@RequestParam("pagesize") Optional<Integer> pagesize) {
		List<AgenciesDTOPO> agencyList = iAgencyService.getAllAgency(page.orElse(0), pagesize.orElse(5));
		if(agencyList != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObjectDTO("ok","Querry successfully",agencyList));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObjectDTO("failed","Cannot find agency",""));
		}
		
	}
	// Get agency by id
	@GetMapping(value = "/agencies/{id}")
	public ResponseEntity<ResponseObjectDTO> getUserById(@PathVariable("id") int id) {
		AgenciesDTOPO agencyFound = iAgencyService.getAgencyById(id);
		if(agencyFound != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObjectDTO("ok","Querry successfully",agencyFound));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObjectDTO("failed","Cannot find agency with id= "+id,""));
		}
		
	}
}
