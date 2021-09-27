package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_File_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor js;

	String filePath = projectPath + File.separator + "image" + File.separator;
	String thinkPad_1 = "thinkpad_1.jpg";
	String thinkPad_2 = "thinkpad_2.jpg";
	String thinkPad_3 = "thinkpad_3.jpg";

	String t1Path = filePath + thinkPad_1;
	String t2Path = filePath + thinkPad_2;
	String t3Path = filePath + thinkPad_3;

	String chromeOnePath = projectPath + "\\AutoIT\\chromeUploadOneTime.exe";
	String firefoxOnePath = projectPath + "\\AutoIT\\firefoxUploadOneTime.exe";
	String chromeMultiPath = projectPath + "\\AutoIT\\chromeUploadMultiple.exe";
	String firefoxMutilPath = projectPath + "\\AutoIT\\firefoUploadMultiple.exe";

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

		js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	}

	@Test
	public void TC_01_Upload_AutoIT() throws IOException {

		driver.findElement(By.cssSelector(".btn-success")).click();

		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxOnePath, t1Path });
		} else if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
			Runtime.getRuntime().exec(new String[] { chromeOnePath, t1Path });
		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + thinkPad_1 + "']")).isDisplayed());

		List<WebElement> itemsStart = driver
				.findElements(By.cssSelector("table button[class*='btn btn-primary start']"));

		for (WebElement start : itemsStart) {
			start.click();
			sleepInSecond(2);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + thinkPad_1 + "']")).isDisplayed());
	}

	@Test
	public void TC_02_Upload_Multi_AutoIT() throws IOException {

		driver.findElement(By.cssSelector(".btn-success")).click();

		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxMutilPath, t1Path, t2Path });
		} else if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
			Runtime.getRuntime().exec(new String[] { chromeMultiPath, t1Path, t2Path });
		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + thinkPad_1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + thinkPad_2 + "']")).isDisplayed());

		List<WebElement> itemsStart = driver
				.findElements(By.cssSelector("table button[class*='btn btn-primary start']"));

		for (WebElement start : itemsStart) {
			start.click();
			sleepInSecond(2);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + thinkPad_1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + thinkPad_2 + "']")).isDisplayed());

	}

	@Test
	public void TC_03_Upload_RoBot() throws AWTException {
		
		StringSelection select = new StringSelection(t1Path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		driver.findElement(By.cssSelector(".btn-success")).click();
		
		Robot robot = new Robot();
		robot.setAutoDelay(1000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + thinkPad_1 + "']")).isDisplayed());
		driver.findElement(By.cssSelector("table button[class*='btn btn-primary start']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + thinkPad_1 + "']")).isDisplayed());
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

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}