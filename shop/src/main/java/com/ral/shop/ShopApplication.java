package com.ral.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by victor on 2018/1/4.
 */
@SpringBootApplication(scanBasePackages = "com.ral")
public class ShopApplication {

    public static void main(String[] args){
        SpringApplication application = new SpringApplication(ShopApplication.class);
        application.run(args);
    }

}
