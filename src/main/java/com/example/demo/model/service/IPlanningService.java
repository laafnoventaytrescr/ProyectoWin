package com.example.demo.model.service;

import java.util.List;

import com.example.demo.model.entity.Planning;

public interface IPlanningService {

	public List<Planning> findAllByUser_UserId(Long id);
	
	public Planning findById(Long id);
	
    public Planning save(Planning planning);
    
    public void delete(Long id);
}
