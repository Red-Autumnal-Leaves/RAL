package com.ral.service.queue.impl;

import com.ral.constants.queue.MQConstant;
import com.ral.model.domain.DLXMessage;
import com.ral.model.enums.queue.MessageQueueEnum;
import com.ral.service.queue.IMessageQueueService;
import com.ral.util.codec.JSONUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author victor
 * @desc 消息队列服务接口实现
 */
@Service("messageQueueService")
public class MessageQueueServiceImpl implements IMessageQueueService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void send(MessageQueueEnum queue, String msg) {
		rabbitTemplate.convertAndSend(queue.getExchange(),queue.getName(), msg);
	}

	@Override
	public void send(MessageQueueEnum queue, String msg, long times) {
		if(times <= 0){// 直接发送，无需进入死信队列
			send(queue, msg);
		}else{
			DLXMessage dlxMessage = new DLXMessage(queue,msg,times);
			MessagePostProcessor processor = new MessagePostProcessor(){
				@Override
				public Message postProcessMessage(Message message) throws AmqpException {
					message.getMessageProperties().setExpiration(times + "");
					return message;
				}
			};
			dlxMessage.setExchange(MQConstant.DEFAULT_EXCHANGE);
			rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, JSONUtils.toJson(dlxMessage), processor);
		}
	}

}
