package com.ral.ons.config;

import java.util.HashMap;
import java.util.Map;

import com.ral.constants.queue.MQConstant;
import com.ral.model.enums.queue.MessageQueueEnum;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author victor
 * @desc 队列配置
 */
@Configuration
public class QueueConfiguration {
	
	//信道配置
	@Bean
	public DirectExchange defaultExchange() {
		return new DirectExchange(MQConstant.DEFAULT_EXCHANGE, true, false);
	}

	//死信转发队列
	@Bean
	public Queue repeatTradeQueue() {
		return new Queue(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME,true,false,false);
	}
	
	@Bean
	public Binding  drepeatTradeBinding() {
		return BindingBuilder.bind(repeatTradeQueue()).to(defaultExchange()).with(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
	}

	//死信队列
	@Bean
	public Queue deadLetterQueue() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange", MQConstant.DEFAULT_EXCHANGE);
		arguments.put("x-dead-letter-routing-key", MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
		return new Queue(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME,true,false,false,arguments);
	}

	@Bean
	public Binding  deadLetterBinding() {
		return BindingBuilder.bind(deadLetterQueue()).to(defaultExchange()).with(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME);
	}



	//operation 队列
	@Bean
	public Queue operationLogQueue() {
		return new Queue(MessageQueueEnum.RAL_OPERATION_LOG_QUEUE.getName(),true,false,false);
	}

	@Bean
	public Binding  operationLogQueueBinding() {
		return BindingBuilder.bind(exampleQueue()).to(defaultExchange()).with(MessageQueueEnum.RAL_OPERATION_LOG_QUEUE.getName());
	}



	//example 队列
	@Bean
	public Queue exampleQueue() {
		return new Queue(MessageQueueEnum.EXAMPLE_QUEUE.getName(),true,false,false);
	}

	@Bean
	public Binding  exampleBinding() {
		return BindingBuilder.bind(exampleQueue()).to(defaultExchange()).with(MessageQueueEnum.EXAMPLE_QUEUE.getName());
	}


}
