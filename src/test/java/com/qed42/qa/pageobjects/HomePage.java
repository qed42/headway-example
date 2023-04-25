package com.qed42.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qed42.qa.configurations.Configuration;
import com.qed42.qa.driver.DriverManager;

public class HomePage implements Configuration{
	
	WebDriver driver;
	private By inputSearch = By.id("twotabsearchtextbox");
	private By searchButton = By.id("nav-search-submit-button");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Goto homepage i.e. BASE_URL 
	 * 
	 */
	public void visitHomePage()
	{
		driver.get(BASE_URL);
	}
	
	/**
	 * Searches for keyword
	 * @return
	 */
	public SearchResultPage searchFor(String text)
	{
		driver.findElement(inputSearch).sendKeys(text);
		driver.findElement(searchButton).click();
		return new SearchResultPage(DriverManager.getDriver());
	}
}
