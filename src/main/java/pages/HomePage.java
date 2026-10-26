package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import config.ConfigReader;
import utiles.PriceUtils;
import utiles.WaitUtility;

public class HomePage {

	WebDriver driver;
	WaitUtility wait;
	//WebElement of the Home page or Product page
	//add-to-cart-sauce-labs-bike-light
	By addToCartBikeLight = By.xpath("//button[contains(@id,'sauce-labs-bike-light')]"); //click on add to cart button of first item
	By userCartButton = By.xpath("//a[@class='shopping_cart_link']");
	By menuButton = By.id("react-burger-menu-btn");
	By logOutBtn = By.id("logout_sidebar_link");

	By facebookIcon = By.cssSelector(".social_facebook a");
	By twitterIcon = By.cssSelector(".social_twitter a");
	By linkedinIcon = By.cssSelector(".social_linkedin a");
    By AddToCartBtn = By.xpath("//button[text()='Add to cart']"); ////button[text()='Add to cart']
	By productItem = By.xpath("//div[@class='inventory_item'][1]");
	By productTitle = By.xpath("//div[text()='Sauce Labs Backpack']");
	By backPackaddToCartBtn = By.id("add-to-cart-sauce-labs-backpack");
	By productPrice = By.xpath("//div[@class='inventory_item_price']");
	By productList = By.className("inventory_item");
	By productContainer = By.xpath("//div[@class='inventory_item']");
	By productName = By.className("inventory_item_name");
	By applicationLogo = By.xpath("//div[@class='app_logo']");
    By filterDropdown = By.className("product_sort_container");
    By AddToCartBtn1 = By.xpath("//button[contains(text(),'cart') or contains(text(),'Remove')]");


	// Home page constructor
	public HomePage(WebDriver driver) {
		wait = new WaitUtility(driver);
		this.driver = driver;
	}

	// create method for adding multiple products in cart

	public List<String> addProductsToCart() {
		List<String> addedproducts = new ArrayList<>();
		List<WebElement> products = driver.findElements(productContainer);
		for (WebElement product : products) {
			String prodName = product.findElement(productName).getText();
			WebElement addBtn = product.findElement(AddToCartBtn);
			WaitUtility.waitUptoElementVisibility(driver, AddToCartBtn);
			addedproducts.add(prodName);
			addBtn.click();
			System.out.println("Added products in cart " + prodName);
		}
		return addedproducts;
	}
	// Create method to add products in cart page with their product name and prices

	public Map<String, String> productsAddToCartWithPriceAndName() {

		Map<String, String> addedproducts = new HashMap<>();
		// wait.waitForElementVisible(productContainer);

		List<WebElement> products = wait.waitForElementToBeVisible(productContainer);
		for (WebElement product : products) {
			String productname = product.findElement(By.className("inventory_item_name")).getText();
			String productPrice = product.findElement(By.className("inventory_item_price")).getText();
			WebElement addTocartBtn = product.findElement(By.tagName("button"));
			addTocartBtn.click();
			addedproducts.put(productname, productPrice);
		}
		System.out.println("products added from homepage" + addedproducts);
		System.out.println("Size of HomePage product list " + addedproducts.size());
		return addedproducts;
	}

	// we adding two products in cart to validate end to end flow of the application
	public List<String> addTwoProducts() {
		List<String> selectedProducts = new ArrayList<>();
		List<WebElement> names = driver.findElements(productName);
		List<WebElement> buttons = driver.findElements(AddToCartBtn);

		for (int i = 0; i < 2; i++) {
			String productsName = names.get(i).getText();
			selectedProducts.add(productsName);
			buttons.get(i).click();
		}
		return selectedProducts;

	}

	// create method for product validation using price and their name

	public Map<String, Double> getAllproductsWithPrice() {
		Map<String, Double> productsWithPrice = new HashMap<>();
		List<WebElement> productsName = driver.findElements(productName);
		List<WebElement> productsPrice = driver.findElements(productPrice);

		for (int i = 0; i < productsName.size(); i++) {
			String name = productsName.get(i).getText();
			double price = PriceUtils.calculatePrice(productsPrice.get(i).getText().replace("$", ""));
			productsWithPrice.put(name, price);
		}
		return productsWithPrice;
	}
	// List of Products after select Low To High Filter
	public Map<String, Double> getProductsafterClickOnFilter(){
		Map<String, Double> prodWithPrice = new HashMap<>();
		WaitUtility.waitUptoElementVisible(productContainer);
		List<WebElement> prodName = driver.findElements(productName);
		List<WebElement> prodPrice = driver.findElements(productPrice);
		for(int i=0; i<prodName.size(); i++) {
			String name = prodName.get(i).getText();
			double price = PriceUtils.calculatePrice(prodPrice.get(i).getText().replace("$",""));
			prodWithPrice.put(name, price);
			
		}
		return prodWithPrice;
	}
	
	public List<Double> getProductsPriceBeforeApplyingFilter(){
		List<Double> priceBeforeApplyingFilter = new ArrayList<>();
		WaitUtility.waitUptoElementVisible(productPrice);

		List<WebElement> productPricesBeforeFilter = driver.findElements(productPrice);
		for(WebElement price : productPricesBeforeFilter) {
			double prices = Double.parseDouble(price.getText().replace("$", ""));
			priceBeforeApplyingFilter.add(prices);
		}
		return priceBeforeApplyingFilter;
	}
	
	public List<Double> getProductsPriceAfterApplyingFilter(){
		List<Double> priceAfterApplyingFilter = new ArrayList<>();
		WaitUtility.waitForElementVisible(productPrice);
		List<WebElement> productPricesAfterFilter = driver.findElements(productPrice);
		
		for(WebElement price : productPricesAfterFilter) {
			double prices = Double.parseDouble(price.getText().replace("$", ""));
			priceAfterApplyingFilter.add(prices);
		}
		return priceAfterApplyingFilter;
	}
	
	// Getting all the products 
	public List<Double> getAllProductPrices(){
		List<Double> prices = new ArrayList<>();
		WaitUtility.waitForElementClickable(productPrice);
		List<WebElement> priceElement = driver.findElements(productPrice);
		for(WebElement price : priceElement) {
			String priceText = price.getText();
			double d = Double.parseDouble(priceText.replace("$", ""));
			prices.add(d);
		}
		return prices;
	}
	
	// Geting names of all the products 
	public List<String> getAllProductsName(){
		List<String> productsName = new ArrayList<>();
		WaitUtility.waitForElementVisible(productName);
		List<WebElement> nameOfProduct = driver.findElements(productName);
		for(WebElement name : nameOfProduct) {
			String prodText = name.getText();
//			if(prodText.contains("Sauce Labs ")) {
//				prodText.replace("Sauce Labs ", "");
//			}
			productsName.add(prodText);
		}
		return productsName;
	}
	
	public void applyPriceLowToHighFilter() {
		WebElement filterdropdown = driver.findElement(filterDropdown);
		Select select = new Select(filterdropdown);
		select.selectByVisibleText(ConfigReader.getProperties("LowToHighFilter"));
	}
	
	public void applyPriceHighToLowFilter() {
		WebElement filterdropdown = driver.findElement(filterDropdown);
		Select select = new Select(filterdropdown);
		select.selectByVisibleText(ConfigReader.getProperties("HighToLowFilter"));
	}
	
	public void applyNameZ_to_Afilter() {
		WebElement filterdropdown = driver.findElement(filterDropdown);
		Select select = new Select(filterdropdown);
		select.selectByVisibleText(ConfigReader.getProperties("Name_Z_to_A"));
	}
	
	public void applyNameA_to_Zfilter() {
		WebElement filterdropdown = driver.findElement(filterDropdown);
		Select select = new Select(filterdropdown);
		select.selectByVisibleText(ConfigReader.getProperties("Name_A_to_Z"));
	}
	public void clickOnAddToCartBtn() {
		driver.findElement(AddToCartBtn1).click();
	}
   public String checkTextOfButton() {
		String btnText = driver.findElement(AddToCartBtn1).getText();
		return btnText;
	}
	

	// click on Add to cart button of sauce Bike Light item
	public void clickOnAddToCartBikeLight() {
		WaitUtility.waitUptoElementVisibility(driver, addToCartBikeLight);
		driver.findElement(addToCartBikeLight).click();

	}

	public String bikeLightBtnText() {
		// WaitUtility.waitUptoTextToBePresentInElement(driver, addToCartBikeLight);
		return driver.findElement(addToCartBikeLight).getText();
	}

	// When user click on Cart Icon of Homepage user will navigate to cart page
	// Return cart page object using page chaining
	public CartPage clickCartIcon() {
//		wait.waitForElementClickable(userCartButton);
		driver.findElement(userCartButton).click();
		return new CartPage(driver);

	}

	// click on cart menu
	public void cartMenu() {
		driver.findElement(userCartButton).click();
	}

	// click on main menu
	public void clickOnMainMenu() {
		WaitUtility.waitUptoElementVisibility(driver, menuButton);
		driver.findElement(menuButton).click();
	}

	// click on logout link text
	public void clickOnLogout() {
		WaitUtility.waitUptoElementVisibility(driver, logOutBtn);
		driver.findElement(logOutBtn).click();
	}

	// click on twitter icon in footer
	public void clickOnTwitterIcon() {
		WaitUtility.waitUptoElementVisibility(driver, twitterIcon);
		driver.findElement(twitterIcon).click();
	}

	// click on facebook icon in footer
	public void clickOnFacebookIcon() {
		WaitUtility.waitUptoElementVisibility(driver, facebookIcon);
		driver.findElement(facebookIcon).click();
		;
	}

	// click on linkedin icon in footer
	public void clickOnLinkedinIcon() {
		WaitUtility.waitUptoElementVisibility(driver, linkedinIcon);
		driver.findElement(linkedinIcon).click();
		;
	}

	// click on product
	public void clickOnProduct() {
		driver.findElement(productItem).click();
	}

	// click on add to cart button
//	public void clickOnAddToCartBtn() {
//		WaitUtility.waitUptoElementVisibility(driver, addToCartBtn);
//    driver.findElement(clickOnAddToCartBikeLight()).click();
//		
//	}
	public void clickOnBikeLightAddToCartbtn() {
		WaitUtility.waitUptoElementVisibility(driver, addToCartBikeLight);
		driver.findElement(addToCartBikeLight);
	}

	// method to get product title
	public String getProuctTitle() {
		WaitUtility.waitUptoElementVisibility(driver, productTitle);
		return driver.findElement(productTitle).getText();
	}

	// To get the product price
	public String getProductPrice() {
		return driver.findElement(productPrice).getText();

	}

	// to get the product list
	public List<WebElement> productList() {
		return driver.findElements(productList);

	}

	// To get the logo of the application
	public WebElement pageLogo() {
		return driver.findElement(applicationLogo);
	}

	public LoginPage clickOnMenuBurgurBtnAndLogOut() {
		driver.findElement(menuButton).click();
		driver.findElement(logOutBtn).click();
		return new LoginPage(driver);
	}
}
