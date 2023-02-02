package com.agencyapp.dao.properties;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agencyapp.dto.properties.ResponsePropertiesDTO;
import com.agencyapp.model.properties.PropertiesEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PropertiesDao extends JpaRepository<PropertiesEntity, Integer> {
	public PropertiesEntity findById(int id);
	@Query(value = "SELECT p.id, u.username as user_name, p.for_sale, p.for_rent_long, p.for_rent_short, p.price_sale, p.price_rent_long, p.price_rent_short, pt.name as type_name, pst.name as subtype_name, p.status, p.floor, p.description, p.address1, p.address2, c.name as country_name, prov.name as province_name, d.name as district_name, w.name as ward_name, p.commission, p.commission_pct, p.commission_list_agent, p.commission_list_agent_pct, p.commission_sell_agent, p.commission_sell_agent_pct, p.beds, p.baths, p.built_space, p.garden_space, p.terrace_space, p.currency, p.dimension, p.created, p.updated FROM properties as p INNER JOIN users as u ON p.user_id = u.id INNER JOIN property_types as pt ON p.type_id = pt.id INNER JOIN property_subtypes as pst ON pst.id = p.subtype_id INNER JOIN countries as c on c.id = p.country_id INNER JOIN provinces as prov ON prov.id = p.province_id INNER JOIN districts as d ON d.id = p.district_id INNER JOIN wards as w ON w.id = p.ward_id where p.id = :idP", nativeQuery = true)
	public ResponsePropertiesDTO findPropertiesDTOById(@Param("idP") int id);

	@Query(value = "SELECT * FROM #{#entityName} WHERE price_sale <= :priceP", nativeQuery = true)
	List<PropertiesEntity> findByPropertyByPrice(@Param("priceP") Long price);

	List<PropertiesEntity> findByBaths(int baths);

	List<PropertiesEntity> findByBeds(int beds);

	@Query(value = "SELECT * FROM `properties` as p WHERE p.type_id = :typeP", nativeQuery = true)
	List<PropertiesEntity> findByTypeId(@Param("typeP") int propertyTypes);

	@Query(value = "SELECT id FROM properties ORDER BY id DESC LIMIT 0,1", nativeQuery = true)
	public int findLastIDForTest();

	@Query(value = "SELECT * FROM properties WHERE #{#entityName.:paramP} = :valueP", nativeQuery = true)
	List<PropertiesEntity> findByPropertyByParam(@Param("paramP") String param, @Param("valueP") int value);
	//
	@Query(value = "SELECT p.id, u.username as user_name, p.for_sale, p.for_rent_long, p.for_rent_short, p.price_sale, p.price_rent_long, p.price_rent_short, pt.name as type_name, pst.name as subtype_name, p.status, p.floor, p.description, p.address1, p.address2, c.name as country_name, prov.name as province_name, d.name as district_name, w.name as ward_name, p.commission, p.commission_pct, p.commission_list_agent, p.commission_list_agent_pct, p.commission_sell_agent, p.commission_sell_agent_pct, p.beds, p.baths, p.built_space, p.garden_space, p.terrace_space, p.currency, p.dimension, p.created, p.updated FROM properties as p INNER JOIN users as u ON p.user_id = u.id INNER JOIN property_types as pt ON p.type_id = pt.id INNER JOIN property_subtypes as pst ON pst.id = p.subtype_id INNER JOIN countries as c on c.id = p.country_id INNER JOIN provinces as prov ON prov.id = p.province_id INNER JOIN districts as d ON d.id = p.district_id INNER JOIN wards as w ON w.id = p.ward_id", nativeQuery = true)
	List<ResponsePropertiesDTO> findAllProperties(Pageable pageable);
	
	//

	@Query("SELECT p FROM properties p WHERE (:minbaths is null or (p.baths >= :minbaths and p.baths <= :maxbaths )) "
			+ "and (:minbeds is null or (p.beds >= :minbeds and p.beds <= :maxbeds )) "
			+ "and (:minprices is null or (p.priceSale >= :minprices and p.priceSale <= :maxprices)) "
			+ "and (:address is null or p.address2 LIKE :address) " 
			+ "and (:type_id is null or p.type_id = :type_id) "
			+ "and (:floor is null or p.floor = :floor) "
			+ "and (:user_id is null or p.user_id = :user_id) "
			+ "and (:minpricesrentlong is null or (p.priceRentLong >= :minpricesrentlong and p.priceRentLong <= :maxpricesrentlong)) "
			+ "and (:minpricesrentshort is null or (p.priceRentShort >= :minpricesrentshort and p.priceRentShort <= :maxpricesrentshort))")
	List<PropertiesEntity> findByMultiFilter(@Param("minbaths") Integer minbaths, @Param("maxbaths") Integer maxbaths,
			@Param("minbeds") Integer minbeds, @Param("maxbeds") Integer maxbeds, @Param("minprices") Long minprices,
			@Param("maxprices") Long maxprices, @Param("address") String address, @Param("type_id") Integer type_id,
			@Param("floor") Integer floor, @Param("user_id") Integer user_id, @Param("minpricesrentlong") Long minPricesRentLong,
			@Param("maxpricesrentlong") Long maxPricesRentLong,@Param("minpricesrentshort") Long minPricesRentShort,
			@Param("maxpricesrentshort") Long maxPricesRentShort, Pageable pageable);
	
	@Query(value = "SELECT p.id, u.username as user_name, p.for_sale, p.for_rent_long, p.for_rent_short, p.price_sale, p.price_rent_long, p.price_rent_short, pt.name as type_name, pst.name as subtype_name, p.status, p.floor, p.description, p.address1, p.address2, c.name as country_name, prov.name as province_name, d.name as district_name, w.name as ward_name, p.commission, p.commission_pct, p.commission_list_agent, p.commission_list_agent_pct, p.commission_sell_agent, p.commission_sell_agent_pct, p.beds, p.baths, p.built_space, p.garden_space, p.terrace_space, p.currency, p.dimension, p.created, p.updated FROM properties as p INNER JOIN users as u ON p.user_id = u.id INNER JOIN property_types as pt ON p.type_id = pt.id INNER JOIN property_subtypes as pst ON pst.id = p.subtype_id INNER JOIN countries as c on c.id = p.country_id INNER JOIN provinces as prov ON prov.id = p.province_id INNER JOIN districts as d ON d.id = p.district_id INNER JOIN wards as w ON w.id = p.ward_id WHERE p.created >= DATE_SUB(CURDATE(), INTERVAL :daysP DAY)", nativeQuery = true)
	List<ResponsePropertiesDTO> findAllPropertiesInLastCustomDays(Pageable pageable,@Param("daysP") int days);
	
	@Query(value = "SELECT p.id, u.username as user_name, p.for_sale, p.for_rent_long, p.for_rent_short, p.price_sale, p.price_rent_long, p.price_rent_short, pt.name as type_name, pst.name as subtype_name, p.status, p.floor, p.description, p.address1, p.address2, c.name as country_name, prov.name as province_name, d.name as district_name, w.name as ward_name, p.commission, p.commission_pct, p.commission_list_agent, p.commission_list_agent_pct, p.commission_sell_agent, p.commission_sell_agent_pct, p.beds, p.baths, p.built_space, p.garden_space, p.terrace_space, p.currency, p.dimension, p.created, p.updated FROM properties as p INNER JOIN users as u ON p.user_id = u.id INNER JOIN property_types as pt ON p.type_id = pt.id INNER JOIN property_subtypes as pst ON pst.id = p.subtype_id INNER JOIN countries as c on c.id = p.country_id INNER JOIN provinces as prov ON prov.id = p.province_id INNER JOIN districts as d ON d.id = p.district_id INNER JOIN wards as w ON w.id = p.ward_id WHERE p.created >= DATE_SUB(CURDATE(), INTERVAL :daysP DAY) AND u.id = :idP", nativeQuery = true)
	List<ResponsePropertiesDTO> findAllPropertiesOfAgenciesInLastCustomDays(@Param("idP") int agenciesId,Pageable pageable,@Param("daysP") int days);
	
}
