package com.pro.webui;

import io.github.bonigarcia.wdm.ChromeDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebManagerdevtest  {
	
	 private static WebDriver driver;
	@BeforeClass
	public static void Setup()
	{
	    ChromeDriverManager.chromedriver().arch64().setup();
	    driver = new ChromeDriver();
}
	

	@Test
	public  static void  baidu() throws InterruptedException
	{
		driver.get("http://10.5.4.50:8120/Bridge/swagger-ui.html");
		Thread.sleep(2000);
	}
	@AfterTest
	public static void  over()
	{
		driver.quit();

	}
	
}
