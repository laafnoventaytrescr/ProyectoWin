package com.example.demo.model.service;

import java.util.List;

import com.example.demo.model.entity.Categorization;

public interface ICategorizationService {

	public List<Categorization> findAll();
	
	public Categorization findById(Long id);
	
    public Categorization save(Categorization user);
    
    public void delete(Long id);
    
	public List<Categorization> findAllCategorizations();
}
