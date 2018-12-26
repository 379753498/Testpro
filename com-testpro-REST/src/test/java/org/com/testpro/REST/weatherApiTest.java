package org.com.testpro.REST;


import java.util.HashMap;

import io.qameta.allure.Description;
import io.restassured.response.Response;

import org.com.testpro.Base.SpringTestngBase;
import org.com.testpro.DataMapper.weatherMapper;
import org.com.testpro.restassured.restAssuredUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class weatherApiTest extends SpringTestngBase {
	
	String bashUrl=null;
	
	@BeforeTest
	public void setup()
	{
		bashUrl="https://www.sojson.com";
	}
//	@Test(testName="请求天气数据json" ,dataProvider="weatherMapper",dataProviderClass=weatherMapper.class)
	@Description("请求天气数据Json REST服务")
	public void  weatherApiTestForJson(  HashMap<String, String> parameters) throws InterruptedException
	{	
		
		String url="/open/api/weather/json.shtml";
		restAssuredUtil restAssuredUtil = new restAssuredUtil(bashUrl);
		Response getResponse = restAssuredUtil.GetResponse(url, parameters);
		String getResponseString = getResponse.getContentType();
		System.out.println(getResponseString);
//		MatcherAssert.assertThat(getResponseString, JsonSchemaValidator.matchesJsonSchemaInClasspath("weatherApi-jsonschema.json"));
		Assert.assertEquals( getResponse.jsonPath().getString("date"),"20180911");
	}
	
	@Test(testName="请求天气数据xml" ,dataProvider="weatherMapper",dataProviderClass=weatherMapper.class)
	@Description("请求天气数据Json REST服务")
//	@Step("Info{BridgeParam.terninal}/{BridgeParam.sensor}")
	public void  weatherApiTestForXMl(  HashMap<String, String> parameters) throws InterruptedException
	{	
		
		String url="/open/api/weather/xml.shtml";
		restAssuredUtil restAssuredUtil = new restAssuredUtil(bashUrl);
		String string = restAssuredUtil.GetResponse(url, parameters).xmlPath().getString("resp.city");
		System.out.println(string);
		Assert.assertEquals( string,"北京");
	}
	
	
}
