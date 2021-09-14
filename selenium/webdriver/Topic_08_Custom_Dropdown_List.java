package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import org.testng.internal.junit.ExactComparisonCriteria;

public class Topic_08_Custom_Dropdown_List {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor js;

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		explicitWait = new WebDriverWait(driver, 15);
	}

	//@Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		driver.findElement(By.id("number-button")).click();

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='number-menu']//div")));

		List<WebElement> storeAllElement = driver.findElements(By.xpath("//ul[@id='number-menu']//div"));

		for (WebElement element : storeAllElement) {
			if (element.getText().equals("5")) {
				if (element.isDisplayed()) {
					element.click();
				} else {
					js.executeScript("argument[0].scrollIntoView(True)", element);
					element.click();
				}
			}
		}

	}

	//@Test
	public void TC_02_ReactJs() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		driver.findElement(By.cssSelector(".ui.fluid.selection.dropdown")).click();
		explicitWait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='visible menu transition']/div")));

		List<WebElement> storeAllElement = driver.findElements(By.xpath("//div[@class='visible menu transition']/div"));

		for (WebElement element : storeAllElement) {
			if (element.getText().equals("Stevie Feliciano")) {
				if (element.isDisplayed()) {
					element.click();
				} else {
					js.executeScript("argument[0].scrollIntoView(True)", element);
					element.click();
				}
			}
		}

	}

	//@Test
	public void TC_03_VueJs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		customDropdown(By.cssSelector(".dropdown-toggle"), By.xpath("//ul[@class='dropdown-menu']//li"),
				"First Option");
	}

	//@Test
	public void TC_04_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		customDropdownAngular(By.xpath("//ejs-dropdownlist[@id='games']/span"), By.cssSelector("ul#games_option>li"),
				"Basketball");

		Assert.assertEquals(
				driver.findElement(By.xpath("//ejs-dropdownlist[@id='games']/span/input")).getAttribute("aria-label"),
				"Basketball");
	}

	@Test
	public void TC_05_ReactJs() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
	customEditableDropdown(By.xpath("//div[text()='Default']/following-sibling::div/input"),
			By.xpath("//ul[@class=\"es-list\"]/li[@style=\'display: none;']"),"BMW","BMW");
	
	Assert.assertEquals(getHiddenText("div[id='default-place'] input[type='text']"), "BMW");
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

	public void customDropdown(By parentBy, By childBy, String ExpectedItem) {

		driver.findElement(parentBy).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

		List<WebElement> storeAllElement = driver.findElements(childBy);

		for (WebElement element : storeAllElement) {
			if (element.getText().trim().equals(ExpectedItem)) {
				if (element.isDisplayed()) {
					element.click();
				} else {
					js.executeScript("argument[0].scrollIntoView(True)", element);
					element.click();
				}
			}
		}
	}
	
	public void customDropdownAngular(By parentBy, By childBy, String ExpectedItem) {
		driver.findElement(parentBy).click();
		//explicitWait.until(ExpectedConditions.elementToBeClickable(childBy));
		
		List<WebElement> storeItems = driver.findElements(childBy);
		for (WebElement item : storeItems) {
			if(item.getText().trim().equals(ExpectedItem)) {
				if(item.isDisplayed()) {
					item.click();
				}else {
					js.executeScript("argument[0].scrollIntoView(True)", item);
					item.click();
				}
			}
		}
		
	}

	public void customEditableDropdown(By parentBy, By childBy, String ExpectedItem, String sendKey) {

		driver.findElement(parentBy).sendKeys(sendKey);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

		List<WebElement> storeAllElement = driver.findElements(childBy);

		for (WebElement element : storeAllElement) {
			if (element.getText().trim().equals(ExpectedItem)) {
				if (element.isDisplayed()) {
					element.click();
				} else {
					js.executeScript("argument[0].scrollIntoView(True)", element);
					element.click();
				}
				break;
			}
		}
	}
	
	public String getHiddenText(String cssLocator) {
		return (String) js.executeScript("return document.querySelector(\"" + cssLocator + "\").value");
	}
}