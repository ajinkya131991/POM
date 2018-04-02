package com.test.POM.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.POM.testBase.TestBase;
import com.test.POM.uiActions.Homepage;

public class TC001_LoginWithInvalidCredential extends TestBase
{
	public static final Logger log = Logger.getLogger(TC001_LoginWithInvalidCredential.class.getName());
	
	Homepage homepage;  //Reference
	 
	@BeforeTest
	public void setUp() throws IOException
	{
		init();
	}
	
	@Test
	public void IvalidCredential() throws InterruptedException 
	{
		log.info("================ Starting Test ==================");
		homepage = new Homepage(driver); //Object of class
		homepage.LoginApplication("testgmailcom", "password123");   //Call Method
		Assert.assertEquals(homepage.getInvalidtexts() , "Invalid email address.");
		getScreenShot("verifyAaddToCart");
		log.info("================ Finished Test ==================");
	}
	
	/*@AfterTest
	public void endTest()
	{
		driver.quit();
	}*/

}
