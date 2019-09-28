package com.AutoBots.cucumber.TestUtils;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.AutoBots.cucumber.testrunner.RunCucumberFeatures;




public class TestUtil
{
	
	public static By getElement(String os, String element) throws JSONException {
		By elementval = null;
			
		JSONObject jsonObject =  (JSONObject) RunCucumberFeatures.obj;
        JSONObject ele = (JSONObject) jsonObject.get(element);
            
        JSONObject osvalue  = (JSONObject) ele.get(os);
        String locvalue = (String) osvalue.get("locator");
            
        switch (locvalue) {
            case "id" : elementval =  By.id((String) osvalue.get("value")); break;
            case "xpath" : elementval =  By.xpath((String) osvalue.get("value")); break;
            case "classname" : elementval =  By.className((String) osvalue.get("value")); break;
        }
        
               
        //System.out.println("The locator value is :"+elementval);
		return elementval;
	}

}
