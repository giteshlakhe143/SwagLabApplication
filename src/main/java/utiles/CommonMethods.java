package utiles;



import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CommonMethods {

	public static void validateIcons(List<WebElement> icons) {

	    for (WebElement icon : icons) {

	        // 1️⃣ Validate displayed
	        Assert.assertTrue(icon.isDisplayed(), "Icon not displayed");

	        // 2️⃣ Validate enabled
	        Assert.assertTrue(icon.isEnabled(), "Icon not clickable");

	        // 3️⃣ Validate href attribute
	        String url = icon.getAttribute("href");
	        Assert.assertNotNull(url, "Icon link is null");
	        Assert.assertFalse(url.isEmpty(), "Icon link is empty");

	        System.out.println("Validated icon with URL: " + url);
	    }
	}
}
