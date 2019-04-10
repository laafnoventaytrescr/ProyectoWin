package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dao.IPlanning;
import com.example.demo.model.entity.Planning;

@Service
public class PlanningServiceImpl implements IPlanningService{

	@Autowired
	private IPlanning planningDao;

	@Override
	public List<Planning> findAllByUser_UserId(Long id) {
		
		return planningDao.findAllByUser_UserId(id);
	}

	@Override
	public Planning findById(Long id) {
		
		return planningDao.findById(id).orElse(null);
	}


	@Override
	public Planning save(Planning planning) {
		
		return planningDao.save(planning);
	}

	@Override
	public void delete(Long id) {
		
		planningDao.deleteById(id);
	}
}
