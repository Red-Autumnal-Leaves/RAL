package com.ral.config;

import com.ral.service.file.impl.OssFileStoreServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author victor
 * @desc
 * @date 2018/1/23 21:55
 */
@Configuration
@PropertySource("classpath:aliyun-oss.properties")
@ConfigurationProperties(prefix = "aliyun.oss")
@ConditionalOnClass(OssFileStoreServiceImpl.class)
public class MongoConfiguration {
}
