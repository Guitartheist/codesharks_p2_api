package com.revature.trial_by_combat.aspects;


import com.revature.trial_by_combat.exceptions.AuthenticationException;
import com.revature.trial_by_combat.exceptions.AuthorizationException;
import com.revature.trial_by_combat.exceptions.InvalidRequestException;
import com.revature.trial_by_combat.exceptions.ResourceNotFoundException;
import com.revature.trial_by_combat.exceptions.ResourcePersistenceException;
import com.revature.trial_by_combat.web.dtos.ErrorExceptionResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ErrorExceptionAspect {

    @ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorExceptionResponse handleRuntimeException(RuntimeException e) {
		return new ErrorExceptionResponse(500, e);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorExceptionResponse handleException(Exception e) {
		return new ErrorExceptionResponse(500, e);
	}

    @ExceptionHandler(ResourcePersistenceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorExceptionResponse handleResourcePersistanceException(RuntimeException e) {
		return new ErrorExceptionResponse(500, e);
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorExceptionResponse handleInvalidRequestException(RuntimeException e) {
		return new ErrorExceptionResponse(400, e);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorExceptionResponse handleAuthenticationException(RuntimeException e) {
		return new ErrorExceptionResponse(401, e);
	}

    @ExceptionHandler(AuthorizationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorExceptionResponse handleAuthorizationException(RuntimeException e) {
		return new ErrorExceptionResponse(400, e);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorExceptionResponse handleResourceNotFoundException(RuntimeException e) {
		return new ErrorExceptionResponse(401, e);
	}

    
}
