package com.example.demo.auth.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.example.demo.StaticSpring;
import com.example.demo.auth.SimpleGrantedAuthorityMixin;
import com.example.demo.model.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTServiceImpl implements JWTService {                      //Clase para gestionar el token

	@Autowired
	IUserService userService;
	
	@Override
	public String create(Authentication auth) throws IOException {

		String username = ((User) auth.getPrincipal()).getUsername();
		
		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();                              //Metodo para crear Token

		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));

		String token = Jwts.builder().setClaims(claims).setId(userService.findByEmail(username).getUserId().toString())
				.setSubject(username)
				.signWith(SignatureAlgorithm.HS512, StaticSpring.SECRET.getBytes()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + StaticSpring.EXPIRATION_DATE)).compact();

		return token;
	}

	@Override
	public boolean validate(String token) {

		try {

			getClaims(token);

			return true;
		} catch (JwtException | IllegalArgumentException e) {                                             //Metodo para validar token
			return false;
		}

	}

	@Override
	public Claims getClaims(String token) {
		Claims claims = Jwts.parser().setSigningKey(StaticSpring.SECRET.getBytes())                       //Metodo para obtener claims mediante firma
				.parseClaimsJws(resolve(token)).getBody();                              
		return claims;
	}
    
	@Override
	public String getId(String token) {
		
		return getClaims(token).getId();                                                                 //Metodo para obtener id de usuario
	}
	
	@Override
	public String getUsername(String token) {
		
		return getClaims(token).getSubject();                                                            //Metodo que obtiene usuario
	}

	@Override
	public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {
		Object roles = getClaims(token).get("authorities");

		Collection<? extends GrantedAuthority> authorities = Arrays
				.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)   //Metodo para obtener roles de usuario
						.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));

		return authorities;
	}

	@Override
	public String resolve(String token) {
		if (token != null && token.startsWith(StaticSpring.TOKEN_PREFIX)) {
			return token.replace(StaticSpring.TOKEN_PREFIX, "");                                       //Metodo para remplazar el prefijo "Bearer"
		}
		return null;
	}

}
