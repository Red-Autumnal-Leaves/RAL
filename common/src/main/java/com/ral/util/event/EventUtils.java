package com.ral.util.event;

import com.ral.constants.event.EventConstants;
import com.ral.util.codec.StringUtils;

public final class EventUtils {

	private EventUtils(){
		
	}
	
	public static String getEventKey(String eventId){
		return EventConstants.EVENT_KEY + "_" + eventId;
	}
	
	public static String getDataKey(String eventId){
		return eventId + "_" + EventConstants.EVENT_DATA_KEY;
	}
	
	public static String getMetaKey(String eventId){
		return eventId + "_" + EventConstants.EVENT_META_KEY;
	}
	
	public static boolean isEvent(String key){
		if(!StringUtils.isNullOrEmpty(key) && key.toUpperCase().indexOf(EventConstants.EVENT_KEY + "_") == 0){
			return true;
		}
		return false;
	}
	
	
	public static String getEventId(String key){
		if(isEvent(key)){
			String eventId = key.replace(EventConstants.EVENT_KEY + "_", "");
			return eventId;
		}
		return null;
	}
	

}
