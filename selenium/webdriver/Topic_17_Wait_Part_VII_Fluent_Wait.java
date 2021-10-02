package webdriver;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_17_Wait_Part_VII_Fluent_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor js;

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	//@Test
	public void TC_01_Fluent_Wait_01() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		By countDown = By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']");
		waitElementDisplayFluent(countDown);

		Assert.assertTrue(driver.findElement(countDown).isDisplayed());

	}

	//@Test
	public void TC_02_Fluent_Wait_02() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		By startButton = By.xpath("//button[text()='Start']");
		By helloTextBox = By.xpath("//div[@id='finish']/h4");
		isClickToElementFluent(startButton);

		waitElementDisplayFluent(helloTextBox);
		Assert.assertTrue(driver.findElement(helloTextBox).isDisplayed());
	}

	@Test
	public void TC_03_Ajax_Loading() {
		driver.get("https://blog.testproject.io/");
		Assert.assertTrue(isJQueryLoadedSuccess(driver));
		WebElement popUp = driver.findElement(By.cssSelector("div.mailch-wrap"));
		if(popUp.isDisplayed()) {
			popUp.click();
		}
		
		driver.findElement(By.cssSelector("section#search-2 .search-field")).sendKeys("Selenium");
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> JQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Boolean) js.executeScript("return (window.jQuery != null)&&(jQuery.active === 0);");
			}

		};
		return explicitWait.until(JQueryLoad);
	}

	public boolean isJQueryAndAjaxIconLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> JQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) js.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}

			}

		};

		ExpectedCondition<Boolean> ajaxIconLoading = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return js.executeScript("return $('.raDiv').is(':visible')").toString().equals("false");
			}

		};

		return explicitWait.until(JQueryLoad) && explicitWait.until(ajaxIconLoading);
	}

	public boolean isJQueryAndPageLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> JQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) js.executeScript("return JQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}

			}

		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return js.executeScript("return document.readyState").toString().equals("complete");
			}

		};

		return explicitWait.until(JQueryLoad) && explicitWait.until(jsLoad);
	}

	public WebElement getElement(By locator) {
		FluentWait<WebDriver> fluentwait;
		fluentwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class);

		WebElement element = (WebElement) fluentwait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}

	public Boolean waitElementDisplayFluent(By locator) {
		WebElement element = getElement(locator);
		FluentWait<WebElement> fluentElement;
		fluentElement = new FluentWait<WebElement>(element);

		fluentElement.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class);

		Boolean waitElementDisplay = fluentElement.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				boolean flag = element.isDisplayed();
				return flag;
			}
		});
		return waitElementDisplay;
	}

	public void isClickToElementFluent(By locator) {
		FluentWait<WebDriver> fluentdriver;
		fluentdriver = new FluentWait<WebDriver>(driver);
		fluentdriver.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class);

		WebElement element = (WebElement) fluentdriver.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		element.click();
	}
}