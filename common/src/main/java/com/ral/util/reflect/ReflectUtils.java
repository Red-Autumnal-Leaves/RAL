package com.ral.util.reflect;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author victor
 * @desc 反射工具类
 */
public final class ReflectUtils {

	private ReflectUtils(){
	}
	
	/**
	 * 获取类所有属性和类型名称
	 * @param className 类名称
	 * @return
	 */
	public static Map<String,String> getPropertyAndTypes(String className) throws Exception{
		return getPropertyAndTypes(Class.forName(className));
	}
	
	/**
	 * 获取类所有属性和类型名称
	 * @param clz 类
	 * @return
	 */
	public static Map<String,String> getPropertyAndTypes(Class<?> clz) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clz).getPropertyDescriptors();
		Arrays.stream(propertyDescriptors).forEach((property)->{
			String key = property.getName();
			if (!key.equals("class")) {
				String typeName = property.getPropertyType().getName();
				map.put(key, typeName);
			}
		});
	    return map;  
	}
	
	
	/**
	 * 获取对象所有属性和类型名称
	 * @param obj 对象
	 * @return
	 */
	public static <T> Map<String,String> getPropertyAndTypes(T obj) throws Exception{
		return getPropertyAndTypes(obj.getClass());
	}
	
	
	/**
	 * 获取属性和类所有setter方法
	 * @param className 类名称
	 * @return
	 */
	public static Map<String, String> getPropertyAndSetter(String className)throws Exception{
		return getPropertyAndSetter(Class.forName(className));
	}
	
	/**
	 * 获取属性和类所有setter方法
	 * @param clz 类型
	 * @return
	 */
	public static Map<String, String> getPropertyAndSetter(Class<?> clz) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clz).getPropertyDescriptors();
		Arrays.stream(propertyDescriptors).forEach((property)->{
			String key = property.getName();
			if (!key.equals("class")) {
				Method method = property.getWriteMethod();
				map.put(key, method.getName());
			}
		});
	    return map;  
	}
	
	/**
	 * 获取对象所有属性和setter方法
	 * @param obj 对象
	 * @return
	 */
	public static <T> Map<String, String> getPropertyAndSetter(T obj) throws Exception{
		return getPropertyAndSetter(obj.getClass());
	}
	
	/**
	 * 获取属性和类所有getter方法
	 * @param className 类名称
	 * @return
	 */
	public static Map<String, String> getPropertyAndGetter(String className)throws Exception{
		return getPropertyAndGetter(Class.forName(className));
	}
	
	/**
	 * 获取属性和类所有getter方法
	 * @param clz 类型
	 * @return
	 */
	public static Map<String, String> getPropertyAndGetter(Class<?> clz) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clz).getPropertyDescriptors();
		Arrays.stream(propertyDescriptors).forEach((property)->{
			String key = property.getName();
			if (!key.equals("class")) {
				Method method = property.getReadMethod();
				map.put(key, method.getName());
			}
		});
	    return map;  
	}
	
	/**
	 * 获取对象所有属性和getter方法
	 * @param obj 对象
	 * @return
	 */
	public static <T> Map<String, String> getPropertyAndGetter(T obj) throws Exception{
		return getPropertyAndGetter(obj.getClass());
	}
	
	
	
	/**
	 * 获取对象值
	 * @param obj 对象
	 * @param property 属性名称
	 * @return
	 */
	public static <T> Object getPropertyValues(T obj,String property) throws Exception{
		Map<String, Method> getters = getClassGetters(obj.getClass());
		Method method = getters.get(property);
		Object value = method.invoke(obj, new Object[]{});
		return value;
	}
	
	/**
	 * 设置对象值
	 * @param obj 对象
	 * @return
	 */
	public static <T> void setPropertyValue(T obj,String property,Object value) throws Exception{
		Map<String, Method> setters = getClassSetters(obj.getClass());
		Method method = setters.get(property);
		method.invoke(obj, value);
	}
	
	/**
	 * 获取setters
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Method> getClassGetters(Class<?> clz) throws Exception{
		Map<String,Method> map = new HashMap<String,Method>();
		PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clz).getPropertyDescriptors();
		Arrays.stream(propertyDescriptors).forEach((property)->{
			String key = property.getName();
			if (!key.equals("class")) {
				map.put(key, property.getReadMethod());
			}
		});
	    return map;
	}
	
	/**
	 * 获取getter
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Method> getClassSetters(Class<?> clz) throws Exception{
		Map<String,Method> map = new HashMap<String,Method>();
		PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clz).getPropertyDescriptors();
		Arrays.stream(propertyDescriptors).forEach((property)->{
			String key = property.getName();
			if (!key.equals("class")) {
				map.put(key, property.getWriteMethod());
			}
		});
	    return map;
	}
	
	
	
	
	
	/**
	 * 获取类所有属性
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public static List<String> getProperties(String className)throws Exception{
		Class<?> clz = Class.forName(className);
		return getProperties(clz);
	}
	
	
	
	/**
	 * 获取对象所有属性
	 * @param obj 对象
	 * @return
	 * @throws Exception
	 */
	public static <T> List<String> getProperties(T obj) throws Exception{
		return getProperties(obj.getClass());
	}
	
	/**
	 * 获取类所有属性
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	public static List<String> getProperties(Class<?> clz) throws Exception{
		List<String> fieldNames = new ArrayList<String>();
		PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clz).getPropertyDescriptors();
		Arrays.stream(propertyDescriptors).forEach((property)->{
			String key = property.getName();
			if (!key.equals("class")) {
				fieldNames.add(key);
			}
		});
	    return fieldNames;  
	}
}
