package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	// action
	By nameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirmpasswordTextboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	By registerbuttonBy = By.xpath("//button[@class='btn_pink_sm fs16']");

	// error message
	By nameErrorMsgBy = By.id("txtFirstname-error");
	By emailErrorMsgBy = By.id("txtEmail-error");
	By confirmEmailErrorMsgBy = By.id("txtCEmail-error");
	By passwordErrorMsgBy = By.id("txtPassword-error");
	By confirmpasswordErrorMsgBy = By.id("txtCPassword-error");
	By phoneErrorMsgBy = By.id("txtPhone-error");

	String name, emailAddress, password, phone;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		name = "Duy Quang";
		emailAddress = "Qqquang@gmail.com";
		password = "123456";
		phone = "09866868685";
	}

	@BeforeMethod
	public void beforMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_EmptyData() {

		driver.findElement(registerbuttonBy).click();
		Assert.assertEquals(driver.findElement(nameErrorMsgBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmpasswordErrorMsgBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_InvalidEmail() {

		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys("duyquang@12412@");
		driver.findElement(confirmEmailTextboxBy).sendKeys("duyquang@12412@");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmpasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerbuttonBy).click();

		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_03_IncorrectEmail() {

		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys("duyquang@12412@");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmpasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(registerbuttonBy).click();

		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_04_InvalidPassword() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys("duyquang@12412@");
		driver.findElement(passwordTextboxBy).sendKeys("123");
		driver.findElement(confirmpasswordTextboxBy).sendKeys("123");
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(registerbuttonBy).click();

		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmpasswordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_05_IncorrectPassword() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys("duyquang@12412@");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmpasswordTextboxBy).sendKeys("1231234");
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(registerbuttonBy).click();

		Assert.assertEquals(driver.findElement(confirmpasswordErrorMsgBy).getText(), "Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_06_InvalidPhone() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys("duyquang@12412@");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmpasswordTextboxBy).sendKeys("1231234");
		driver.findElement(phoneTextboxBy).sendKeys(emailAddress);
		driver.findElement(registerbuttonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập con số");
		
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("0314545");
		driver.findElement(registerbuttonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("03145457897897");
		driver.findElement(registerbuttonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("12345678");
		driver.findElement(registerbuttonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}