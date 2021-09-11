package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_TextBox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExcutor;
	String currentURL, userID, passWordLI, email, customerID;
	String name, date, address, city, state, pin, phone, pass;
	String addressEdit, cityEdit, stateEdit, phoneEdit, emailEdit;

	By nameTextboxBy = By.name("name");
	By genderRadioBy = By.xpath("//input[@value='f']");
	By dateTextboxBy = By.name("dob");
	By addressTextAreaBy = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By phoneTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By passTextboxBy = By.name("password");

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		jsExcutor = (JavascriptExecutor) driver;

		email = "toothless" + randomInt() + "@gmail.com";
		name = "shiro";
		date = "1990-01-01";
		address = "tao dai";
		city = "caili";
		state = "texas";
		pin = "121255";
		phone = "095435342";
		pass = "tesst123";

		addressEdit = "hello";
		cityEdit = "newyork";
		stateEdit = "banshee";
		phoneEdit = "098466545";
		emailEdit = "shiro123";

	}

	@Test
	public void TC_01_Register() {
		driver.get("http://demo.guru99.com/v4/");
		currentURL = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passWordLI = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() {
		driver.get(currentURL);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(passWordLI);
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());
	}

	@Test
	public void TC_03_Add_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(genderRadioBy).click();
		

		jsExcutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(dateTextboxBy));

		driver.findElement(dateTextboxBy).sendKeys(date);
		driver.findElement(addressTextAreaBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passTextboxBy).sendKeys(pass);

		driver.findElement(By.name("sub")).click();

		Assert.assertTrue(
		driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), "female");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), date);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

	}

	@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();

		driver.findElement(By.name("addr")).clear();
		driver.findElement(By.name("addr")).sendKeys(addressEdit);
		driver.findElement(By.name("city")).clear();
		driver.findElement(By.name("city")).sendKeys(cityEdit);
		driver.findElement(By.name("state")).clear();
		driver.findElement(By.name("state")).sendKeys(stateEdit);
		driver.findElement(By.name("telephoneno")).clear();
		driver.findElement(By.name("telephoneno")).sendKeys(phoneEdit);
		driver.findElement(By.name("emailid")).clear();
		driver.findElement(By.name("emailid")).sendKeys(emailEdit);

		driver.findElement(By.name("sub")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), cityEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), stateEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phoneEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailEdit);
	}

	@AfterClass
	public void AfterClass() {
		//driver.quit();
	}

	public int randomInt() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
}