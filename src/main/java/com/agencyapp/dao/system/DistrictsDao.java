package com.agencyapp.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agencyapp.dto.system.DistrictsDTO;
import com.agencyapp.model.system.DistrictsEntity;
@Repository
public interface DistrictsDao extends JpaRepository<DistrictsEntity, Integer>{
	@Query(value = "SELECT * FROM districts as d WHERE d.province_id = :idP", nativeQuery = true)
	List<DistrictsEntity> findByProvincesID(@Param("idP") int id);
	@Query(value = "SELECT name FROM districts WHERE id = :idP", nativeQuery = true)
	public String findNameByid(@Param("idP") int id);
	
	@Query(value = "SELECT d.id, p.name as province_name, d.name FROM districts as d INNER JOIN provinces as p ON d.province_id = p.id ", nativeQuery = true)
	List<DistrictsDTO> findAllDistrictsDTO();
	@Query(value = "SELECT d.id, p.name as province_name, d.name FROM districts as d INNER JOIN provinces as p ON d.province_id = p.id WHERE d.province_id = :idP ", nativeQuery = true)
	List<DistrictsDTO> findAllDistrictsByProviceIdDTO(@Param("idP") int id);
	
}
