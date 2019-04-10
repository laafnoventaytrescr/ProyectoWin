package com.example.demo;

import org.springframework.util.Base64Utils;

public class StaticSpring {                                                          //Clase para definir constantes globales

	public static final String emailMep = "mepcostaricaprueba@hotmail.com";
	
	public static String createEmailMessage(String fullName, String temporalPassword) {
		String message = "Estimado " + fullName + "<br>"
				+ "Se le ha asignado la siguiente contraseña temporal: <b>" + temporalPassword + "</b><br>"             //Servicio Email
				+ "Ingrese nuevamente en el sistema con la contraseña temporal y seguidamente se\r\n"
				+ "le solicitará que la cambie por una nueva.<br>"
				+ " <a href='#'>Cambiar contraseña</a><br>"
				+ "<img src='https://www.mep.go.cr/sites/default/files/imagecache/Slideshow/blog/portada/noticiaprensa_50.png'><br>";
		
		return message;
	}
	
	
	
	
	public static final String SECRET = Base64Utils.encodeToString("WinProject".getBytes());
	public static final long EXPIRATION_DATE = 600000L;                                   
	public static final String TOKEN_PREFIX = "Bearer ";                                                                //Servicio Token
	public static final String HEADER_STRING = "Authorization";
}
