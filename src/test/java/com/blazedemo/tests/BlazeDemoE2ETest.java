package com.blazedemo.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class BlazeDemoE2ETest 

{
	
	
	public static void main(String args[]) 
	{
		String url = "https://blazedemo.com/index.php";
		String expHomePageTitle = "The is a sample site you can test with BlazeMeter!";
		System.setProperty("webdriver.chrome.driver", "D:\\Projects\\BlazeEndToEnd\\drivers\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// Open the website
		driver.get(url);
		//Verify title displaying on homepage
		String actHomePageTitle = driver.findElement(By.xpath("//p[text()='The is a sample site you can test with BlazeMeter! ']")).getText();
		Assert.assertEquals(actHomePageTitle, expHomePageTitle,"Homepage title is not displaying corectly");
		// Verify Home Page title
		//Assert.assertEquals(driver.getTitle(), "Welcome to the Simple Travel Agency!", "Incorrect Home Page title");
		// Click on destination of the week hyperlink
		//driver.findElement(By.linkText("destination of the week! The Beach!")).click();
		//get parent window handle
		//String parentTab = driver.getWindowHandle();
		//get the list of tabs and switch to the child tab
		//Set<String> tabs = driver.getWindowHandles();
		//Iterator<String> itr = tabs.iterator();
		/*
		 * while(itr.hasNext()) { String childTab = itr.next();
		 * if(!parentTab.equals(childTab)) { driver.switchTo().window(childTab); } }
		 */
		//validate the String vacation in the URL
		//Assert.assertTrue(driver.getCurrentUrl().contains("vacation"), "URL doesn't contain the Sting vacation");
		
		//navigate back to homepage 
		//driver.switchTo().window(parentTab);
		
		// Select 'Mexico City' as departure city and 'London' as destination city
        WebElement departureCityDropdown = driver.findElement(By.name("fromPort"));
        departureCityDropdown.sendKeys("Mexico City");
        WebElement destinationCityDropdown = driver.findElement(By.name("toPort"));
        destinationCityDropdown.sendKeys("London");
		
        // Click 'Find Flights'
        WebElement findFlightsButton = driver.findElement(By.cssSelector("input[value='Find Flights']"));
        findFlightsButton.click();

        // Select the flight with lowest price by clicking 'Choose This Flight'
        WebElement chooseFlightButton = driver.findElement(By.xpath("//table//tr[2]//input[@type='submit']"));
        chooseFlightButton.click();
		
     // Check if 'Total Cost' field is available with price in xxx.xx format
        WebElement totalCostElement = driver.findElement(By.xpath("//p[text()='Please submit the form below to purchase the flight.']/preceding-sibling::p/em"));
        String totalCost = totalCostElement.getText();
        if (totalCost.matches("\\d{1,3}\\.\\d{2}")) {
            System.out.println("Scenario 3: Total Cost format is xxx.xx.");
        } else {
            System.out.println("Scenario 3: Total Cost format verification failed.");
        }
        
        // Click 'Purchase Flight' button
        WebElement purchaseFlightButton = driver.findElement(By.cssSelector("input[value='Purchase Flight']"));
        purchaseFlightButton.click();

        // Check if the user is navigated to the Purchase Confirmation page
        if (driver.getCurrentUrl().contains("confirmation")) {
            System.out.println("Scenario 4: User navigated to Purchase Confirmation page.");
        } else {
            System.out.println("Scenario 4: Purchase Confirmation page verification failed.");
        }

        // Store the 'Id' in the console for future reference
        WebElement idElement = driver.findElement(By.xpath("//table//tr[descendant::td[contains(text(), 'Id')]]/td[2]"));
        String purchaseId = idElement.getText();
        System.out.println("Purchase ID: " + purchaseId);

        // Close the browser
        driver.quit();
	}
}
