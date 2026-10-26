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

public class ProductOrderSuccessfullyTest extends BaseTest {

	HomePage homePage;
	LoginPage loginPage;
	CartPage cartPage;
	CheckOutPage checkoutPage;
	OverviewPage overviewPage;
	ConfirmationPage confirmationPage;

	@BeforeMethod
	public void setup() {
		cartPage = new CartPage(driver);
		loginPage = new LoginPage(driver);
		checkoutPage = new CheckOutPage(driver);
		overviewPage = new OverviewPage(driver);
		confirmationPage = new ConfirmationPage(driver);
		homePage = loginPage.login(ConfigReader.getProperties("username"), ConfigReader.getProperties("password"));
	}

	@Test
	public void productsOrderedSuccessfully() {
		// expected products of homepage means user selected products
		List<String> expectedProducts = homePage.addTwoProducts();
		System.out.println(expectedProducts + " ,");
		System.out.println(driver.getCurrentUrl());
		// click on cart icon
		homePage.clickCartIcon();
		System.out.println(driver.getCurrentUrl());
		System.out.println("clicked on cart icon");
		// actual products means user selected products in cart page
		List<String> actualProductsInCartpage = cartPage.getCartProducts();
		System.out.println(actualProductsInCartpage + " ,");
		// validate cartpage products and homepage products should match
		Assert.assertEquals(actualProductsInCartpage, expectedProducts, "Products are not same");
		// click on checkout button of cart page it will return object of checkout page
		checkoutPage = cartPage.clickOnCheckoutPageBtn();
		System.out.println(driver.getCurrentUrl());
		System.out.println("clicked on checkout button");
		// Enter user credentials first name, Last name, postalCode
		checkoutPage.checkout("Gitesh", "Lakhe", "413512");
		System.out.println("user info filled");
		// click on continue button of checkout page it will return object of overview
		// page
		// overviewPage = checkoutPage.clickonContinueBtn();
		// actual overviewpage products
		List<String> actualProductsInOverviewPage = overviewPage.productsInOverviewPage();
		System.out.println(actualProductsInOverviewPage + " ,");
		// validate overview page and actual cart page are matched or not
		Assert.assertEquals(actualProductsInOverviewPage, actualProductsInCartpage,
				"cartPage products and overview products are matching");
		System.out.println("Almost reach at overview page");
		overviewPage.clickOnFinishBtn();

		String actualMsgAfterPayment = confirmationPage.getTextMsg();
		Assert.assertEquals(actualMsgAfterPayment, "Thank you for your order!", "Confirmation msg are mismatch");
	}
	
}
