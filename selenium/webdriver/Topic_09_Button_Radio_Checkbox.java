package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor js;

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Button() {
		By loginButton = By.xpath("//button[@class='fhs-btn-login']");
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");

		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		driver.findElement(
				By.xpath("//label[text()='Số điện thoại/Email']/parent::div[@class='fhs-input-box']/div/input"))
				.sendKeys("0984754415");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("123123");
		Assert.assertTrue(driver.findElement(loginButton).isDisplayed());

		String rbaColor = driver.findElement(loginButton).getCssValue("background-color");
		String hexaColor = Color.fromString(rbaColor).asHex().toUpperCase();
		Assert.assertEquals(hexaColor, "#C92127");

		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		js.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButton));
		
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver
				.findElement(By.xpath(
						"//div[@class='fhs-input-box checked-error']//div[text()='Thông tin này không thể để trống']"))
				.isDisplayed());
		
		Assert.assertTrue(driver
				.findElement(By.xpath(
						"//div[@class='fhs-input-box fhs-input-display checked-error']//div[text()='Thông tin này không thể để trống']"))
				.isDisplayed());
	}
	
	@Test
	public void TC_02_Checkbox_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		By dualZone = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		sleepInSecond(3);
		Assert.assertFalse(driver.findElement(dualZone).isSelected());
		clicktoCheck(dualZone);
		Assert.assertTrue(driver.findElement(dualZone).isSelected());
		clicktoUncheck(dualZone);
		Assert.assertFalse(driver.findElement(dualZone).isSelected());

	}
	
	@Test
	public void TC_03_Radio_Button() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		By petrolV2 = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		Assert.assertFalse(driver.findElement(petrolV2).isSelected());
		driver.findElement(petrolV2).click();
		clicktoCheck(petrolV2);
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
	
	public void clicktoCheck(By by) {
		if(!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void clicktoUncheck(By by) {
		if(driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	

}