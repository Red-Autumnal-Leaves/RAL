package com.ral.service.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

public interface IRedisService {

	/**
	 * 
	 * @param key
	 * @param seconds
	 * @desc 给指定Key设置时间
	 */
	void expire(final String key, final int seconds);
	
	/**
	 * 
	 * @param key
	 * @param seconds
	 * @desc 给指定Key设置时间
	 */
	long expire(byte[] key, int seconds);

	/**
	 * 
	 * @param script
	 * @return
	 * @desc 编译脚本,返回脚本Id
	 */
	String scriptLoad(final String script);

	/**
	 * 
	 * @param script
	 * @return
	 * @desc 执行脚本
	 */
	Object evalsha(final String script);
	
	/**
	 * 
	 * @param key
	 * @return 操作之后的新值
	 * @desc 将 key 中储存的数字值增一,如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作
	 */
	Long incr(String key);
	
	/**
	 * 
	 * @param key
	 * @param intval
	 * @return 操作之后的新值
	 * @desc 将 key 中储存的数字加上指定的增量值,如果 key不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
	 */
	Long incrBy(String key, Integer intval);

	/**
	 * 
	 * @param key
	 * @return 操作之后的新值
	 * @desc 将 key 中储存的数字值减一,如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 decr 操作
	 */
	Long decr(String key);

	/**
	 * 
	 * @param key
	 * @param intval
	 * @return 操作之后的新值
	 * @desc 将 key 中储存的数字加上指定的减量,如果 key不存在，那么 key 的值会先被初始化为 0 ，然后再执行 decrBy 命令。
	 */
	Long decrBy(String key, Integer intval);

	/**
	 * 
	 * @param key
	 * @param list
	 * @desc 插入List集合
	 */
	<T> void setList(final String key, final List<T> list);
	
	
	/**
	 * 
	 * @param key
	 * @param list
	 * @desc 插入List集合
	 */
	<T> void setList(final String key, final List<T> list, final int seconds);

	/**
	 * 
	 * @param key
	 * @param clz
	 * @return
	 * @desc 获取存放的List集合，JSON转换为List
	 */
	<T> List<T> getList(final String key, Class<T> clz);

	/**
	 * 
	 * @param key
	 * @param obj 对象
	 * @desc 将对象json序列化之后存放于队列中
	 */
	void lpush(final String key, final Object obj);

	/**
	 * 
	 * @param key
	 * @param string
	 * @desc 将字符串存放在队列中
	 */
	void lpush(String key, String string);

	/**
	 * 
	 * @param key
	 * @param strings 字符串数组
	 * @desc 将多个字符串依次存放在队列中
	 */
	void lpush(String key, String[] strings);

	/**
	 * 
	 * @param key
	 * @param strings 字符串数组
	 * @desc 使用 管道方式 批量存放在队列之中。
	 */
	void lpushPipe(String key, String[] strings);

	/**
	 * 
	 * @param key
	 * @param value
	 * @desc 将指定值存放指定key 
	 */
	void set(final String key, final String value);
	
	
	/**
	 * set 原生方法
	 * @param key
	 * @param value
	 */
	void set(byte[] key, byte[] value);
	

	/**
	 * 
	 * @param key 
	 * @param seconds
	 * @param value
	 * @desc 将指定值存放指定key，并且指定失效时间
	 */
	void setex(final String key, final int seconds, final String value);
	
	

	/**
	 * 
	 * @param key
	 * @param seconds
	 * @param obj
	 * @desc 将对象json序列化,存放指定key，并且指定失效时间
	 */
	void setex(final String key, final int seconds, final Object obj);

	
	<T> T getBean(String key, Class<T> clz);

	/**
	 * 
	 * @param key
	 * @param obj
	 * @desc 将对象JSON序列化，存放于指定key
	 */
	void setBean(final String key, final Object obj);

	/**
	 * 
	 * @param map 键值对集合
	 * @desc 以管道方式批量存放 键/值 集合,键将作为key,值将JSON序列化存储
	 */
	void setPipeMap(final Map<String, Object> map);
	
	/**
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 * @desc 获取指定key的值,使用JSON反序列化为对象
	 */
	<T> T get(final String key, Class<T> clazz);

	/**
	 * 
	 * @param clazz
	 * @param keys
	 * @return
	 * @desc 以管道方式获取一组value,并Json反序列化为List,如果指定key为空，将会被忽略
	 */
	<T> List<T> getPipe(Class<T> clazz, final String[] keys);
	
	/**
	 * 
	 * @param clazz
	 * @param keys
	 * @return
	 * @desc 以管道方式获取一组value,并Json反序列化为Map,如果指定key为空，将会被忽略
	 */
	<T> Map<String,T> getPipe(final String[] keys, Class<T> clazz);

	/**
	 * 
	 * @param key
	 * @return
	 * @desc 获取指定Key值
	 */
	String get(final String key);
	
	
	byte[] get(byte[] key);

	/**
	 * 
	 * @param clazz
	 * @param keys
	 * @return 泛型集合
	 * @desc 通过合并key方式一次获取多个同一类型对象JSON字符串，转换为List, mget实现
	 */
	<T> List<T> mget(Class<T> clazz, String... keys);

	/**
	 * 
	 * @param key
	 * @desc 删除指定key
	 */
	void del(final String key);
	
	void del(byte[] key);

	/**
	 * 
	 * @param key
	 * @return
	 * @desc 判断指定key是否存在
	 */
	boolean exist(final String key);

	/**
	 * 
	 * @param key
	 * @param string 数组
	 * @desc 将多个值插入到列表的尾部(最右边),如果列表不存在，一个空列表会被创建并执行 RPUSH 操作。 当列表存在但不是列表类型时，出现错误
	 */
	void rpush(final String key, final String[] string);

	/**
	 * 
	 * @param key
	 * @param string
	 * @desc 将单个值插入到列表的尾部(最右边),如果列表不存在，一个空列表会被创建并执行 RPUSH 操作。 当列表存在但不是列表类型时，出现错误
	 */
	void rpush(final String key, final String string);

	/**
	 * 
	 * @param key
	 * @return
	 * @desc 将返回存储在key列表的长度。如果key不存在，它被解释为一个空列表，则返回0。当存储在关key的值不是一个列表，则会返回错误。
	 */
	Long llen(final String key);
	
	/**
	 * 
	 * @param key
	 * @param start 起始
	 * @param end 结束
	 * @return
	 * @desc 返回指定范围内元素的列表。该命令的参数start和end都是0-based,
	 * 即0表示链表头部(leftmost)的第一个元素。其中start的值也可以为负值,
	 * -1将表示链表中的最后一个元素，即尾部元素，-2表示倒数第二个并以此类推。
	 * 该命令在获取元素时，start和end位置上的元素也会被取出。
	 * 如果start的值大于链表中元素的数量，空链表将会被返回。
	 */
	List<String> lrange(final String key, final long start, final long end);

	/**
	 * 
	 * @param key
	 * @param start 起始
	 * @param end 结束
	 * @desc 该命令将仅保留指定范围内的元素，从而保证链接中的元素数量相对恒定。
	 * start和stop参数都是0-based，0表示头部元素。和其他命令一样，
	 * start和stop也可以为负值，-1表示尾部元素。如果start大于链表的尾部，
	 * 或start大于stop，该命令不错报错，而是返回一个空的链表，与此同时该Key也将被删除。
	 * 如果stop大于元素的数量，则保留从start开始剩余的所有元素
	 */
	void ltrim(final String key, final long start, final long end);

	/**
	 * 
	 * @param key
	 * @return
	 * @desc  返回并弹出指定Key关联的链表中的第一个元素，即头部元素。如果该Key不存，返回null。
	 */
	String lpop(final String key);

	/**
	 * 
	 * @param key
	 * @param clz
	 * @return 
	 * @desc 返回并弹出指定Key关联的链表中的第一个元素，即头部元素,通过JSON序列化为Bean对象,如果该Key不存，返回null。
	 */
	<T> T lpop(final String key, Class<T> clz);

	/**
	 * 
	 * @param key
	 * @return
	 * @desc 返回并弹出指定Key关联的链表中的最后一个元素，即尾部元素,如果该Key不存，返回null。
	 */
	String rpop(final String key);

	/**
	 * 
	 * @param key
	 * @return
	 * @desc 返回并弹出指定Key关联的链表中的最后一个元素，即尾部元素,JSON序列化Bean,如果该Key不存，返回null。
	 */
	<T> T rpop(final String key, Class<T> clazz);

	/**
	 * 
	 * @param key 
	 * @param field 属性
	 * @param value 值
	 * @return 如果字段是哈希表中的一个新建字段，并且值设置成功，返回 1 。 如果哈希表中域字段已经存在且旧值已被新值覆盖，返回 0 。
	 * @desc 为哈希表中的字段赋值,值将以JSON序列化字符串 ,如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。如果字段已经存在于哈希表中，旧值将被覆盖,。
	 */
	Long hset(final String key, final String field, final Object value);
	
	
	Long hsetnx(final String key, final String field, final Object value);
	
	/**
	 * 为哈希表中的字段赋值,值将以JSON序列化字符串 ,如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。如果字段已经存在于哈希表中，旧值将被覆盖,。
	 * @param key
	 * @param field
	 * @param value
	 * @param seconds 有效期
	 * @return 如果字段是哈希表中的一个新建字段，并且值设置成功，返回 1 。 如果哈希表中域字段已经存在且旧值已被新值覆盖，返回 0 。
	 */
	Long hsetex(final String key, final String field, final Object value, final int seconds);
	
	
	/**
	 * 原生方法
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	Long hset(byte[] key, byte[] field, byte[] value);
	
	/**
	 * 原生方法
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	Long hset(byte[] key, byte[] field, byte[] value, final int seconds);
	
	/**
	 * 原生方法
	 * @param key
	 * @param field
	 * @return
	 */
	byte[] hget(byte[] key, byte[] field);
	
	/**
	 * 原生方法
	 * @param key
	 * @param field
	 * @return
	 */
	Long hdel(byte[] key, byte[] field);
	
	
	/**
	 * 
	 * @param key 
	 * @param field 属性
	 * @param value 值
	 * @return 如果字段是哈希表中的一个新建字段，并且值设置成功，返回 1 。 如果哈希表中域字段已经存在且旧值已被新值覆盖，返回 0 。
	 * @desc 为哈希表中的字段赋值,如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。如果字段已经存在于哈希表中，旧值将被覆盖,。
	 */
	Long hset(final String key, final String field, final String value);
	
	/**
	 * 为哈希表中的字段赋值,值将以JSON序列化字符串 ,如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。如果字段已经存在于哈希表中，旧值将被覆盖,。
	 * @param key
	 * @param field
	 * @param value
	 * @param seconds 有效期
	 * @return 如果字段是哈希表中的一个新建字段，并且值设置成功，返回 1 。 如果哈希表中域字段已经存在且旧值已被新值覆盖，返回 0 。
	 */
	Long hset(final String key, final String field, final String value, final int seconds);

	/**
	 * 
	 * @param key
	 * @param field
	 * @param clazz
	 * @return
	 * @desc 返回哈希表中指定字段的值，并进行JSON反序列化,如果给定的字段或 key 不存在时，返回 null 。
	 */
	<T> T hget(final String key, final String field, Class<T> clazz);
	
	
	/**
	 * 
	 * @param key
	 * @param field
	 * @return
	 * @desc 返回哈希表中指定字段的原声值,特殊值自行处理,如果给定的字段或 key 不存在时，返回 null 。
	 */
	String hget(final String key, final String field);

	/**
	 * 
	 * @param dstkey 结果存储集合key
	 * @param keys 给定集合key,需要取交集的集合key
	 * @return 返回交集数量
	 * @desc  命令将给定集合之间的交集存储在指定的集合中。如果指定的集合已经存在，则将其覆盖
	 */
	Long sinterstore(String dstkey, String... keys);

	/**
	 * 
	 * @param dstkey 结果存储集合key
	 * @param keys 给定集合key,需要取交集的集合key
	 * @return 返回并集数量
	 * @desc 命令将给定集合的并集存储在指定的集合 中。如果指定key已经存在，则将其覆盖
	 */
	Long sunionstore(String dstkey, String... keys);

	/**
	 * 
	 * @param key 指定集合
	 * @param sortingParams 排序参数
	 * @return 返回排序结果集
	 */
	List<String> sort(final String key, final SortingParams sortingParams);

	/**
	 * 
	 * @param key 指定集合
	 * @param sortingParams 排序参数
	 * @return 返回排序结果集,将结果集元素JSON序列化转换为泛型对象，过滤空对象
	 */
	<T> List<T> sort(final String key, final SortingParams sortingParams, Class<T> clazz);

	/**
	 * 
	 * @param key
	 * @param hash 对象Map集合，存储时对象将会被JSON序列化
	 * @desc 逐对依次设置参数中给出的Field/Value对。如果其中某个Field已经存在，则用新值覆盖原有值,如果Key不存在，则创建新Key，同时设定参数中的Field/Value。
	 */
	void hmset(final String key, final Map<String, Object> hash);
	
	/**
	 * 
	 * @param key
	 * @param hash 字符串Map集合，以原生字符串存储
	 * @desc 逐对依次设置参数中给出的Field/Value对。如果其中某个Field已经存在，则用新值覆盖原有值,如果Key不存在，则创建新Key，同时设定参数中的Field/Value。
	 */
	void hmsetWithString(String key, Map<String, String> hash);

	/**
	 * 
	 * @param key 指定集合Ksy
	 * @param clazz JSON序列化类型
	 * @param fields 属性Key
	 * @return 返回属性key值JSON序列化对象集合
	 * @desc 获取指定集合中的指定属性，并逐个将值转换为泛型集合
	 */
	<T> List<T> hmget(final String key, Class<T> clazz, final String... fields);

	/**
	 * 
	 * @param key 指定集合Key
	 * @param fields 属性Key
	 * @return 属性值数组
	 * @desc 获取指定集合中指定属性数据
	 */
	List<String> hmget(String key, String... fields);

	/**
	 * 
	 * @param key hash表key
	 * @param field 属性key
	 * @return 
	 * @desc 检查哈希字段是否存在
	 */
	boolean hexists(final String key, final String field);

	/**
	 * 
	 * @param key 指定Hash集合key
	 * @param fields 需要被删除的属性
	 * @return 被删除的数量
	 * @desc 从存储在键散列删除指定的字段。如果没有这个哈希中存在指定的字段将被忽略
	 */
	Long hdel(final String key, final String... fields);

	/**
	 * 
	 * @param key 指定Hash集合key
	 * @return 属性数量
	 */
	Long hlen(final String key);

	/**
	 * 
	 * @param key 指定Hash集合key
	 * @return
	 * @desc 获取指定Hash集合所有字段名,如果key不存在返回Null
	 */
	Set<String> hkeys(final String key);
	
	Set<byte[]> hkeys(byte[] key);

	/**
	 * 
	 * @param pattern 通配符
	 * @return
	 * @desc 根据通配符搜索redis中key,返回key集合
	 */
	Set<String> keys(final String pattern);

	/**
	 * 
	 * @param pattern 通配符
	 * @return
	 * @desc 根据通配符搜索redis中key,返回key集合,取出所有key的值，并且转换为JSON序列化转换为泛型集合
	 */
	<T> List<T> keys(final String pattern, Class<T> clazz);

	
	/**
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 * @desc 获取在存储于 key的散列的所有值,并将值json序列化为泛型集合
	 */
	<T> List<T> hvals(final String key, Class<T> clazz);
	
	/**
	 * 
	 * @param key
	 * @return
	 * @desc 获取在存储于 key的散列的所有值,空列表将返回null
	 */
	List<String> hvals(final String key);
	
	
	/**
	 * 
	 * @param key 指定Hash集合key
	 * @return
	 * @desc 获取指定的哈希集中所有的字段和值(JSON序列化对象),key不存在返回null,
	 */
	<T> Map<String, T> hgetAll(final String key, Class<T> clazz);

	/**
	 * 
	 * @param key 指定Hash集合key
	 * @return
	 * @desc 获取指定的哈希集中所有的字段和值,key不存在返回null
	 */
	Map<String, String> hgetAll(String key);
	
	
	/**
	 * 
	 * @param key 指定集合Key
	 * @param members 成员对象
	 * @return 返回生效个数
	 * @desc 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略,当 key 不是集合类型时，返回一个错误
	 */
	long sadd(final String key, final String... members);

	/**
	 * 
	 * @param key 指定集合Key
	 * @param members  成员对象
	 * @return 返回生效个数
	 * @desc 使用循环遍历单个插入将一个或多个 member 元素JSON序列化之后加入到集合 key 当中，已经存在于集合的 member 元素将被忽略,当 key 不是集合类型时，返回一个错误。
	 */
	long sadd(final String key, final Object... members);

	/**
	 * 
	 * @param key 指定集合Key
	 * @return
	 * @desc 获取集合中的所有的成员。 不存在的集合 key 被视为空集合
	 */
	Set<String> smembers(final String key);

	/**
	 * 
	 * @param key 指定集合Key
	 * @return
	 * @desc 获取集合中的所有的成员。 不存在的集合 key 被视为空集合
	 */
	String[] smembersToArray(String key);

	/**
	 * 
	 * @param key 指定集合Key
	 * @return
	 * @desc 获取集合中的所有的成员并JSON序列化为Java对象。 不存在的集合 key 被视为空集合
	 */
	<T> Set<T> smembers(final String key, Class<T> clazz);

	/**
	 * 
	 * @param keys 指定集合Key
	 * @return
	 * @desc 批量获取集合中的所有的成员。 不存在的集合 key 被视为空集合
	 */
	List<String> smembersPipe(final String[] keys);

	/**
	 * 
	 * @param key  指定集合Key
	 * @param members 成员
	 * @return 返回被移除的个数，不存在的被忽略
	 * @desc 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略
	 */
	long srem(final String key, final String... members);

	/**
	 * 
	 * @param key 指定集合Key
	 * @return
	 * @desc 获取存储在集合中的元素的数量
	 */
	long scard(final String key);

	/**
	 * 
	 * @param key 指定集合Key
	 * @param member 成员对象字符串
	 * @return 判断指定成员是否存在于指定Set集合
	 */
	boolean sismember(final String key, final String member);

	/**
	 * 
	 * @param key 指定集合Key
	 * @param score 分值
	 * @param member 成员
	 * @return 返回是否执行成功。
	 * @desc  添加时参数中的某一成员已经存在，该命令将更新此成员的分数为新值，
	 * 同时再将该成员基于新值重新排序。如果键不存在，该命令将为该键创建一个新的
	 * Sorted-Sets Value，并将score/member对插入其中。如果该键已经存在，
	 * 但是与其关联的Value不是Sorted-Sets类型，相关的错误信息将被返回。
	 */
	boolean zadd(final String key, final double score, final String member);

	/**
	 * 
	 * @param key 指定集合Key
	 * @param score 分值
	 * @param obj 此对象将以JSON序列化之后存储
	 * @return 返回是否执行成功。
	 * @desc  添加时参数中的某一成员已经存在，该命令将更新此成员的分数为新值，
	 * 同时再将该成员基于新值重新排序。如果键不存在，该命令将为该键创建一个新的
	 * Sorted-Sets Value，并将score/member对插入其中。如果该键已经存在，
	 * 但是与其关联的Value不是Sorted-Sets类型，相关的错误信息将被返回。
	 */
	boolean zadd(final String key, final double score, final Object obj);

	/**
	 * 
	 * @param key
	 * @param member
	 * @return 返回字符串 - 分数(双精度浮点数)，表示为字符串。
	 * @decs 成员的有序集合在键比分。如果成员没有在排序集合存在，或键不存在，则返回0。
	 */
	double zscore(final String key, final String member);

	/**
	 * 
	 * @param key
	 * @param member
	 * @return 如果成员在有序集合存在，返回整数：成员的权重
	 * @desc 返回成员的有序集合保存在key，由低到高的分数顺序排名。(或索引)是基于0，这意味着，具有最低得分的构件具有等级0。
	 */
	Long zrank(final String key, final String member);

	/**
	 * 
	 * @param key
	 * @param member
	 * @return 如果member是有序集key的成员，返回integer-reply:member的排名。如果member不是有序集key的成员，返回bulk-string-reply: 0。
	 * @desc 返回有序集key中成员member的排名，其中有序集成员按score值从大到小排列。排名以0为底，也就是说，score值最大的成员排名为0。使用ZRANK命令可以获得成员按score值递增(从小到大)排列的排名
	 */
	Long zrevrank(final String key, final String member);

	/**
	
	 * 
	 * @param key	
	 * @param start 起始score
	 * @param end	截至score
	 * @return
	 * @desc  该命令将返回分数在min和max之间的所有成员，即满足表达式
	 * min <= score <= max的成员，其中返回的成员是按照其分数从低到高的顺序返回，
	 * 如果成员具有相同的分数，则按成员的字典顺序返回。
	 */
	Set<String> zrangeByScore(final String key, final double start, final double end);

	/**
	 * 
	 * @param key
	 * @param start 起始score
	 * @param end	截至score
	 * @return
	 * @desc 有序集中指定分数区间内的所有的成员。有序集成员按分数值递减(从大到小)的次序排列。具有相同分数值的成员按字典序的逆序(reverse lexicographical order )排列。
	 */
	Set<String> zrevrangeByScore(final String key, final double start, final double end);

	/**
	 * 
	 * @param key
	 * @param start 起始score
	 * @param end	截至score
	 * @return 有序集中指定分数区间内的所有的成员。有序集成员按分数值递减(从大到小)的次序排列。具有相同分数值的成员按字典序的逆序(reverse lexicographical order )排列。
	 */
	Set<Tuple> zrevrangeWithScores(final String key, final long start, final long end);

	/**
	 * 
	 * @return 执行是否成功
	 * @desc FLUSHALL删除所有现有的数据库，而不仅仅是当前选择的一个的键。
	 */
	boolean flushall();
	
	/**
	 * 
	 * @return 执行是否成功
	 * @desc FLUSHALL删除所有现有的数据库，而不仅仅是当前选择的一个的键。
	 */
	boolean flushDb();

	/**
	 * 
	 * @param key
	 * @return 整数，有序集合的基数(元素的数量)，或者如果键不存在则返回0
	 * @desc 获取在指定的键存储在集合中的元素的数量。
	 */
	long zcard(final String key);

	/**
	 * 
	 * @param key
	 * @param member
	 * @return 返回移除数量
	 * @desc 移除有序集中的一个或多个成员，不存在的成员将被忽略。当 key 存在但不是有序集类型时，返回一个错误。
	 */
	long zrem(final String key, final String member);

	/**
	 * 
	 * @param key
	 * @param k key
	 * @param v value 将被系列化JSON存储
	 * @return
	 * @desc 将一个对象存到redis的一个map中
	 */
	<K, V> Long putObjectToMap(String key, K k, V v);

	  /**
    
     * @param key redis中的map的名字
     * @param id 对象唯一id
     * @param clazz 对象对应的类
     * @return
     * 根据一个id列表从redis的map中获取一个对象
     */
	<T> T getObjectFromMapById(String key, String id, Class<T> clazz);
	
	
	/**
	 * 递增hash字段
	 * @param key
	 * @param filed
	 * @param inter
	 * @return
	 */
	long incrHash(String key, String filed, int inter);

	/**
	 * 如果存在则set, 返回0或者1
	 * @param key
	 * @param value
	 * @return
	 */
	long setnx(final String key, final String value);

	/**
	 * 当前db key 数量
	 * @return
	 */
	Long dbSize();


	/**
	 *
	 * @param key
	 * @param value
	 * @param seconds
	 * @desc 将指定值存放指定key，并且指定失效时间
	 */
	void setex(final String key, final String value, final int seconds);

	/**
	 * hash 设置
	 * @param key
	 * @param map
	 * @return
	 */
	 Long hsetnxs(final String key, Map<String, Object> map);

	/**
	 * 自增
	 * @param key
	 * @return
	 */
	Long incyby(String key);
}
