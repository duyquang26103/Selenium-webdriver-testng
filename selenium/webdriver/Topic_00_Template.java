package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@Test
	public void TC_01_Firefox_latest() {
//		1/ firefox latest: 89
//		2/ Selenium 3.141.59
//		3/ TestNG 6.14.3
//		4/ Gecko Driver
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.guru99.com/");
		driver.close();
	
	}
	
//	@Test
	public void TC_01_Firefox_Old() {	
//		1/ firefox 41.0.2
//		2/ Selenium 2.53.1
//		3/ no need TestNG
//		4/ no need Gecko Driver
		driver = new FirefoxDriver();
		driver.get("https://www.guru99.com/");
		driver.close();
	
	}
}