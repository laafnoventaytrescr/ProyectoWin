package com.example.demo.model.error;


import org.springframework.http.HttpStatus;

public class SendEmailException extends Exception implements IError{              //Clase modelo para problemas de Smtp

	private static final long serialVersionUID = 1L;
	private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	private final int statusValue = HttpStatus.INTERNAL_SERVER_ERROR.value();
    private final String message = "El puerto STMP presenta un problema, servicio de correo no disponible";

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
		
		return this.message;
	}

}
