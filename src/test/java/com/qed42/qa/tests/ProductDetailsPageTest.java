package com.qed42.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.qed42.qa.driver.DriverManager;
import com.qed42.qa.pageobjects.CartPage;
import com.qed42.qa.pageobjects.HomePage;
import com.qed42.qa.pageobjects.ProductDetailsPage;
import com.qed42.qa.pageobjects.SearchResultPage;
import com.qed42.qa.reportmanager.Report;

@Listeners(com.qed42.qa.utilities.TestListener.class)

public class ProductDetailsPageTest extends BaseTest {
	

	/**
	 * Test to search a keyword and add product to the cart 
	 * @throws InterruptedException
	 */
	@Test
	public void testAddToCart() throws InterruptedException
	{	
		HomePage homePage = new SearchResultPage(DriverManager.getDriver());
		homePage.visitHomePage();
		SearchResultPage searchPage = homePage.searchFor("harry potter books set");
		
		String product = "Harry Potter 1–3 Box Set: A Magical Adventure Begins";
		ProductDetailsPage productPage = searchPage.goToProductPage(product);
		Report.log(Status.INFO, "Navigated to the " + product + " page");
		
		productPage.addToCart();
		Assert.assertTrue(productPage.isAddToCartMessageDisplayed(), "Failed to add " + product + " to Cart");
		CartPage cartPage = productPage.goToCartPage();
		Assert.assertTrue(cartPage.cartContains(product));
		Report.log(Status.INFO, "Product " + product + " is added to Cart");
	}
	
	
	/**
	 * Test to search a keyword and add multiple quantities of the product to the cart
	 * @throws InterruptedException
	 */
	@Test
	public void testAddToCartMultipleQuantity() throws InterruptedException
	{	
		SearchResultPage searchPage = new SearchResultPage(DriverManager.getDriver());
		searchPage.visitHomePage();
		searchPage.searchFor("harry potter books set");
		
		String product = "Harry Potter 1–3 Box Set: A Magical Adventure Begins";
		String quantity = "2";
		ProductDetailsPage productPage = searchPage.goToProductPage(product);
		Report.log(Status.INFO, "Navigated to the " + product + " page");
		
		productPage = productPage.selectQuantity(quantity);
		productPage.addToCart();
		Assert.assertTrue(productPage.isAddToCartMessageDisplayed(), "Failed to add " + product + " to Cart");
		
		CartPage cartPage = productPage.goToCartPage();
		Assert.assertTrue(cartPage.getQuantity(product).equals(quantity));
		Report.log(Status.INFO, "Product " + product + " with quantity" + quantity +" is added to Cart");
		
	}

}
