package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dao.IKnowAboutThemeDao;
import com.example.demo.model.entity.KnowAboutTheme;




@Service
public class KnowAboutThemeServiceImpl implements IKnowAboutThemeService{

	@Autowired
	private IKnowAboutThemeDao knowAboutThemeDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<KnowAboutTheme> findAll() {
		// TODO Auto-generated method stub
		return (List<KnowAboutTheme>)knowAboutThemeDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public KnowAboutTheme findById(Long id) {
		// TODO Auto-generated method stub
		return knowAboutThemeDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public KnowAboutTheme save(KnowAboutTheme knowAboutTheme) {
		// TODO Auto-generated method stub
		return knowAboutThemeDao.save(knowAboutTheme);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		knowAboutThemeDao.deleteById(id);
	}
}
