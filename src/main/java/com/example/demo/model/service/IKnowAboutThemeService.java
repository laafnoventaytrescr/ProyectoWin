package com.example.demo.model.service;

import java.util.List;

import com.example.demo.model.entity.KnowAboutTheme;

public interface IKnowAboutThemeService {
	
	public List<KnowAboutTheme> findAll();
	
	public KnowAboutTheme findById(Long id);
	
	public KnowAboutTheme save(KnowAboutTheme knowAboutTheme);
	
	public void delete(Long id);

}
