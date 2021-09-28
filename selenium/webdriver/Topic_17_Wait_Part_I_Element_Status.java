package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_I_Element_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicit;
	
	By registerButton = By.xpath("//a[text()='Tạo tài khoản mới']");
	By emailTextboxBy = By.cssSelector("input[name='reg_email__']");
	By cemailTextboxBy = By.cssSelector("input[name='reg_email_confirmation__']");
	
	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicit = new WebDriverWait(driver,15);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Visible() {
		driver.get("https://www.facebook.com/");
		driver.findElement(registerButton).click();
		driver.findElement(emailTextboxBy).sendKeys("abc@gmail.com"); 
		explicit.until(ExpectedConditions.visibilityOfElementLocated(cemailTextboxBy));
		
		driver.findElement(cemailTextboxBy).sendKeys("abc@gmail.com"); 
		
	}
	@Test
	public void TC_02_Invisible() {
		driver.get("https://www.facebook.com/");
		driver.findElement(registerButton).click();
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(cemailTextboxBy));
	}
	@Test
	public void TC_03_Presence_InDOM() {
		driver.get("https://www.facebook.com/");
		driver.findElement(registerButton).click();
		driver.findElement(emailTextboxBy).sendKeys("abc@gmail.com"); 
		
		explicit.until(ExpectedConditions.presenceOfElementLocated(cemailTextboxBy));
		driver.findElement(cemailTextboxBy).sendKeys("abc@gmail.com"); 
	}
	@Test
	public void TC_04_Presence_NotInDOM() {
		driver.get("https://www.facebook.com/");
		driver.findElement(registerButton).click();
		explicit.until(ExpectedConditions.presenceOfElementLocated(cemailTextboxBy));
	}
	
	@Test
	public void TC_05_Staless() {
		driver.get("https://www.facebook.com/");
		driver.findElement(registerButton).click();
		WebElement registerTitle = driver.findElement(By.xpath("//div[text()='Đăng ký']"));
		Assert.assertTrue(registerTitle.isDisplayed());
		driver.findElement(By.cssSelector("._8ien>img")).click();
		explicit.until(ExpectedConditions.stalenessOf(registerTitle));
		
	}
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}