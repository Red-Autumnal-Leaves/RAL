package com.ral.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.victor.sdk.domain.VictorClient;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:victor.properties")
@ConfigurationProperties(prefix = "victor")
public class VictorConfiguration{
	
	private String url;

	private String mchId;
	
	private String appId;
	
	private String key;
	
	@Bean
	public VictorClient client(){
		return new VictorClient(url, mchId, appId, key);
	}
	

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
