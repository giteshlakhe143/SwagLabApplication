package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utiles.WaitUtility;

public class CartPage {

	WebDriver driver;
	WaitUtility wait;
	// Constructor 
	public CartPage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WaitUtility(driver);
	}
	      // Locators of CartPage 
	
	
	By cartProductName = By.className("inventory_item_name"); 
	By cartItems = By.xpath("//div[@class='cart_item_label']");
	By checkoutBtn = By.xpath("//button[@id='checkout']");
	By cartProductPrice = By.className("inventory_item_price");
	
	public List <String> getAddedProducts(){
		WaitUtility.waitUptoElementVisibility(driver, cartProductName);
		List <String> cartProducts = new ArrayList<>();
		List<WebElement> products = driver.findElements(cartProductName);
		
		for(WebElement product : products) {
			String productName = product.getText();
			cartProducts.add(productName);
			System.out.println("Products added in Cart Page"+productName);
		}
		return cartProducts;
		
	}
	
	public CheckOutPage clickOnCheckoutPageBtn() {
	//	wait.waitForElementClickable(checkoutBtn);
		driver.findElement(checkoutBtn).click();
		return new CheckOutPage(driver);
	}
	
	
	
	// Methods 
	public void getItemsName(String item) {
		 driver.findElement(By.xpath("//div[text()='"+item+"']")).getText();
	}


	public String getCartItems() {
		
		return driver.findElement(By.className("inventory_item_name")).getText();
	}
	
	// getting two products of cart to verify end to end flow of the application
	public List<String> getCartProducts(){
	//	WaitUtility.waitUptoElementVisibility(driver, cartItem);
		List<String> productsOfCart = new ArrayList<>();
		List<WebElement> cartproducts = driver.findElements(cartProductName);
		for(WebElement product : cartproducts) {
			productsOfCart.add(product.getText());
		}
		return productsOfCart;
	}
	
	// getting products of cart with their price and product name
	public Map<String, String> getProductsInCartWithNameAndPrice(){
		Map<String,String> getAddedProducts = new HashMap<>();
//		wait.waitForElementToBeVisible(cartItems);
		List<WebElement> actualProductsOfCart = driver.findElements(cartItems);
		for(WebElement cartProducts : actualProductsOfCart) {
			String actualCartProductName = cartProducts.findElement(By.className("inventory_item_name")).getText();
			String actualCartProductPrice = cartProducts.findElement(By.className("inventory_item_price")).getText();
			getAddedProducts.put(actualCartProductName,actualCartProductPrice);
		}
		System.out.println("Get the products which are selected in homepage "+getAddedProducts);
		System.out.println("Size of cart page product "+getAddedProducts.size());
		return getAddedProducts;
	}
}
