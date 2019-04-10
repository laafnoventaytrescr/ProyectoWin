package com.example.demo.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.User;


public interface IUser extends CrudRepository<User,Long> {                       //Clase que extiende de crud

	
	public User findByEmail(String email);                                          //Metodo para buscar usuario por email
} 
