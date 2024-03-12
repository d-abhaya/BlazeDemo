package com.blazedemo.pageElements;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.blazedemo.abstractUtilities.AbstractUtilities;

public class BlazedemoHomePage extends AbstractUtilities
{
	WebDriver driver;

	public BlazedemoHomePage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h1[text()='Welcome to the Simple Travel Agency!']")
	public 
	WebElement homePageTitle ;
	
	@FindBy(linkText="destination of the week! The Beach!") 
	WebElement destinationOfTheWeekLink;
	
	@FindBy(name="fromPort") 
	WebElement departureCityDropdown;
	
	@FindBy(name="toPort") 
	WebElement destinationCityDropdown;
	
	@FindBy(css="input[value='Find Flights']") 
	WebElement findFlightsButton;
	
	public void selectDeptAndDestCitiesByName(String deptCity,String destCity) 
	{
		departureCityDropdown.sendKeys(deptCity);
		destinationCityDropdown.sendKeys(destCity);
	}
	
	public void clikFindFlightsBtn() 
	{
		findFlightsButton.click();
	}
	
	public void clickdestinationOfTheWeekLink() 
	{
		destinationOfTheWeekLink.click();
	}
	
	public String getHomePageTitle() 
	{
		return homePageTitle.getText();
	}
	
	public void switchToChildTab(String ParentTabHandle) 
	{
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> itr = tabs.iterator();
		while(itr.hasNext()) 
		{
		  String childTab = itr.next();
		   if(!ParentTabHandle.equals(childTab)) 
		   { 
			driver.switchTo().window(childTab); 
		   } 
		}
	}
}
