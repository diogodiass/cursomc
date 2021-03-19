package com.diogoamorim.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.diogoamorim.cursomc.services.exceptions.DateIntegrityException;
import com.diogoamorim.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StanderError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StanderError er = new StanderError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
	}

	@ExceptionHandler(DateIntegrityException.class)
	public ResponseEntity<StanderError> dateIntegrity(DateIntegrityException e, HttpServletRequest request) {
		StanderError er = new StanderError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StanderError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		StanderError er = new StanderError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	}

}
