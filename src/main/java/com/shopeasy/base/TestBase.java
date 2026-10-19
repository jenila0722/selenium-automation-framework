package com.shopeasy.base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shopeasy.driver.DriverFactory;
import com.shopeasy.utils.ConfigReader;

public class TestBase 
{
	protected WebDriver driver;
	protected Properties prop;
	protected WebDriverWait wait;
	
	protected ConfigReader configReader;
	
	public TestBase()
	{
		configReader =new ConfigReader();
		prop =configReader.getProperties();
	}
	
	public void initialization() 
	{
		String browsername= prop.getProperty("browser");
		String url=prop.getProperty("url");
		driver= DriverFactory.createDriver(browsername);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(url);
	}
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	public void tearDeown()
	{
		if(driver!=null)
		{
			driver.quit();
			driver=null;
			
		}
	}
}