package com.ral.model.enums.auth;

import com.google.common.collect.ImmutableMap;
import com.ral.model.enums.IndexedEnum;
import com.ral.model.enums.IndexedEnumUtil;
import org.springframework.http.HttpMethod;

/**
 * 
 * @author victor
 * @desc auth授权API url枚举
 */
public enum AuthApiEnum  implements IndexedEnum<AuthApiEnum> {

	LOGIN(101,"管理后台用户登录","/{tenantId}/login",HttpMethod.POST);
	
	AuthApiEnum(int index,String name,String url,HttpMethod method){
		this.index = index;
		this.name= name;
		this.url = url;
		this.method = method;
	}

	private static final ImmutableMap<Integer, AuthApiEnum> INDEXS = IndexedEnumUtil.toIndexes(values());

	public static AuthApiEnum indexOf(int index) {
		return INDEXS.get(index);
	}

	private int index;
	
	private String name;
	
	private String url;
	
	private HttpMethod method;

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public HttpMethod getMethod() {
		return method;
	}
}
