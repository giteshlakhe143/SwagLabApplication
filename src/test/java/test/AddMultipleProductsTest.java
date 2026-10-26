package test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import config.ConfigReader;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

public class AddMultipleProductsTest extends BaseTest{

	HomePage homePage;
	LoginPage loginPage;
	CartPage cartPage;
	@BeforeMethod
	public void setup() {
		loginPage = new LoginPage(driver);
		cartPage = new CartPage(driver);
		homePage = new HomePage(driver);
		homePage = loginPage.login(ConfigReader.getProperties("username"),
				ConfigReader.getProperties("password"));
		
	}
	// select multiple products and validate in cart page
	@Test
	public void validateProductsInCart() {
		
		List<String> expectedProducts = homePage.addProductsToCart();
		System.out.println(expectedProducts);
		cartPage = homePage.clickCartIcon();
		List<String> actualProducts = cartPage.getAddedProducts();
		System.out.println(actualProducts);
		Assert.assertEquals(actualProducts, expectedProducts);
	}
}
