package com.test.POM.uiActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.POM.testBase.TestBase;

public class AddToCartPage extends TestBase 
{
	WebDriver driver;
	
	/*@FindBy(xpath=".//*[@id='social_block']/ul/li[1]/a/span[text()='Facebook']")
	WebElement facebookLink;
	
	@FindBy(xpath=".//*[@id='social_block']/ul/li[1]/a/span[text()='Twitter']")
	WebElement TwitterLink;
	
	@FindBy(xpath=".//*[@id='social_block']/ul/li[1]/a/span[text()='Youtube']")
	WebElement YoutubeLink;*/
	
	//@FindBy(xpath="")
	//WebElement AddToCart;
	
/*	@FindBy(xpath=".//*[@id='blueBarDOMInspector']/div/div[1]/div/div[1]/h1/a/i/u")
	WebElement FacebookVerify;*/
	
	public AddToCartPage (WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickonFacebooklink()
	{
		
		driver.findElement(By.cssSelector(".facebook>a")).click();	
	}
	
	public boolean verifyfacebookMessage()
	{
		try {
			driver.findElement(By.cssSelector(".fb_logo.img.sp_apXA4TNie-C.sx_d16af2")).isDisplayed();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	
	}
	
	public void clickonTwitterLink()
	{
		driver.findElement(By.cssSelector(".twitter>a")).click();
	}
	public boolean verifytwitterMessage()
	{
		try {
			driver.findElement(By.xpath(".//*[@id='global-nav-home']/a/span[1]")).isDisplayed();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	public void clickonYoutubeLink()
	{
		driver.findElement(By.cssSelector(".youtube>a")).click();
	}
	
	public boolean verifyyoutubeMessage()
	{
		try {
			driver.findElement(By.cssSelector(".logo.masthead-logo-renderer-logo.yt-sprite")).isDisplayed();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	
	
}}
