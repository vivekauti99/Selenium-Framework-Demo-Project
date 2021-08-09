package com.DemoProject.testcases;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.DemoProject.PageObject.AmazonSearchResultPage;
import com.DemoProject.PageObject.AmazonWebPage;
import com.DemoProject.PageObject.GooglePage;
import com.DemoProject.PageObject.GoogleSearchResultPage;
import com.DemoProject.Resources.Base;
import com.DemoProject.Resources.ExcelOperations;

public class AmazonSearch extends Base {
	WebDriver driver;
	@BeforeTest
	public void setup() throws IOException {
		driver = initializeDriver();
		String baseURL = prop.getProperty("url");
		driver.get(baseURL);
	}

	@Test
	public void findBook() throws IOException{
		GooglePage gp = new GooglePage(driver);
		String searchKey = prop.getProperty("searchKeyword");
		gp.search(searchKey);

		GoogleSearchResultPage sp = new GoogleSearchResultPage(driver);
		sp.OpenAmazon();

		AmazonWebPage ap = new AmazonWebPage(driver);
		String bookname = prop.getProperty("BookName");
		ap.SearchBook(bookname);

		AmazonSearchResultPage asp = new AmazonSearchResultPage(driver);
		//asp.chooseCategory();

		ExcelOperations eo = new ExcelOperations();

		List<WebElement> SearchResult = asp.searchBook();
		Map<Integer, Object[]> Data = new TreeMap<Integer, Object[]>();
		Data.put(0,new Object[] {"Sr. No","Book Name","Book Price","Purchase Link"});
		int count = 0;
		for (int i = 0; i < SearchResult.size(); i++) {
			if (SearchResult.get(i).findElement(asp.getBookname()).getText().equals(bookname)) {
				int p = 0;
				try {
					String price = SearchResult.get(i).findElement(asp.getPrice()).getText();
					p = Integer.parseInt(price);
				} catch (NoSuchElementException e) {
					e.getStackTrace();
					continue;
				}
				count++;
				String Website = SearchResult.get(i).findElement(asp.getWebsite()).getAttribute("href");		
				Data.put(i+1,new Object[] {count,bookname,p,Website});
				eo.writeToExcel(Data);
			}
		}
		System.out.println("Total Books Found: " + count);
		
		String Weblink = eo.readFromExcel();
		driver.get(Weblink);
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(10000);
		driver.close();
	}
}
