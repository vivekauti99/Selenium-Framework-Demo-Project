package com.DemoProject.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonSearchResultPage {
	WebDriver ldriver;

	By bookname = By.cssSelector(".a-size-medium.a-color-base.a-text-normal");
	By price = By.cssSelector(".a-price-whole");
	By website = By.cssSelector(".a-link-normal.a-text-normal");

	public By getBookname() {
		return bookname;
	}

	public By getPrice() {
		return price;
	}

	public By getWebsite() {
		return website;
	}

	public AmazonSearchResultPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}

	@FindBy(xpath = "//*[@id='p_n_binding_browse-bin/1318376031']/span/a/span")
	WebElement Paperback;

	@FindBy(xpath = "//div[@data-component-type='s-search-result']")
	List<WebElement> result;

	public void chooseCategory() {
		JavascriptExecutor js = (JavascriptExecutor) ldriver;
		js.executeScript(
				"document.querySelector(`a[aria-label='See more, Format'] span[class='a-expander-prompt']`).click()");
		Paperback.click();
	}

	public List<WebElement> searchBook() {
		return result;
	}
}
