package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dao.IUnitDao;
import com.example.demo.model.entity.Unit;

@Service
public class UnitServiceImpl implements IUnitService{

	@Autowired
	IUnitDao unitDao;
	
	@Override
	public List<Unit> findAll() {
	
		return (List<Unit>)unitDao.findAll();
	}

	
}
