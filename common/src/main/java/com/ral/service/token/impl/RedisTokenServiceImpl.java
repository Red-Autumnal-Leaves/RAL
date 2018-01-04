package com.ral.service.token.impl;

import com.ral.constants.redis.RedisKeyConstants;
import com.ral.util.codec.UUIDUtils;
import com.ral.constants.RalConstants;
import com.ral.model.domain.AccessToken;
import com.ral.service.redis.IRedisService;
import com.ral.service.token.IRedisTokenService;
import com.ral.util.codec.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author victor
 * @desc token 服务类实现
 * <br/>1.实现方式:基于redsi存储，适应分布式
 * <br/>2.存储方式:每个将tokenId作为key,必须保证唯一,校验也通过token校验,与token属性无关,token属性为额外信息，可以为Null
 */
@Service
public class RedisTokenServiceImpl implements IRedisTokenService {
	
	private static Logger logger = Logger.getLogger(RedisTokenServiceImpl.class);

	@Autowired
	private IRedisService redisService;
	
	@Override
	public int getExpire() {
		return RalConstants.DEFAULT_TOKEN_EXPIRE;
	}
	
	@Override
	public String getKey() {
		return RedisKeyConstants.DEFAULT_TOKEN_KEY;
	}
	
	private String getKey(String tokenId){
		String key = getKey() + tokenId;
		return key;
	}

	@Override
	public AccessToken checkToken(String tokenId) {
		String value = redisService.get(getKey(tokenId));
		if(value != null){
			return new AccessToken(tokenId,value);
		}
		return null;
	}

	@Override
	public AccessToken checkAndRefreshToken(String tokenId, int seconds) {
		AccessToken token = checkToken(tokenId);
		if(token != null){
			redisService.expire(getKey(tokenId), seconds);
		}
		return token;
	}
	
	@Override
	public AccessToken createToken() {
		return createToken(getExpire());
	}

	@Override
	public AccessToken createToken(int seconds) {
		String tokenId = UUIDUtils.uuid();
		AccessToken token = new AccessToken(tokenId, "");
		return createToken(token,seconds) ? token : null;
	}

	@Override
	public boolean createToken(AccessToken token) {
		return createToken(token,getExpire());
	}

	@Override
	public boolean createToken(AccessToken token, int seconds) {
		if(token == null || StringUtils.isNullOrEmpty(token.getTokenId())){
			logger.error("Token's tokenId not null or empty!");
			return false;
		}
		if(redisService.exist(getKey(token.getTokenId()))){
			logger.error("Token's tokenId already exist!");
			return false;
		}
		String key = getKey(token.getTokenId());
		redisService.setex(key, seconds, token.getToken());
		logger.info("Create new token ,tokenId:!" + token.getTokenId());
		return true;
	}

	@Override
	public void removeToken(String tokenId) {
		redisService.del(getKey(tokenId));
	}

	@Override
	public void refresh(String tokenId, int seconds) {
		redisService.expire(getKey(tokenId), seconds);
	}

}
