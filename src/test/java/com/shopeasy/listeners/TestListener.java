package com.shopeasy.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.shopeasy.base.TestBase;
import com.shopeasy.utils.ScreenshotUtils;

public class TestListener implements ITestListener{

	@Override
	public void onTestFailure(ITestResult result)
	{
		Object testclass= result.getInstance();
		
		TestBase testBase =(TestBase) testclass;
		
		WebDriver driver =testBase.getDriver();
		
		String screenshotname= result.getName();
		
		ScreenshotUtils.captureScreenshot(driver, screenshotname);
		
	}

	
}
