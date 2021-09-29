package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_IV_Static {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	By registerButton = By.xpath("//a[text()='Tạo tài khoản mới']");
	By emailTextboxBy = By.cssSelector("input[name='reg_email__']");
	By cemailTextboxBy = By.cssSelector("input[name='reg_email_confirmation__']");
	By firstNameTextBoxBy = By.cssSelector("input[name*='firstname']");
	By loginTextboxBy = By.cssSelector("#email");
	
	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_FindElement_lack_of_time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	
	@Test
	public void TC_02_FindElement_Enough() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	
	@Test
	public void TC_01_FindElement_Over() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		sleepInSecond(10);
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}