package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Command_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	By newPasswordBy = By.id("new_password");
	By creatAccountBy = By.id("create-account");

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Register_Function() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("abc124@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("tester");
		
		//number
		driver.findElement(newPasswordBy).sendKeys("1234");
		Assert.assertTrue(driver.findElement(By.cssSelector(".number-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		
		//lower
		driver.findElement(newPasswordBy).clear();
		driver.findElement(newPasswordBy).sendKeys("abc");
		Assert.assertTrue(driver.findElement(By.cssSelector(".lowercase-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		
		//Upper
		driver.findElement(newPasswordBy).clear();
		driver.findElement(newPasswordBy).sendKeys("ABC");
		Assert.assertTrue(driver.findElement(By.cssSelector(".uppercase-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		
		//special-char
		driver.findElement(newPasswordBy).clear();
		driver.findElement(newPasswordBy).sendKeys("@@@$");
		Assert.assertTrue(driver.findElement(By.cssSelector(".special-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		
		//8-char
		driver.findElement(newPasswordBy).clear();
		driver.findElement(newPasswordBy).sendKeys("12345678");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		
		//success
		driver.findElement(newPasswordBy).clear();
		driver.findElement(newPasswordBy).sendKeys("Abcd9@123");
		Assert.assertTrue(driver.findElement(By.id("create-account")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector(".number-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector(".lowercase-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector(".uppercase-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector(".special-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		
		driver.findElement(By.id("marketing_newsletter")).click();
		driver.findElement(By.id("marketing_newsletter")).isSelected();
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}