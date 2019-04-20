package com.example.demo.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.entity.Correlation;


public interface ICorrelationDao extends CrudRepository<Correlation,Long>{

	@Query(value="SELECT c.correlation_id, c.concept, c.level, c.pre_planning_id, c.unit_id " +
			"FROM pre_planning pr, plan p, users u, correlation c \r\n" + 
			"WHERE u.user_id = :id AND p.user_id = u.user_id AND p.id_plan = pr.planning_id \r\n" + 
			"AND pr.id_pre_planning = c.pre_planning_id",
			nativeQuery=true)
	public List<Correlation> findCorrelationByUser(@Param("id") Long id);
}
