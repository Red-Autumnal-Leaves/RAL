package com.ral.model.enums;

import com.google.common.collect.ImmutableMap;

/**
 * http 请求响应枚举
 */
public enum HttpStatusEnum implements IndexedEnum<HttpStatusEnum> {

	/**
	 * 查询，删除请求成功
	 */
	OK(200,"Ok"),
	
	/**
	 * 创建,修改成功
	 */
	CREATED(201,"Ok"),
	
	/**
	 * 受理请求，异步处理
	 */
	ACCEPTED(202,"Accepted"),
	
	/**
	 * 请求非法，或者参数不合法
	 */
	BAD_REQUEST(400,"Bad request"),
	
	/**
	 * 请求无权限,未登录，token失效等
	 */
	NOT_AUTHORIZED(403,"Not authorized"),
	
	/**
	 * 主要用于资源访问无效,或者请求地址不存在
	 */
	NOT_FOUND(404,"Not found"),
	
	/**
	 * 请求方式错误
	 */
	METHOD_NOT_SUPPORTED(405,"Request method not supported."),
	
	/**
	 * 服务器异常,主要用于异常
	 */
	ERROR(500,"Error");
	
	HttpStatusEnum(int status,String message){
		this.status = status;
		this.mssage = message;
	}


	private int status;
	
	private String mssage;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMssage() {
		return mssage;
	}

	public void setMssage(String mssage) {
		this.mssage = mssage;
	}


	@Override
	public int getIndex() {
		return status;
	}

	private static final ImmutableMap<Integer, HttpStatusEnum> INDEXS = IndexedEnumUtil.toIndexes(values());

	public static HttpStatusEnum indexOf(int index){
		return INDEXS.get(index);
	}

}
