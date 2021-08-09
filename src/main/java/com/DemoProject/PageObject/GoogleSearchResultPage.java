package com.DemoProject.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchResultPage {
	
	WebDriver ldriver;
	public GoogleSearchResultPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath = "//h3[text()='Amazon.in']")
	WebElement AmazonLink;
	
	public void OpenAmazon() {
		AmazonLink.click();
	}
	
}
