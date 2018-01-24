package com.ral.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


/**
 * Created by victor on 2018/1/24.
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.ral.mongo")
@PropertySource("classpath:mongodb.properties")
public class MongodbConfiguration {

}
