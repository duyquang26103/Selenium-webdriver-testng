package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Frame_Iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	ChromeOptions chromeOptions = new ChromeOptions();
	JavascriptExecutor executor;

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		executor = (JavascriptExecutor) driver;
		chromeOptions.addArguments("--lang= ja-JP");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		 ChromeOptions opt = new ChromeOptions();
	      opt.addArguments("−−lang=jp");
	}

	@Test
	public void TC_01_Iframe() {

		driver.get("https://kyna.vn/");
		
		List<WebElement> popUp = driver.findElements(By.xpath("//div[@class='fancybox-outer']"));
		
		if(popUp.size()>0 && popUp.get(0).isDisplayed()) {
			driver.findElement(By.xpath("//a[@title='Close']")).click();
			sleepInSecond(2);
		}
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage ']//iframe")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='uiScaledImageContainer _2zfr']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='_1drq']")).isDisplayed());
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("cs_chat_iframe");
		executor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector(".meshim_widget_widgets_BorderOverlay")));
		driver.findElement(By.cssSelector(".meshim_widget_widgets_BorderOverlay")).click();
		driver.findElement(By.cssSelector(".input_name")).sendKeys("Jasson");
		driver.findElement(By.cssSelector(".input_phone")).sendKeys("032254451");
		driver.findElement(By.cssSelector("#serviceSelect")).click();
		driver.findElement(By.cssSelector("#serviceSelect option[value='60021807']")).click();
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("auto test");
		driver.findElement(By.cssSelector("input.submit")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Jasson']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='032254451']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//textarea[text()='auto test']")).isDisplayed());
		
		driver.switchTo().defaultContent();
		executor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector("input#live-search-bar")));
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector(".search-button")).click();
		
		List<WebElement> courseTitle = driver.findElements(By.xpath("//div[@class='content']//h4"));
		for (WebElement course : courseTitle) {
			Assert.assertTrue(course.getText().contains("Excel"));
			System.out.println(course);
		}
		
	}
	
	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame(driver.findElement(By.xpath("//frame")));
		driver.findElement(By.cssSelector("div.loginData>input")).sendKeys("Culalumbar");
		driver.findElement(By.cssSelector("div.loginData>a")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div.loginData>input[id*='fldPasswordDispId']")).isDisplayed());
		driver.findElement(By.xpath("//div[@class='footer-btm']/a[text()='Terms and Conditions']")).click();
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