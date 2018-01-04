package com.ral.model.domain;

import java.io.Serializable;

/**
 * session 存储类
 */
public class AccessToken implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public AccessToken(String tokenId, String token) {
		super();
		this.tokenId = tokenId;
		this.token = token == null ?  "" : token;
	}

	private String tokenId;//token唯一标识
	
	private String token;//token值
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token == null ?  "" : token;;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
}
