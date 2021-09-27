package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	String filePath = projectPath + File.separator + "image" + File.separator;
	String thinkPad_1 = "thinkpad_1.jpg";
	String thinkPad_2 = "thinkpad_2.jpg";
	String thinkPad_3 = "thinkpad_3.jpg";
	
	String t1Path = filePath + thinkPad_1;
	String t2Path = filePath + thinkPad_2;
	String t3Path = filePath + thinkPad_3;

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Upload_Sendkeys() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(t1Path);
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(t2Path);
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(t3Path);
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ thinkPad_1 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ thinkPad_2 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ thinkPad_3 +"']")).isDisplayed());
		
		
		List<WebElement> itemsStart = driver.findElements(By.cssSelector("table button[class*='btn btn-primary start']"));
		
		for (WebElement start : itemsStart) {
			start.click();
			sleepInSecond(2);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ thinkPad_1 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ thinkPad_2 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ thinkPad_3 +"']")).isDisplayed());	
	}
	
	@Test
	public void TC_02_Upload_Multi_Sendkeys() {
		
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(t1Path + "\n" + t2Path + "\n" + t3Path);

		
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ thinkPad_1 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ thinkPad_2 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ thinkPad_3 +"']")).isDisplayed());
		
		
		List<WebElement> itemsStart = driver.findElements(By.cssSelector("table button[class*='btn btn-primary start']"));
		
		for (WebElement start : itemsStart) {
			start.click();
			sleepInSecond(2);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ thinkPad_1 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ thinkPad_2 +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ thinkPad_3 +"']")).isDisplayed());	
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