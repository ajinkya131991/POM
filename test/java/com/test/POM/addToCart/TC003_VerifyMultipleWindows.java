package com.test.POM.addToCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.POM.testBase.TestBase;
import com.test.POM.uiActions.Homepage;
import com.test.POM.uiActions.ProductDetailPage;

//   -------------------   Demo code for Handling Multiple Windows ------------------------// 

public class TC003_VerifyMultipleWindows extends TestBase
{
	List<String> windowids = new ArrayList<String>();
	
	@BeforeClass
	public void setUp() throws IOException {
       
	}

	@Test
	public void verifyAaddToCart() 
	{
		Iterator<String> itr = getAllWindows();
		
		while(itr.hasNext())
		{
			windowids.add(itr.next());
		}
		
		driver.switchTo().window(windowids.get(6));
		
		driver.switchTo().window(windowids.get(4));
		
		driver.switchTo().window(windowids.get(8));
		
		
	}

}
