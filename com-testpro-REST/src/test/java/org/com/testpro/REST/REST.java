package org.com.testpro.REST;


import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import java.util.HashMap;
import java.util.List;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.com.testpro.Base.SpringTestngBase;
import org.com.testpro.restassured.restAssuredUtil;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class REST extends SpringTestngBase {

	
	private String baseURI;
	@BeforeTest
	public void before() {
		baseURI = "http://10.5.4.50:8120/Bridge";

	}

	@Test(testName = "Get parameters")
	public void testUtilLongtime() {
		restAssuredUtil restAssuredUtil = new restAssuredUtil(baseURI);
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("Longtime", "1534348800000");
		parameters.put("fomat", "yyyy-MM-dd HH:mm:ss");
		Response getResponse = restAssuredUtil.GetResponse(
				"/Bridge/UtilLongtime", parameters);
		// 将返回数据转换为Josn并提取指定的字段
		String format = getResponse.andReturn().jsonPath().getString("format");
		System.out.println(format);
		getResponse.then().body("format", Matchers.equalTo("yyyy-MM-dd HH:mm:ss"));
	}

	@Test(testName = "Get")
	public void testUtilLongtime2() {
		restAssuredUtil restAssuredUtil = new restAssuredUtil(baseURI);
		Response getResponse = restAssuredUtil.GetResponse("/Bridge/UtilLongtime?Longtime=1534348800000&fomat=yyyy-MM-dd HH:mm:ss");
		System.err.println(getResponse.asString());
		String format = getResponse.andReturn().jsonPath().getString("format");
		System.out.println(format);
		getResponse.then().body("format", Matchers.equalTo("yyyy-MM-dd HH:mm:ss"));
	}
	
	@Test(testName = "Getjsonschemajson")
	public void Getjsonschemajson() {
		restAssuredUtil restAssuredUtil = new restAssuredUtil(baseURI);
		Response getResponse = restAssuredUtil.GetResponse("/Bridge/UtilLongtime?Longtime=1534348800000&fomat=yyyy-MM-dd HH:mm:ss");
		String asString = getResponse.asString();
		MatcherAssert.assertThat(asString, JsonSchemaValidator.matchesJsonSchemaInClasspath("time-jsonschema.json"));
	}

	@Test(testName = "SensorServiceList")
	public void testSensorServiceList() {
		restAssuredUtil restAssuredUtil = new restAssuredUtil(baseURI);
		Response getResponse = restAssuredUtil.GetResponse("/database/SensorServiceList");
				List<Object> list = JsonPath.from(getResponse.asString()).getList("equipmentname");
				for (Object object : list) {
//					System.out.println(object.toString());
				}
	}
	
}
