package com.ral.service.event;

import com.ral.model.enums.event.EventEnum;

import java.util.Date;

/**
 * 事件服务接口
 * @author victor
 *
 */
public interface IEventService {

	/**
	 * 注册事件,相同事件不会覆盖，注册成功将返回全局唯一事件ID
	 * @param event  事件
	 * @param data	业务数据
	 * @return 事件ID,全局唯一
	 */
	String register(EventEnum event, String data);
	
	/**
	 * 注册事件,相同事件不会覆盖，注册成功将返回全局唯一事件ID
	 * @param event  事件
	 * @param data	业务数据
	 * @param seconds 事件执行时间, 单位秒
	 * @return 事件ID,全局唯一
	 */
	String register(EventEnum event, String data, int seconds);
	
	/**
	 * 注册事件,相同事件不会覆盖，注册成功将返回全局唯一事件ID
	 * @param event  事件
	 * @param data	业务数据
	 * @param date 事件执行时间
	 * @return 事件ID,全局唯一
	 */
	String register(EventEnum event, String data, Date date);
	
	
	/**
	 * 移除事件,如果事件已经被执行,移除无意义,如果事件未执行,则返回移除成功或者失败
	 * @param eventId  事件ID
	 * @return 是否移除成功,true 表示成功，false表示失败(如事件正在被执行)
	 */
	boolean remove(String eventId);
	
}
