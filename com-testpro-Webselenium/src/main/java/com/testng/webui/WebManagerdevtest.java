package com.testng.webui;

import io.github.bonigarcia.wdm.ChromeDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebManagerdevtest  {
	
	 private static WebDriver driver;
	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void Setup()
	{
	    ChromeDriverManager.iedriver().arch32().setup();
	    
//过时的代码不建议使用
//	    DesiredCapabilities dc =DesiredCapabilities.internetExplorer(); 
//	    dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	    
	    InternetExplorerOptions ieoptions=  new InternetExplorerOptions();
	    ieoptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	    driver = new InternetExplorerDriver(ieoptions);
}
	

	@Test
	public  static void  baidu() throws InterruptedException
	{
		driver.get("http://10.5.4.143:8080/bridge/login.html");
		Thread.sleep(2000);
	}
	@AfterTest
	public static void  over()
	{
		driver.quit();

	}
	
}
