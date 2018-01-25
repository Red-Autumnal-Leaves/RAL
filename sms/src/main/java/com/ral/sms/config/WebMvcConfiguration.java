package com.ral.sms.config;

import com.ral.components.ApplicationContextHolder;
import com.ral.sms.web.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan("com.ral.sms")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	// view
	@Bean
	public InternalResourceViewResolver viewResolver() {
		return new InternalResourceViewResolver();
	}

	// file upload
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(1000000);
		return resolver;
	}

	// Resource
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
	}



	// Interceptors
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(ApplicationContextHolder.getBean(LoginInterceptor.class));
	}



}
