package com.agencyapp.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agencyapp.dto.system.WardsDTO;
import com.agencyapp.model.system.WardsEntity;
@Repository
public interface WardsDao extends JpaRepository<WardsEntity, Integer>{
	@Query(value = "SELECT * FROM wards as w WHERE w.district_id = :idP", nativeQuery = true)
	List<WardsEntity> findByDistrictsID(@Param("idP") int id);
	@Query(value = "SELECT name FROM wards WHERE id = :idP", nativeQuery = true)
	public String findNameByid(@Param("idP") int id);
	
	@Query(value = "SELECT w.id, d.name as district_name, w.name FROM wards as w INNER JOIN districts as d ON d.id = w.district_id ", nativeQuery = true)
	public List<WardsDTO> findAllWardsDTO();
	
	@Query(value = "SELECT w.id, d.name as district_name, w.name FROM wards as w INNER JOIN districts as d ON d.id = w.district_id WHERE w.district_id = :idP", nativeQuery = true)
	public List<WardsDTO> findWardsByDistrictIdDTO(@Param("idP") int id);
}
