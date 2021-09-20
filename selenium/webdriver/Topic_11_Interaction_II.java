package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Interaction_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;
	JavascriptExecutor javascriptExecutor;
	String jsHelperPath = projectPath + "\\\\dragAndDrop\\drag_and_drop_helper.js";

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		javascriptExecutor = (JavascriptExecutor) driver;
	}

	// @Test
	public void TC_01_Right_Click() {
		By quitE = By.cssSelector(".context-menu-item.context-menu-icon.context-menu-icon-quit");
		By quitEHold = By.cssSelector(
				".context-menu-item.context-menu-icon.context-menu-icon-quit.context-menu-visible.context-menu-hover");
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		action.contextClick(driver.findElement(By.cssSelector(".context-menu-one.btn.btn-neutral"))).perform();
		action.moveToElement(driver.findElement(quitE)).perform();
		Assert.assertEquals(driver.findElement(quitEHold).getAttribute("class"),
				"context-menu-item context-menu-icon context-menu-icon-quit context-menu-hover context-menu-visible");
		action.click(driver.findElement(quitEHold)).perform();
		driver.switchTo().alert().accept();
		Assert.assertFalse(driver.findElement(quitE).isDisplayed());

	}

	// @Test
	public void TC_02_DragDrop_HTML4() {

		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		action.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droptarget"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='You did great!']")).isDisplayed());

		String rbaColor = driver.findElement(By.id("droptarget")).getCssValue("background-color");
		String hexaColor = Color.fromString(rbaColor).asHex();
		Assert.assertEquals(hexaColor, "#03a9f4");

	}

	//@Test
	public void TC_03_DragDrop_HTML5_Css() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		String jsHelperFileContent = getContentFile(jsHelperPath);
		String sourceCss = "#column-a";
		String targetCss = "#column-b";
		// A to B
		jsHelperFileContent = jsHelperFileContent + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \""
				+ targetCss + "\"});";
		javascriptExecutor.executeScript(jsHelperFileContent);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
		// B to A
		javascriptExecutor.executeScript(jsHelperFileContent);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());

	}

	@Test
	public void TC_04_DragDrop_HTML5_Xpath() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		DragtoDropbyHTML5_Robot("//div[@id='column-a']","//div[@id='column-b']");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
		
		DragtoDropbyHTML5_Robot("//div[@id='column-a']","//div[@id='column-b']");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());

	}

	@AfterClass
	public void AfterClass() {
		driver.quit();

	}

	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void DragtoDropbyHTML5_Robot(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
}