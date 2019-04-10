package com.example.demo.model.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.error.UserNotFoundException;

import org.springframework.security.core.userdetails.User;


@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{               //Servicio para autenticacion de usuario

	@Autowired
	private IUserService userDao;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
		
		com.example.demo.model.entity.User user = userDao.findByEmail(email);
        
        if(user == null) {
        	logger.error("Error en el Login: no existe el usuario con correo'" + email + "' en el sistema!");
        	throw new UserNotFoundException("Usuario o contraseña invalidos"); 
        }
        
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        	authorities.add(new SimpleGrantedAuthority(user.getRole()));

        
        if(authorities.isEmpty()) {
        
				throw new UserNotFoundException("El usuario no posee un rol definido");
		   	
        }
        
		return new User(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
	}

}
