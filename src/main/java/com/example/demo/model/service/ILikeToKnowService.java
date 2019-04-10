package com.example.demo.model.service;

import java.util.List;


import com.example.demo.model.entity.LikeToKnow;

public interface ILikeToKnowService {
	
	public List<LikeToKnow> findAll();
	
	public LikeToKnow findById(Long id);
	
	public LikeToKnow save(LikeToKnow likeToKnow);
	
	public void delete(Long id);

}
