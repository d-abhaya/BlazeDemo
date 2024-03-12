package com.blazedemo.pageElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.blazedemo.abstractUtilities.AbstractUtilities;

public class BlazeDemoPurchaseFlightPage extends AbstractUtilities

{

	WebDriver driver;
	public BlazeDemoPurchaseFlightPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//p[text()='Please submit the form below to purchase the flight.']/preceding-sibling::p/em") 
	WebElement totalCost;
	
	@FindBy(css="input[value='Purchase Flight']") 
	WebElement purchaseFlightButton;
	
	public void clickOnPurchaseFlightButton() 
	{
		purchaseFlightButton.click();
	}
	
	public String getTotalCost() 
	{
		return totalCost.getText();
	}
	
	
}
