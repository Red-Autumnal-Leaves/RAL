package com.ral.ons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author victor
 * @desc
 * @date 2018/1/5 0:30
 */
@SpringBootApplication(scanBasePackages = "com.ral")
public class OnsApplication {
    public static void main(String[] args){
        SpringApplication application = new SpringApplication(OnsApplication.class);
        application.run(args);
    }
}
