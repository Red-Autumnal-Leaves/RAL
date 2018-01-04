package com.ral.service.event.handler;

import com.ral.model.enums.event.EventEnum;
import com.ral.service.event.IEventHandlerService;
import org.springframework.stereotype.Service;

/**
 * Created by victor on 2018/1/4.
 */
@Service("exampleEventHandlerService")
public class ExampleEventHandlerServiceImpl implements IEventHandlerService {

    /**
     * 执行
     *
     * @param event
     * @param eventId
     * @param data
     */
    @Override
    public void handle(EventEnum event, String eventId, String data) {
        System.out.println("Hello event！");
    }

    /**
     * 事件类型
     *
     * @return
     */
    @Override
    public EventEnum getEvent() {
        return EventEnum.EXAMPLE_EVENT;
    }
}
