package com.id.spring.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer{
	
	@Value("${rutaGenerica}")
	private String rutaFiles;
	
	@Value("${folderImages}")
	private String folderFiles;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		registry.addResourceHandler("/"+folderFiles+"/**")
		.addResourceLocations("file:/"+rutaFiles+"/");
	}
	
	
	public String rutaGenrica() {
		return rutaFiles;
	}
}
