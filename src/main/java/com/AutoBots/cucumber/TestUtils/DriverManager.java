package com.AutoBots.cucumber.TestUtils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.android.AndroidDriver;

public class DriverManager
{
	@SuppressWarnings("rawtypes")
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return DriverManager.driver.get();
	}
	
	public static void setDriver(WebDriver driver) {
		DriverManager.driver.set(driver);
	}
	
	public static void setImplicitWait(WebDriver driver)
	{
		DriverManager.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public static void closeDriver(WebDriver driver) {
		driver.quit();
	}


}
