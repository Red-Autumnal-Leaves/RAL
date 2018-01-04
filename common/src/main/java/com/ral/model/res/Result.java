package com.ral.model.res;

import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.query.Query;

public class Result {

	private int status;
	
	private String msg;
	
	private boolean success;
	
	private Query query;
	
	private Object response;	
	
	private Result(){
		
	}
	
	public Result(HttpStatusEnum status, boolean success) {
		super();
		this.status = status.getStatus();
		this.msg = status.getMssage();
		this.success = success;
	}
	
	public Result(HttpStatusEnum status, String msg, boolean success) {
		super();
		this.status = status.getStatus();
		this.msg = msg;
		this.success = success;
	}
	
	public Result(HttpStatusEnum status, String msg, boolean success,Object response) {
		super();
		this.status = status.getStatus();
		this.msg = msg;
		this.success = success;
		this.response = response;
	}
	
	public Result(HttpStatusEnum status, boolean success,Object response) {
		super();
		this.status = status.getStatus();
		this.msg = status.getMssage();
		this.success = success;
		this.response = response;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}
	
	public static Result initSuccessResult(Object data,Query query){
		Result result = new Result(HttpStatusEnum.OK, true);
		result.setResponse(data);
		result.setQuery(query);
		return result;
	}
	
	public static Result initResult(){
		Result result = new Result();
		return result;
	}
	
	public static Result initErrorResult(String message){
		Result result = new Result(HttpStatusEnum.ERROR,message,false);
		return result;
	}
	
	public static Result initErrorResult(HttpStatusEnum status,String message){
		Result result = new Result(status,message,false);
		return result;
	}
	
	public static Result initParamsErrorResult(String message){
		Result result = new Result(HttpStatusEnum.BAD_REQUEST,message,false);
		return result;
	}
}
