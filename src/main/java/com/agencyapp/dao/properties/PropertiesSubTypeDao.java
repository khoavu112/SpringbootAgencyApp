package com.agencyapp.dao.properties;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agencyapp.dto.properties.PropertiesSubTypeDTO;
import com.agencyapp.model.properties.PropertiesSubtypesEntity;
@Repository
public interface PropertiesSubTypeDao extends JpaRepository<PropertiesSubtypesEntity, Integer>{
	@Query(value = "SELECT * FROM `property_subtypes` as p WHERE p.property_type_id = :idP", nativeQuery = true)
	List<PropertiesSubtypesEntity> findByPropertyTypeId(@Param("idP") int id);
	@Query(value = "SELECT name FROM property_subtypes WHERE id = :idP", nativeQuery = true)
	public String findNameByid(@Param("idP") int id);
	
	@Query(value = "SELECT pst.id, pt.name AS type_name, pst.name, pst.use_built_space, pst.use_terrace_space, pst.use_plot_space FROM property_subtypes as pst INNER JOIN property_types as pt ON pst.property_type_id = pt.id ", nativeQuery = true)
	List<PropertiesSubTypeDTO> findAllPropertiesSubTypeDTO();
	
	
	@Query(value = "SELECT pst.id, pt.name AS type_name, pst.name, pst.use_built_space, pst.use_terrace_space, pst.use_plot_space FROM property_subtypes as pst INNER JOIN property_types as pt ON pst.property_type_id = pt.id WHERE pst.property_type_id = :idP", nativeQuery = true)
	List<PropertiesSubTypeDTO> findPropertiesSubTypeByTypeIdDTO(@Param("idP") int id);
}

