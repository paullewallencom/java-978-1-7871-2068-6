package com.sample.tests.testng;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;

public class BookingSearchTest {
	private WebDriver driver;

	@BeforeTest
	public void setUp() throws Exception {
		Configuration.load();
		Configuration.print();
		String baseUrl = Configuration.get("url");
		System.setProperty("webdriver.gecko.driver", new File("drivers/geckodriver").getAbsolutePath());
		System.setProperty("webdriver.chrome.driver", new File("drivers/chromedriver").getAbsolutePath());
		
		DesiredCapabilities cap = new DesiredCapabilities();
		Driver.add(Configuration.get("browser"), cap);
		driver = Driver.current();
		driver.get(baseUrl);
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "source")
	public static Object[][] getParameters() {
        return new Object[][] {
        		{"London", true, 2 },
        		{"Manchester", false, 1 },
        };
    }
	
	@Test(dataProvider="source")
	public void testValidSearch(String destination, boolean isLeisure,
			int numberOfAdults) {
		driver.findElement(By.id("ss")).click();
		driver.findElement(By.id("ss")).clear();
		driver.findElement(By.id("ss")).sendKeys(destination);
		driver.findElement(
				By.cssSelector("i.sb-date-field__chevron.bicon-downchevron"))
				.click();
		
		driver.findElement(
				By.xpath("//table[@class='c2-month-table']//td[contains(@class, 'c2-day-s-today')]"))
				.click();
		if (isLeisure) {
			driver.findElement(By.xpath("(//input[@name='sb_travel_purpose'])[2]"))
					.click();
		} else {
			driver.findElement(By.xpath("(//input[@name='sb_travel_purpose'])[1]"))
					.click();
		}
		driver.findElement(By.xpath("(//input[@name='nflt'])[2]")).click();
		new Select(driver.findElement(By.id("group_adults")))
				.selectByVisibleText("" + numberOfAdults);
		driver.findElement(
				By.cssSelector("#group_adults > option[value=\"" + numberOfAdults + "\"]")).click();
		driver.findElement(By.xpath("//button[@type='submit']"))
				.click();
		driver.findElement(By.id("ss")).click();
	}

}
