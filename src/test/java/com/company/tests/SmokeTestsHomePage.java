package com.company.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.framework.base.BaseTest;
import com.company.framework.driver.DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SmokeTestsHomePage extends BaseTest {
	
@Test
public void VerifyHomePageLoading() throws InterruptedException {
	


	
WebDriver driver = DriverManager.getDriver();

driver.manage().window().maximize();

driver.get("https://www.amazon.in");

Thread.sleep(3000); 


String currentUrl = driver.getCurrentUrl();

String title = driver.getTitle();

System.out.println(title);
System.out.println(currentUrl);

Assert.assertTrue(currentUrl.contains("amazon.in"), "URL did not navigate to Amazon");
Assert.assertTrue(title.toLowerCase().contains("amazon"), "Home page title does not contain 'Amazon' - page may not have loaded correctly");
driver.quit();
}


	
	


}
