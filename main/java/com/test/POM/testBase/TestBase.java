package com.test.POM.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.POM.customListners.WebEvenListener;
import com.test.POM.excelReader.Excel_Reader;

public class TestBase 
{
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	public WebDriver dr;
	//String browser = "chrome";
	//String url= "http://automationpractice.com/index.php";
	Excel_Reader excel;
	//listner lis;	
	public EventFiringWebDriver driver;
	public WebEvenListener eventlistener;
	public static ExtentReports extent;
	public static ExtentTest test;
	Properties OR = new Properties();
	
	public void selectBrowser(String browser)     // For Browsers
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\jars\\chromedriver\\chromedriver.exe");
			log.info("Creating object of " +browser);
		    dr = new ChromeDriver();
		    driver = new EventFiringWebDriver(dr);
		    eventlistener = new WebEvenListener();
		    driver.register(eventlistener);
		}
		else if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir")+"\\src\\jars\\geckodriver-v0.16.1-win64\\geckodriver.exe");
			log.info("Creating object of " +browser);
		    dr = new FirefoxDriver();
		    driver = new EventFiringWebDriver(dr);
		    eventlistener = new WebEvenListener();
		    driver.register(eventlistener);
		}
	}
	
	public void loadData() throws IOException {    // To create Static objects 
		
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\POM\\config\\config.properties");
		FileInputStream f = new FileInputStream(file);
		OR.load(f);
	}
		
	public void getUrl(String url)   //URL
	{ 
		driver.get(url);
		log.info("Navigating to " +url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);	
	}
	
	public void init() throws IOException    // Main method for initialising browser / URL / Log.class
	{
		loadData(); 
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/main/java/com/test/POM/Report/test.html", false);   //false means will not be overwritten  //True everything wil be overwritten
		selectBrowser(OR.getProperty("browser"));
		//lis = new listner(driver);
		getUrl(OR.getProperty("url"));
		String log4jconfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jconfPath);
	}
	
	public String[][] getData(String ExcelName,String sheetName)      // Method for Excel Sheets
	{
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\test\\POM\\data\\" + ExcelName;
		excel = new Excel_Reader(path);
		String [][] data = excel.getDataFromSheet(sheetName, ExcelName);
		return data;
	}
	
	public void getScreenShot(String Name)   // ScreenShot
	{
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/POM/screenshot/";
			File destFile = new File((String) reportDirectory +"_"+  Name + "_" + formater.format(calender.getTime()) + ".png");
			FileUtils.copyFile(src, destFile);
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "'height='100' width='100'/> </a>"); // For emailable report
		} catch (IOException e) {
				e.printStackTrace();
		}     
	}
	
	public void waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element) {     // Wait for visibility
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public WebElement waitForElement(WebDriver driver, WebElement element, long timeOutInSeconds) {    // Wait for element clickability
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
	
	public  Iterator<String> getAllWindows(){          // Window Handles
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows .iterator();
		return itr;
	}
	
	public String captureScreen(String fileName) {    //Extent
		if (fileName == "") 
		{
			fileName = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/POM/screenshot/";
			destFile = new File((String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}
	
	public void getresult(ITestResult result) {              //Extent
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
			String Screen = captureScreen("");
			test.log(LogStatus.FAIL, test.addScreenCapture(captureScreen(Screen)));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}
	
	@AfterMethod()   //Extent
	public void afterMethod(ITestResult result) 
	{
		getresult(result);
	}

	@BeforeMethod()  //Extent
	public void beforeMethod(Method result) 
	{
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	@AfterClass(alwaysRun = true)  //Extent
	public void endTest() 
	{
		closeBrowser();
	}
	
	public void closeBrowser() {   //Extent
		driver.quit();
		log.info("browser closed");
		extent.endTest(test);
		extent.flush();
	}
}
