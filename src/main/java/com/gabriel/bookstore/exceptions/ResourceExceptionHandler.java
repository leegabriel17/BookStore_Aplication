package com.gabriel.bookstore.exceptions;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gabriel.bookstore.service.exceptions.DataIntegrityViolationException;
import com.gabriel.bookstore.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandarError> objectNotFoundExecption(ObjectNotFoundException e, ServletRequest request) {
		StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());//retorna o tempo que ocorreu , o status do http ex(404,200) esse status, e a mensagem definida no objectNotFoundexception.
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandarError> dataIntegrityViolationException(DataIntegrityViolationException d, ServletRequest request) {
		StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value() , d.getMessage());//retorna o tempo que ocorreu , o status do http ex(404,200) esse status, e a mensagem definida no objectNotFoundexception.
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
		
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandarError> ValidationError(MethodArgumentNotValidException e, ServletRequest request) {
		ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value() , "Erro na Validacao do Campo");//retorna o tempo que ocorreu , o status do http ex(404,200) esse status, e a mensagem definida no objectNotFoundexception.
		
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			error.addErrors(x.getField(),x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
		
	}

}
