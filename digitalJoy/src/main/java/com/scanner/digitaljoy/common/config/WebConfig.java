package com.scanner.digitaljoy.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.scanner.digitaljoy.common.interceptor.HeaderLoggingInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HeaderLoggingInterceptor())
        .order(1)
        .addPathPatterns("/**");
	}
	

}
