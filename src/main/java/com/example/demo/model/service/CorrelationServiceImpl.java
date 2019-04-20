package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dao.ICorrelationDao;
import com.example.demo.model.entity.Correlation;


@Service
public class CorrelationServiceImpl implements ICorrelationService{

	@Autowired
	private ICorrelationDao correlationDao;

	@Override
	public List<Correlation> findAllByPrePlanning_IdPrePlanning(Long id) {
		
		return correlationDao.findCorrelationByUser(id);
	}

	@Override
	public Correlation findById(Long id) {
		
		return correlationDao.findById(id).orElse(null);
	}

	@Override
	public Correlation save(Correlation correlation) {
		
		return correlationDao.save(correlation);
	}

	@Override
	public void delete(Long id) {
		
		correlationDao.deleteById(id);
	}


}
