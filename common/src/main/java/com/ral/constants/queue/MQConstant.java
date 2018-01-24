package com.ral.constants.queue;

/**
 * 
 * @author victor
 * @desc Rabbit消息队列相关常量
 * 常量对应ONS中的Queue
 */
public final class MQConstant {
	
	private MQConstant(){

	}
	
	//exchange name
	public static final String DEFAULT_EXCHANGE = "RAL.SHOP.DEFAULT.EXCHANGE";
	
	//DLX QUEUE
	public static final String DEFAULT_DEAD_LETTER_QUEUE_NAME = "ral.shop.dead.letter.queue";
	
	//DLX repeat QUEUE 死信转发队列
	public static final String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "ral.shop.repeat.trade.queue";


	//operation log  队列
	public static final String RAL_OPERATION_LOG_QUEUE_NAME = "ral.operation.log.queue";



	//example 队列
	public static final String EXAMPLE_QUEUE_NAME = "ral.shop.example.queue";

	
}
