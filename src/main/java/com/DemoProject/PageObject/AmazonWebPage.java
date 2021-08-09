package com.DemoProject.PageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonWebPage {
	WebDriver ldriver;
	public AmazonWebPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver,this);
	}
	
	@FindBy(id="twotabsearchtextbox")
	WebElement AmazonSearchBar;
	
	public void SearchBook(String bookName) {
		AmazonSearchBar.sendKeys(bookName);
		AmazonSearchBar.sendKeys(Keys.ENTER);
	}
}
