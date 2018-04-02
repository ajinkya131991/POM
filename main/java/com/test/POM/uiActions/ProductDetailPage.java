package com.test.POM.uiActions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.POM.testBase.TestBase;

public class ProductDetailPage extends TestBase 
{
	WebDriver driver;
	
	public final String FadedShortSleeveTshirts = "Faded Short Sleeve T-shirts"; //Women
	public final String Blouse = "Blouse"; //Women
	public final String PrintedDress  = "Printed Dress";
	public final String PrintedChiffonDress = "Printed Chiffon Dress";  
	
	
	@FindBy(xpath=".//*[@id='center_column']/ul/li")
	List<WebElement> products;
 
	//constructor
	public ProductDetailPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void selectProduct(String product) {   // Add to cart TC001
		System.out.println("driver:-"+driver);
		driver.findElement(By.xpath(".//*[@id='center_column']/ul/li/div/div[2]/h5/a[contains(text(),'"+product+"')]")).click();
	}
	
	public List<WebElement> selectProduct(){
		List<WebElement> element = products;
		return element;
	}
	
	
}
