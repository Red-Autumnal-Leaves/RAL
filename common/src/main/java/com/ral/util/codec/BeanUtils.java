package com.ral.util.codec;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ral.util.reflect.ReflectUtils;
import org.apache.log4j.Logger;

public class BeanUtils {

	private static Logger logger  = Logger.getLogger(BeanUtils.class);
	
	public static Map<String, Object> beanToMap(Object bean){
		if (bean == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (!key.equals("class")) {
					Method getter = property.getReadMethod();
					Object value = getter.invoke(bean);
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Bean转Map出错，错误信息:" + e.getMessage());
		}
		return map;
	}
	
	/**
	 * 将对象相同类型，相同名称属性设置到另一对象
	 * @param source 被读取的对象
	 * @param target 被赋值的对象
	 */
	public static<T,E> void copy(T source,E target){
		Class<?> sourceClass = source.getClass(); 
		Class<?> targetClass = target.getClass(); 
		try{
			Map<String, Method> sourceGetters = ReflectUtils.getClassGetters(sourceClass);
			Map<String, Method> targetSetters = ReflectUtils.getClassSetters(targetClass);
			Map<String, String> sourceTypes = ReflectUtils.getPropertyAndTypes(sourceClass);
			Map<String, String> targetTypes = ReflectUtils.getPropertyAndTypes(targetClass);
			List<String> properties = ReflectUtils.getProperties(targetClass);
			properties.forEach((property) -> {
				String ttName = targetTypes.get(property);
				String stName = sourceTypes.get(property);
				if(!StringUtils.isNullOrEmpty(stName) && ttName.equals(stName)){
					Method getter = sourceGetters.get(property);
					Method setter = targetSetters.get(property);
					if(getter != null && setter !=null){
						try {
							Object value = ReflectUtils.getPropertyValues(source, property);
							if(value != null){
								ReflectUtils.setPropertyValue(target, property, value);
							}
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("copy get set error ",e);
						}
					}
				}
			});
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("copy error ",e);
		}
	}
}
