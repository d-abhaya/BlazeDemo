package com.blazedemo.tests;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.blazedemo.pageElements.BlazeDemoConfirmationPage;
import com.blazedemo.pageElements.BlazeDemoPurchaseFlightPage;
import com.blazedemo.pageElements.BlazedemoHomePage;
import com.blazedemo.pageElements.BlazedemoSelectFlightPage;
import com.blazedemo.testComponents.BaseTest;

public class BlazeDemoEndToEndTest extends BaseTest

{
	WebDriver driver;
	BlazeDemoConfirmationPage confirmationPage;
	BlazedemoHomePage homePage;
	BlazeDemoPurchaseFlightPage purchaseFlightPage;
	BlazedemoSelectFlightPage selectFlightPage;
	
	@BeforeClass
	public void initializePages() 
	{
      
       confirmationPage = new BlazeDemoConfirmationPage(driver);
	   homePage = new BlazedemoHomePage(driver);
	   purchaseFlightPage = new BlazeDemoPurchaseFlightPage(driver);
	   selectFlightPage = new BlazedemoSelectFlightPage(driver);
	}
	
	
	@Test(dataProvider= "flightData")
	public void verifyEndToEndTest(HashMap<String,String> cities)
	{
		//Validate title on homepage
		Assert.assertEquals(homePage.getHomePageTitle() , "Welcome to the Simple Travel Agency!", "Title is not displaying correctly");
	    homePage.clickdestinationOfTheWeekLink(); 
	    String parentTab = driver.getWindowHandle(); 
	    //get the list of tabs and switch to the child tab********The below 3 lines of code will not work as the link is not redirected to a new tab
	    homePage.switchToChildTab(parentTab); 
	    //validate the String vacation in the URL 
	    Assert.assertTrue(driver.getCurrentUrl().contains("vacation"), "URL doesn't contain the Sting vacation"); 
	    //navigate back to homepage
	    driver.switchTo().window(parentTab); 
	    //Select Departure and Destination city
	    homePage.selectDeptAndDestCitiesByName(cities.get("deptCity"), cities.get("destCity"));
	    //CLick on FindFlight button
	    homePage.clikFindFlightsBtn();
	    //Select flight based on lowest price 
	    selectFlightPage.clickChooseFlightofLowestPrice();
	    //Validating the Total cost format
	    Assert.assertTrue(purchaseFlightPage.getTotalCost().matches("\\d{1,3}\\.\\d{2}"), "Total Cost is not displaying in xx.x format");
	    //Click on Purchase Flight button
	    purchaseFlightPage.clickOnPurchaseFlightButton();
	    //validating the presence of confirmation page
	    Assert.assertTrue(confirmationPage.isConfirmationPageDisplayed(), "User is not on the Confirmation page");
	    //Printing the Confirmation ID
	    System.out.println(confirmationPage.getConfirmationId());   
    }
	
	
	
	@DataProvider
    public Object[][] flightData() 
	{
		HashMap<String,String> dataSet1 = new HashMap<String,String>();
		dataSet1.put("deptCity", "Mexico City");
		dataSet1.put("destCity", "London");
		return new Object[][] {{dataSet1}};
	}

}
