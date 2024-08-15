package com.scanner.digitaljoy.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.MultipartConfigElement;
import lombok.extern.slf4j.Slf4j;

@Configuration
@PropertySource("classpath:/config.properties")
@Slf4j
public class FileConfig implements WebMvcConfigurer{
	
	@Value("${spring.servlet.multipart.file-size-threshold}")
	private long fileSizeThreshold;
	
	@Value("${spring.servlet.multipart.max-request-size}")
	private long maxRequestSize; 
	
	@Value("${spring.servlet.multipart.max-file-size}")
	private long maxFileSize; 
	
	@Value("${spring.servlet.multipart.location}")
	private String location; 
	
	// scan 파일 
    @Value("${scan.file.resource-handler}")
    private String scanFileResourceHandler;
    
    @Value("${scan.file.resource-location}")
    private String scanFileResourceLocation;
    
    
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("scanFileResourceHandler={}", scanFileResourceHandler);
		log.info("scanFileResourceLocation={}", scanFileResourceLocation);
		registry.addResourceHandler(scanFileResourceHandler)
		.addResourceLocations(scanFileResourceLocation);
	}
	
	@Bean
	public MultipartConfigElement configElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		
		factory.setFileSizeThreshold(DataSize.ofBytes(fileSizeThreshold));
		
		factory.setMaxFileSize(DataSize.ofBytes(maxFileSize));
		
		factory.setMaxRequestSize(DataSize.ofBytes(maxRequestSize));
		
		factory.setLocation(location);
		
		return factory.createMultipartConfig();
	} 
	
	@Bean
	public MultipartResolver multipartResolver() {
		StandardServletMultipartResolver multipartResolver
			= new StandardServletMultipartResolver();
		return multipartResolver;
	}
	
	
}
