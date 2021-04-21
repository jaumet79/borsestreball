package com.jrosselloj;

import java.util.HashMap;
import java.util.Map;

import javax.faces.webapp.FacesServlet;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.sun.faces.config.ConfigureListener;

@SpringBootApplication
public class BorsestreballApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BorsestreballApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean facesServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
		registration.setLoadOnStartup(1);
		//		registration.addUrlMappings("*.jrj");
		registration.addUrlMappings("/index.xhtml");
		return registration;
	}
	
	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> {
			servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
			servletContext.setInitParameter("primefaces.THEME", "bootstrap");
			//servletContext.setInitParameter("primefaces.THEME", "sunny");
			//servletContext.setInitParameter("primefaces.THEME", "redmond");
			
			//			servletContext.setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/springsecurity.taglib.xml");
		};
	}
	
	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<>(new ConfigureListener());
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public static CustomScopeConfigurer viewScope() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		Map<String, Object> mapScopes = new HashMap<>();
		mapScopes.put("view", new ViewScope());
		
		configurer.setScopes(mapScopes);
		return configurer;
	}
	
}
