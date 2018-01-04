package com.ral.config;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.ral.components.ApplicationContextHolder;
import com.ral.service.redis.IRedisService;
import com.ral.util.codec.SerializeUtils;
import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基于redis的mybatis二级缓存
 * 
 * @author victor
 *
 */
public class MybatisRedisCache implements Cache {

	private static final Logger logger = Logger.getLogger(MybatisRedisCache.class);

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

	@Autowired
	private IRedisService redisService;

	private final String id;

	public MybatisRedisCache(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
	}

	@Override
	public void clear() {
		getRedisService().del(getKey());//删除当前命名空间
	}

	@Override
	public Object getObject(Object field) {
		byte[] _field = SerializeUtils.serialize(field);
		byte[] value = getRedisService().hget(id.getBytes(), _field);
		logger.debug("Redis getObject:" + _field + "=" + value);
		return SerializeUtils.unserialize(value);
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	@Override
	public int getSize() {
		return getRedisService().hkeys(id.getBytes()).size();
	}

	@Override
	public void putObject(Object field, Object value) {
		byte[] _field = SerializeUtils.serialize(field);
		byte[] _value = SerializeUtils.serialize(value);
		logger.debug("Redis putObject:" + _field + "=" + value);
		getRedisService().hset(id.getBytes(), _field, _value);
	}

	@Override
	public Object removeObject(Object field) {
		byte[] _field = SerializeUtils.serialize(field);
		logger.debug("Redis remove key [" + _field + "]");
		return getRedisService().hdel(id.getBytes(), _field);
	}

	@Override
	public String getId() {
		return id;
	}

	private IRedisService getRedisService() {
		if (redisService == null) {
			this.redisService = ApplicationContextHolder.getBean(IRedisService.class);
		}
		return redisService;
	}
	
	private byte[] getKey(){
		return id.getBytes();
	}

}
