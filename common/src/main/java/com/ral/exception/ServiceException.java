package com.ral.exception;

public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 140085406084367381L;
	
	private String message;
	
	public ServiceException(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
