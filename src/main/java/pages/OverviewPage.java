package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utiles.PriceUtils;
import utiles.WaitUtility;

public class OverviewPage {

	WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	CartPage cartPage;
	CheckOutPage checkOut;
	WaitUtility wait;
	WebDriverWait waits;
	
//	By overViewItems = By.id("Sauce Labs Backpack");
	By overViewItems = By.className("inventory_item_name");
	By overViewItemsPrice = By.className("inventory_item_price");
	By finishBtn = By.id("finish");
	By overviewProduct = By.className("cart_item_label");
	By totalItemsPrice = By.cssSelector(".summary_subtotal_label");
	By itemTaxPrice = By.cssSelector(".summary_tax_label");
	By totalPrice = By.cssSelector(".summary_total_label");
	
	public OverviewPage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WaitUtility(driver);
	}
	
	public List<String> productsInOverviewPage(){
//		WaitUtility.waitUptoElementVisibility(driver, finishBtn);
		List<String> overviewProducts = new ArrayList<>();
		List<WebElement> products = driver.findElements(overViewItems);
		if(products.size()==0) {
			System.out.println("No products found on overview page");
		}
		for(WebElement product : products) {
			overviewProducts.add(product.getText());
			System.out.println("Products from overview page"+product);
		}
		return overviewProducts;
	}
	public ConfirmationPage clickOnFinishBtn() {
//		WaitUtility.waitUptoElementVisibility(driver, finishBtn);
		driver.findElement(finishBtn).click();
		return new ConfirmationPage(driver);
	}
	
	// Create method for products name with their prices
	public Map<String , String> overviewPageProductsWithNameAndPrice(){
		Map<String,String> getOverviewProducts = new HashMap<>();
		
		List<WebElement> overViewproducts = wait.waitForElementToBeVisible(overviewProduct);
		for(WebElement overviewProduct : overViewproducts) {
			String actualOverviewProductName = overviewProduct.findElement(By.className("inventory_item_name")).getText();
			String actualOverviewProductPrice = overviewProduct.findElement(By.className("inventory_item_price")).getText();
			getOverviewProducts.put(actualOverviewProductName, actualOverviewProductPrice);
		}
		System.out.println("Products of overview page "+getOverviewProducts);
		System.out.println("Size of overview product page "+getOverviewProducts.size());
		return getOverviewProducts;
	}
	
	public double getTotalItemPrice() {
		WaitUtility.waitForElementVisible(totalItemsPrice);
		String itemTotalprice = driver.findElement(totalItemsPrice).getText();
//		String justItemTotalPrice = itemTotalprice.replaceAll("[^0-9]", "");
		double totalItemPrice = PriceUtils.calculatePrice(itemTotalprice);
		return totalItemPrice;
	}
	
	public double getItemTaxPrice() {
        WaitUtility.waitForElementVisible(itemTaxPrice);
		String itemPriceWithTax = driver.findElement(itemTaxPrice).getText();
//		String justPrice = itemPriceWithTax.replaceAll("[^0-9.]" , "");
		double totalPriceWithTax = PriceUtils.calculatePrice(itemPriceWithTax);
		return totalPriceWithTax;
	}
	
	public double getTotalPrice() {
        WaitUtility.waitForElementVisible(totalPrice);
		String totalPriceOfAll = driver.findElement(totalPrice).getText();
		double finalTotalPrice = PriceUtils.calculatePrice(totalPriceOfAll);
		return finalTotalPrice;
	}
}
