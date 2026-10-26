package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import config.ConfigReader;
import pages.HomePage;
import pages.LoginPage;

public class ProductPageValidation extends BaseTest{
    
	LoginPage loginPage;
	HomePage homePage;
//	LoginLogoutTest loginOutTest = new LoginLogoutTest();
	@BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        homePage = loginPage.login(ConfigReader.getProperties("username"),
        		ConfigReader.getProperties("password"));
        
    }
 
	@Test
	public void pageUrl() {
		
	   String actualPageurl = driver.getCurrentUrl();
	   String expectedurl = "https://www.saucedemo.com/inventory.html";
	   Assert.assertEquals(actualPageurl, expectedurl,"Failed due to loading");
	   
	}
}
