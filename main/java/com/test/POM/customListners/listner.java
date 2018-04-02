package com.test.POM.customListners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.test.POM.testBase.TestBase;

public class listner extends TestBase implements ITestListener //ItestListener is a interface 
{
	/*WebDriver driver;
	
	public listner(WebDriver driver)
	{
		this.driver=driver;
	}*/
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result1)
	{
		if(result1.isSuccess())
		{
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		
	    System.out.println("1");
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		System.out.println("2");
		
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/SuccessScreenshot/";
			System.out.println("3");
			File destFile = new File((String) reportDirectory +"_"+  result1 + "_" + formater.format(calender.getTime()) + ".png");
			System.out.println("4");
			FileUtils.copyFile(src, destFile);
			
			//FileUtils.copyFile(src, "C:\\Users\\ajinkya.bh\\workspace\\POM\\src\\main\\java\\com\\test\\POM\\screenshot\\test.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		
	}}

	public void onTestFailure(ITestResult result) {
		if(!result.isSuccess())
		{
	    Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		String methodName = result.getName();
		
	
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/POM/FailureScreenshot/";
			 System.out.println("1");
			File destFile = new File((String) reportDirectory +"Failure_Screenshots"+  methodName + "_" + formater.format(calender.getTime()) + ".png");
			FileUtils.copyFile(src, destFile);
			
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "'height='100' width='100'/> </a>");
			
			//FileUtils.copyFile(src, "C:\\Users\\ajinkya.bh\\workspace\\POM\\src\\main\\java\\com\\test\\POM\\screenshot\\test.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}}
	}
		
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
