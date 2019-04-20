package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dao.IMediationStrategiesDao;
import com.example.demo.model.entity.MediationStrategies;

@Service
public class MediationStrategiesImpl implements IMediationStrategiesService{

	@Autowired
	private IMediationStrategiesDao mediationStrategiesDao;
	
	@Override
	public List<MediationStrategies> findMediationStrategiesByUser(Long id) {
		
		return mediationStrategiesDao.findMediationStrategiesByUser(id);
	}

	@Override
	public MediationStrategies findById(Long id) {
	
		return mediationStrategiesDao.findById(id).orElse(null);
	}

	@Override
	public MediationStrategies save(MediationStrategies mediationStrategies) {
	
		return mediationStrategiesDao.save(mediationStrategies);
	}

	@Override
	public void delete(Long id) {
		
		mediationStrategiesDao.deleteById(id);
	}

}
