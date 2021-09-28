package webdriver;

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

public class Topic_17_Wait_Part_II_FindElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By registerButton = By.xpath("//a[text()='Tạo tài khoản mới']");
	By emailTextboxBy = By.cssSelector("input[name='reg_email__']");
	By cemailTextboxBy = By.cssSelector("input[name='reg_email_confirmation__']");
	By firstNameTextBoxBy = By.cssSelector("input[name*='firstname']");
	By loginTextboxBy = By.cssSelector("#email");

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_FindElement() {
		driver.get("https://www.facebook.com/");
		// No Element -> wait and find element in 15s (0.5s/times)
		// throw exception no such element -> fail testcase

		//driver.findElement(firstNameTextBoxBy);

		// have 1 Element
		// find element until find out -> if over timeout -> fail
									// -> if find out -> pass
		driver.findElement(loginTextboxBy);

		// multi Element
		// find and affect the first element
		driver.findElement(By.cssSelector("input"));

	}

	@Test
	public void TC_02_FindElements() {
		driver.get("https://www.facebook.com/");
		// No Element-> wait and find element in 15s (0.5s/times)
		// return a list with size = 0
		driver.findElements(firstNameTextBoxBy).size();

		// have 1 Element
		// return a list with size = 1
		driver.findElements(loginTextboxBy).size();

		// have multi Element
		// return a list with size = n
		driver.findElements(By.cssSelector("input")).size();
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}