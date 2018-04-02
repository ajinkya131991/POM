package com.test.POM.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.test.POM.testBase.TestBase;

public class Homepage extends TestBase 
{
	public final Logger log = Logger.getLogger(Homepage.class.getName());
	
	public final String Women = "Women";
	public final String Dresses = "Dresses";
	public final String T_shirts = "T-shirts";
	
	public final String EveningDresses = "Evening Dresses";
	public final String SummerDresses   = "Summer Dresses";
	public final String CasualDresses   = "Casual Dresses";
	
	// Global Webdriver
	WebDriver driver;
	
	//Objects
	@FindBy(xpath=".//*[@id='header']/div[2]/div/div/nav/div[1]/a")
	WebElement signIN;
	
	@FindBy(id="email")
	WebElement Email;
	
	@FindBy(id="passwd")
	WebElement password;
	
	@FindBy(id="SubmitLogin")
	WebElement submit;
	
	@FindBy(xpath=".//*[@id='email_create']")
	WebElement EmailAddress;
	
	@FindBy(xpath=".//*[@id='center_column']/div[1]/ol/li")
	WebElement AuthenticationError;
	
	@FindBy(xpath=".//*[@id='SubmitCreate']")
	WebElement CreateAccountButton;
	
	@FindBy(id="id_gender1")
	WebElement RadioButton;
	
	@FindBy(id="customer_firstname")
	WebElement FirstName;
	
	@FindBy(id="customer_lastname")
	WebElement LastName;
	
	@FindBy(id="email")
	WebElement Email1;
	
	@FindBy(id="passwd")
	WebElement Password;
	
	@FindBy(xpath=".//*[@id='days']")
	WebElement Select;
	
	@FindBy(id="months")
	WebElement select1;
	
	@FindBy(xpath=".//*[@id='years']")
	WebElement select2;
	
	@FindBy(id="address1")
	WebElement address;
	
	@FindBy(id="city")
	WebElement city;
	
	@FindBy(id="id_state")
	WebElement select3;
	
	@FindBy(id="postcode")
	WebElement postcode;
	
	@FindBy(id="phone_mobile")
	WebElement phone_mobile;
	
	@FindBy(id="submitAccount")
	WebElement submitAccount;
	
	@FindBy(xpath=".//*[@id='header']/div[2]/div/div/nav/div[2]/a")
	WebElement signOut;
	
	@FindBy(xpath=".//*[@id='columns']/div[1]/span[2]")
	WebElement MyAccount;
	
	//constructor for page factory...Need to initialize above objects otherwise null pointer exception occurs 
	public Homepage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);  //this - refers current class objects
	}
	
	//Method
	public void LoginApplication(String emailaddress,String passwords)            //TC003
	{
		signIN.click();
		log.info("Clicked On SignIn and object is " + signIN.toString() );
		Email.sendKeys(emailaddress);
		log.info("Email Address Entered " + emailaddress.toString() );
		password.sendKeys(passwords);
		log.info("Password Entered " + passwords.toString() );
		//waitForElement(driver,3,submit);
		submit.click();
		log.info("Clicked On Submit button and object is " + submit.toString());
	}
	
	public String getInvalidtexts(){                                             
		log.info("Error Message is " +AuthenticationError.getText());
		return AuthenticationError.getText();
	}
	
	public void createAccount(String email,String Fname,String Lname,String pass,String addresss,String City,String code,String Phone)
	{
		signIN.click();
		EmailAddress.click();
		EmailAddress.sendKeys(email);
		CreateAccountButton.click();
		RadioButton.click();
		FirstName.click();
		FirstName.sendKeys(Fname);
		LastName.click();
		LastName.sendKeys(Lname);
		Email1.click();
		Password.click();
		Password.sendKeys(pass);
		Select day = new Select(Select);
		day.selectByIndex(13);
		Select month = new Select(select1);
		month.selectByIndex(10);
		Select year = new Select(select2);
		//year.selectByIndex(1991);
		year.selectByValue("1991");
		address.click();
		address.clear();
		address.sendKeys(addresss);
		city.click();
		city.sendKeys(City);
		Select state = new Select(select3);
		state.selectByVisibleText("New York");
		postcode.click();
		postcode.sendKeys(code);
		phone_mobile.click();
		phone_mobile.sendKeys(Phone);
		submitAccount.click();
		}
	
	public boolean verifyLogOutDisplay()
	{
		try {
			signOut.isDisplayed();
			return true;
		} catch (Exception e) {e.printStackTrace();
		return false;
		}
	}
	
	public void clickOnLogOut()
	{
		signOut.click();
	}
	
	public boolean getRegistrationFailure()
	{
		try {
			MyAccount.isDisplayed();
			return true;
		} 
		catch (Exception e) {e.printStackTrace();
		return false;
		}
	}
	
	public void clickOnNavigationMenu(String menuName){	     // Add to cart TC001
		System.out.println("Hello..");
		driver.findElement(By.xpath(".//*[@id='block_top_menu']/ul/li/*[contains(@title,'"+menuName+"')]")).click();	
	}
	
	public void clickOnProductInDresssSection(String product){   // Add to cart TC001
		
		waitForElement(driver, 80, driver.findElement(By.xpath(".//*[@id='categories_block_left']/div/ul/li/*[contains(text(), '"+product+"')]")));
		driver.findElement(By.xpath(".//*[@id='categories_block_left']/div/ul/li/*[contains(text(), '"+product+"')]")).click();
	}
	
	

	public void clickOnProductInWomensSection(String product){   // Add to cart TC001
		//waitForElement(driver, 80, driver.findElement(By.xpath(".//button[contains(text(),'Womens') and @aria-expanded='true']/following-sibling::ul/child::li/child::a[contains(text(),'"+product+"')]")));
		driver.findElement(By.xpath(".//button[contains(text(),'Womens') and @aria-expanded='true']/following-sibling::ul/child::li/child::a[contains(text(),'"+product+"')]")).click();
	}
	
	public void switchToFrame(){
		//driver.switchTo().frame(homePageIframe);
		
	}
	
	public void switchToDefaultContent(){
		driver.switchTo().defaultContent();
		
	}
	
	
}
