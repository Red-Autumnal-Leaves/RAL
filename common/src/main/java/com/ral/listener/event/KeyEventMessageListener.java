package com.ral.listener.event;

import com.ral.constants.event.EventConstants;
import com.ral.exception.event.EventHandleException;
import com.ral.exception.event.EventNotMappingHandlerException;
import com.ral.model.enums.event.EventEnum;
import com.ral.service.event.EventHandlerServiceFacotry;
import com.ral.service.event.IEventHandlerService;
import com.ral.service.lock.IRedisLock;
import com.ral.service.redis.IRedisService;
import com.ral.util.codec.StringUtils;
import com.ral.util.event.EventUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * Key event 频道订阅<br/>
 * 需要redis版本2.8以上，并且修改conf配置文件参数: <cite>notify-keyspace-events Ex</cite><br/>
 * 开启配置之后redis将会在key失效时，将消息发送到<cite>__keyevent@&lt;db&gt;__:expired</cite>频道，其中db代表key所在数据库的index
 * @author victor
 *
 */
@Component
@Configuration
public class KeyEventMessageListener implements MessageListener{
	
	private static Logger logger = Logger.getLogger(KeyEventMessageListener.class);
	
	private final RedisSerializer<String> serializer = new StringRedisSerializer();
	
	@Autowired
	private EventHandlerServiceFacotry eventHandlerServiceFacotry;
	
	@Autowired
	private IRedisService redisService;
	
	@Autowired
	private IRedisLock redisLock;
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		String key = serializer.deserialize(message.getBody());
		String eventId = EventUtils.getEventId(key);
		if(!StringUtils.isNullOrEmpty(eventId)){
			if(redisLock.lock(eventId, 300)){//must executed in 300S
				logger.debug("Handle event :" + eventId + " start !");
				try{
					handler(eventId);
				}catch (Exception ex) {
					logger.error("Key event hadppend bug : eventId:" + eventId + ", message :" + ex.getMessage() , ex);
					ex.printStackTrace();
				}finally{
					//delete data and meta
					redisService.del(EventUtils.getEventKey(eventId));
					redisService.hdel(EventConstants.KEY_EVENT_DATA_MAP, EventUtils.getDataKey(eventId),EventUtils.getMetaKey(eventId));
					redisLock.unlock(eventId);
				}
				logger.debug("Handle event :" + eventId + " end!");
			}else{// event was handled or is handling
				logger.debug("Event is handled");
			}
		} 
	}
	
	private void handler(String eventId){
		String data = redisService.hget(EventConstants.KEY_EVENT_DATA_MAP,EventUtils.getDataKey(eventId));
		String meta = redisService.hget(EventConstants.KEY_EVENT_DATA_MAP,EventUtils.getMetaKey(eventId));
		if(StringUtils.isNullOrEmpty(data) || StringUtils.isNullOrEmpty(meta)){// event was handled
			logger.debug("Event was handled, eventId:" + eventId + ".");
			return;
		}
		int event = Integer.parseInt(meta);
		data = data.equals(EventConstants.DEFAULT_EMPTY_DATA) ? null : data;
		IEventHandlerService service = eventHandlerServiceFacotry.instance(event);
		if(service == null){
			logger.error("Event handler not found, eventId:" + eventId + ",data:" + data + ",meta:" + meta + "!");
			throw new EventNotMappingHandlerException("Event handler service not mapping!",eventId,event,data);
		}
		try{
			service.handle(EventEnum.indexOf(event), eventId, data);
		}catch (Exception e) {
			logger.error("Handler event error, eventId : " + eventId, e);
			throw new EventHandleException("Handler event error.",eventId,event,data);
		}
	}
}
