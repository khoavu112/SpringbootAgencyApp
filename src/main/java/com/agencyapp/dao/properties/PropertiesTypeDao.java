package com.agencyapp.dao.properties;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agencyapp.model.properties.PropertiesTypesEntity;
import com.agencyapp.dto.properties.PropertiesTypesDTO;
@Repository
public interface PropertiesTypeDao extends JpaRepository<PropertiesTypesEntity, Integer>{
	@Query(value = "SELECT name FROM property_types WHERE id = :idP", nativeQuery = true)
	public String findNameByid(@Param("idP") int id);
	@Query(value = "SELECT pt.id , pt.name , l.text_en, l.text_vi FROM property_types as pt INNER JOIN languages as l ON l.id = pt.language_id ", nativeQuery = true)
	public List<PropertiesTypesDTO> findAllTypePropertiesDTO();
}
