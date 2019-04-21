package com.example.demo.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.EducationalRegion;

public interface IEducationalRegionDao extends CrudRepository<EducationalRegion,Long>{
	
}
