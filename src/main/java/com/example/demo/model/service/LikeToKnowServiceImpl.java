package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dao.ILikeToKnowDao;
import com.example.demo.model.entity.LikeToKnow;

@Service
public class LikeToKnowServiceImpl implements ILikeToKnowService{

	@Autowired
	private ILikeToKnowDao iLikeToKnowDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<LikeToKnow> findAllByIdLikeToKnow(Long id) {
		// TODO Auto-generated method stub
		return iLikeToKnowDao.findLikeToKnowByUser(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public LikeToKnow findById(Long id) {
		// TODO Auto-generated method stub
		return iLikeToKnowDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public LikeToKnow save(LikeToKnow likeToKnow) {
		// TODO Auto-generated method stub
		return iLikeToKnowDao.save(likeToKnow);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		iLikeToKnowDao.deleteById(id);
	}


}
