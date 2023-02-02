package com.agencyapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agencyapp.dao.system.CountriesDao;
import com.agencyapp.model.system.CountriesEntity;
import com.agencyapp.service.ICountriesService;
@Service
public class CountriesServiceImpl implements ICountriesService{
	@Autowired
	private CountriesDao countriesDao;
	@Override
	public List<CountriesEntity> getAllCountries() {
		return countriesDao.findAll();
	}

}
