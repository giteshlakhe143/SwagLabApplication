package test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import config.ConfigReader;
import pages.CartPage;
import pages.CheckOutPage;
import pages.ConfirmationPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OverviewPage;
import utiles.AlphabeticalUtils;
import utiles.PriceUtils;

public class FilterValidationTest extends BaseTest{

	LoginPage loginPage;
	HomePage homePage;
	CartPage cartPage;
	CheckOutPage checkoutPage;
	OverviewPage overviewPage;
	ConfirmationPage confirmationPage;
	
	@BeforeMethod
	public void setUp() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.login(ConfigReader.getProperties("username"),
				ConfigReader.getProperties("password"));
		cartPage = new CartPage(driver);
		checkoutPage = new CheckOutPage(driver);
		overviewPage = new OverviewPage(driver);
		confirmationPage = new ConfirmationPage(driver);
		
	}
	
	
	@Test
	public void validateLowToHighProductPriceFilter() {
	 List<Double> actualProductsPriceBeforeFilter = homePage.getProductsPriceBeforeApplyingFilter();
	 System.out.print("Actual prices of products before applying filter "+actualProductsPriceBeforeFilter+" ");
	 homePage.applyPriceLowToHighFilter();
	 List<Double> actualProductsPriceAfterFilter = homePage.getProductsPriceAfterApplyingFilter();
	 System.out.println("Actual prices of product after apply Low to High filter "+actualProductsPriceAfterFilter+" ");
	 Assert.assertNotEquals(actualProductsPriceAfterFilter, actualProductsPriceBeforeFilter, "Products price sequence has changed after click on filter");
//	 List<Double> productPriceSortedList = new ArrayList<>(priceAfterApplyingFilter);
//	 Collections.sort(productPriceSortedList);
//     Assert.assertNotSame(actualProductsPriceBeforeFilter, actualProductsPriceAfterFilter, "Products price sequence has changed after click on filter" );
	
	}
	// Validate Low To High Price product filter
	@Test
	public void verifyLowToHighPriceFilter() {
		List<Double> actualPriceBeforeClickOnLowToHighFilter = homePage.getAllProductPrices();
		System.out.println("Actual Prices before applying Low To High filter "+actualPriceBeforeClickOnLowToHighFilter+" ");
		homePage.applyPriceLowToHighFilter();
		List<Double> actualPricesAfterClickOnLowToHighFilter = homePage.getAllProductPrices();
		System.out.println("Actual price after applying Low To Highfilter "+actualPricesAfterClickOnLowToHighFilter+" ");
		Assert.assertTrue(PriceUtils.isProductPricesInAscendingOrder(actualPricesAfterClickOnLowToHighFilter), "Prices are not in ascending order");
	}
	
	// Validate High to Low Price Product filter
	@Test
	public void verifyHighToLowPriceFilter() {
		List<Double> actualPriceBeforeClickOnHighToLowFilter = homePage.getAllProductPrices();
		System.out.println("Actual prices before applying High to Low Filter "+actualPriceBeforeClickOnHighToLowFilter+" ");
		homePage.applyPriceHighToLowFilter();
		List<Double> actualpriceAfterClickOnHighToLowFilter = homePage.getAllProductPrices();
		System.out.println("Actual price after applying High To Low filter "+actualpriceAfterClickOnHighToLowFilter+" ");
		Assert.assertTrue(PriceUtils.isProductPricesInDescendingOrder(actualpriceAfterClickOnHighToLowFilter), "Price are not in descending order");
	}
	// Validate product name A to Z filter
	@Test
	public void verifyProductNameA_to_Z_filter() {
		List<String> actualProductsBeforeClickOnName_A_to_Z_filter = homePage.getAllProductsName();
		System.out.println("Actual products name before applying Name A to Z filter "+actualProductsBeforeClickOnName_A_to_Z_filter+" ");
		homePage.applyNameA_to_Zfilter();
		List<String> actualProductsNameAfterClickOnName_A_to_Z_filter = homePage.getAllProductsName();
		System.out.println("Actual products name after applying Name A to Z filter "+actualProductsNameAfterClickOnName_A_to_Z_filter+" ");
		Assert.assertTrue(AlphabeticalUtils.isInAlphabeticSequence(actualProductsNameAfterClickOnName_A_to_Z_filter), "Products names are not same");
	}
	// Validate product name Z to A filter
	@Test
	public void verifyProductNameZ_to_A_filter() {
		List<String> actualProductsBeforeClickOnName_Z_to_A_filter = homePage.getAllProductsName();
		System.out.println("Actual products name before applying Name Z to A filter "+actualProductsBeforeClickOnName_Z_to_A_filter+" ");
		homePage.applyNameZ_to_Afilter();
		List<String> actualProductAfterClickOnName_Z_to_A_filter = homePage.getAllProductsName();
		System.out.println("Actual products name after applying Name Z to A filter "+actualProductAfterClickOnName_Z_to_A_filter+" ");
		Assert.assertTrue(AlphabeticalUtils.isInAlphabeticReverseSequence(actualProductAfterClickOnName_Z_to_A_filter), "Products names mismatch or not in Alphabetic order");
	}
}
