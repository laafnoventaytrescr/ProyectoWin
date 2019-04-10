package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dao.ICategorizationDao;
import com.example.demo.model.entity.Categorization;

@Service
public class CategorizationServiceImpl implements ICategorizationService{

	@Autowired
	private ICategorizationDao iCategorizationDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Categorization> findAll() {
		// TODO Auto-generated method stub
		return (List<Categorization>)iCategorizationDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Categorization findById(Long id) {
		// TODO Auto-generated method stub
		return iCategorizationDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Categorization save(Categorization categorization) {
		// TODO Auto-generated method stub
		return iCategorizationDao.save(categorization);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		iCategorizationDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Categorization> findAllCategorizations() {
		return iCategorizationDao.findAllCategorizations();
	}

}
