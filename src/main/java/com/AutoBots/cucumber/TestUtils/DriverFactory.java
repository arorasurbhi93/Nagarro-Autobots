package com.AutoBots.cucumber.TestUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory
{
	public static WebDriver driver;

	@SuppressWarnings("rawtypes")
	public WebDriver createDriverInstance(String platformname)
	{
		
		if(platformname.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}else if(platformname.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}else if(platformname.equalsIgnoreCase("ie"))
		{
			
		}else if(platformname.equalsIgnoreCase("Android"))
		{
			
		}else {
			
		}
		return driver;
		
	}
	
	public static void destory()
	{
		
		DriverManager.getDriver().quit();
		
	}

	
}
