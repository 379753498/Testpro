package org.com.testpro.StringUtil;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.com.testpro.Bean.SensorData;
import org.com.testpro.Dao.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class StringUtil {

	@Autowired
	SensorService SensorService;
	public  String StringisoForUtf(String s)
	{
		try {
			return new String(s.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
		
	}
	public  String Bridgenamechangegetawy(String s)
	{
		
		 List<SensorData> getbridgenameinfo = SensorService.getbridgenameinfo();
		 	for (SensorData sensorData : getbridgenameinfo) {
				
		 		if(sensorData.getBridgename().equals(s))
		 		{
		 			s=sensorData.getGatewaynum();
		 		}
		 		
			}

		return s;
		
	}
	
	
}
