package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown_List {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	Select select;
	
	//testcase 2
	String fName, lName, day, month, year, email, companyName, password, cPassword;
	By genderRadioBy = By.id("gender-male");
	By fNameTextboxBy = By.id("FirstName");
	By lNameTextboxBy = By.id("LastName");
	By dayBy = By.name("DateOfBirthDay");
	By monthBy = By.name("DateOfBirthMonth");
	By yearBy = By.name("DateOfBirthYear");
	By emailTextboxBy = By.id("Email");
	By companyNameTextboxBy = By.id("Company");
	By passworTextboxdBy = By.id("Password");
	By cPassworTextboxdBy = By.id("ConfirmPassword");
	By registerButtonBy = By.id("register-button");
	
	

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		email = "shogim" + randInt() + "@gmail.com";
	}

	//@Test
	public void TC_01_Rode() {

		driver.get("https://www.rode.com/wheretobuy");
		
		select = new Select(driver.findElement(By.name("where_country")));
		select.selectByVisibleText("Vietnam");
		Assert.assertFalse(select.isMultiple());
		
		driver.findElement(By.id("search_loc_submit")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//select[@name='where_country']/option[text()='Vietnam']")).isDisplayed());
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		driver.findElement(By.id("search_loc_submit")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='search_results_count']//span[text()='29']")).isDisplayed());
		List<WebElement> storeName = driver.findElements(By.xpath("//div[@class='result_item']//div[@class='store_name']"));
		Assert.assertEquals(storeName.size(), 29);
		
		for (WebElement store : storeName) {
			System.out.println(store.getText());
		}
		
	}
	
	@Test
	public void TC_02_Ecommerce() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector(".ico-register")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Register']")).isDisplayed());
		
		driver.findElement(genderRadioBy).click();
		
		select = new Select(driver.findElement(dayBy));
		select.selectByVisibleText("1");
		List<WebElement> dateOfBirthDay = driver.findElements(By.xpath("//select[@name='DateOfBirthDay']/option"));
		Assert.assertEquals(dateOfBirthDay.size(),32);
		
		select = new Select(driver.findElement(monthBy));
		select.selectByVisibleText("May");
		List<WebElement> dateOfBirthMonth = driver.findElements(By.xpath("//select[@name='DateOfBirthMonth']/option"));
		Assert.assertEquals(dateOfBirthMonth.size(),13);
		
		select = new Select(driver.findElement(yearBy));
		select.selectByVisibleText("1999");
		List<WebElement> dateOfBirthYear = driver.findElements(By.xpath("//select[@name='DateOfBirthYear']/option"));
		Assert.assertEquals(dateOfBirthYear.size(),112);
		
		driver.findElement(fNameTextboxBy).sendKeys("carlot");
		driver.findElement(lNameTextboxBy).sendKeys("miro");
		
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(companyNameTextboxBy).sendKeys("sabialonso TDA");
		driver.findElement(passworTextboxdBy).sendKeys("123123");
		driver.findElement(cPassworTextboxdBy).sendKeys("123123");
		
		driver.findElement(registerButtonBy).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[@class='button-1 register-continue-button']")).click();
		
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		
		Assert.assertEquals(driver.findElement(genderRadioBy).getAttribute("value"), "M");

		
		Assert.assertEquals(driver.findElement(fNameTextboxBy).getAttribute("value"), "carlot");
		Assert.assertEquals(driver.findElement(lNameTextboxBy).getAttribute("value"), "miro");
		Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"), email);
		
		Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[text()='1']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']/option[text()='May']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']/option[text()='1999']")).isDisplayed());

		
		Assert.assertEquals(driver.findElement(companyNameTextboxBy).getAttribute("value"), "sabialonso TDA");
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
	
	public int randInt() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
}