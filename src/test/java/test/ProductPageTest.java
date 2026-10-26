package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import config.ConfigReader;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

public class ProductPageTest extends BaseTest {
	LoginPage loginPage;
	HomePage homePage;
	CartPage cartPage;
	@BeforeMethod
	public void setUp() {
	 loginPage = new LoginPage(driver);
//	 homePage = new HomePage(driver);
	 cartPage = new CartPage(driver);
	 homePage = loginPage.login(ConfigReader.getProperties("username"),
			 ConfigReader.getProperties("password"));
	}
	// Validate correct page url fetching 
	@Test
	public void productPageUrlvalidation() {
		
		// validate the url of the page 
		String inventoryPageUrl = driver.getCurrentUrl();
		System.out.println(inventoryPageUrl);
		Assert.assertEquals(inventoryPageUrl,"https://www.saucedemo.com/inventory.html","Url does not matched");
	}
	
	// Validate the product list size on the webPage
	
	@Test
	public void productListValidation() {
		
		int productSize = homePage.productList().size();
		System.out.println("product size on page "+productSize);
		Assert.assertEquals(productSize, 6, "Not able to count properly");
	}
	
	// Validate logo of the application
	@Test
	public void logoValidation() {
		String pageLogo = homePage.pageLogo().getText();
		System.out.println("Logo "+pageLogo);
		Assert.assertEquals(pageLogo, "Swag Labs", "Logo didnt match");
			
		}
	// Validate the product price
	@Test
	public void productPrice() {
		String productprice = homePage.getProductPrice();
		System.out.println(productprice);
		Assert.assertEquals(productprice, "$29.99", "Price mismatch");
	}
	// validate Bike Light product get add in cart. we have to check it in cart
	@Test
	public void validateBikeLightProductAddToCart() {
		homePage.clickOnAddToCartBikeLight();
		homePage.clickCartIcon();
		String actualProductName = cartPage.getCartItems();
		String cartPageUrl = driver.getCurrentUrl();
		System.out.println(cartPageUrl);
		Assert.assertEquals(actualProductName, "Sauce Labs Bike Light", "mismatch product");
		System.out.println("Bike Light item gets added in the cart");
		
	}
	
	// Validate button text of Bike Light product should be change from Add to Cart to Remove when user click on Add to Cart
	@Test 
	public void validateBikeLightButtonText() {
		String buttonText = homePage.bikeLightBtnText();
		System.out.println(buttonText);
        homePage.clickOnAddToCartBikeLight();
        System.out.println("click on button");
        String buttonTextAfterClick = homePage.bikeLightBtnText();
        Assert.assertNotSame(buttonTextAfterClick, buttonText, "Both text should not be equals" );
        System.out.println("should not same text");
	}
	
	// Validate user selected product should visible in Cart Page which are select in Product page
}
