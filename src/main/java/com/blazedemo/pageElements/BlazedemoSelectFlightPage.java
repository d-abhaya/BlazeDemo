package com.blazedemo.pageElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.blazedemo.abstractUtilities.AbstractUtilities;

public class BlazedemoSelectFlightPage extends AbstractUtilities
{

	WebDriver driver;

	public BlazedemoSelectFlightPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table[@class= 'table']//td[6]") 
	List<WebElement> priceList ;
	
	By ChooseFlightFromPrice = By.xpath("//input[@value='Choose This Flight']");
	
	public void clickChooseFlightofLowestPrice() 
	{
		double minPrice = Double.MAX_VALUE;
		for (WebElement priceEle : priceList) 
		{
            double price = Double.parseDouble(priceEle.getText().replace("$", ""));
            if (price < minPrice) 
            {
                minPrice = price;
                priceEle.findElement(ChooseFlightFromPrice).click();
            }
		}
	}
}
