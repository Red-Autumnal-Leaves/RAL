package com.ral.service.session.impl;

import com.ral.constants.redis.RedisKeyConstants;
import com.ral.util.codec.JSONUtils;
import com.ral.constants.RalConstants;
import com.ral.service.redis.IRedisService;
import com.ral.service.session.IRedisSessionService;
import com.ral.util.codec.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RedisSessionServiceImpl implements IRedisSessionService {

	@Autowired
	private IRedisService redisService;
	
	private static Logger logger = Logger.getLogger(RedisSessionServiceImpl.class);
	
	@Override
	public String getKey() {
		return RedisKeyConstants.DEFAULT_SESSION_KEY;
	}

	@Override
	public int getExpire() {
		return RalConstants.DEFAULT_SESSION_EXPIRE;
	}
	

	private String getKey(String sessionId){
		return getKey() + sessionId;
	}
	
	@Override
	public boolean isExist(String sessionId){
		return redisService.exist(getKey(sessionId));
	}
	
	public void createSession(String sessionId){
		if(!isExist(sessionId)){
			redisService.hset(getKey(sessionId), "sessionId", sessionId);
			logger.info("created session sessionId:" + sessionId);
		}
		redisService.expire(getKey(sessionId), getExpire());
	}
	
	public void createSession(String sessionId,int interval){
		if(!isExist(sessionId)){
			redisService.hset(getKey(sessionId), "sessionId", sessionId);
			logger.info("created session sessionId:" + sessionId);
		}
		redisService.expire(getKey(sessionId), interval);
	}
	
	@Override
	public void setMaxInactiveInterval(String sessionId,int interval) {
		redisService.expire(getKey(sessionId), interval);
	}

	@Override
	public <T> T getAttribute(String sessionId,String name,Class<T> clz) {
		return redisService.hget(getKey(sessionId), name, clz);
	}

	@Override
	public void setAttribute(String sessionId,String name, Object value) {
		redisService.hset(getKey(sessionId), name, value);
	}

	@Override
	public void removeAttribute(String sessionId,String name) {
		redisService.hdel(getKey(sessionId), name);
	}

	@Override
	public void invalidate(String sessionId) {
		redisService.del(getKey(sessionId));
	}

	@Override
	public <T> List<T> getAttributeAsList(String sessionId, String name, Class<T> clz) {
		String value = redisService.hget(getKey(sessionId), name);
		return StringUtils.isNullOrEmpty(value) ? null : JSONUtils.toList(value, clz);
	}

	@Override
	public Map<String, Object> getAttributeAsMap(String sessionId, String name) {
		String value = redisService.hget(getKey(sessionId), name);
		return StringUtils.isNullOrEmpty(value) ? null : JSONUtils.toMap(value);
	}

	@Override
	public String getAttribute(String sessionId,String name) {
		Map<String, String> session = redisService.hgetAll(getKey(sessionId));
		return session.get(name);
	}

}
