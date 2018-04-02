package com.test.POM.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.test.POM.testBase.TestBase;
import com.test.POM.uiActions.Homepage;


public class TC0003_VerifyLoginWithDifferentRecords extends TestBase
{
public static final Logger log = Logger.getLogger(TC0003_VerifyLoginWithDifferentRecords.class.getName());
	
	Homepage homepage;  //Reference
	/*String emailaddress = "test@gmailcom";
	String passwords ="password123";*/
	
	@DataProvider(name="LoginData")
	public String[][] getTestDatas()
	{
		String[][] testRecords = getData("TestData.xls","LoginTestData");
		return testRecords;
	}
	 
	@BeforeTest
	public void setUp() throws IOException
	{
		init();
	}
	
	@Test(dataProvider="LoginData")
	public void testLogins(String emailaddress,String passwords,String rumMode)
	{
		if(rumMode.equalsIgnoreCase("n")){
			throw new SkipException("User marked this record to not run");
		}
		log.info("================ Starting Test ==================");
		homepage = new Homepage(driver);
		homepage.LoginApplication(emailaddress, passwords);
		boolean status = homepage.verifyLogOutDisplay();
		getScreenShot(emailaddress);
		if(status)
		{
			homepage.clickOnLogOut();
		}
		Assert.assertEquals(status, true);
		log.info("================ Finished Test ==================");
	}
	
	@AfterTest
	public void endTest()
	{
		driver.quit();
	}
}
