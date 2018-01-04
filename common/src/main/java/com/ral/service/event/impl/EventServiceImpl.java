package com.ral.service.event.impl;

import com.google.common.collect.Maps;
import com.ral.constants.event.EventConstants;
import com.ral.model.enums.event.EventEnum;
import com.ral.service.redis.IRedisService;
import com.ral.service.event.IEventService;
import com.ral.service.lock.IDistributedLock;
import com.ral.util.date.DateUtils;
import com.ral.util.event.EventUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author victor
 * @desc 默认事件实现
 * @date 2018/1/3 0:03
 */
@Service
public class EventServiceImpl implements IEventService {

    private static Logger logger = Logger.getLogger(EventServiceImpl.class);

    @Autowired
    private IRedisService redisService;

    @Autowired
    private IDistributedLock distributedLock;

    @Override
    public String register(EventEnum event, String data) {
        return register(event, data,1);
    }

    @Override
    public String register(EventEnum event, String data, int seconds) {
        seconds = seconds <= 0 ? 1 : seconds;
        String eventId = String.valueOf(redisService.incyby(EventConstants.KEY_EVENT_AUTO_ID));
        Map<String,Object> map = Maps.newHashMap();
        map.put(EventUtils.getDataKey(eventId), data);
        map.put(EventUtils.getMetaKey(eventId), event.getIndex());
        redisService.hsetnxs(EventConstants.KEY_EVENT_DATA_MAP,map);
        redisService.setex(EventUtils.getEventKey(eventId), event.getIndex() + "", seconds);
        logger.debug("register event :" + eventId);
        return eventId;
    }

    @Override
    public String register(EventEnum event, String data, Date date) {
        int seconds = DateUtils.calculateDifferenceSeconds(date, new Date());
        return register(event, data,seconds);
    }

    @Override
    public boolean remove(String eventId) {
        if(distributedLock.lock(eventId, 5)){
            redisService.del(EventUtils.getEventKey(eventId));
            redisService.hdel(EventConstants.KEY_EVENT_DATA_MAP, EventUtils.getDataKey(eventId),EventUtils.getMetaKey(eventId));
            distributedLock.unlock(eventId);
        }
        logger.debug("Remove key event error : event is executed!");
        return false;
    }
}
