package com.testng.webui;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Person implements InitializingBean {

	private String name;
	private String age;
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Person 实例化后我进行了一些设置");
	}
}
