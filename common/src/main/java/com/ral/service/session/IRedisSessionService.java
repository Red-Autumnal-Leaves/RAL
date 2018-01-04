package com.ral.service.session;

/**
 * 
 * @author victor
 * @desc redis session 接口
 */
public interface IRedisSessionService extends ISessionService {

	/**
	 * 全局唯一，作为redis存储标识
	 */
	String getKey();
	
	/**
	 * 
	 * 默认过期时间，key过期时间
	 */
	int getExpire();
}
