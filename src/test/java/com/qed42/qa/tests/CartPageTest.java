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

public class CartPageTest extends BaseTest {
	
	/**
	 * Test to update the cart by adding multiple products and updating their quantities.
	 * @throws InterruptedException
	 */
	@Test
	public void testUpdateCart() throws InterruptedException
	{
		/* Add first product to the cart */
		HomePage homePage = new SearchResultPage(DriverManager.getDriver());
		homePage.visitHomePage();
		SearchResultPage searchPage = homePage.searchFor("harry potter books set");
		
		String productOne = "Harry Potter 1–3 Box Set: A Magical Adventure Begins";
		ProductDetailsPage productPage = searchPage.goToProductPage(productOne);
		Report.log(Status.INFO, "Navigated to the " + productOne + " page");
		
		productPage.addToCart();
		CartPage cartPage = productPage.goToCartPage();
		Assert.assertTrue(cartPage.cartContains(productOne));
		Report.log(Status.INFO, "Product " + productOne + " is added to Cart");
		
		/* Add second product to the cart */
		searchPage.searchFor("money plant");
		
		String productTwo = "Plantoos Live Money Plant Variegated with Pot";
		productPage = searchPage.goToProductPage(productTwo);
		Report.log(Status.INFO, "Navigated to the " + productTwo + " page");
		
		productPage.addToCart();
		cartPage = productPage.goToCartPage();
		Assert.assertTrue(cartPage.cartContains(productTwo));
		Report.log(Status.INFO, "Product " + productTwo + " is added to Cart");
		
		/* Update quantity of first product */
		cartPage = cartPage.updateQuantity(productOne, "3");       
        String selectedProductQuantity = cartPage.getQuantity(productOne);
        Assert.assertTrue(selectedProductQuantity.equals("3"));
        Report.log(Status.INFO, "Quantity of " + productTwo + " is updated to " + selectedProductQuantity);
	}
	
	/**
	 * Test to delete product from the cart.
	 * @throws InterruptedException
	 */
	//@Test
	public void testDeleteCart() throws InterruptedException
	{
		/* Add first product to the cart */
		SearchResultPage searchPage = new SearchResultPage(DriverManager.getDriver());
		searchPage.visitHomePage();
		searchPage.searchFor("harry potter books set");
		
		String productOne = "Harry Potter 1–3 Box Set: A Magical Adventure Begins";
		ProductDetailsPage productPage = searchPage.goToProductPage(productOne);
		Report.log(Status.INFO, "Navigated to the " + productOne + " page");
		
		productPage.addToCart();
		CartPage cartPage = productPage.goToCartPage();
		Assert.assertTrue(cartPage.cartContains(productOne));
		Report.log(Status.INFO, "Product " + productOne + " is added to Cart");
		
		/* Add second product to the cart */
		searchPage.searchFor("money plant");
		
		String productTwo = "Plantoos Live Money Plant Variegated with Pot";
		productPage = searchPage.goToProductPage(productTwo);
		Report.log(Status.INFO, "Navigated to the " + productTwo + " page");
		
		productPage.addToCart();
		cartPage = productPage.goToCartPage();
		Assert.assertTrue(cartPage.cartContains(productTwo));
		Report.log(Status.INFO, "Product " + productTwo + " is added to Cart");
		
		/* Delete second product from the cart */
		cartPage = cartPage.deleteCart(productTwo);
		Assert.assertTrue(!cartPage.cartContains(productTwo));
		Report.log(Status.INFO, "Product " + productTwo + " is deleted from the Cart");
	}
	
	//@Test
	public void testProceedToCheckout() throws InterruptedException
	{
		/* Add a product to the cart */
		SearchResultPage searchPage = new SearchResultPage(DriverManager.getDriver());
		searchPage.visitHomePage();
		searchPage.searchFor("harry potter books set");
		
		String productOne = "Harry Potter 1–3 Box Set: A Magical Adventure Begins";
		ProductDetailsPage productPage = searchPage.goToProductPage(productOne);
		Report.log(Status.INFO, "Navigated to the " + productOne + " page");
		
		productPage.addToCart();
		CartPage cartPage = productPage.goToCartPage();
		Assert.assertTrue(cartPage.cartContains(productOne));
		Report.log(Status.INFO, "Product " + productOne + " is added to Cart");
		
		cartPage.goToCheckoutPage();
		
	}
	

}
