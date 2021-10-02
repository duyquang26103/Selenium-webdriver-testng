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

public class Topic_17_Wait_Part_VI_Mixing_Implicit_Explicit {
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
	public void TC_01_Find_Out_Element() {
		explicit = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']"));
		//pass
	}
	
	@Test
	public void TC_02_No_Find_Out_Element_Implicit() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Text']"));
		//wait 5s-> throw nosuch element
	}
	
	@Test
	public void TC_03_No_Find_Out_Element_Explicit() {
		explicit = new WebDriverWait(driver, 5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='Text']")));
		//wait 5s-> throw timeout
	}
	
	@Test
	public void TC_04_No_Find_Out_Element_Explicit_Over_Implicit() {
		explicit = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='Text']")));
		//wait 5-8s -> throw time out
	}
	
	@Test
	public void TC_05_No_Find_Out_Element_Explicit_Less_Than_Implicit() {
		explicit = new WebDriverWait(driver, 3);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='Text']")));
		//wait 5 -> throw time out
	}
	
	@Test
	public void TC_06_No_Find_Out_Element_Explicit_Equals_Implicit1() {
		explicit = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='Text']")));
		//wait over 5 -> throw time out
		
	}
	
	@Test
	public void TC_07_No_Find_Out_Element_Explicit_Equals_Implicit2() {
		explicit = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicit.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()='Text']"))));
		//wait over 5 -> throw nosuch
		
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