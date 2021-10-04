package Admin;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Admin {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("beforeClass");
	}

	@Test( groups = {"Admin" , "regression"} )
	public void TC_01() {
		System.out.println("TC_01");
	}
	@Test( groups = {"Admin" , "regression"} )
	public void TC_02() {
		System.out.println("TC_02");
	}
	@Test( groups = {"Admin" , "regression"} )
	public void TC_03() {
		System.out.println("TC_03");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {

		driver.quit();
	}

}
