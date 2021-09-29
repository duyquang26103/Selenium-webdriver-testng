package webdriver;

import java.io.File;
import java.io.IOException;
import java.util.Set;
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

public class Topic_17_Wait_Part_V_Explicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	String filePath = projectPath + File.separator + "image" + File.separator;
	String thinkPad_1 = "thinkpad_1.jpg";
	String t1Path = filePath + thinkPad_1;
	String chromeOnePath = projectPath + "\\AutoIT\\chromeUploadOneTime.exe";
	
	WebDriverWait explicit;
	By loadingA = By.id("loading");
	
	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Explicit_Wait_lack_of_time_Invisible() {
		explicit = new WebDriverWait(driver, 3);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingA));
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	
	@Test
	public void TC_02_Explicit_Wait_Enough_Invisible() {
		explicit = new WebDriverWait(driver, 5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingA));
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	
	@Test
	public void TC_03_Explicit_Wait_Over_Invisible() {
		explicit = new WebDriverWait(driver, 10);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingA));
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	
	@Test
	public void TC_04_Explicit_Wait_lack_of_time_Visible() {
		explicit = new WebDriverWait(driver, 3);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[text()='Hello World!']")));
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	
	@Test
	public void TC_05_Explicit_Wait_Enough_Visible() {
		explicit = new WebDriverWait(driver, 5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[text()='Hello World!']")));
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	
	@Test
	public void TC_06_Explicit_Wait_Over_Visible() {
		explicit = new WebDriverWait(driver, 10);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4[text()='Hello World!']")));
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}
	
	@Test
	public void TC_07_Explicit_Wait_1() {
		explicit = new WebDriverWait(driver, 15);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		explicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[text()='1']")));
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		driver.findElement(By.xpath("//a[text()='1']")).click();
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		explicit.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Label1")));
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Wednesday, September 1, 2021");
		
	}
	
	@Test
	public void TC_08_Explicit_Wait_2() throws IOException {
		explicit = new WebDriverWait(driver, 15);
		driver.get("https://gofile.io/uploadFiles");
		explicit.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rowUploadButton .uploadButton")));
		driver.findElement(By.cssSelector("#rowUploadButton .uploadButton")).click();
		Runtime.getRuntime().exec(new String[] { chromeOnePath, t1Path });
		
		explicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		driver.findElement(By.id("rowUploadSuccess-downloadPage")).click();
		
		String parentID = driver.getWindowHandle();
		SwitchToWindownByID(parentID);
		
		explicit.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#rowFolder-tableContent .fas.fa-ellipsis-h")));
		driver.findElement(By.cssSelector("#rowFolder-tableContent .fas.fa-ellipsis-h")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='dropdown-item' and text()='Download']")).isDisplayed());
		
	}
	
	public void SwitchToWindownByID(String parentID) {
		Set<String> Allindows = driver.getWindowHandles();
		for (String id : Allindows) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}