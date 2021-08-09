package com.DemoProject.PageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage {
	WebDriver ldriver;
	
	public GooglePage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(name="q")
	WebElement searchBar;
	
	public void search(String text) {
		searchBar.sendKeys(text);
		searchBar.sendKeys(Keys.ENTER);
	}
	 
}
