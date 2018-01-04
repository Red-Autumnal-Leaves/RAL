package com.ral.config;

import com.ral.service.file.IFileStoreService;
import com.ral.service.file.impl.OssFileStoreServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:aliyun-oss.properties")
@ConfigurationProperties(prefix = "aliyun.oss")
@ConditionalOnClass(OssFileStoreServiceImpl.class)
public class OssConfiguration {
	
	private String endpoint;
	
	private String key;
	
	private String secret;
	
	@Bean("smsOssFileStoreService")
	public IFileStoreService getSmsFileStoreService(){
		IFileStoreService service = new OssFileStoreServiceImpl(endpoint,key,secret);
		return service;
	}
	

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
}
