package com.ral.ons.processor.example;

import com.ral.constants.queue.MQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by victor on 2018/1/5.
 */
@Component
@RabbitListener(queues = MQConstant.EXAMPLE_QUEUE_NAME)
public class ExampleProcessor {

    @RabbitHandler
    public void process(String content) {
        System.out.println("example processor :" + content);
    }
}
