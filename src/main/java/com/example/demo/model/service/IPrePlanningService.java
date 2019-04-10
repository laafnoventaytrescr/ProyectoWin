package com.example.demo.model.service;

import java.util.List;

import com.example.demo.model.entity.PrePlanning;

public interface IPrePlanningService {
	
	public List<PrePlanning> findAll();
	
	public PrePlanning findById(Long id);
	
    public PrePlanning save(PrePlanning prePlanning);
    
    public void delete(Long id);

}
