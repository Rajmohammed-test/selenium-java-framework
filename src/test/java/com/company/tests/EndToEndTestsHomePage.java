package com.company.tests;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.framework.base.BaseTest;
import com.company.framework.driver.DriverManager;
import com.company.framework.pages.HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EndToEndTestsHomePage extends BaseTest{

	
@Test

public void VerifyAddToCart() throws InterruptedException {


	
	WebDriver driver = DriverManager.getDriver();
	
	HomePage hp = new HomePage(driver);
	
//	driver.manage().window().maximize();
	
	

//	driver.get("https://www.amazon.in");

	Thread.sleep(7000); 
	
	String searchkeyword ="iphone";
	
	hp.getSearchField().sendKeys(searchkeyword);
	
	Thread.sleep(4000);
	
	// Step: Click the first suggestion from dropdown
	
	List<WebElement> suggestions = driver.findElements(By.xpath("//div[@class='s-suggestion s-suggestion-ellipsis-direction']"));
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
	suggestions.get(0).click();
	
	Thread.sleep(7000);
	
	
	WebElement firstResult = driver.findElement(By.xpath("(//h2[@class='a-size-medium a-spacing-none a-color-base a-text-normal'])[1]"));
	firstResult.click();
	
	
	 // Step: Switch driver control to the new tab (Product Detail page)
    String originalWindow = driver.getWindowHandle();
    Set<String> allWindows = driver.getWindowHandles();

    for (String windowHandle : allWindows) {
        if (!windowHandle.equals(originalWindow)) {
            driver.switchTo().window(windowHandle);
            break;
        }
    }

    Thread.sleep(7000);
    System.out.println("Product page title: " + driver.getTitle());
    System.out.println("Product page URL: " + driver.getCurrentUrl());
    
  //  driver.findElement(By.xpath("//i[@class='a-icon a-accordion-radio a-icon-radio-inactive']")).click();
    
    
    
 // Scope to the specific feature div wrapping the real Add to Cart button, avoiding duplicates
    WebElement addToCartBtn = driver.findElement(
        By.xpath("//div[@id='addToCart_feature_div']//input[@id='add-to-cart-button']")
    );

    System.out.println("Displayed: " + addToCartBtn.isDisplayed());
    System.out.println("Enabled: " + addToCartBtn.isEnabled());
    System.out.println("Value attribute: " + addToCartBtn.getAttribute("value"));

    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView({block:'center'});", addToCartBtn);
    Thread.sleep(1000);

    // Direct JS click bypasses Selenium's visibility/opacity-based clickability checks entirely
    js.executeScript("arguments[0].click();", addToCartBtn);

    Thread.sleep(5000);
    System.out.println("URL after click: " + driver.getCurrentUrl());
    
    Thread.sleep(7000);
    
   
    
    
  WebElement addedToCartText = driver.findElement(By.xpath("//*[contains(text(),'Added to cart')]"));
    
    
  System.out.println(addedToCartText.getText());
    Assert.assertTrue(addedToCartText.isDisplayed(), "Add to Cart confirmation not shown");
    
    
    
    
    
    
	
}
	}
