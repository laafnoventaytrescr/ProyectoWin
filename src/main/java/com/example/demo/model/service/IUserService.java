package com.example.demo.model.service;

import java.util.List;

import com.example.demo.model.entity.User;

public interface IUserService {                              //Interface de mantenimieto de usuario
	
	public List<User> findAll();
	
	public User findById(Long id);
	
	public User findByEmail(String email);
	
    public User save(User user);
    
    public void delete(Long id);
    
    
	
}
