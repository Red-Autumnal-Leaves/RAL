package com.ral.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by victor on 2018/1/5.
 */
@SpringBootApplication(scanBasePackages = "com.ral")
public class TaskApplication {

    public static void main(String[] args){
        SpringApplication application = new SpringApplication(TaskApplication.class);
        application.run(args);
    }

}
