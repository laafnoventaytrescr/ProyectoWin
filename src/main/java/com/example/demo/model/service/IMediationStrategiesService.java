package com.example.demo.model.service;

import java.util.List;

import com.example.demo.model.entity.MediationStrategies;


public interface IMediationStrategiesService {

public List<MediationStrategies> findMediationStrategiesByUser(Long id);
	
	public MediationStrategies findById(Long id);
	
    public MediationStrategies save(MediationStrategies mediationStrategies);
    
    public void delete(Long id);
}
