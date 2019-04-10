package com.example.demo.auth.filter;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.StaticSpring;
import com.example.demo.auth.service.JWTService;
import com.example.demo.model.entity.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;                                                      //Clase para gestionar y autenticar el usuario
	private JWTService jwtService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		this.authenticationManager = authenticationManager;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/v1/tokens", "POST"));             //Uri con el post de login
		
		this.jwtService = jwtService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		String email = request.getParameter("email");                                                       //Obtiene parametros del formulario
		String password = request.getParameter("password");                              
		
		if(email != null && password !=null) {
			logger.info("Username desde request parameter (form-data): " + email);
			logger.info("Password desde request parameter (form-data): " + password);                      //Obtiene parametros en formato form-data
			
		} else {
			User user = null;
			try {
				
				user = new ObjectMapper().readValue(request.getInputStream(), User.class);
				
				email = user.getEmail();
				password = user.getPassword();
				
				logger.info("Username desde request InputStream (raw): " + email);                         //Obtiene parametros en formato json
				logger.info("Password desde request InputStream (raw): " + password);
				
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		email = email.trim();
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
		
		return authenticationManager.authenticate(authToken);                                                      //Autentica usuario y devuelve booleano
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String token = jwtService.create(authResult);
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));                             //Metodo para manejar la autenticacion exitosa
		
		response.addHeader(StaticSpring.HEADER_STRING, StaticSpring.TOKEN_PREFIX + token);
		response.setStatus(200);
		response.setContentType("application/json");
	 
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("mensaje", "Error de autenticación: username o password incorrecto!");
		body.put("error", failed.getMessage());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));                           //Metodo para manejar el error de autenticacion
		response.setStatus(401);
		response.setContentType("application/json");
	}
	
	

}
