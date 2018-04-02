package com.test.POM.homepage;

import java.io.IOException;

import org.apache.http.util.Asserts;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.POM.testBase.TestBase;
import com.test.POM.uiActions.Homepage;



public class TC0002_CreateAnAccount extends TestBase
{
public static final Logger log = Logger.getLogger(TC0002_CreateAnAccount.class.getName());
	
	Homepage homepage;  //Reference
	 
	@BeforeTest
	public void setUp() throws IOException
	{
		init();
	}
	
	@Test
	public void CreateAccount() throws InterruptedException 
	{
		log.info("================ Starting Test ==================");
		
		homepage = new Homepage(driver);
		homepage.createAccount("zzzzzffasgagdfsfasf.com", "Ajinkya", "Bhobad", "@jinkyA99","xyz xyz xyz xyz xyz xyz ","US","10000","9800000000");
		
		homepage.getRegistrationFailure();
		Assert.assertEquals(true, homepage.getRegistrationFailure());
		getScreenShot("verifyAaddToCart");
		
		
		
		
		
		log.info("================ Finished Test ==================");
	}
	
	@AfterTest
	public void endTest()
	{
		//driver.quit();
	}


}
