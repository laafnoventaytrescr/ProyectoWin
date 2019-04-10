package com.example.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dao.IUser;
import com.example.demo.model.entity.User;

@Service
public class UserServiceImpl implements IUserService{             //Servicio para mantenimiento de usuario

	@Autowired
	private IUser userDao;
	
	@Override
	@Transactional(readOnly=true)
	public User findByEmail(String email) {
	
		return userDao.findByEmail(email);
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> findAll() {
	
		return (List<User>)userDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public User findById(Long id) {
		
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public User save(User user) {
	
		return userDao.save(user);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		userDao.deleteById(id);
	}

}
