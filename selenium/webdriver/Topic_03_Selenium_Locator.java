package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	
	@Test
	public void TC_01_ID() {	
		driver.findElement(By.id("email")).sendKeys("duyquang123");

	}
	
	@Test
	public void TC_02_Class() {	
		driver.navigate().refresh();
		driver.findElement(By.className("validate-email")).sendKeys("12334");;
		
	}
	
	@Test
	public void TC_03_Name() {	
		driver.navigate().refresh();
		driver.findElement(By.name("login[password]")).sendKeys("abcd");;
	}
	
	@Test
	public void TC_04_Tagname() {	
		driver.navigate().refresh();
	// take all of links in this screen then getText
		List<WebElement> loginPageLinks = driver.findElements(By.tagName("a"));
		
		for (WebElement webElement : loginPageLinks) {
			System.out.println(webElement.getText());
		}
	}
	
	@Test
	public void TC_05_LinkText() {	
		driver.findElement(By.linkText("Forgot Your Password?")).click();
		Assert.assertTrue(driver.findElement(By.id("email_address")).isDisplayed());

	}
	
	@Test
	public void TC_06_PartialLinkText() {	
		driver.findElement(By.partialLinkText("Back to")).click();
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
	}
	
	@Test
	public void TC_07_Css() {	
		driver.findElement(By.cssSelector("#email")).sendKeys("duyquang2610@gmail.com");
		driver.findElement(By.cssSelector("#send2")).click();
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-pass")).isDisplayed());
		
		
	}
	
	@Test
	public void TC_08_Xpath() {	
		driver.navigate().refresh();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("123456");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}