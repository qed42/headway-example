package com.qed42.qa.pageobjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qed42.qa.driver.DriverManager;

public class SearchResultPage extends HomePage{
	
	private By searchResult = By.xpath("//div[@data-component-type='s-search-result']//h2/a/span");
	
	public SearchResultPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Get product title of first match in search result
	 * @return
	 */
	public String getSearchResult() {
		return driver.findElement(searchResult).getText();
	}

	/**
	 * Goto Product Details Page of the selected product
	 * @param productName
	 * @return
	 */
	public ProductDetailsPage goToProductPage(String productName) {
		List<WebElement> productList = driver.findElements(searchResult);
		for (WebElement product : productList) {
			if (product.getText().equalsIgnoreCase(productName)) {
				product.click();
				//Switching to new opened tab
				ArrayList<String> tabHandles = new ArrayList<String>(driver.getWindowHandles());
				driver.close();
				driver.switchTo().window(tabHandles.get(1));
				break;
			}
		}
		return new ProductDetailsPage(DriverManager.getDriver());
	}	
	
}
