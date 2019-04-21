package com.example.demo.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.MediationStrategies;

public interface IMediationStrategiesDao extends CrudRepository<MediationStrategies,Long> {

	@Query(value="SELECT m.mediation_strategies_id, m.content, m.interest, m.resources, m.correlation_id, m.unit, m.strategy, m.indicator \r\n" +
			"FROM pre_planning pr, plan p, users u, correlation c, mediation_strategies m \r\n" + 
			"WHERE u.user_id = :id AND p.user_id = u.user_id AND p.id_plan = pr.planning_id \r\n" + 
			"AND pr.id_pre_planning = c.pre_planning_id AND c.correlation_id = m.correlation_id",
			nativeQuery=true)
	public List<MediationStrategies> findMediationStrategiesByUser(Long id);
}
