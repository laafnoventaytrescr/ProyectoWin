package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.StaticSpring;
import com.example.demo.auth.service.JWTService;
import com.example.demo.model.entity.User;
import com.example.demo.model.error.DataAccessRuntimeException;
import com.example.demo.model.service.IUserService;


@RestController
@RequestMapping("/v1")                                       //Controlador para gestionar funcionalidad de usuario
public class UserRestController {
	
	@Autowired(required=true)
	private IUserService userService;                                          //Injeccion de servicio se usuario
	
	@GetMapping("/users")
	public List<User> index(){
		return userService.findAll();                                          //Lista de usuarios
	}
	
	@GetMapping("/users/{id}")
	public User show(@PathVariable Long id) {
		return userService.findById(id);                                       //Buscar usuario por id
	}
	
	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) {
		user.setPassword(encodePassword(user.getPassword()));//Crear usuario
		return userService.save(user);
	}
	
	@PutMapping("/users/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public User update(@RequestBody User user, @PathVariable Long id) {
		User userActual = userService.findById(id);
		userActual.setBirthDate(user.getBirthDate());
		userActual.setCircuito(user.getCircuito());
		userActual.setEmail(user.getEmail());                                       //Actualizar usuario
		userActual.setIdNumber(user.getIdNumber());
		userActual.setLevelOfTeaches(user.getLevelOfTeaches());
		userActual.setName(user.getName());
		userActual.setPassword(user.getPassword());
		userActual.setRegionalDirection(user.getRegionalDirection());
		userActual.setSchool(user.getSchool());
		return userService.save(userActual);
	}
	
	@DeleteMapping("/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {                                       //Borrar usuario por id
		userService.delete(id);
	}
	
	
	public String encodePassword(String password) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();          //Metodo para encriptar contraseÃ±as
		String encodedPassword = passwordEncoder.encode(password);
		
		return encodedPassword;
	}

	
	////////////////////////////////////////////////////////////////////////////////////
	
	@Autowired
	JWTService tokenService;
	
	@GetMapping(value="/user")
	public ResponseEntity<User> loadUser(HttpServletRequest request){
		
		String header = request.getHeader(StaticSpring.HEADER_STRING);                        
		String email = tokenService.getUsername(header);
		User user = null;
		User loadUsu = null;
		
		try {     
			
		user = userService.findByEmail(email);
		loadUsu = userService.findById(user.getUserId());                    //Metodo para cargar el usuario logueado
		
		}catch(DataAccessException ex) {
			throw new DataAccessRuntimeException("Error al consultar base de datos");
		}
		
		if(loadUsu != null) {
			return new ResponseEntity<>(loadUsu,HttpStatus.OK);  
		}else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); 
		}
	}
	
	
	
	
}
