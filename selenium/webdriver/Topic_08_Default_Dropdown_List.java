package webdriver;

import java.util.Iterator;
import java.util.List;
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

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
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

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}