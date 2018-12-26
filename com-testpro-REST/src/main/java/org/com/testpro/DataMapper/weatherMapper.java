package org.com.testpro.DataMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.testng.annotations.DataProvider;

@Component
@Data
@ConfigurationProperties(prefix = "weathermapper" )

public class weatherMapper implements InitializingBean {
	
	
	private String[] city;
	private static List<Object> weatherMapper= new ArrayList<Object>();

	@DataProvider(name="weatherMapper")
	public static Iterator<Object[]> CreateData()
	{
		List<Object[]> personlistobj= new ArrayList<Object[]>();
		for (Object object : weatherMapper) {
			personlistobj.add( new Object[] {object});
		}
		return personlistobj.iterator();
		
	}
	


	@Override
	public void afterPropertiesSet() throws Exception {
		
		for (String string : city) {
			HashMap<String, String> map =new HashMap<String, String>(); 
			map.put("city", string);
			weatherMapper.add(map);
		}
	}
	
	@PostConstruct
	public void init()
	{
		System.out.println("init方法已被调用");
	}
	@PreDestroy
	public void destory()
	{
		System.out.println("destory方法已被调用");

	}
}
