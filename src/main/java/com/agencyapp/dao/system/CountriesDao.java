package com.agencyapp.dao.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agencyapp.model.properties.PropertiesTypesEntity;
import com.agencyapp.model.system.CountriesEntity;

@Repository
public interface CountriesDao extends JpaRepository<CountriesEntity, Integer>{
	@Query(value = "SELECT name FROM countries WHERE id = :idP", nativeQuery = true)
	public String findNameByid(@Param("idP") int id);
}
