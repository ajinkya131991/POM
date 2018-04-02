package com.test.POM.addToCart;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.POM.testBase.TestBase;
import com.test.POM.uiActions.AddToCartPage;
import com.test.POM.uiActions.Homepage;
import com.test.POM.uiActions.ProductDetailPage;

public class TC002_VerifyFacebookLink extends TestBase
{
	Homepage homepage;
	ProductDetailPage ProductDetailsPage;
	AddToCartPage addToCartPage;

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
			addToCartPage = new AddToCartPage(driver);
			addToCartPage.clickonFacebooklink();
			
			
			Iterator<String> itr = getAllWindows();
			
			String pw = itr.next();
			String cw = itr.next();
			
			driver.switchTo().window(cw);
			boolean status = addToCartPage.verifyfacebookMessage();
			driver.switchTo().window(pw);
			Assert.assertEquals(true, status);
			System.out.println("True");
			
			
			getScreenShot("verifyAaddToCart");
		} catch (Exception e) {
			e.printStackTrace();
            getScreenShot("verifyAaddToCart");
		}	
}
}
