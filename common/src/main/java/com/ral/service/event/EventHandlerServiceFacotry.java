package com.ral.service.event;

import java.util.List;

import com.ral.model.enums.event.EventEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 事件处理工厂
 * @author victor
 *
 */
@Service
public class EventHandlerServiceFacotry {
	
	private List<IEventHandlerService> services;
	
	@Autowired
	public EventHandlerServiceFacotry(List<IEventHandlerService> services){
		this.services = services;
	}
	
	public IEventHandlerService instance(int event){
		for(IEventHandlerService service : services){
			if(service.getEvent().getIndex() == event){
				return service;
			}
		}
		return null;
	}
	
	public IEventHandlerService instance(EventEnum event){
		return instance(event.getIndex());
	}
}
