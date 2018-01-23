package com.ral.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by victor on 2018/1/23.
 */
@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AuthApplication.class);
        application.run(args);
    }
}
