package com.ral.service.event;

import com.ral.model.enums.event.EventEnum;

/**
 * 事件触发服务 自定义
 * @author victor
 *
 */
public interface IEventHandlerService {

    /**
     * 执行
     * @param eventId
     * @param event
     * @param data
     */
    void handle(EventEnum event,String eventId,String data);

    /**
     * 事件类型
     * @return
     */
    EventEnum getEvent();
}
