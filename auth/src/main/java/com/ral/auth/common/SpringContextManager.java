package com.ral.auth.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextManager implements ApplicationContextAware {

	private static ApplicationContext appContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		appContext = applicationContext;
		System.out.println("Spring init finished ..");
	}

	/**
	 * 获取Bean
	 */
	public static <T> T getBean(String beanName) {
		return (T) appContext.getBean(beanName);
	}

	/**
	 * 获取Bean
	 */
	public static <T> T getBean(Class<T> clazz) {
		return appContext.getBean(clazz);
	}

	/**
	 * 获取Bean
	 */
	public static <T> T getBean(String beanName, Class<T> clazz) {
		return appContext.getBean(beanName, clazz);
	}

}
