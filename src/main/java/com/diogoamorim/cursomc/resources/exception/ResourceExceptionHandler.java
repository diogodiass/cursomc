package com.diogoamorim.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.diogoamorim.cursomc.services.exceptions.AuthorizationException;
import com.diogoamorim.cursomc.services.exceptions.DateIntegrityException;
import com.diogoamorim.cursomc.services.exceptions.FileException;
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
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validacao",
				System.currentTimeMillis());

		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StanderError> authorization(AuthorizationException e, HttpServletRequest request) {
		
		StanderError er = new StanderError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
	}

	@ExceptionHandler(FileException.class)
	public ResponseEntity<StanderError> file(FileException e, HttpServletRequest request) {
		
		StanderError er = new StanderError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	}

	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StanderError> amazonService(AmazonServiceException e, HttpServletRequest request) {
		
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		StanderError er = new StanderError(code.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(code).body(er);
	}

	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StanderError> amazonClient(AmazonClientException e, HttpServletRequest request) {

		StanderError er = new StanderError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StanderError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {

		StanderError er = new StanderError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	}

}
