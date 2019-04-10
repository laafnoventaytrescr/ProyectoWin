package com.example.demo.model.error;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;

public class DataAccessRuntimeException extends NestedRuntimeException implements IError{

	private static final long serialVersionUID = 1L;
	private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	private final int statusValue = HttpStatus.INTERNAL_SERVER_ERROR.value();
	
	public DataAccessRuntimeException(String msg) {
		super(msg);
	}

	@Override
	public HttpStatus getStatus() {
		// TODO Auto-generated method stub
		return this.status;
	}

	@Override
	public int getStatusValue() {
		// TODO Auto-generated method stub
		return this.statusValue;
	}
	
	@Override
	public String getMessage() {
		
		return super.getMessage();
	}

}
