package com.ral.service.queue;

import com.ral.model.enums.queue.MessageQueueEnum;

/**
 * 
 * @author victor
 * @desc 消息队列服务接口
 */
public interface IMessageQueueService {
	
	/**
	 * 发送消息到队列
	 * @param queue 队列
	 * @param message 消息内容
	 */
	public void send(MessageQueueEnum queue, String message);
	
	/**
	 * 延迟发送消息到队列
	 * @param queue 队列
	 * @param message 消息内容
	 * @param times 延迟时间 单位毫秒
	 */
	public void send(MessageQueueEnum queue, String message, long times);
}
