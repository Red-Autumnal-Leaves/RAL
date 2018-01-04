package com.ral.service.session;

import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 分布式redis锁服务
 * @author victor
 *
 */
public interface IRedissonService {

	void setConfig(Config config);
	
	RedissonClient getRedissonClient();
	
	RedissonClient getRedissonClient(Config config);
	
}
