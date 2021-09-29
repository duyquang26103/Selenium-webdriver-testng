package webdriver;

import java.util.List;
import java.util.Set;
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

public class Topic_14_Windows_Tab {
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
	public void TC_01_Windows_Tab1() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		SwitchToWindownByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");

		SwitchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		SwitchToWindownByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");

		SwitchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		SwitchToWindownByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		CloseWindowsExcept(parentID);
		SwitchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(3);
	}

	@Test
	public void TC_02_Windows_Tab2() {

		driver.get("https://kyna.vn/");
		String parentID = driver.getWindowHandle();
		List<WebElement> popUp = driver.findElements(By.xpath("//div[@class='fancybox-outer']"));
		if (popUp.size() > 0 && popUp.get(0).isDisplayed()) {
			driver.findElement(By.xpath("//a[@title='Close']")).click();
		}
		sleepInSecond(3);
		js.executeScript("arguments[0].click()",
				driver.findElement(By.cssSelector("ul.bottom+div>a>img[alt='facebook']")));
		SwitchToWindownByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");

		SwitchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
		js.executeScript("arguments[0].click()",
				driver.findElement(By.cssSelector("ul.bottom+div>a>img[alt='youtube']")));
		SwitchToWindownByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");

		SwitchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
		js.executeScript("arguments[0].click()",
				driver.findElement(By.cssSelector("div[id='k-footer-copyright'] a:nth-child(1) img:nth-child(1)")));
		SwitchToWindownByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		Assert.assertEquals(driver.getTitle(), "Thông tin website thương mại điện tử - Online.Gov.VN");

		SwitchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
		js.executeScript("arguments[0].click()",
				driver.findElement(By.cssSelector("div[id='k-footer-copyright'] a:nth-child(2) img:nth-child(1)")));
		SwitchToWindownByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		Assert.assertEquals(driver.getTitle(), "Thông tin website thương mại điện tử - Online.Gov.VN");

		CloseWindowsExcept(parentID);
		SwitchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
	}

	@Test
	public void TC_03_Windows_Tab3() {

		driver.get("http://live.demoguru99.com/index.php/");
		String parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();

		driver.findElement(By.xpath("//a[@title='Xperia']/following-sibling::div//a[@class='link-compare']")).click();
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']"))
				.isDisplayed());

		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//a[@class='link-compare']"))
				.click();
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']"))
				.isDisplayed());
		driver.findElement(By.xpath("//button[@title='Compare']")).click();

		SwitchToWindownByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

		CloseWindowsExcept(parentID);
		SwitchToWindownByTitle("Mobile");
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		Assert.assertEquals(driver.switchTo().alert().getText(),
				"Are you sure you would like to remove all products from your comparison?");
		driver.switchTo().alert().accept();
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
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

	public void SwitchToWindownByTitle(String titleExpected) {
		Set<String> Allindows = driver.getWindowHandles();
		for (String id : Allindows) {
			driver.switchTo().window(id);
			String windowTitle = driver.getTitle();
			if (windowTitle.equals(titleExpected)) {
				break;
			}
		}
	}

	public void CloseWindowsExcept(String parentID) {
		Set<String> Allindows = driver.getWindowHandles();
		for (String id : Allindows) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
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