package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Command_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By emailTextboxBy = By.id("mail");
	By ageOver18RadioBy = By.id("over_18");
	By educationTextboxBy = By.id("edu");
	By user5TextboxBy = By.xpath("//h5[contains(.,'Name: User5')]");
	By job1SelectBy = By.id("job1");
	By job2SelectBy = By.id("job2");
	By developmentSelectBy = By.id("development");
	By slide01SliderBy = By.id("slider-1");
	By javaSelectedBy = By.id("java");

	By passwordTextboxBy = By.id("password");
	By disableRadioBy = By.id("radio-disabled");
	By bioTextboxBy = By.id("bio");
	By job3SelectBy = By.id("job3");
	By checkDisableCBDBy = By.id("check-disbaled");
	By slide02SliderBy = By.id("slider-2");

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_isDisplayed() {
		if (driver.findElement(By.id("mail")).isDisplayed()) {
			driver.findElement(By.id("mail")).sendKeys("Automation Testing");
			System.out.println("Email is displayed");
		} else {
			System.out.println("Email is not displayed");
		}

		if (driver.findElement(By.id("under_18")).isDisplayed()) {
			System.out.println("under_18 is displayed");
		} else {
			System.out.println("Email is not displayed");
		}

		if (driver.findElement(By.id("edu")).isDisplayed()) {
			driver.findElement(By.id("edu")).sendKeys("Automation Testing");
			System.out.println("Education is displayed");
		} else {
			System.out.println("Email is not displayed");
		}

		Assert.assertFalse(driver.findElement(By.xpath("//h5[contains(.,'Name: User5')]")).isDisplayed());
	}

	@Test
	public void TC_02_isDisplayed_Refactor() {
		if (isElementDisplayBy(emailTextboxBy)) {
			sendKeytoElement(emailTextboxBy, "Automation Testing");
		}

		if (isElementDisplayBy(ageOver18RadioBy)) {
			clickToElement(ageOver18RadioBy);
		}

		if (isElementDisplayBy(educationTextboxBy)) {
			sendKeytoElement(educationTextboxBy, "Automation Testing");
		}

		Assert.assertFalse(isElementDisplayBy(user5TextboxBy));
	}

	@Test
	public void TC_03_isEnabled() {
		Assert.assertTrue(isElementEnabledBy(emailTextboxBy));
		Assert.assertTrue(isElementEnabledBy(ageOver18RadioBy));
		Assert.assertTrue(isElementEnabledBy(educationTextboxBy));
		Assert.assertTrue(isElementEnabledBy(job1SelectBy));
		Assert.assertTrue(isElementEnabledBy(job2SelectBy));
		Assert.assertTrue(isElementEnabledBy(developmentSelectBy));
		Assert.assertTrue(isElementEnabledBy(slide01SliderBy));
		Assert.assertFalse(isElementEnabledBy(passwordTextboxBy));
		Assert.assertFalse(isElementEnabledBy(disableRadioBy));
		Assert.assertFalse(isElementEnabledBy(bioTextboxBy));
		Assert.assertFalse(isElementEnabledBy(job3SelectBy));
		Assert.assertFalse(isElementEnabledBy(slide02SliderBy));
		Assert.assertFalse(isElementEnabledBy(checkDisableCBDBy));

	}

	@Test
	public void TC_04_isSelected() {
		clickToElement(ageOver18RadioBy);
		clickToElement(javaSelectedBy);
		Assert.assertTrue(isElementSelectedBy(ageOver18RadioBy));
		Assert.assertTrue(isElementSelectedBy(javaSelectedBy));
		clickToElement(javaSelectedBy);
		Assert.assertFalse(isElementSelectedBy(javaSelectedBy));
	}

	public boolean isElementDisplayBy(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element" + by + " is displayed");
			return true;
		} else {
			System.out.println("Element" + by + " is not displayed");
			return false;
		}
	}

	public boolean isElementEnabledBy(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element" + by + " is Enabled");
			return true;
		} else {
			System.out.println("Element" + by + " is not Enabled");
			return false;
		}
	}
	
	public boolean isElementSelectedBy(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element" + by + " is Selected");
			return true;
		} else {
			System.out.println("Element" + by + " is not Selected");
			return false;
		}
	}

	public void sendKeytoElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}

	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}