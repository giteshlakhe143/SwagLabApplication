package base;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import config.ConfigReader;




public class BaseTest {

     	protected WebDriver driver;
     	 protected Properties prop;


    @Parameters("browser")
	@BeforeMethod
	public void launchBrowser(@Optional("chrome")String browser) 
	{
    	
		if(browser.equalsIgnoreCase("chrome")) {
//			driver = new ChromeDriver();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--disable-notifications");
			option.addArguments("--incognito");
			driver = new ChromeDriver(option);
		}
		else if(browser.equalsIgnoreCase("firefox")) {
		driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong
				(ConfigReader.getProperties("implicitwait"))));
		driver.get(ConfigReader.getProperties("url"));
		
	}
	@AfterMethod
	public void clodebrowser() throws InterruptedException {
		
		driver.quit();
	}
}
