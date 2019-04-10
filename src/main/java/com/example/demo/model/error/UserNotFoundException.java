package com.example.demo.model.error;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class UserNotFoundException extends AuthenticationException implements IError{       //Clase modelo para problemas de usuario

	private static final long serialVersionUID = 1L;
	private final HttpStatus status = HttpStatus.NOT_FOUND;
	private final int statusValue = HttpStatus.NOT_FOUND.value();
    
    public UserNotFoundException(String msg) {
		super(msg);
	}
    
	@Override
	public HttpStatus getStatus() {
		
		return this.status;
	}
	
	@Override
	public int getStatusValue() {
		
		return this.statusValue;
	}

	@Override
	public String getMessage() {
		
		return super.getMessage();
	}
	
}
