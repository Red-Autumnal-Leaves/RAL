package com.ral.ons.processor.system;

import com.ral.constants.queue.MQConstant;
import com.ral.model.domain.DLXMessage;
import com.ral.model.enums.queue.MessageQueueEnum;
import com.ral.service.queue.IMessageQueueService;
import com.ral.util.codec.JSONUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author victor
 * @desc 死信接收处理消费者
 */
@Component
@RabbitListener(queues = MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME)
public class RepeatTradeProcessor {
	
	@Autowired
	private IMessageQueueService messageQueueService;

	@RabbitHandler
    public void process(String content) {
		DLXMessage message = JSONUtils.toBean(content, DLXMessage.class);
		MessageQueueEnum queue = MessageQueueEnum.nameOf(message.getQueueName());
		messageQueueService.send(queue, message.getContent());
    }
}
