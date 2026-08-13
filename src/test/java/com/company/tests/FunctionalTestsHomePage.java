package com.company.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.framework.base.BaseTest;
import com.company.framework.driver.DriverManager;
import com.company.framework.pages.HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FunctionalTestsHomePage extends BaseTest {

	@Test
	public void VerifySearchFunctionalityOnHomePage() throws InterruptedException {

	
		
		WebDriver driver = DriverManager.getDriver();

		HomePage hp = new HomePage(driver);

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.amazon.in");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		hp.getSearchField().sendKeys("iphone" + Keys.ENTER);

		String currentUrl = driver.getCurrentUrl();

		System.out.println("URL: " + currentUrl);

		Assert.assertTrue(currentUrl.contains("k=iphone"), "URL does not reflect the searched keyword");

		String title = driver.getTitle();

		System.out.println("Title: " + title);

		Assert.assertTrue(title.toLowerCase().contains("iphone"), "Page title does not reflect the search keyword");

	}

	@Test
	public void VerifyDeliverToLocationSelectorOnHomePage() throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		HomePage hp = new HomePage(driver);

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.amazon.in");
		
		Thread.sleep(5000);
		
		
		String testPincode = "560006";

		driver.findElement(By.id("nav-global-location-popover-link")).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement pincode = driver.findElement(By.id("GLUXZipUpdateInput"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		pincode.sendKeys(testPincode+Keys.ENTER);
		
		Thread.sleep(6000);
		
		
		//Validation
		
	WebElement DeliveryLocation=driver.findElement(By.xpath("//span[@class='nav-line-2 nav-progressive-content']"));
	
	
	String DeliveryLocationText = DeliveryLocation.getText();
	
	System.out.println(DeliveryLocationText);
	
	
	Assert.assertTrue(DeliveryLocationText.contains(testPincode),"Deliver to Location not reflected as per entered pincode");
	
	
	driver.quit();
	
		
		
		
		

	}

}
