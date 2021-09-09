package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Topic_04_Xpath_Css_III {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	
	String firstName = "silva";
	String lastName = "strom";
	String email = "silva" + randomInt() + "@gmail.com";
	String password ="123123";
	
	
	By accountTextBy = By.cssSelector("div[class='footer'] a[title='My Account']");
	By emailTextboxBy = By.id("email");
	By passwordTextboxBy = By.id("pass");
	By loginButtonBy = By.id("send2");
	By createButtonBy = By.xpath("//a[@class='button']/child::span");
	
	By firstNameTextboxBy = By.id("firstname");
	By lastNameTextboxBy = By.id("lastname");
	By emailAdressTextboxBy = By.id("email_address");
	By passwordRegisterTexboxBy = By.id("password");
	By confirmPasswordTexboxBy = By.id("confirmation");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@BeforeMethod
	public void beforMethod() {
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC_01_EmptyData() {
		driver.findElement(accountTextBy).click();
		driver.findElement(loginButtonBy).click();
		String notifyEmail1 = driver.findElement(By.id("advice-required-entry-email")).getText();
		String notifyPassword = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(notifyEmail1, "This is a required field.");
		Assert.assertEquals(notifyPassword, "This is a required field.");

	}

	@Test
	public void TC_02_InvalidEmail() {
		driver.findElement(accountTextBy).click();
		driver.findElement(emailTextboxBy).sendKeys("123123123@123123");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(loginButtonBy).click();
		String notifyEmail2 = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(notifyEmail2, "Please enter a valid email address. For example johndoe@domain.com.");
		
	}

	@Test
	public void TC_03_IncorrectPassword() {
		driver.findElement(accountTextBy).click();
		driver.findElement(emailTextboxBy).sendKeys("automation@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("123");
		driver.findElement(loginButtonBy).click();
		String notifyPassword2 = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(notifyPassword2, "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_Incorrect_Email_Password() {
		driver.findElement(accountTextBy).click();
		driver.findElement(emailTextboxBy).sendKeys("automation@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("1235855433");
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
	}
	
	@Test
	public void TC_05_Creat_Account() {
	
		driver.findElement(accountTextBy).click();
		driver.findElement(createButtonBy).click();
		driver.findElement(firstNameTextboxBy).sendKeys(firstName);
		driver.findElement(lastNameTextboxBy).sendKeys(lastName);
		driver.findElement(emailAdressTextboxBy).sendKeys(email);
		driver.findElement(passwordRegisterTexboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTexboxBy).sendKeys(password);
		driver.findElement(By.xpath("//p[@class='back-link']/following::button//span[text()='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class=\"success-msg\"]//span[text()='Thank you for registering with Main Website Store.']")).getText(), "Thank you for registering with Main Website Store.");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"col-1\"]//p")).getText().contains(firstName + " " + lastName));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"col-1\"]//p")).getText().contains(email));
		
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']/span[@class='label']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		Assert.assertEquals(driver.getTitle(),"Magento Commerce");
	}
	
	@Test
	public void TC_06_Valid_Email_Password() {
		driver.findElement(accountTextBy).click();
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.cssSelector(".hello")).getText(), "Hello, "+ firstName +" " + lastName + "!");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(firstName + " " + lastName));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(email));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomInt() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
	
	
}