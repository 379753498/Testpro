package org.com.testpro.ApplicationConfig;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("springBeanUtil")

public class SpringBeanUtil implements ApplicationContextAware {
	private  ApplicationContext ApplicationContext;

	public  ApplicationContext getApplicationContext() {
		return this.ApplicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {

		this.ApplicationContext=applicationContext;		
	}}