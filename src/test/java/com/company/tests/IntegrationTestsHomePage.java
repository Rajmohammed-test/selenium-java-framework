package com.company.tests;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.framework.base.BaseTest;
import com.company.framework.driver.DriverManager;
import com.company.framework.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class IntegrationTestsHomePage extends BaseTest {
	

@Test
public void VerifyHomePageSearchBarIntegratesWithSearchSuggestionsDropdown() throws InterruptedException {
	
	
	
	WebDriver driver = DriverManager.getDriver();
	
	HomePage hp = new HomePage(driver);
	
	driver.manage().window().maximize();
	
	

	driver.get("https://www.amazon.in");

	Thread.sleep(7000); 
	
	String searchkeyword ="iphone";
	
	hp.getSearchField().sendKeys(searchkeyword);
	
	Thread.sleep(4000);
	
	List<WebElement> suggestions = driver.findElements(By.xpath("//div[@class='s-suggestion s-suggestion-ellipsis-direction']"));

    System.out.println("Number of suggestions shown: " + suggestions.size());
    
    // 1. Dropdown actually appeared
    
    Assert.assertTrue(suggestions.size() > 0, "No search suggestions were displayed for keyword: " + searchkeyword);
    
    // 2. ALL visible suggestions must contain the keyword
    
    boolean allMatch = true;
    
    for (WebElement suggestion : suggestions) {
        String suggestionText = suggestion.getText().toLowerCase();
        System.out.println("Suggestion: " + suggestionText);

        if (!suggestionText.contains(searchkeyword.toLowerCase())) {
            allMatch = false;
            System.out.println("Mismatch found: \"" + suggestionText + "\" does not contain \"" + searchkeyword + "\"");
        }
    }

    Assert.assertTrue(allMatch, "One or more suggestions did not contain the searched keyword: " + searchkeyword);

    
    driver.quit();

}
	
	}
