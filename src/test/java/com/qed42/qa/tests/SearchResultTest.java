package com.qed42.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.qed42.qa.driver.DriverManager;
import com.qed42.qa.pageobjects.HomePage;
import com.qed42.qa.pageobjects.SearchResultPage;
import com.qed42.qa.reportmanager.Report;

@Listeners(com.qed42.qa.utilities.TestListener.class)

public class SearchResultTest extends BaseTest {
	
	/**
	 * Test to search a keyword and verify result 
	 * @throws InterruptedException
	 */
	@Test
	public void testSearchResult()
	{
		HomePage homePage = new HomePage(DriverManager.getDriver());
		homePage.visitHomePage();
		Report.log(Status.INFO, "Navigated to the homepage");
		
		SearchResultPage searchPage = homePage.searchFor("Macbook Air");
		Report.log(Status.INFO, "Search keyword is entered and search button is clicked");
		Assert.assertTrue(searchPage.getSearchResult().contains("MacBook Air"));
		Assert.assertEquals("Amazon.in : Macbook Air", DriverManager.getDriver().getTitle());
		Report.log(Status.INFO, "Search result is displayed");
	}

}
