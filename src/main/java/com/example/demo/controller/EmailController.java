package com.example.demo.controller;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.StaticSpring;
import com.example.demo.model.entity.User;
import com.example.demo.model.error.SendEmailException;
import com.example.demo.model.error.UserNotFoundException;
import com.example.demo.model.service.IUserService;
import com.example.demo.model.service.SendMailService;


@RestController
@RequestMapping(value="/email")
public class EmailController {

	
	@Autowired
	IUserService userService;                                                                                          //Servicios de acceso a los datos de usuario(DAO)
	
	@Autowired
	SendMailService mailService;                                                                                             //Servicio para enviar correos

	@PostMapping(value="/sendEmail")
	public ResponseEntity<User> sendEmail(@RequestParam("email") String email) throws UserNotFoundException, SendEmailException{
		
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
		
	}
	
	private String generateRandomPassword() {
	    SecureRandom random = new SecureRandom();
	    byte bytes[] = new byte[8];
	    random.nextBytes(bytes);
	    Encoder encoder = Base64.getUrlEncoder().withoutPadding();                                                          //Metodo que genera contraseña aleatoria
	    String password = encoder.encodeToString(bytes);
	    return password;
	}
}
