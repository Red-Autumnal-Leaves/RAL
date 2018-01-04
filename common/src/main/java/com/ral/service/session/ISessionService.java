package com.ral.service.session;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author victor
 * @desc Session Service接口
 *
 */
public interface ISessionService {

	/**
	 * @desc 判断sessin是否存在
	 */
	boolean isExist(String sessionId);
	
	/**
	 * 
	 * @param sessionId
	 * @desc 创建session,如果session已经存在，将会刷新session过期时间,时间为默认时间.
	 */
	void createSession(String sessionId);
	
	/**
	 * 
	 * @param sessionId
	 * @desc 创建session,如果session已经存在，将会刷新session过期时间.
	 */
	void createSession(String sessionId, int interval);
	
	/**
	 * 
	 * @param interval 秒
	 * @desc 设置session过期时间
	 */
	void setMaxInactiveInterval(String sessionId, int interval);
	
	
	/**
	 * 
	 * @param name 存放session属性name
	 * @return 属性值
	 * @desc 根据sesison属性name 获取属性值
	 */
	String getAttribute(String sessionId, String name);
	
	/**
	 * 
	 * @param name 存放session属性name
	 * @return 属性值
	 * @desc 根据sesison属性name 获取属性值,JSON序列化
	 */
	<T> T getAttribute(String sessionId, String name, Class<T> clz);
	
	/**
	 * 
	 * @param name 存放session属性name
	 * @return 属性值
	 * @desc 根据sesison属性name 获取属性值,JSON序列化List
	 */
	<T> List<T> getAttributeAsList(String sessionId, String name, Class<T> clz);
	
	/**
	 * 
	 * @param name 存放session属性name
	 * @return 属性值
	 * @desc 根据sesison属性name 获取属性值,JSON序列化Map
	 */
	 Map<String,Object> getAttributeAsMap(String sessionId, String name);
	
	/**
	 * 
	 * @param name 属性名
	 * @param value 属性值
	 * @desc 设置session属性,如果name已经存在，原值将会被覆盖
	 */
	void setAttribute(String sessionId, String name, Object value);
	
	
	/**
	 * 
	 * @param name 属性名
	 * @desc 移除session属性
	 */
	void removeAttribute(String sessionId, String name);
	
	/**
	 * 
	 * @desc 销毁当前session对象
	 */
	void invalidate(String sessionId);
	
}
