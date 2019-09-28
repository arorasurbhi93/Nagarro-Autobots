package com.AutoBots.cucumber.steps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.AutoBots.cucumber.TestUtils.DriverFactory;
import com.AutoBots.cucumber.TestUtils.DriverManager;

/*import com.BillersApp.TestUtils.DriverFactory;
import com.BillersApp.TestUtils.DriverManager;
import com.BillersApp.cucumber.tests.RunCucumberFeatures;*/

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

public class BaseSteps
{
	
	public WebDriverWait wait;
	public static WebDriver driver;
	public static Properties configProperty;
	public static AppiumDriverLocalService service;
	public static AppiumServiceBuilder builder;
	public static DesiredCapabilities cap;
	public int driverTimeOut = 62;
	
	public static String screenshotPath;
	
	public static String screenshotName;
	
	
	public void startServer() {
		
		
		//Set Capabilities
		cap = new DesiredCapabilities();
		cap.setCapability("noReset", "false");
		
		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		builder.withCapabilities(cap);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
		
	
		
		//Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		System.out.println("Successfully started server");
	}
	
	public void stopServer() {
		service.stop();
	}
	
	public boolean checkIfServerIsRunnning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		System.out.println("isServerRunning:"+isServerRunning);
		return isServerRunning;
	}
	
	public void initializeTheDriver () {
    	
        try {
			FileInputStream config = new FileInputStream(new File(System.getProperty("user.dir")+"//src//main//java//com//SubscribeApp//resources//config.properties"));
			configProperty = new Properties();
			try {
				configProperty.load(config);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		driver = new DriverFactory().createDriverInstance(configProperty.getProperty("platformname"));
        DriverManager.setDriver(driver);
        DriverManager.setImplicitWait(driver);
    }
	
	public void waitForElement(By by) {
    	WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    
    
   
	
	public static String getNewMobileNumber() {
		String number = null;
		
		Fairy fairy = Fairy.create();
    	Person person = fairy.person();
    	
    	number = person.telephoneNumber();
    
		
		  if (person.telephoneNumber().replace("-","").length() < 10)
		  { 
			  number = person.telephoneNumber().replace("-","") + "2"; }
		  
		  else
		  { 
			  number = person.telephoneNumber().replace("-","");
		  }
		  
		  //number = "7950" + number.substring(4, number.length());
		 
		return number;
		
	}
	
	public static String createName()
	{
		String name = null;
		
		Fairy f = Fairy.create();
		Person p = f.person();
		name = p.firstName();
		
		return name;
	}
	
	public static String createEmail()
	{
		String name = null;
		
		Fairy f = Fairy.create();
		Person p = f.person();
		name = p.email();
		
		return name;
	}
	
	
    
    protected void teardownTheDriver() {
     
        try
        {
        DriverManager.closeDriver(DriverManager.getDriver());
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    
	
	
	public void click(WebElement element, String elementName)
	{
		element.click();
		
		//testCaseLogger.get().log(Status.INFO, "Clicked on "+ elementName);
		
	}
	
	public void type(WebElement element, String text, String elementName)
	{
		
		element.sendKeys(text);
		
		//testCaseLogger.get().log(Status.INFO, "Entered "+"<b>"+ text + "</b>" + "as"+ elementName);	
		
	}
	
	public boolean isEnabled(WebElement element)
	{
		/*
		 * 
		 * wait.until(ExpectedConditions.elementToBeClickable(element));
		 */
		for(int i=0; i<=60; i++)
		{
			try
			{
				element.isEnabled();
				System.out.println("returned true");
				return false;
			}catch(Exception e)
			{
				try
				{
					Thread.sleep(61000);
				}catch(InterruptedException e1)
				{
					
				}
			}
			
		}
		System.out.println("returned false");
		return false;
		
	}
	
	
	

}
