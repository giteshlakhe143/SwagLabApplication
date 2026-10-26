package utiles;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	WebDriver driver;
	static WebDriverWait waits;
	
	public WaitUtility(WebDriver driver) {
		this.driver = driver;
		waits=new WebDriverWait(driver,Duration.ofSeconds(10));
	}

	public static void waitUptoElementVisibility(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static void waitUptoTextToBePresentInElement(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElementValue(locator, "Remove"));
	}
	public static void waitUptoTextAppear(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, "$"));
	}
	// wait for element visibility on page
	public static WebElement waitForElementVisible(By locator) {
		return waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	// wait for multiple webelement
	public List<WebElement> waitForElementToBeVisible(By locator){
		return waits.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	// wait for element to be clickable
	public static WebElement waitForElementClickable(By locator) {
		return waits.until(ExpectedConditions.elementToBeClickable(locator));
	}
	// wait for element to be visible 
	public static WebElement waitUptoElementIsVisible(WebElement locator) {
		return waits.until(ExpectedConditions.visibilityOf(locator));
	}
	// wait for element to be presence in DOM
	public static WebElement waitUptoElementPresence(By locator) {
		return waits.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	// wait for list of elements
	public static List<WebElement> waitUptoElementVisible(By locator){
		return waits.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	// wait for element text to appear
	public static boolean waitUptoTextAppear(By locator, String text) {
		return waits.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}
	// wait for page title
	public static boolean waitUptoTitleContains(String title) {
		return waits.until(ExpectedConditions.titleContains(title));
	}
	// wait for page url
	public static boolean waitForUrlContains(String url) {
		return waits.until(ExpectedConditions.urlContains(url));
	}
	// wait for page load
	public void waitForPageLoad() {
		waits.until(driver ->
		  (JavascriptExecutor) driver)
		   .executeScript("Return document.readyState")
		   .equals(driver);
	}
	
}
