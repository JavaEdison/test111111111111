package com.zjf.babasports;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
public class FreeMarker {
	@Bean
	public FreeMarkerConfigurer getFreeMarkerConfigurer(){
		FreeMarkerConfigurer fmc=new FreeMarkerConfigurer();
		fmc.setTemplateLoaderPath("/DoMain/");
		fmc.setDefaultEncoding("UTF-8");
		return fmc;
	}
}
