package com.ral.ons.processor.logs;

import com.ral.constants.queue.MQConstant;
import com.ral.mongo.ral.entity.logs.RalOperationLog;
import com.ral.mongo.ral.repository.logs.IRalOperationLogRepository;
import com.ral.util.codec.JSONUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by victor on 2018/1/24.
 */
@Component
@RabbitListener(queues = MQConstant.RAL_OPERATION_LOG_QUEUE_NAME)
public class RalOperationLogProcessor {

    @Autowired
    private IRalOperationLogRepository ralOperationLogRepository;

    @RabbitHandler
    public void process(String content) {
        RalOperationLog log = JSONUtils.toBean(content,RalOperationLog.class);
        ralOperationLogRepository.save(log);
    }
}
