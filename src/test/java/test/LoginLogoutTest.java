package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import config.ConfigReader;
import pages.HomePage;
import pages.LoginPage;

public class LoginLogoutTest extends BaseTest {
	
	    LoginPage loginPage;
	    HomePage homePage;

	    @BeforeMethod
	    public void setUpPages() {
	        loginPage = new LoginPage(driver);
	        homePage = new HomePage(driver);
	    }

	    // Common login method using valid username and password
	    public void doLogin() {
	        loginPage.enterUserName(ConfigReader.getProperties("username"));
	        loginPage.enterpassword(ConfigReader.getProperties("password"));
	        loginPage.clickOnlogin();
	    }
	    
// Verify the homepage url match after successful login
	    @Test
	    public void validateLogin() {

	        doLogin();

	        String homePageUrl = driver.getCurrentUrl();
	        Assert.assertEquals(
	                homePageUrl,
	                "https://www.saucedemo.com/inventory.html",
	                "Login failed: Home page URL mismatch"
	        );
	    }
// Verify url of the application when user click on logout button 
	    @Test
	    public void validateLogout()  {

	        doLogin();

	        homePage.clickOnMainMenu();
	        homePage.clickOnLogout();
	        

	        String loginPageUrl = driver.getCurrentUrl();
	        System.out.println(loginPageUrl);
//	        Assert.assertEquals(
//	                loginPageUrl,
//	                "https://www.saucedemo.com/",
//	                "Logout failed: Login page URL mismatch"
//	        );
	    }
	}

