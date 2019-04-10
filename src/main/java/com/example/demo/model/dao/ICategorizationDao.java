package com.example.demo.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.Categorization;

public interface ICategorizationDao extends CrudRepository<Categorization,Long>{
	
	@Query("from Categorization")
	public List<Categorization> findAllCategorizations();

}
