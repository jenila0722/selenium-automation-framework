package com.shopeasy.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id="user-name")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit' and @value='Login']")
	WebElement loginBtn;

	public LoginPage(WebDriver driver, WebDriverWait wait)
	{
		this.driver=driver;
		this.wait= wait;
		
		PageFactory.initElements(driver, this);
	}
	
	public void loginMethod(String uname, String pword)
	{
		wait.until(ExpectedConditions.visibilityOf(username)).sendKeys(uname);
		wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pword);
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
		
		/*JavascriptExecutor Ja = (JavascriptExecutor) driver;
		Ja.executeScript("arguments[0].click", loginBtn);*/
		
	}
	
	public String getTitleName()
	{
		String pageTitle = driver.getTitle();
		return pageTitle;
	}
	
	public boolean buttonClickable()
	{
		loginBtn.click();
		 boolean flag=loginBtn.isSelected();
		 return flag;
	}
}

