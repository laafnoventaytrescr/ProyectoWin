package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import com.example.demo.auth.filter.JWTAuthenticationFilter;
import com.example.demo.auth.filter.JWTAuthorizationFilter;
import com.example.demo.auth.service.JWTService;
import com.example.demo.model.service.JpaUserDetailsService;


@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{


	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTService jwtService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/login","/registro/**","/email/**","/api/**").permitAll()                           //Permitir login, registro y email sin generar token
		.anyRequest().authenticated()		
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))                                   //Filtro de autenticacion (login)      
		.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))                                    //Filtro de autorizacion de token
		.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())                     //Permitir cross origin (javascript)
		.and()
		.csrf().disable()                                                                                              //Deshabilitar certificado de formularios
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);                                   //Servidor sin estado

	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.userDetailsService(userDetailsService)                                                                   //Desencriptar contraseñas Bcrypt
		.passwordEncoder(passwordEncoder);
	}
}
