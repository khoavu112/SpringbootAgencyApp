package com.agencyapp.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agencyapp.dto.system.ProvincesDTO;
import com.agencyapp.dto.system.WardsDTO;
import com.agencyapp.model.system.ProvincesEntity;

@Repository
public interface ProvincesDao extends JpaRepository<ProvincesEntity, Integer>{
	@Query(value = "SELECT * FROM `provinces` as p WHERE p.country_id = :idP", nativeQuery = true)
	List<ProvincesEntity> findByCountryID(@Param("idP") int id);
	@Query(value = "SELECT name FROM provinces WHERE id = :idP", nativeQuery = true)
	public String findNameByid(@Param("idP") int id);
	
	
	@Query(value = "SELECT p.id, c.name as country_name, p.name FROM provinces AS p INNER JOIN countries as c ON p.country_id = c.id ", nativeQuery = true)
	public List<ProvincesDTO> findAllProvincesDTO();
	
	@Query(value = "SELECT p.id, c.name as country_name, p.name FROM provinces AS p INNER JOIN countries as c ON p.country_id = c.id WHERE p.country_id = :idP", nativeQuery = true)
	public List<ProvincesDTO> findProvincesByCountryIdDTO(@Param("idP") int id);
}
