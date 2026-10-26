package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;

public class IconNavigation extends BaseTest{

	HomePage homePage;
	LoginLogoutTest loginOut = new LoginLogoutTest();
	@BeforeMethod
	public void pageSetUp() {
	 homePage = new HomePage(driver);
	 
	 }
	@Test
	public void validateTwitterIcon() {
		loginOut.doLogin();
		homePage.clickOnTwitterIcon();
		
	}
}
