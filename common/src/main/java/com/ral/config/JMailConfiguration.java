package com.ral.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by victor on 2018/2/1.
 */
@Configuration
@PropertySource("classpath:jmail.properties")
public class JMailConfiguration {

}
