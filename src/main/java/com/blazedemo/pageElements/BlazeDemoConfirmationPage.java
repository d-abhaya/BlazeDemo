package com.blazedemo.pageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.blazedemo.abstractUtilities.AbstractUtilities;

public class BlazeDemoConfirmationPage  extends AbstractUtilities
{

	WebDriver driver;
	public BlazeDemoConfirmationPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//td[text()='Id']/following-sibling::td") 
	WebElement confirmationID;
	
	
	public boolean isConfirmationPageDisplayed() 
	{
        return driver.getTitle().contains("Confirmation");
    }

    public String getConfirmationId() {
        return confirmationID.getText();
    }
}
