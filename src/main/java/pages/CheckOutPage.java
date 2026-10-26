package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utiles.WaitUtility;

public class CheckOutPage {

	WebDriver driver;
	WaitUtility wait;
	HomePage homePage;
	LoginPage loginPage;
	CartPage cartPage;
	
	public CheckOutPage(WebDriver driver) {
		this.driver=driver;
		wait = new WaitUtility(driver);
	}
	
	By firstName = By.id("first-name");
	By lastName = By.id("last-name");
	By postlCode = By.id("postal-code");
	By continueBtn = By.id("continue");
	By cancleBtn = By.id("cancel");
	
	
	public void enterUserName(String userfirstname) {
		driver.findElement(firstName).sendKeys(userfirstname);
	}
	public void enterLastname(String userlastname) {
		driver.findElement(lastName).sendKeys(userlastname);
	}
	public void postalCodeNumber(String postalcode) {
		driver.findElement(postlCode).sendKeys(postalcode);
	}
//	public OverviewPage clickonContinueBtn() {
//		driver.findElement(continueBtn).click();
//		return new OverviewPage(driver);
//	}
	public void clickonContinueBtn() {
		WaitUtility.waitUptoElementVisibility(driver, continueBtn);
		driver.findElement(continueBtn).click();
	}
	public OverviewPage checkout(String firstname, String lastname, String postcode) {
//		WaitUtility.waitUptoElementVisibility(driver, firstName);
		enterUserName(firstname);
		
		enterLastname(lastname);
		
		postalCodeNumber(postcode);
		clickonContinueBtn();
		return new OverviewPage(driver);
	}
}


