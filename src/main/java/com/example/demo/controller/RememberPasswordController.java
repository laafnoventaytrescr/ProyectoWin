package com.example.demo.controller;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.StaticSpring;
import com.example.demo.model.entity.User;
import com.example.demo.model.error.DataAccessRuntimeException;
import com.example.demo.model.error.SendEmailException;
import com.example.demo.model.error.UserNotFoundException;
import com.example.demo.model.service.IUserService;
import com.example.demo.model.service.SendMailService;


@RestController
@RequestMapping(value="/v1/password")
public class RememberPasswordController {

	
	@Autowired
	IUserService userService;                                                                                          //Servicios de acceso a los datos de usuario(DAO)
	
	@Autowired
	SendMailService mailService;                                                                                             //Servicio para enviar correos

	@PostMapping(value="/sendEmail")
	public ResponseEntity<User> sendEmail(@RequestParam("email") String email) throws UserNotFoundException, SendEmailException{
		
		try {
				User user = userService.findByEmail(email);                                                                 //Consulta de usuario por su email
				
				if(user != null) {                                                                                                //Validacion de existencia de usuario en la base de datos
							
				    String randomPassword = generateRandomPassword();                                                                //Generar contraseña aleatoria temporal
				    String message = StaticSpring.createEmailMessage(user.getName(), randomPassword);                         //Obtener template del correo
					
					user.setTempPassword(randomPassword);
					userService.save(user);                                                                                   //Guardar contraseña temporal en base de datos
					
					mailService.sendEmail(StaticSpring.emailMep, user.getEmail(), "Cambiar contraseña", message);                //Enviar correo
					return new ResponseEntity<>(user,HttpStatus.OK);                                                             //Respuesta del servidor al front end
							
				}else {
					throw new UserNotFoundException("El correo ingresado no existe");                                                                              //Error de usuario inexistente
				}
		}catch(DataAccessException ex) {
			throw new DataAccessRuntimeException("Error al consultar base de datos");
		}
	}
	
	@PutMapping(value="/change")
	public ResponseEntity<String> changePassword(@RequestParam("email") String email, 
												 @RequestParam("tempPassword") String tempPassword,
												 @RequestParam("newPassword") String newPassword){
		try {
			User user = userService.findByEmail(email); 
			
			if(user != null) {
				
				if(user.getTempPassword().equals(tempPassword)) {
					
		             user.setPassword(encodePassword(newPassword));			             
		             userService.save(user);		             
		             return new ResponseEntity<>("Contraseña modificada correctamente",HttpStatus.OK);  
		             
				}else {
					return new ResponseEntity<>("Error, contraseña temporal inválida",HttpStatus.FORBIDDEN);             //Metodo para actualizar contraseña
				}
				
			}else {
				throw new UserNotFoundException("El correo ingresado no existe");
			}
		}catch(DataAccessException ex) {
			throw new DataAccessRuntimeException("Error al consultar base de datos");
		}
	}
	
	private String generateRandomPassword() {
	    SecureRandom random = new SecureRandom();
	    byte bytes[] = new byte[8];
	    random.nextBytes(bytes);
	    Encoder encoder = Base64.getUrlEncoder().withoutPadding();                                                          //Metodo que genera contraseña aleatoria
	    String password = encoder.encodeToString(bytes);
	    return password;
	}
	
	public String encodePassword(String password) {
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();                                          //Metodo para encriptar contraseÃ±as
			String encodedPassword = passwordEncoder.encode(password);
			
			return encodedPassword;
		}
}
