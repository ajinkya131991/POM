package com.test.POM.addToCart;

import java.io.IOException;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

import com.test.POM.testBase.TestBase;
import com.test.POM.uiActions.Homepage;
import com.test.POM.uiActions.ProductDetailPage;

public class TC001_VerifyAaddToCart extends TestBase 
{
	Homepage homepage;
	ProductDetailPage ProductDetailsPage;
	@BeforeClass
	public void setUp() throws IOException {
      init();
      System.out.println("URL");
      //org.openqa.selenium.InvalidSelectorException: 
	}

	@Test
	public void verifyAaddToCart() {
		try {
			log.info("=======Starting verifyAaddToCart test========");
			homepage = new Homepage(driver);
			homepage.clickOnNavigationMenu(homepage.Dresses);
			homepage.clickOnProductInDresssSection(homepage.EveningDresses);
			ProductDetailsPage = new ProductDetailPage(driver);
			ProductDetailsPage.selectProduct(ProductDetailsPage.PrintedDress);
			
			getScreenShot("verifyAaddToCart");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
            getScreenShot("verifyAaddToCart");
		}
		
		/*@AfterClass
		public void endTest()
		{
			closeBrowser();
		}*/
	}
}
