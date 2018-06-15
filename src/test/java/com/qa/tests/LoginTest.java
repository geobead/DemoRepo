package com.qa.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
	
	WebDriver driver;
	
	static int passes=0;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Mr Truong\\Desktop\\selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}
	
	//Better to use IAnnotationTransformer. Scalable and don't need to write on every test
	@Test(priority=0)
	public void RetryAtRunTimeAlwaysFail() {
		Assert.assertTrue(false);
	}
	
	@Test(priority=1)
	public void RetryAtRunTimeEventuallyPass() {
		if(passes==0) {
			passes++;
			Assert.assertTrue(false);
		}
		//pass on first retry
		else {
			Assert.assertTrue(true);
		}
	}
	@Test(priority=2)
	public void RetryAtRunTimeAlwaysPass() {
		Assert.assertTrue(true);
	}
	
	//retry at test level if failed
	@Test(priority=100, retryAnalyzer=Analyzer.RetryAnalyzer.class)
	public void retryAtTestLevelAlwaysFail() {
		//always false to trigger retry
		System.out.println("retry with failure");
		Assert.assertTrue(false);
	}
	
	//retry at test level if failed
		@Test(priority=100, retryAnalyzer=Analyzer.RetryAnalyzer.class)
		public void retryAtTestLevelAlwaysPass() {
			Assert.assertTrue(true);
		}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
