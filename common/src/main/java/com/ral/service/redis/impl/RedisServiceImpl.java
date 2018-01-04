package com.ral.service.redis.impl;

import com.ral.util.codec.JSONUtils;
import com.ral.service.redis.IRedisService;
import com.ral.util.codec.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import java.text.MessageFormat;
import java.util.*;

@Service("redisService")
public class RedisServiceImpl implements IRedisService {

	private static Logger logger = Logger.getLogger(RedisServiceImpl.class);

	@Autowired
	private JedisPool jedisPool;

	private Jedis getResource() {
		return jedisPool.getResource();
	}

	private void returnResource(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	@Override
	public void expire(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.expire(key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					MessageFormat.format("Jedis exec :expire {0} by {1}, message: {2}", key, seconds, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public String scriptLoad(String script) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			String id = jedis.scriptLoad(script);
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :scriptLoad {0} , message: {1}", script, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public Object evalsha(String script) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			
			Object obj = jedis.evalsha(script);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :evalsha {0} , message: {1}", script, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public Long decr(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.decr(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :decr {0}, message: {1}", key, e.getMessage()));
		} finally {
			returnResource(jedis);
		}
		return null;
	}

	@Override
	public void del(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : del {0}, message: {1}", key, e.getMessage()));
		} finally {
			returnResource(jedis);
		}
	}

	@Override
	public Long incr(String key) {
		Long num = 0l;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			num = jedis.incr(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : incr {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return num;
	}

	@Override
	public Long incrBy(String key, Integer intval) {
		Long num = 0l;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			num = jedis.incrBy(key, intval);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					MessageFormat.format("Jedis exec : incrBy {0} - {1}, message: {2}", key, intval, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}

		return num;
	}

	@Override
	public Long decrBy(String key, Integer intval) {
		Long num = 0l;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			num = jedis.decrBy(key, intval);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					MessageFormat.format("Jedis exec : decrBy {0} - {1}, message: {2}", key, intval, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return num;
	}

	@Override
	public <T> void setList(String key, List<T> list) {
		set(key, JSONUtils.toJson(list));
	}

	@Override
	public <T> List<T> getList(final String key, Class<T> clz) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			List<T> list = JSONUtils.toList(jedis.get(key), clz);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : getList {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public void lpush(String key, Object obj) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.lpush(key, JSONUtils.toJson(obj));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : lpush {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public void lpush(String key, String string) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.lpush(key, string);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : lpush {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public void lpush(String key, String[] strings) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.lpush(key, strings);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : lpush {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public void lpushPipe(String key, String[] strings) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			Pipeline p = jedis.pipelined();
			for (String s : strings) {
				p.lpush(key, s);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : lpushPipe {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : set {0} - {1}, message: {2}", key, value, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public void setex(String key, int seconds, String value) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					MessageFormat.format("Jedis exec : setex {0} - {1}, message: {2}", key, value, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public void setex(String key, int seconds, Object obj) {
		setex(key, seconds, JSONUtils.toJson(obj));
	}

	@Override
	public <T> T getBean(String key, Class<T> clz) {
		return JSONUtils.toBean(get(key), clz);
	}

	@Override
	public void setBean(String key, Object obj) {
		set(key, JSONUtils.toJson(obj));
	}

	@Override
	public void setPipeMap(Map<String, Object> map) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			Pipeline pie = jedis.pipelined();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				pie.set(entry.getKey(), JSONUtils.toJson(entry.getValue()));
			}
			pie.sync();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : setPipeMap {0} , message: {1}", JSONUtils.toJson(map),
					e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			String value = jedis.get(key);
			if (!StringUtils.isNullOrEmpty(value)) {
				return JSONUtils.toBean(value, clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : get {0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public <T> List<T> getPipe(Class<T> clazz, String[] keys) {
		List<T> list = new ArrayList<T>();
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			Pipeline pie = jedis.pipelined();
			List<Response<String>> values = new ArrayList<Response<String>>();
			for (String key : keys) {
				values.add(pie.get(key));
			}
			pie.sync();
			for (Response<String> rString : values) {
				String value = rString.get();
				if (!StringUtils.isNullOrEmpty(value)) {
					list.add(JSONUtils.toBean(value, clazz));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : getPipe {0} , message: {1}", JSONUtils.toJson(keys),
					e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return list;
	}

	public <T> Map<String, T> getPipe(final String[] keys, Class<T> clazz) {
		Map<String, T> map = new HashMap<>();
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			Pipeline pie = jedis.pipelined();
			Map<String, Response<String>> values = new HashMap<>();
			for (String key : keys) {
				values.put(key, pie.get(key));
			}
			pie.sync();
			for (Map.Entry<String, Response<String>> entry : values.entrySet()) {
				String json = entry.getValue().get();
				if (!StringUtils.isNullOrEmpty(json)) {
					map.put(entry.getKey(), JSONUtils.toBean(json, clazz));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : getPipe {0} , message: {1}", JSONUtils.toJson(keys),
					e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return map;
	}

	@Override
	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : get {0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public <T> List<T> mget(Class<T> clazz, String... keys) {
		List<T> list = new ArrayList<T>();
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			List<String> keylist = jedis.mget(keys);
			if (keylist != null && !keylist.isEmpty()) {
				for (String string : keylist) {
					if (string != null) {
						list.add(JSONUtils.toBean(string, clazz));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : mget {0} , message: {1}", keys, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return list;
	}

	@Override
	public boolean exist(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : exist {0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	@Override
	public void rpush(String key, String[] string) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.rpush(key, string);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : rpush {0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public void rpush(String key, String string) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.rpush(key, string);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : rpush {0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public Long llen(String key) {
		Long len = 0l;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			len = jedis.llen(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : llen {0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return len;
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		List<String> list = null;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			list = jedis.lrange(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : lrange {0} - {1} to {2}, message: {3}", key, start, end,
					e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return list;
	}

	@Override
	public void ltrim(String key, long start, long end) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.ltrim(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : lrange {0} - {1} to {2}, message: {3}", key, start, end,
					e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public String lpop(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.lpop(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : lpop {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public <T> T lpop(String key, Class<T> clz) {
		String json = lpop(key);
		if (!StringUtils.isNullOrEmpty(json)) {
			return JSONUtils.toBean(json, clz);
		}
		return null;
	}

	@Override
	public String rpop(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.rpop(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : rpop {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public <T> T rpop(String key, Class<T> clz) {
		String json = rpop(key);
		if (!StringUtils.isNullOrEmpty(json)) {
			return JSONUtils.toBean(json, clz);
		}
		return null;
	}

	@Override
	public Long hset(String key, String field, Object value) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hset(key, field, JSONUtils.toJson(value));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : hset {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}

	@Override
	public <T> T hget(String key, String field, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			String result = jedis.hget(key, field);
			if (!StringUtils.isNullOrEmpty(result)) {
				return JSONUtils.toBean(result, clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : hget {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public Long sinterstore(String dstkey, String... keys) {
		Long result = null;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			result = jedis.sinterstore(dstkey, keys);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : sinterstore {0} - {1}, message: {2}", dstkey,
					JSONUtils.toJson(keys), e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return result;
	}

	@Override
	public Long sunionstore(String dstkey, String... keys) {
		Long result = null;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			result = jedis.sunionstore(dstkey, keys);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec  : sunionstore {0} - {1}, message: {2}", dstkey,
					JSONUtils.toJson(keys), e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return result;
	}

	@Override
	public List<String> sort(String key, SortingParams sortingParams) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.sort(key, sortingParams);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :sort{0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public <T> List<T> sort(String key, SortingParams sortingParams, Class<T> clazz) {
		List<String> result = sort(key, sortingParams);
		if (result != null && !result.isEmpty()) {
			List<T> list = new ArrayList<>();
			for (String str : result) {
				if (!StringUtils.isNullOrEmpty(str)) {
					list.add(JSONUtils.toBean(str, clazz));
				}
			}
			return list;
		}
		return null;
	}

	@Override
	public void hmset(String key, Map<String, Object> hash) {
		Map<String, String> map = new HashMap<String, String>(hash.size());
		for (String hkey : hash.keySet()) {
			map.put(key, JSONUtils.toJson(hash.get(hkey)));
		}
		hmsetWithString(key, map);
	}

	@Override
	public void hmsetWithString(String key, Map<String, String> hash) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.hmset(key, hash);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :hmsetWithString{0}-{1}, message: {2}", key,
					JSONUtils.toJson(hash), e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public <T> List<T> hmget(String key, Class<T> clazz, String... fields) {
		List<String> list = hmget(key, fields);
		List<T> result = new ArrayList<T>();
		if (list != null && !list.isEmpty()) {
			for (String value : list) {
				if (!StringUtils.isNullOrEmpty(value)) {
					result.add(JSONUtils.toBean(value, clazz));
				}
			}
		}
		return result;
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hmget(key, fields);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :hmget{0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public boolean hexists(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hexists(key, field);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					MessageFormat.format("Jedis exec :hexists{0} - {1}, message: {2}", key, field, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	@Override
	public Long hdel(String key, String... fields) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hdel(key, fields);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :hdel{0} - {1}, message: {2}", key, JSONUtils.toJson(fields),
					e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}

	@Override
	public Long hlen(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hlen(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :hlen{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}

	@Override
	public Set<String> hkeys(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hkeys(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :hkeys{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public Set<String> keys(String pattern) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.keys(pattern);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :keys{0} , message: {1}", pattern, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public <T> List<T> keys(String pattern, Class<T> clazz) {
		Set<String> set = keys(pattern);
		if (set != null && !set.isEmpty()) {
			List<T> result = mget(clazz, set.toArray(new String[set.size()]));
			return result;
		}
		return null;
	}

	public List<String> hvals(final String key) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hvals(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :hvals{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public <T> List<T> hvals(String key, Class<T> clazz) {
		List<String> values = hvals(key);
		if (values != null && !values.isEmpty()) {
			List<T> result = new ArrayList<T>(values.size());
			for (String string : values) {
				result.add(JSONUtils.toBean(string, clazz));
			}
			return result;
		}
		return null;
	}

	@Override
	public <T> Map<String, T> hgetAll(String key, Class<T> clazz) {
		Map<String, String> values = hgetAll(key);
		if (values != null && !values.isEmpty()) {
			Map<String, T> result = new HashMap<String, T>(values.size());
			for (String string : values.keySet()) {
				result.put(string, JSONUtils.toBean(values.get(key), clazz));
			}
			return result;
		}
		return null;
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :hgetAll{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public long sadd(String key, String... members) {
		long result = 0l;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			result = jedis.sadd(key, members);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :sadd{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return result;
	}

	@Override
	public long sadd(String key, Object... members) {
		long result = 0l;
		for (Object obj : members) {
			result = result + sadd(JSONUtils.toJson(obj));
		}
		return result;
	}

	@Override
	public Set<String> smembers(String key) {
		Jedis jedis = null;
		Set<String> set = null;
		try {
			jedis = this.getResource();
			set = jedis.smembers(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :smembers{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return set;
	}

	@Override
	public String[] smembersToArray(String key) {
		Set<String> set = smembers(key);
		String[] result = set.toArray(new String[set.size()]);
		return result;
	}

	@Override
	public <T> Set<T> smembers(String key, Class<T> clazz) {
		Set<String> set = smembers(key);
		if (set != null && !set.isEmpty()) {
			Set<T> result = new HashSet<>();
			for (String json : set) {
				result.add(JSONUtils.toBean(json, clazz));
			}
			return result;
		}
		return null;
	}

	@Override
	public List<String> smembersPipe(String[] keys) {
		Jedis jedis = null;
		try {
			List<String> result = new ArrayList<String>();
			List<Response<Set<String>>> responses = new ArrayList<Response<Set<String>>>(keys.length);

			jedis = this.getResource();
			Pipeline pie = jedis.pipelined();
			for (String key : keys) {
				responses.add(pie.smembers(key));
			}
			pie.sync();
			for (Response<Set<String>> resp : responses) {
				Set<String> rspSet = resp.get();
				Collections.addAll(result, rspSet.toArray(new String[rspSet.size()]));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :smembersPipe{0} , message: {1}", JSONUtils.toJson(keys),
					e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public long srem(String key, String... members) {
		long result = 0l;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			result = jedis.srem(key, members);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :srem{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return result;
	}

	@Override
	public long scard(String key) {
		long result = 0l;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			result = jedis.scard(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :srem{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return result;
	}

	@Override
	public boolean sismember(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.sismember(key, member);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :sismember{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}

	@Override
	public boolean zadd(String key, double score, String member) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.zadd(key, score, member);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :zadd{0} , message: {1}", key, e.getMessage()));
			result = false;
		} finally {
			this.returnResource(jedis);
		}
		return result;
	}

	@Override
	public boolean zadd(String key, double score, Object obj) {
		return zadd(key, score, JSONUtils.toJson(obj));
	}

	@Override
	public double zscore(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.zscore(key, member);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :zscore{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0;
	}

	@Override
	public Long zrank(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.zrank(key, member);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :zrank{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}

	@Override
	public Long zrevrank(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.zrevrank(key, member);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :zrevrank{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}

	@Override
	public Set<String> zrangeByScore(String key, double start, double end) {
		Set<String> set = null;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			set = jedis.zrangeByScore(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :zrangeByScore{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return set;
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double start, double end) {
		Set<String> set = null;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			set = jedis.zrevrangeByScore(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :zrevrangeByScore{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return set;
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
		Set<Tuple> tuples = null;
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			tuples = jedis.zrevrangeWithScores(key, start, end);
			return tuples;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					MessageFormat.format("Jedis exec :zrevrangeWithScores{0} , message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return tuples;
	}

	@Override
	public boolean flushall() {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.flushAll();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :flushall, message: {1}", e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}
	
	@Override
	public boolean flushDb() {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.flushDB();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :flushall, message: {1}", e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return false;
	}


	@Override
	public long zcard(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.zcard(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :zcard{0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}

	@Override
	public long zrem(String key, String member) {
		Jedis jedis = null;
        try{
        	jedis = this.getResource();
        	return jedis.zcard(key);
        }catch(Exception e){
        	e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :key{0}, message: {1}", key, e.getMessage()));
        }finally{
        	this.returnResource(jedis);
        }
		return 0l;
	}

	@Override
	public <K, V> Long putObjectToMap(String key, K k, V v) {
		Jedis jedis = null;
    	Long result = null;
        try{
        	jedis = this.getResource();
	    	if(v instanceof String){
	    		return jedis.hset(key, k.toString(), (String)v);
	    	}
	        String json = JSONUtils.toJson(v);
	        result = jedis.hset(key, k.toString(), json);        
        }catch(Exception e){
        	e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :key{0}, message: {1}", key, e.getMessage()));
        }finally{
        	this.returnResource(jedis);
        }   
        return result;
	}

	@Override
	public <T> T getObjectFromMapById(String key, String id, Class<T> clazz) {
		Jedis jedis = null;
        try{
        	jedis = this.getResource();
	        String json = jedis.hget(key, id);
	        T object = JSONUtils.toBean(json, clazz);
	        return object;
        }catch(Exception e){
        	e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec :getObjectFromMapById{0}, message: {1}", key, e.getMessage()));
        }finally{
        	this.returnResource(jedis);
        }
        return null;
	}

	@Override
	public Long hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hset(key, field, value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : hset {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hget(key, field);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : hget {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public long incrHash(String key, String filed, int inter) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hincrBy(key, filed, Long.valueOf(inter));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : hget {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0;
	}

	@Override
	public void set(byte[] key, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : hget {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public byte[] get(byte[] key) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : hget {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public long expire(byte[] key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : hget {0}, message: {1}", key, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0;
	}

	@Override
	public Long dbSize() {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.dbSize();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : dbSize ", e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}

	@Override
	public Long hset(byte[] key, byte[] field, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hset(key, field, value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis hset ", e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}

	@Override
	public byte[] hget(byte[] key, byte[] field) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hget(key, field);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedishget", e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public Long hdel(byte[] key, byte[] field) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hdel(key, field);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis hdel ", e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}

	@Override
	public void del(byte[] key) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis del ", e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public Set<byte[]> hkeys(byte[] key) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hkeys(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis del ", e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return null;
	}

	@Override
	public <T> void setList(String key, List<T> list, int seconds) {
		this.setList(key, list);
		this.expire(key, seconds);
	}

	@Override
	public Long hsetex(String key, String field, Object value, int seconds) {
		long result = this.hset(key, field, value);
		this.expire(key, seconds);
		return result;
	}

	@Override
	public Long hset(byte[] key, byte[] field, byte[] value, int seconds) {
		long result = this.hset(key, field, value);
		this.expire(key, seconds);
		return result;
	}

	@Override
	public Long hset(String key, String field, String value, int seconds) {
		long result = this.hset(key, field, value);
		this.expire(key, seconds);
		return result;
	}

	@Override
	public long setnx(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.setnx(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis setnx ", e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}

	@Override
	public Long hsetnx(String key, String field, Object value) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.hsetnx(key, field, value + "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis hsetnx ", e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}

	@Override
	public void setex(String key, String value, int seconds) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					MessageFormat.format("Jedis exec : setex {0} - {1}, message: {2}", key, value, e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public Long hsetnxs(String key, Map<String, Object> map) {
		Jedis jedis = null;
		try {
			long count = 0;
			jedis = this.getResource();
			for(String field : map.keySet()){
				jedis.hset(key, field, map.get(field).toString());
				count ++;
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis hsetnxs : key:{0} , map:{1} ,error:{2}",key,JSONUtils.toJson(map), e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}


	@Override
	public Long incyby(String key) {
		Jedis jedis = null;
		try {
			jedis = this.getResource();
			return jedis.incr(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Jedis exec : incyby key:{0} message: {1}", key,e.getMessage()));
		} finally {
			this.returnResource(jedis);
		}
		return 0l;
	}


}
