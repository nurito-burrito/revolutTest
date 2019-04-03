package com.revolut.mavenTestNg;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class mainTask {

	public String baseUrl = "https://revolut.com";
	String driverPath = "C:\\chromedriver.exe";
	public WebDriver driver;

	@BeforeTest
	public void startBrowser() throws InterruptedException {

		System.out.println("Launching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath);

		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		Thread.sleep(2000);
		driver.manage().window().maximize();
		driver.get(baseUrl);

	}

	// Verify “We got a banking licence” topic text.
	@Test(priority = 1)
	public void clickHelp() throws InterruptedException {

		// WebDriverWait wait = new WebDriverWait(driver, 10);
		// wait.until(ExpectedConditions.elementToBeClickable(By.id("Icon-close_close"))).click();

		Actions action = new Actions(driver);
		WebElement help = driver.findElement(By.id("Help"));
		action.moveToElement(help).perform();

		driver.findElement(By.id("Help")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("search")).click();
		driver.findElement(By.id("search-term")).sendKeys("We got a banking licence");

		WebElement weGotBankingLicence = driver.findElement(By.linkText("/t/we-got-a-banking-licence/72891"));
		String strng = weGotBankingLicence.getText();
		System.out.println(strng);
		Assert.assertEquals("We got a banking licence", strng);

	}

	// Scroll to the bottom. Click on “United Kingdom” flag and change country to
	// “United States”. Verify page url.
	@Test(priority = 2)
	public void changeCountry() throws InterruptedException {

		driver.get(baseUrl);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,5000)");

		driver.findElement(By.className("United Kingdom")).click();
		driver.findElement(By.className("United Kingdom")).sendKeys("United Kingdom");
		driver.findElement(By.className("styles__StyledText-pgefe6-1 VjBYu")).click();

		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "https://www.revolut.com/en-US");

		/**
		  Also can be verified by:
		  
		  String actualData = driver.findElement(By.className("rvl-IndexPage-country")).getText(); 
		  String expectedData = "the U.S.A"; if(actualData.equals(expectedData)) {
		  System.out.println("country changed to United Stated");
		 */

	}

}
