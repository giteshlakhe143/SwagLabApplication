package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

	WebDriver driver;
	OverviewPage overviewPage;
	HomePage homePage;
	LoginPage loginPage;
	
	By confirmMsg = By.xpath("//h2[@class='complete-header']");
	By backToHomeBtn = By.xpath("//button[@id='back-to-products']");
	
	public ConfirmationPage(WebDriver driver) {
		this.driver=driver;
	}
	
    public String getTextMsg() {
    	 String actualMsg = driver.findElement(confirmMsg).getText();
		 return actualMsg;
    }
    public HomePage backToHomePage() {
    	 driver.findElement(backToHomeBtn).click();;
    	return new HomePage(driver);
    }
}
