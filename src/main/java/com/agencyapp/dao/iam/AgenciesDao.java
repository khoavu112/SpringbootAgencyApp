package com.agencyapp.dao.iam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agencyapp.model.iam.AgenciesEntity;
@Repository
public interface AgenciesDao extends JpaRepository<AgenciesEntity, Integer> {

	public AgenciesEntity findByid(int id);
}
