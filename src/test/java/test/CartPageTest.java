package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import config.ConfigReader;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

public class CartPageTest extends BaseTest {
	
	
	HomePage homePage;
	LoginPage loginPage;
	CartPage cartPage;
	
	
		
	@BeforeMethod	
	public void cartPageSetUp() {
		
	//	homePage = new HomePage(driver);
		cartPage = new CartPage(driver);
		loginPage = new LoginPage(driver);
        homePage = loginPage.login
        		(ConfigReader.getProperties("username"),
        		ConfigReader.getProperties("password"));
	}
	
	
	// validate same product gets added in cart page which are selected in product page
	@Test
	public void validateSameProductAddInCartPage() {
		// click on Add to Cart button of Bike Light product
		homePage.clickOnAddToCartBikeLight();
		// click on Cart Icon which are present on product page
		homePage.clickCartIcon();
		// get the product name which are added in cart 
		String actualProduct = cartPage.getCartItems();
		System.out.println("Actual products "+actualProduct);
		// Verify cart page product and product item is same
		Assert.assertEquals(actualProduct, "Sauce Labs Bike Light", "Products are not similar");
	}

// Validate products gets removed into cart by clicking on button so button text should 
//	change by Remove to Add To Cart
	
	@Test
	public void validateProductBtnTextChange() {
		String actualBtnText = homePage.checkTextOfButton();
		System.out.println("Actual button text before click on Add to Cart "+actualBtnText);
		homePage.clickOnAddToCartBtn();
		String expectedBtnText = homePage.checkTextOfButton();
		System.out.println("Expected text after click on Add to cart button "+expectedBtnText);
		Assert.assertNotEquals(actualBtnText, expectedBtnText, "Both text are same ");
	}
}

