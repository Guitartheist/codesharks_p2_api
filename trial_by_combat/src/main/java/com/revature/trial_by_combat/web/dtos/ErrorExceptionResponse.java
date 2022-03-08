package com.revature.trial_by_combat.web.dtos;

import java.time.LocalDateTime;

public class ErrorExceptionResponse {

	private int statusCode;
	private String message;
	private String cause;
	private String datetime;
	
	
	
	public ErrorExceptionResponse() {
		super();
		this.datetime = LocalDateTime.now().toString();
	}
	
	
	
	public ErrorExceptionResponse(int statusCode, Throwable t) {
		super();
		this.statusCode = statusCode;
		this.message = t.getMessage();
		this.cause = t.getClass().getSimpleName();
		this.datetime = LocalDateTime.now().toString();
	}
	
	public ErrorExceptionResponse(int statusCode, String message, Throwable t) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.cause = t.getClass().getSimpleName();
		this.datetime = LocalDateTime.now().toString();
	}



	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return "ErrorExceptionResponse [statusCode=" + statusCode + ", message=" + message + ", cause=" + cause
				+ ", datetime=" + datetime + "]";
	}
	
	
	
}