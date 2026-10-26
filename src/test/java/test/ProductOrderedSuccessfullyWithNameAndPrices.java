package test;

import java.util.Map;

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

public class ProductOrderedSuccessfullyWithNameAndPrices extends BaseTest{

	
	LoginPage loginPage;
	HomePage homePage;
	CartPage cartPage;
	CheckOutPage checkoutPage;
	OverviewPage overviewPage;
	ConfirmationPage confirmationPage;
	
	
	@BeforeMethod
	public void setup() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.login(ConfigReader.getProperties("username"),
				ConfigReader.getProperties("password"));
		cartPage = new CartPage(driver);
		checkoutPage = new CheckOutPage(driver);
		overviewPage = new OverviewPage(driver);
		confirmationPage = new ConfirmationPage(driver);
		
	}	
		
	@Test
	public void validateEndToEndFlowWithProductNameAndPrice() {
		// expected products gets added in cart 
		Map<String, String> expectedProducts = homePage.productsAddToCartWithPriceAndName();
		System.out.println("expected product "+expectedProducts);
		System.out.println("Home page url "+driver.getCurrentUrl());
		// after adding products in cart click on cart icon on homepage
		cartPage=homePage.clickCartIcon();
		// get the url for better understanding on which page we are
		System.out.println("Click on cart icon "+driver.getCurrentUrl());
		System.out.println("Cart page url "+driver.getCurrentUrl());
		// getting information about which products added in user cart
		Map<String, String> actualProductsOfCart = cartPage.getProductsInCartWithNameAndPrice();
		System.out.println("Actual products "+actualProductsOfCart);
		// validate User selected products are successfully added in their cart
		Assert.assertEquals(actualProductsOfCart, expectedProducts, "Homepage products and Cart products are different");
		System.out.println("Checkout page Url "+driver.getCurrentUrl());
		// click on checkout button on cart page
		checkoutPage = cartPage.clickOnCheckoutPageBtn();
		System.out.println("Click on cart icon");
		System.out.println("Overview page Url "+driver.getCurrentUrl());
		// Fill the user information on checkout page
		overviewPage = checkoutPage.checkout(ConfigReader.getProperties("firstName"),
				ConfigReader.getProperties("lastName"),
				ConfigReader.getProperties("pincode"));
		// gets all the products into overview page 
		Map<String, String> actualProductsOfOverview = overviewPage.overviewPageProductsWithNameAndPrice();
		System.out.println("products in overview page"+actualProductsOfOverview);
		// Validate same products are present in overview page and cart page
		Assert.assertEquals(actualProductsOfOverview, actualProductsOfCart,
				"overviewpage products and cart Page products are different");
		//click on Finish button
//		confirmationPage = overviewPage.clickOnFinishBtn();
//		// validate error msg 
//		Assert.assertEquals(confirmationPage.getTextMsg(), "Thank you for your order!", "Text does not matched");
//		homePage = confirmationPage.backToHomePage();
//		   String homePageUrl = driver.getCurrentUrl();
//		   System.out.println(homePageUrl);
//		   Assert.assertEquals(homePageUrl, "https://www.saucedemo.com/inventory.html", "User not able to navigate on HomePage");
//		   homePage.clickOnMenuBurgurBtnAndLogOut();
//		   String loginPageUrl = driver.getCurrentUrl();
//		   System.out.println(loginPageUrl);
//		   Assert.assertEquals(loginPageUrl,"https://www.saucedemo.com/", "Url Matched");
//	   
	   
	}
	
	@Test
	public void validateProductsWithPriceValidation() {
		validateEndToEndFlowWithProductNameAndPrice();
		
		double itemsTotal = overviewPage.getTotalItemPrice();
		System.out.println("Total Items price $"+itemsTotal);
		double taxprice = overviewPage.getItemTaxPrice();
		System.out.println("Applied Tax on user products $"+taxprice);
		double totalPrice = overviewPage.getTotalPrice();
		System.out.println("Final price of product with Tax $"+totalPrice);
		double expectedTotal = itemsTotal + taxprice;
		System.out.println("Expected total price $"+expectedTotal);
		System.out.println("Actual total price $"+totalPrice);
		
		Assert.assertEquals(totalPrice, expectedTotal, "Prices are not matching");
 
		confirmationPage = overviewPage.clickOnFinishBtn();
		// validate error msg 
		Assert.assertEquals(confirmationPage.getTextMsg(), "Thank you for your order!", "Text does not matched");
		homePage = confirmationPage.backToHomePage();
		   String homePageUrl = driver.getCurrentUrl();
		   System.out.println(homePageUrl);
		   Assert.assertEquals(homePageUrl, "https://www.saucedemo.com/inventory.html", "User not able to navigate on HomePage");
		   homePage.clickOnMenuBurgurBtnAndLogOut();
		   String loginPageUrl = driver.getCurrentUrl();
		   System.out.println(loginPageUrl);
		   Assert.assertEquals(loginPageUrl,"https://www.saucedemo.com/", "Url Matched");
	   
		
	}
	
	
		
		
	}


 