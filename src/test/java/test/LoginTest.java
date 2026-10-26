package test;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utiles.ExcelUtility;

public class LoginTest extends BaseTest {
	

	@DataProvider(name = "logindata")
	public Object[][] getData() throws EncryptedDocumentException, IOException{
		return ExcelUtility.getExcelData("F:\\Download\\SwagLabData.xlsx", "LoginData");
		
	}
	@Test(dataProvider = "logindata")
	
	
	public void loginTest(String username, String password, String expectedResult) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUserName(username);
		loginPage.enterpassword(password);
		loginPage.clickOnlogin();
//		Assert.assertEquals(driver.getCurrentUrl(),
//                "https://www.saucedemo.com/inventory.html");
		if(expectedResult.equals("success")) {
			boolean isProductPageDisplayed = driver.getCurrentUrl().contains("inventory");
			Assert.assertTrue(isProductPageDisplayed, "Login successfull for valid user "+username);
		 }
		 else if(expectedResult.equalsIgnoreCase("locked")) {
			String errorMsg = driver.findElement(By.cssSelector("[data-test='error']")).getText();
			Assert.assertTrue(errorMsg.contains("locked out"),
					("Locked user error message not displayed"));	
		 }
		 else if(expectedResult.equalsIgnoreCase("Failuer")) {
			 String errorMsg = driver.findElement(By.cssSelector("[data-test='error']")).getText();
			 Assert.assertTrue(errorMsg.contains("username and password do not match"),
					 "Invalid user error message not displayed");
		
			 
		 }
		 
	}
	
	
}
