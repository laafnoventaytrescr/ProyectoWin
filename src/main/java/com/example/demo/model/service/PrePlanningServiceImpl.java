package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dao.IPrePlanningDao;
import com.example.demo.model.entity.PrePlanning;

@Service
public class PrePlanningServiceImpl implements IPrePlanningService{
	
	@Autowired
	private IPrePlanningDao prePlanningDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<PrePlanning> findAll() {
		// TODO Auto-generated method stub
		return (List<PrePlanning>)prePlanningDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public PrePlanning findById(Long id) {
		// TODO Auto-generated method stub
		return prePlanningDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public PrePlanning save(PrePlanning prePlanning) {
		// TODO Auto-generated method stub
		return prePlanningDao.save(prePlanning);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		prePlanningDao.deleteById(id);
	}

}
