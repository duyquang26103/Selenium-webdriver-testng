package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_EmptyData() {
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		// case 1
		driver.findElement(By.xpath("//label[@id=\"txtFirstname-error\"]")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id=\"txtFirstname-error\"]")).getText(),
				"Vui lòng nhập họ tên");
		// case 2
		driver.findElement(By.xpath("//label[@id=\"txtEmail-error\"]")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id=\"txtEmail-error\"]")).getText(),
				"Vui lòng nhập email");
		// case 3
		driver.findElement(By.xpath("//label[@id=\"txtCEmail-error\"]")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id=\"txtCEmail-error\"]")).getText(),
				"Vui lòng nhập lại địa chỉ email");
		// case 4
		driver.findElement(By.xpath("//label[@id=\"txtPassword-error\"]")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id=\"txtPassword-error\"]")).getText(),
				"Vui lòng nhập mật khẩu");
		// case 5
		driver.findElement(By.xpath("//label[@id=\"txtCPassword-error\"]")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id=\"txtCPassword-error\"]")).getText(),
				"Vui lòng nhập lại mật khẩu");
		// case 6
		driver.findElement(By.xpath("//label[@id=\"txtPhone-error\"]")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id=\"txtPhone-error\"]")).getText(),
				"Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_InvalidEmail() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//input[@id=\"txtFirstname\"]")).sendKeys("quang vuong");
		driver.findElement(By.xpath("//input[@id=\"txtEmail\"]")).sendKeys("duyquang@12412@");
		driver.findElement(By.xpath("//input[@id=\"txtCEmail\"]")).sendKeys("duyquang@12412@");
		driver.findElement(By.xpath("//input[@id=\"txtPassword\"]")).sendKeys("12345@");
		driver.findElement(By.xpath("//input[@id=\"txtCPassword\"]")).sendKeys("12345@");
		driver.findElement(By.xpath("//input[@id=\"txtPhone\"]")).sendKeys("09521522432");

		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		driver.findElement(By.xpath("//label[@id=\"txtEmail-error\"]")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id=\"txtEmail-error\"]")).getText(),
				"Vui lòng nhập email hợp lệ");
		driver.findElement(By.xpath("//label[@id=\"txtCEmail-error\"]")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id=\"txtCEmail-error\"]")).getText(),
				"Email nhập lại không đúng");

	}

	@Test
	public void TC_03_IncorrectEmail() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//input[@id=\"txtFirstname\"]")).sendKeys("quang vuong");
		driver.findElement(By.xpath("//input[@id=\"txtEmail\"]")).sendKeys("duyquang@gmail.com");
		driver.findElement(By.xpath("//input[@id=\"txtCEmail\"]")).sendKeys("duyquang@12412@");
		driver.findElement(By.xpath("//input[@id=\"txtPassword\"]")).sendKeys("12345@");
		driver.findElement(By.xpath("//input[@id=\"txtCPassword\"]")).sendKeys("12345@");
		driver.findElement(By.xpath("//input[@id=\"txtPhone\"]")).sendKeys("09521522432");

		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		driver.findElement(By.xpath("//label[@id=\"txtCEmail-error\"]")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id=\"txtCEmail-error\"]")).getText(),
				"Email nhập lại không đúng");
	}

	@Test
	public void TC_04_InvalidPassword() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//input[@id=\"txtFirstname\"]")).sendKeys("quang vuong");
		driver.findElement(By.xpath("//input[@id=\"txtEmail\"]")).sendKeys("duyquang@gmail.com");
		driver.findElement(By.xpath("//input[@id=\"txtCEmail\"]")).sendKeys("duyquang@gmail.com");
		driver.findElement(By.xpath("//input[@id=\"txtPassword\"]")).sendKeys("123");
		driver.findElement(By.xpath("//input[@id=\"txtCPassword\"]")).sendKeys("123");
		driver.findElement(By.xpath("//input[@id=\"txtPhone\"]")).sendKeys("09521522432");

		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		driver.findElement(By.xpath("//label[@id=\"txtPassword-error\"]")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id=\"txtPassword-error\"]")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}