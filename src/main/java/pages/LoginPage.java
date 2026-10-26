package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;
	// Constructor of LoginPage class
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
    // Locators
   private By username = By.id("user-name");
   private By password = By.id("password");
   private By loginButton = By.id("login-button");
   private By errorMsg = By.cssSelector("#error-message-container error");
	
	// Action
	public void enterUserName(String user) {
		driver.findElement(username).sendKeys(user);;
	}
	
	public void enterpassword(String pass) {
		driver.findElement(password).sendKeys(pass);;
	}
	
	public void clickOnlogin() {
		driver.findElement(loginButton).click();
	}
	
	public boolean isErrorMsgDisply() {
		return driver.findElement(errorMsg).isDisplayed();
	}
	
	public HomePage login(String user, String pass) {
		enterUserName(user);
		enterpassword(pass);
		clickOnlogin();
		return new HomePage(driver);
		
	}
}
