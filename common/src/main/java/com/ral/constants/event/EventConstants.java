package com.ral.constants.event;

/**
 * 事件相关常量
 * @author victor
 *
 */
public class EventConstants {
	
	public static final String KEY_EVENT_AUTO_ID = "KEY_EVENT_AUTO_ID"; // event 计数器， 用于生成EVENT_ID

	public static final String KEY_EVENT_DATA_MAP = "KEY_EVENT_DATA_MAP"; //用来存储用户自定义data的 Hash
	
	public static final String EVENT_KEY = "KEY_EVENT"; // 用来做event redis唯一标识前缀
	
	public static final String EVENT_DATA_KEY = "DATA";// hash  中存储data前缀
	
	public static final String DEFAULT_EMPTY_DATA = "KEY_EVENT_DATA_EMPTY"; // 空data时常量替换串
	
	public static final String EVENT_META_KEY = "META";//用来存储 事件类型前缀
}
