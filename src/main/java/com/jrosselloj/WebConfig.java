package com.jrosselloj;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.xhtml");
		//registry.addViewController("/login").setViewName("forward:/index.jsp");
		//registry.addViewController("/qmgr/**").setViewName("forward:/index.jsp");
	}
	
}
