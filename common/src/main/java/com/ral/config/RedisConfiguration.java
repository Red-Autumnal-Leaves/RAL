package com.ral.config;

import com.ral.listener.event.KeyEventMessageListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfiguration {

	private static Logger logger = Logger.getLogger(RedisConfiguration.class);
	
	private String hostName;

	private int port;

	private String password;

	private int timeout;
	
	private int dbIndex;
	
	@Bean
	public JedisPoolConfig getRedisConfig(){
		JedisPoolConfig config = new JedisPoolConfig();
		return config;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.redis")
	public JedisPool getJedisPool(){
		JedisPoolConfig config = getRedisConfig();
		JedisPool pool = new JedisPool(config,hostName,port,timeout,password,dbIndex);
		logger.info("init JredisPool ...");
		return pool;
	}
	
    @Bean  
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getConnectionFactory(){  
        JedisConnectionFactory factory = new JedisConnectionFactory();  
        JedisPoolConfig config = getRedisConfig();  
        factory.setPoolConfig(config);  
        logger.info("JedisConnectionFactory bean init success.");  
        return factory;  
    }  
    
    @Bean
	public RedisTemplate<?, ?> getRedisTemplate(){
		RedisTemplate<?,?> template = new StringRedisTemplate(getConnectionFactory());
		return template;
	}
    
    @Autowired
    private KeyEventMessageListener keyEventMessageListener;//key event 订阅监听
    
    @Bean
    public RedisMessageListenerContainer getRedisMessageListenerContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(getConnectionFactory());
        Topic topic = new ChannelTopic("__keyevent@"+ getDbIndex() +"__:expired");
        System.out.println("Redis db index : " + getDbIndex());
        container.addMessageListener(keyEventMessageListener,topic);
        return container;
    }
    

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}
	
}
