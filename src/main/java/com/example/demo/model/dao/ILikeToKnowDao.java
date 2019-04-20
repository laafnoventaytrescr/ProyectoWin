package com.example.demo.model.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.entity.LikeToKnow;

public interface ILikeToKnowDao extends CrudRepository<LikeToKnow, Long>{


	@Query(value="SELECT l.id_like_to_know, l.description, l.categorization_id " +
			"FROM pre_planning pr, plan p, users u, like_to_know l \r\n" + 
			"WHERE u.user_id = :id AND p.user_id = u.user_id AND p.id_plan = pr.planning_id AND pr.like_to_know_id = l.id_like_to_know",
			nativeQuery = true)
	public List<LikeToKnow> findLikeToKnowByUser(@Param("id") Long id);
}
