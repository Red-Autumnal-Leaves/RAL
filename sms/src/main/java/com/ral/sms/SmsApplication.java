package com.ral.sms;

import com.ral.service.mongo.IRalOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author victor
 * @desc
 * @date 2018/1/5 0:25
 */
@SpringBootApplication(scanBasePackages = "com.ral")
public class SmsApplication {

    public static void main(String[] args){
        SpringApplication application = new SpringApplication(SmsApplication.class);
        application.run(args);
    }
}
