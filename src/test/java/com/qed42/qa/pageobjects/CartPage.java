package com.qed42.qa.pageobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CartPage extends HomePage{
	
	private By shoppingCartList = By.xpath("//*[@id='sc-active-cart']//img[@class='sc-product-image']");
	private By quantitySelectList = By.id("quantity");
	private By deleteLinkList = By.cssSelector("div[id='sc-active-cart'] input[value='Delete']");
	private By proceedToCheckout = By.cssSelector("input[name='proceedToRetailCheckout']");
	private By headerGreetings = By.xpath("//span[@id='nav-link-accountList-nav-line-1']");
	
	public CartPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Get list of products title added to cart
	 * @return
	 */
	public List<String> getCartProducts()
	{
        List<WebElement> cartList = driver.findElements(shoppingCartList);
        if(cartList.size()>=1)
        {
        	List<String> productNameList = new ArrayList<>();
        	for (WebElement cartItem : cartList) {
        		String innerText = cartItem.getAttribute("alt").split(",")[0];
        		productNameList.add(innerText);
        	}
        	return productNameList;
        }
        else
        {
            List<String> emptyList = Collections.emptyList();
            return emptyList;
        }
        
	}
	
	/**
	 * Check if cart contains the product
	 * @param product
	 * @return
	 */
	public boolean cartContains(String product) {
        return getCartProducts().contains(product);
    }
	
	/**
	 * Get the quantity of the product in the cart
	 * @param product
	 * @return
	 */
	public String getQuantity(String product)
	{
		List<WebElement> quantityList = driver.findElements(quantitySelectList);
		int index = getCartProducts().indexOf(product);
        WebElement quantityDropdown = quantityList.get(index);
        Select dropdown = new Select(quantityDropdown);
		return dropdown.getFirstSelectedOption().getAttribute("value");
	}
	
	/**
	 * Update quantity of the product in the cart
	 * @param product
	 * @param quantity
	 * @return
	 */
	public CartPage updateQuantity(String product, String quantity)
	{
		List<WebElement> quantityList = driver.findElements(quantitySelectList);
		int index = getCartProducts().indexOf(product);
		WebElement quantityDropdown = quantityList.get(index);
		//quantityDropdown.click();
		
		Select dropdown = new Select(quantityDropdown);
        if (!quantityDropdown.isSelected()) {
            dropdown.selectByVisibleText(quantity);
        }
		return this;
	}
	
	/**
	 * Delete the product from the cart
	 * @param product
	 * @return
	 */
	public CartPage deleteCart(String product)
	{
		List<WebElement> deleteList = driver.findElements(deleteLinkList);
		int index = getCartProducts().indexOf(product);
		WebElement quantityDropdown = deleteList.get(index);
		quantityDropdown.click();
		return this;
	}
	
	/**
	 * Goto checkout page
	 */
	public void goToCheckoutPage()
	{
		if(driver.findElement(headerGreetings).getText().contains("sign in"))
		{   
			driver.findElement(proceedToCheckout).click();
			System.out.println("Please sign in to place order");
		}
		else
		{
			driver.findElement(proceedToCheckout).click();
			System.out.println("You can place order");
		}
	}
	
}
