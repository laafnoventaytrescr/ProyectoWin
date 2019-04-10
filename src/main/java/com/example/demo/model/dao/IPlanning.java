package com.example.demo.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.Planning;


public interface IPlanning extends CrudRepository<Planning,Long>{

	public List<Planning> findAllByUser_UserId(Long id);
}
