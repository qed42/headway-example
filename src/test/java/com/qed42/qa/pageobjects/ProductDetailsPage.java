package com.qed42.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.qed42.qa.driver.DriverManager;

public class ProductDetailsPage extends HomePage{
	
	private By btnAddToCart = By.cssSelector("#add-to-cart-button");
	private By quantityOfProduct = By.id("quantity");
	private By successMsgAddToCart = By.cssSelector(".sw-atc-text");
	private By btnGoToCart = By.xpath("//*[@id=\"sw-gtc\"]/span/a");
	
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Clicks Add to Cart button
	 * @throws InterruptedException
	 */
	public void addToCart() throws InterruptedException {
		driver.findElement(btnAddToCart).click();
	}
	
	/**
	 * Updates the quantity of the product in the cart
	 * @param quantity
	 * @return
	 */
	public ProductDetailsPage selectQuantity(String quantity) {
		WebElement quantityDropdown = driver.findElement(quantityOfProduct);
		quantityDropdown.click();
		Select dropdown = new Select(quantityDropdown);
        
        if (!quantityDropdown.isSelected()) {
            dropdown.selectByVisibleText(quantity);
        }
		return this;
	}
	
	/**
	 * Check if add to cart successful message is displayed
	 * @return
	 */
	public boolean isAddToCartMessageDisplayed()
	{
		if(driver.findElement(successMsgAddToCart).getText().equalsIgnoreCase("Added to Cart")) {
			return true;
		}	
		return false;	
	}
	
	/**
	 * Goto Cart Page
	 * @return
	 */
	public CartPage goToCartPage()
	{
		driver.findElement(btnGoToCart).click();
		return new CartPage(DriverManager.getDriver());
	}
}
