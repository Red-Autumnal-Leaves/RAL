package com.ral.exception.event;

/**
 * 事件 无匹到执行者异常
 * @author victor
 *
 */
public class EventNotMappingHandlerException extends RuntimeException{

	private static final long serialVersionUID = 140085406085415945L;
	
	public EventNotMappingHandlerException(String message,String eventId,Integer event,String data){
		super(message);
		this.eventId = eventId;
		this.event = event;
		this.data = data;
	}
	
	private String eventId;
	
	private Integer event;
	
	private String data;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Integer getEvent() {
		return event;
	}

	public void setEvent(Integer event) {
		this.event = event;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}

