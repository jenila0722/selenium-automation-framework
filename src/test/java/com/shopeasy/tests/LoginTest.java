package com.shopeasy.tests;import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shopeasy.base.TestBase;
import com.shopeasy.pages.LoginPage;
import com.shopeasy.utils.ExcelUtils;

public class LoginTest extends TestBase{
	LoginPage login;
	
	@BeforeMethod
	public void setup()
	{
		initialization();
		login =new LoginPage(driver,wait);
	}

	@DataProvider(name="Logindata")
	public Object[][] getLoginData() throws IOException
	{
		String filePath  = System.getProperty("user.dir")+"/src/test/resources/tesdata/Logindata.xlsx";
		
		System.out.println("Filepath:"+filePath);
		System.out.println("fileexist"+ new java.io.File(filePath).exists());
		
		return ExcelUtils.getExcelData(filePath, "Sheet1");
	}
	
	@Test(priority=1, dataProvider="Logindata")
	public void validateLogin(String usr, String pwd, String exresult)
	{
		login.loginMethod(usr, pwd);
		if(exresult.equalsIgnoreCase("Success"))
		{
			Assert.assertTrue(
					driver.getCurrentUrl().contains("inventory.html"),"Login Successed"
		
				);
			
			System.out.println("Current URL: " + driver.getCurrentUrl());
		}
		
		else if(exresult.equalsIgnoreCase("Failure"))
			{
			
			WebElement error=driver.findElement(By.xpath("//h3[@role='alert']"));
			
			boolean flag= error.isDisplayed();
			Assert.assertTrue(flag, "display the error message");
			}
	}
	
	@Test(priority=2)
	public void validatePageTitle()
	{
		String actualTitle =login.getTitleName();
		String ecpectedTitle="Swag Labs";
		Assert.assertEquals(actualTitle,ecpectedTitle);
	}
	
	@Test(priority=3)
	public void validateBtnClick()
	{
		boolean flag=login.buttonClickable();
		Assert.assertTrue(flag, "The login button is selected");

		
	}
	
	@AfterMethod
	public void cleanUp()
	{
		tearDeown();
	}

}
