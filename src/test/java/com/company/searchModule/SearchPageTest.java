package com.company.searchModule;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.framework.pages.SearchPage;

public class SearchPageTest {
	
	@Test
	public void searchWithExistingProducts() {
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.amazon.in/");
		
		SearchPage sp=new SearchPage(driver);
		
		sp.getSearchTextFeild().sendKeys("oneplus nord ce 6 lite phone");
		
		sp.getSearchButton().click();
		
		String actualText="Nord CE6 Lite | 6GB+128GB | Hyper Black | Segment's Fastest Dimensity 7400 Apex Processor | 7000mAh Battery | Segment's Highest 144Hz Refresh Rate | 50MP Main Camera, 4K Video Recording";
		
		String ExpectedphoneText = driver.findElement(By.xpath("(//span[contains(text(),'6GB+128GB | Hyper Black ')])[1]")).getText();
		
		System.out.println(ExpectedphoneText);
		
		Assert.assertEquals(actualText, ExpectedphoneText);
		
	}
	
	

}
