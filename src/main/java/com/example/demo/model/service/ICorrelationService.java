package com.example.demo.model.service;

import java.util.List;

import com.example.demo.model.entity.Correlation;



public interface ICorrelationService {
	
    public List<Correlation> findAllByPrePlanning_IdPrePlanning(Long id);
	
	public Correlation findById(Long id);
	
    public Correlation save(Correlation planning);
    
    public void delete(Long id);
}
