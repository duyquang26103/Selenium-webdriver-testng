package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_JSExecutor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

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

	// @Test
	public void TC_01_JavaExecutor() {

		// navigateToUrlByJS("http://live.demoguru99.com/");
		driver.get("http://live.demoguru99.com/");

		String jsDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(jsDomain, "live.demoguru99.com");

		String jsUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(jsUrl, "http://live.demoguru99.com/");

		clickToElementByJS("//a[text()='Mobile']");
		clickToElementByJS(
				"//a[@title='Samsung Galaxy']/ancestor::div[@class='product-info']//button[@title='Add to Cart']");
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."),
				"Samsung Galaxy was added to your shopping cart.");
		clickToElementByJS("//a[text()='Customer Service']");

		String jsTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(jsTitle, "Customer Service");

		scrollToElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", "xabo@gmail.com");
		clickToElementByJS("//button[@title='Subscribe']");
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."),
				"Thank you for your subscription.");
		navigateToUrlByJS("http://demo.guru99.com/v4/");

		String jsDomain1 = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(jsDomain1, "demo.guru99.com");

	}

	// @Test
	public void TC_02_Validate_Message() {
		driver.get("https://automationfc.github.io/html5/index.html");

		highlightElement("//input[@name='submit-btn']");
		clickToElementByJS("//input[@name='submit-btn']");
		String nameMessage = getElementValidationMessage("//input[@id='fname']");
		Assert.assertEquals(nameMessage, "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='fname']", "Abolo Kira");
		clickToElementByJS("//input[@name='submit-btn']");
		String passMessage = getElementValidationMessage("//input[@id='pass']");
		Assert.assertEquals(passMessage, "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='pass']", "123123");
		clickToElementByJS("//input[@name='submit-btn']");
		String emMessage = getElementValidationMessage("//input[@id='em']");
		Assert.assertEquals(emMessage, "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='em']", "12512@14");
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(emMessage, "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='em']", "123!@#$");
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(emMessage, "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='em']", "kakaka@gmail.com");
		clickToElementByJS("//input[@name='submit-btn']");
		String selectMessage = getElementValidationMessage("//select");
		Assert.assertEquals(selectMessage, "Please select an item in the list.");

	}

	// @Test
	public void TC_03_HTML5_Validate_Message() {
		driver.get("https://login.ubuntu.com/");

		highlightElement("//input[@class='textType']");
		sendkeyToElementByJS("//input[@class='textType']", "a");
		clickToElementByJS("//button[@data-qa-id='login_button']");
		String aMessage = getElementValidationMessage("//input[@class='textType']");
		Assert.assertEquals(aMessage, "Please include an '@' in the email address. 'a' is missing an '@'.");

		navigateToUrlByJS("https://sieuthimaymocthietbi.com/account/register");
		highlightElement("//button[text()='Đăng ký']");
		clickToElementByJS("//button[text()='Đăng ký']");
		String bMessage = getElementValidationMessage("//input[@id='lastName']");
		Assert.assertEquals(bMessage, "Please fill out this field.");

		navigateToUrlByJS("https://warranty.rode.com/");
		clickToElementByJS("//button[contains(text(),'Register')]");
		String cMessage = getElementValidationMessage("//input[@id='firstname']");
		Assert.assertEquals(cMessage, "Please fill out this field.");

		navigateToUrlByJS("https://www.pexels.com/vi-vn/join-contributor/");
		clickToElementByJS("//button[contains(.,'Tạo tài khoản mới')]");
		String dMessage = getElementValidationMessage("//input[@id='user_first_name']");
		Assert.assertEquals(dMessage, "Please fill out this field.");

	}

	//@Test
	public void TC_04_Remove_Attribute() {
		driver.get("http://demo.guru99.com/v4/");
		currentURL = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passWordLI = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		driver.get(currentURL);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(passWordLI);
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(genderRadioBy).click();

		jsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(dateTextboxBy));

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
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				"female");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				date);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}

	@Test
	public void TC_05_Create_Account() {
		String firstName = "silva";
		String lastName = "strom";
		String email = "silva" + randomInt() + "@gmail.com";
		String password = "123123";

		driver.get("http://live.demoguru99.com/");
		clickToElementByJS("//span[@class='label'][normalize-space()='Account']");
		clickToElementByJS("//div[@class='skip-content skip-active']//a[text()='My Account']");
		clickToElementByJS("//a[@class='button']");
		sendkeyToElementByJS("//input[@id='firstname']", firstName);
		sendkeyToElementByJS("//input[@id='lastname']", lastName);
		sendkeyToElementByJS("//input[@id='email_address']", email);
		sendkeyToElementByJS("//input[@id='password']", password);
		sendkeyToElementByJS("//input[@id='confirmation']", password);

		clickToElementByJS("//span[text()='Register']");

		Assert.assertTrue(areExpectedTextInInnerText("Thank you for registering with Main Website Store."));
		clickToElementByJS("//a[@title='Log Out']");
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Default welcome msg! ']")).isDisplayed());
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int randomInt() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
}