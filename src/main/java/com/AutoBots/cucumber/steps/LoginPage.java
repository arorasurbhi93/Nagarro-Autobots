package com.AutoBots.cucumber.steps;

import java.io.IOException;

import org.json.JSONException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.AutoBots.cucumber.TestUtils.DriverManager;
import com.AutoBots.cucumber.TestUtils.TestUtil;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;

public class LoginPage extends BaseSteps
{
	
    @Before
    public void setupLoginSteps () throws IOException
   {                         
                              
        initializeTheDriver();
    }
    
    @After
    public void teardown (Scenario scenario) {
              if (scenario.isFailed()) {
                    // Take a screenshot...
                    final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                    scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
              }
              
              
              
              System.out.println("test teardown!");
              System.out.println("Closing driver!");
               
                 teardownTheDriver();
                 
               /*
               * System.out.println("Stopping Appium Server!"); stopServer();
               */
               
              
        
    }
	
	@Given("^User is home page$")
	public void user_is_home_page()
	{
	   driver.get("https://autothon-nagarro-frontend-y06.azurewebsites.net/");
	   System.out.println("User Navigate to appliaction");
	}

	@When("^User Click on login link$")
	public void user_click_on_login() throws JSONException
	{
		waitForElement(TestUtil.getElement(configProperty.getProperty("platformname"), "login_link"));
		WebElement log_link =  DriverManager.getDriver().findElement(TestUtil.getElement(configProperty.getProperty("platformname"), "login_link"));
		log_link.click();
	    
	}

	@Then("^User enter valid \"([^\"]*)\"$")
	public void user_enter_username(String username) throws JSONException
	{
		WebElement user_name =  DriverManager.getDriver().findElement(TestUtil.getElement(configProperty.getProperty("platformname"), "username"));
		user_name.sendKeys(username);

	}

	@Then("^User enter password and \"([^\"]*)\"$")
	public void user_enter_password(String Password) throws JSONException
	{
		WebElement pwd =  DriverManager.getDriver().findElement(TestUtil.getElement(configProperty.getProperty("platformname"), "password"));
		pwd.sendKeys(Password);
	    
	}

	@Then("^User click on login button$")
	public static void user_click_login_btn() throws JSONException
	{
		WebElement login_btn =  DriverManager.getDriver().findElement(TestUtil.getElement(configProperty.getProperty("platformname"), "login_btn"));
		login_btn.click();
	    
	}



}
