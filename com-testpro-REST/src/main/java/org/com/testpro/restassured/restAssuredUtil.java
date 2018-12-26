package org.com.testpro.restassured;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class restAssuredUtil {

	/**
	 * 根据URL获取Response对象
	 * 
	 * @param Url
	 * @return
	 */
	public restAssuredUtil(String baseURI)
	{
		RestAssured.baseURI=baseURI;
	}
	
	public Response GetResponse(String url) {
		return get(url);
	}

	public Response GetResponse(String url, HashMap<String, String> parameters) {

		return given().queryParams(parameters).get(url);

	}

	public Response GetResponse(String url, HashMap<String, String> parameters,	HashMap<String, String> heders) {

		return given().params(parameters).headers(heders).when().get(url);

	}

	public Response PostResponse(String url) {
		return post(url);
	}

	public Response PostResponse(String url, HashMap<String, String> parameters) {

		return given().params(parameters).when().post(url);

	}

	public Response PostResponse(String url,HashMap<String, String> parameters, HashMap<String, String> heders) {

		return given().params(parameters).headers(heders).when().post(url);

	}

}
