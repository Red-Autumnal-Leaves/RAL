package com.ral.service.token;

/**
 * 
 * @author victor
 * redis token 接口
 */
public interface IRedisTokenService extends ITokenService {

	/**
	 * 全局唯一，作为redis存储标识
	 */
	String getKey();
	
	/**
	 * 
	 * expire 默认过期时间，key过期时间
	 */
	int getExpire();
}
