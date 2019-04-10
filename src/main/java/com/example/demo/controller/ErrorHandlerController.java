package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.model.error.DataAccessRuntimeException;
import com.example.demo.model.error.ErrorJson;
import com.example.demo.model.error.SendEmailException;
import com.example.demo.model.error.UserNotFoundException;

@RestControllerAdvice
public class ErrorHandlerController {

	  @ExceptionHandler(UserNotFoundException.class)
	    public ResponseEntity<ErrorJson> methodArgumentNotValidException(HttpServletRequest request, UserNotFoundException e) {

	        ErrorJson errorJson = new ErrorJson(e.getStatusValue(), e.getMessage(), request.getRequestURI());                   //Metodo que captura el error de la inexistencia de un usuario
	        return new ResponseEntity<>(errorJson, e.getStatus());

	    }
	  
	  @ExceptionHandler(SendEmailException.class)
	    public ResponseEntity<ErrorJson> methodArgumentNotValidException(HttpServletRequest request, SendEmailException e) {

	        ErrorJson errorJson = new ErrorJson(e.getStatusValue(), e.getMessage(), request.getRequestURI());                   //Metodo que captura el error de problemas con el correo
	        return new ResponseEntity<>(errorJson, e.getStatus());

	    }
	  
	  @ExceptionHandler(DataAccessRuntimeException.class)
	    public ResponseEntity<ErrorJson> methodArgumentNotValidException(HttpServletRequest request, DataAccessRuntimeException e) {

	        ErrorJson errorJson = new ErrorJson(e.getStatusValue(), e.getMessage(), request.getRequestURI());                   //Metodo que captura el error de acceso a datos de la base
	        return new ResponseEntity<>(errorJson, e.getStatus());

	    }
	  
	  
}
