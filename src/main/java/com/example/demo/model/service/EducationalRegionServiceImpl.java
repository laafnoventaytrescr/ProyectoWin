package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dao.IEducationalRegionDao;
import com.example.demo.model.entity.EducationalRegion;

@Service
public class EducationalRegionServiceImpl implements IEducationalRegionService{
	
	@Autowired
	IEducationalRegionDao educationalRegionDao;

	@Override
	@Transactional(readOnly=true)
	public List<EducationalRegion> findAll() {
		// TODO Auto-generated method stub
		return (List<EducationalRegion>)educationalRegionDao.findAll();
	}

}
