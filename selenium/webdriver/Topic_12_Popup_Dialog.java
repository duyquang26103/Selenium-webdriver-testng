package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup_Dialog {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		driver.findElement(By.cssSelector(".login_.icon-before")).click();
		driver.findElement(By.id("account-input")).sendKeys("automation123");
		driver.findElement(By.id("password-input")).sendKeys("automation123");
		driver.findElement(By.cssSelector(".btn-v1.btn-login-v1.buttonLoading")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Tài khoản không tồn tại!']")).isDisplayed());
	}

	@Test
	public void TC_02_Random_Popup_In_DOM() {
		driver.get("https://blog.testproject.io/");
		WebElement popUp = driver.findElement(By.cssSelector("div.mailch-wrap"));

		if (popUp.isSelected()) {
			driver.findElement(By.id("close-mailch")).click();
		}

		driver.findElement(By.cssSelector("section#search-2 .search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section[id$='search-2'] span[class='glass']")).click();
		sleepInSecond(5);

		List<WebElement> articleTitle = driver
				.findElements(By.xpath("//div[@class='post-content']/h3[@class='post-title']/a"));
		for (WebElement title : articleTitle) {
			Assert.assertTrue(title.getText().contains("Selenium"));
		}

	}

	@Test
	public void TC_03_Random_Popup_Not_In_DOM() {
		driver.get("https://shopee.vn/");

		// nếu Element k có trong DOM thì hàm findElement không tìm thấy
		// chờ hết timeout của implicit
		// đánh fail testcase tại ngay step đó
		// throw ra exception : NoSuchElement
		// WebElement popUp =
		// driver.findElements(By.xpath("//div[@class='shopee-popup__container']//img"));

		// nếu Element k có trong DOM thì hàm findElements không tìm thấy
		// chờ hết timeout của implicit rồi chuyển qua bước tiếp theo
		// nó sẽ trả về 1 list empty (size =0)
		// k đánh fail testcase 
		// k throw ra exception 
		List<WebElement> popUp = driver.findElements(By.xpath("//div[@class='shopee-popup__container']//img"));

		if (popUp.size() > 0 && popUp.get(0).isDisplayed()) {
			System.out.println("pop up is displayed");
			driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
		} else {
			System.out.println("pop up is not displayed");
		}

		sleepInSecond(2);
		driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Ipad Pro");
		driver.findElement(By.cssSelector("div.shopee-searchbar button")).click();

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