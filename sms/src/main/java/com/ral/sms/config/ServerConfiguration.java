package com.ral.sms.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class ServerConfiguration implements EmbeddedServletContainerCustomizer{

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
	}

}
