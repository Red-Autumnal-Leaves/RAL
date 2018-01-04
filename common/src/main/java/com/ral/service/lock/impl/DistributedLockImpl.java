package com.ral.service.lock.impl;

import com.ral.service.lock.IDistributedLock;
import com.ral.service.lock.IJvmLock;
import com.ral.service.lock.IRedisLock;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分布式锁实现
 * 默认使用jvm + redis 锁实现
 * @author victor
 *
 */
@Service("distributedLock")
public class DistributedLockImpl implements IDistributedLock {
	
	private static Logger logger = Logger.getLogger(DistributedLockImpl.class);

	@Autowired
	private IJvmLock jvmlock;

	@Autowired
	private IRedisLock redislock;

	@Override
	public boolean lock(String key, int exprie) {
		if(!jvmlock.lock(key, exprie)){
			return false;
		}
		if(!redislock.lock(key, exprie)){
			return false;
		}
		logger.debug("lock distributed lock success. key = " + key);
		return true;
	}

	@Override
	public void unlock(String key) {
		jvmlock.unlock(key);
		redislock.unlock(key);
		logger.debug("unlock distributed lock success. key = " + key);
	}

	public IJvmLock getJvmlock() {
		return jvmlock;
	}

	public void setJvmlock(IJvmLock jvmlock) {
		this.jvmlock = jvmlock;
	}

	public IRedisLock getRedislock() {
		return redislock;
	}

	public void setRedislock(IRedisLock redislock) {
		this.redislock = redislock;
	}

}
