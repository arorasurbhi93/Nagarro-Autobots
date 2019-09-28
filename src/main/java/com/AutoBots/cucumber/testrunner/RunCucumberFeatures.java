package com.AutoBots.cucumber.testrunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import com.github.mkolisnyk.cucumber.runner.ExtendedTestNGRunner;

import cucumber.api.CucumberOptions;

@ExtendedCucumberOptions(jsonReport = "src/main/java/results/cucumber-reports/cucumber.json",
retryCount = 3,
detailedReport = true,
overviewReport = true,
knownErrorsReport = true,
detailedAggregatedReport = true,
jsonUsageReport = "src/main/java/results/cucumber-reports/cucumber-usage.json",
usageReport = true,
toPDF = true,
knownErrorsConfig = "src/main/java/com/AutoBots/resources/known-errors-source/sample_model.json",
outputFolder = "src/main/java/results")
@CucumberOptions(plugin = { "pretty","html:src/main/java/results/cucumber-reports/cucumber-html-report",
"json:src/main/java/results/cucumber-reports/cucumber.json",
"usage:src/main/java/results/cucumber-reports/cucumber-usage.json",
"junit:src/main/java/results/cucumber-reports/cucumber-results.xml"
},
features = { "src/main/java/com/AutoBots/cucumber/faeture" },
glue = { "com.AutoBots.cucumber.steps" },
tags = {"@Autothon"})

public class RunCucumberFeatures extends ExtendedTestNGRunner
{
	
	public static Properties locators;
	public static Object obj;
	
	@SuppressWarnings("deprecation")
	@Parameters({"appmode"})
	@BeforeSuite
	public void beforeSuite(String appmode) throws FileNotFoundException, IOException, ParseException
	{
		JSONParser parser = new JSONParser();		
				
		obj = parser.parse(new FileReader(System.getProperty("user.dir")+"//src//main//java//com//AutoBots//resources//Debug_OR.json"));
				
		 	
		
	}
	
	

}
