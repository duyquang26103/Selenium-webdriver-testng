package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Interaction {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;
	Alert alert;
	JavascriptExecutor js;

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);

		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Hover_Element_1() {

		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");

	}

	@Test
	public void TC_02_Hover_Element_2() {

		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());

	}

	@Test
	public void TC_03_Hover_Element_3() {

		driver.get("https://www.fahasa.com/?attempt=1");
		action.moveToElement(driver.findElement(
				By.xpath("//div[@class='header-breadcrumbs  background-menu-homepage  ']//span[text()='Đồ Chơi']")))
				.perform();

		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@class='header-breadcrumbs  background-menu-homepage  ']//a[@title='Đồ Chơi']/following-sibling::div//a[text()='Xếp Hình - Lắp Ghép']"))
				.isDisplayed());
	}

	@Test
	public void TC_04_Click_Hold() {

		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> AllItems = driver.findElements(By.cssSelector(".ui-state-default.ui-selectee"));

		action.clickAndHold(AllItems.get(0)).moveToElement(AllItems.get(3)).release().perform();

		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-state-default.ui-selectee.ui-selected")).size(), 4);

	}

	@Test
	public void TC_05_Click_Hold_Random() {

		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> AllItems = driver.findElements(By.cssSelector(".ui-state-default.ui-selectee"));

		action.keyDown(Keys.CONTROL);
		action.click(AllItems.get(0)).click(AllItems.get(4)).click(AllItems.get(7)).perform();
		action.keyUp(Keys.CONTROL);

		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-state-default.ui-selectee.ui-selected")).size(), 3);
	}

	@Test
	public void TC_06_Double_Click() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		By doubleClick = By.xpath("//button[@ondblclick='doubleClickMe()']");

		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(doubleClick));
		action.doubleClick(driver.findElement(doubleClick)).perform();

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}