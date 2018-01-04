package com.ral.exception;

/**
 * 
 * @author victor
 * @desc 自定义参数异常类
 */
public class ParamsException extends RuntimeException {

	private static final long serialVersionUID = 54656132134254523L;
	
	private String message;
	
	public ParamsException(){
		super();
	}
	
	public ParamsException(String message){
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
