package com.blazedemo.testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest 
{
	public WebDriver driver;
	
	public WebDriver initializeDriver() throws IOException 
	{
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
		
	}
	
	@BeforeMethod//(alwaysRun = true)
	public void lunchApplication() throws IOException 
	{
		driver = initializeDriver();
		goToApplication();
		
	}
	
	@AfterMethod//(alwaysRun = true)
	public void tearDown() 
	{
		driver.quit();
	}
	
	
	public void goToApplication() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\blazedemo\\resources\\globalData.properties");
		prop.load(fis);
		driver.get(prop.getProperty("url"));
	}
	
}
