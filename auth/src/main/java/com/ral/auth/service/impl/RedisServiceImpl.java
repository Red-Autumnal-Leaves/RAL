package com.ral.auth.service.impl;

import com.ral.service.redis.IRedisService;
import org.springframework.stereotype.Service;

@Service("redisService")
public class RedisServiceImpl extends com.ral.service.redis.impl.RedisServiceImpl implements IRedisService {

}
