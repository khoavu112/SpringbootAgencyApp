package com.agencyapp.dao.properties;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agencyapp.model.properties.PropertiesPicturesEntity;
@Repository
public interface PropertiesPicturesDao extends JpaRepository<PropertiesPicturesEntity, Integer>{
	
	@Query(value = "SELECT * FROM pic_properties as pp WHERE pp.properties_id = :idP", nativeQuery = true)
	public List<PropertiesPicturesEntity> findByPropertiesId(@Param("idP") int id);
}
