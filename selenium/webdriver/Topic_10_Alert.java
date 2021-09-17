package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String authenChromeAutoIT = projectPath + "\\AutoIT\\authen_chrome.exe";
	JavascriptExecutor js;
	Alert alert;

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
		alert = driver.switchTo().alert();
		alert.accept();
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='You clicked an alert successfully ']")).isDisplayed());
	}

	// @Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		alert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='You clicked: Ok']")).isDisplayed());

		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		alert.dismiss();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='You clicked: Cancel']")).isDisplayed());
	}

	// @Test
	public void TC_03_Promt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		String keyAlert = "Da qua pepsi oi!!!";
		alert.sendKeys(keyAlert);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + keyAlert);
		sleepInSecond(2);

		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		alert.dismiss();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='You entered: null']")).isDisplayed());

	}

	@Test
	public void TC_4_Authencation_Alert() {
		String username = "admin";
		String password = "admin";
		String urlAuthen = "http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
		driver.get(urlAuthen);

		Assert.assertEquals(driver
				.findElement(By.xpath("//p[contains(.,'Congratulations! You must have the proper credentials.')]"))
				.getText(), "Congratulations! You must have the proper credentials.");
	}

	@Test
	public void TC_05_Authencation_Alert_AutoIT() throws IOException {
		String username = "admin";
		String password = "admin";
		String url = "http://the-internet.herokuapp.com/basic_auth";
		Runtime.getRuntime().exec(new String[] {authenChromeAutoIT, username, password });
		driver.get(url);
		Assert.assertEquals(driver
				.findElement(By.xpath("//p[contains(.,'Congratulations! You must have the proper credentials.')]"))
				.getText(), "Congratulations! You must have the proper credentials.");
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