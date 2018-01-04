package com.ral.service.lock.impl;

import com.mysql.jdbc.StringUtils;
import com.ral.model.domain.LockMeta;
import com.ral.service.lock.IRedisLock;
import com.ral.service.redis.IRedisService;
import com.ral.util.codec.JSONUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * redis分布式锁
 * 
 * @author victor
 */
@Service
public class RedisLockImpl implements IRedisLock {

	private static Logger logger = Logger.getLogger(RedisLockImpl.class);

	private static final int timeout = 3600;

	@Autowired
	private IRedisService redisService;

	@Override
	public boolean lock(String key, int exprie) {
		try {
			exprie = exprie <= 0 ? timeout : exprie;
			String value = JSONUtils.toJson(createMeta(exprie));
			String lockKey = this.getLockKeyPrev() + key;
			if (redisService.setnx(lockKey, value) == 1) {
				logger.debug("Get redis lock success, key =" + lockKey);
				return true;
			}
			value = redisService.get(lockKey);
			if (StringUtils.isNullOrEmpty(value)) {
				redisService.del(lockKey);
				logger.debug("Redis unlock success ,key = " + lockKey);
				Thread.sleep(1000);
				value = JSONUtils.toJson(createMeta(exprie));
				if (redisService.setnx(lockKey, value) == 1) {
					redisService.expire(lockKey, exprie);
					logger.debug("Get redis lock success, key =" + lockKey);
					return true;
				} else {
					logger.warn("Get redis lock fail, key =" + lockKey);
					return false;
				}
			}
			LockMeta meta = JSONUtils.toBean(value, LockMeta.class);
			if (meta.isLose()) {// 已经超时
				redisService.del(lockKey);
				value = JSONUtils.toJson(createMeta(exprie));
				if (redisService.setnx(lockKey, value) == 1) {
					redisService.expire(lockKey, exprie);
					logger.debug("Get redis lock success, key =" + lockKey);
					return true;
				} else {
					logger.warn("Get redis lock fail, key =" + lockKey);
					return false;
				}
			}
			logger.warn("Get redis lock fail, key =" + lockKey);
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
			return true;
		}
	}

	@Override
	public void unlock(String key) {
		String lockKey = this.getLockKeyPrev() + key;
		try {
			redisService.del(lockKey);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		logger.debug("Redis unlock success ,key = " + lockKey);
	}

	private LockMeta createMeta(int exprie) {
		LockMeta meta = new LockMeta();
		meta.setExpireTime(exprie);
		meta.setLockTime(System.currentTimeMillis());
		return meta;
	}

	@Override
	public String getLockKeyPrev() {
		return "lock:";
	}

	public IRedisService getRedisService() {
		return redisService;
	}

	public void setRedisService(IRedisService redisService) {
		this.redisService = redisService;
	}
	
}
