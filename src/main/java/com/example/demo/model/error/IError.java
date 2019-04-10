package com.example.demo.model.error;

import org.springframework.http.HttpStatus;

public interface IError {                                //Interface de error

	public HttpStatus getStatus();
	public int getStatusValue();
	public String getMessage();
}
