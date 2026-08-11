package com.company.searchModule;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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
		
		sp.getSearchTextField().sendKeys("OnePlus Nord CE6 Lite");
		
		sp.getSearchButton().click();
		
		String actualText="Nord CE6 Lite | 6GB+128GB | Hyper Black | Segment's Fastest Dimensity 7400 Apex Processor | 7000mAh Battery | Segment's Highest 144Hz Refresh Rate | 50MP Main Camera, 4K Video Recording";
		
		String ExpectedphoneText = driver.findElement(By.xpath("(//span[contains(text(),'6GB+128GB | Hyper Black ')])[1]")).getText();
		
		System.out.println(ExpectedphoneText);
		
		Assert.assertEquals(actualText, ExpectedphoneText);
		
	}
	
	
	  @Test
	    public void verifySearchWithSpecialCharacters() {
		  
		  	WebDriver driver = new ChromeDriver();
		  	
		  	driver.get("https://www.amazon.in/");
		  	
		  	driver.manage().window().maximize();
		  	
		  	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  	
		  	SearchPage sr=new SearchPage(driver);
		  	
	        List<String> searchData = Arrays.asList(
	                "@phone",
	                "#mobile",
	                "$100",
	                "50%",
	                "phone & case",
	                "phone*",
	                "one-plus",
	                "one_plus",
	                "mobile/phone",
	                "one+",
	                "@phone#123",
	                "@#$%&*",
	                "iPhone @ 15",
	                "@iphone@"
	        );

	        for (String searchText : searchData) {

	            System.out.println("Searching for: " + searchText);

	            sr.getSearchTextField().sendKeys(searchText);
	            
	            sr.searchProduct(searchText);
	            
	            
	            Assert.assertTrue(
	                    sr.isSearchPageDisplayed(),
	                    "Search page is not displayed for: " + searchText
	            );
	            
	            System.out.println(
	                    "Search completed successfully for: " + searchText
	            );
	        }
	    }
	  
	  @Test
	    public void verifySearchSuggestionsFunctionality() {

	        WebDriver driver = new ChromeDriver();

	        driver.manage().window().maximize();
	        
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	        driver.get("https://www.amazon.in/");

	        SearchPage sr = new SearchPage(driver);

	        // Enter partial search term
	        sr.enterSearchText("mobile");

	        // Verify suggestions are displayed
	        Assert.assertTrue(
	                sr.areSuggestionsDisplayed(),
	                "Search suggestions are not displayed"
	        );

	        // Get suggestion count
	        int suggestionCount = sr.getSuggestionCount();

	        System.out.println("Number of suggestions: " + suggestionCount);

	        // Print all suggestions
	        for (int i = 0; i < suggestionCount; i++) {

	            System.out.println(
	                    "Suggestion " + (i + 1) + ": "
	                    + sr.getSuggestionText(i)
	            );
	        }

	        Assert.assertTrue(
	                suggestionCount > 0,
	                "No search suggestions were displayed"
	        );

	        driver.quit();
	    }
	  
	  public class PartialKeywordSearchTest {

		    @DataProvider(name = "partialKeywords")
		    public Object[][] partialKeywords() {

		        return new Object[][] {
		            {"iph"},
		            {"sams"},
		            {"lap"},
		            {"head"},
		            {"mob"},
		            {"one"}
		        };
		    }

		    @Test(dataProvider = "partialKeywords")
		    public void verifySearchWithPartialKeyword(String partialKeyword) {

		        WebDriver driver = new ChromeDriver();

		        driver.manage().window().maximize();
		        
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		        driver.get("https://www.amazon.in/");

		        SearchPage sr = new SearchPage(driver);

		        System.out.println(
		                "Searching with partial keyword: " + partialKeyword
		        );

		        // Enter partial keyword and search
		        sr.searchProduct(partialKeyword);

		        // Verify search results are displayed
		        Assert.assertTrue(
		                sr.isSearchResultsDisplayed(),
		                "Search results are not displayed for partial keyword: "
		                        + partialKeyword
		        );

		        System.out.println(
		                "Search successful for partial keyword: "
		                        + partialKeyword
		        );

		        driver.quit();
		    }
		}
	  
	  @Test
	  public void verifyRecentSearchFunctionality() {
		  
		  WebDriver driver = new ChromeDriver();
		  
		  driver.manage().window().maximize();
		  
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  
		  driver.get("https://www.amazon.in/");

	      SearchPage searchPage = new SearchPage(driver);

	      String keyword = "OnePlus Nord CE6 Lite";

	      // Perform search
	      searchPage.searchProduct(keyword);

	      // Go back to home page
	      driver.navigate().back();

	      // Click search field
	      searchPage.clickSearchField();

	      // Verify recent search
	      Assert.assertTrue(
	          searchPage.verifyRecentSearch(keyword),
	          "Recent search '" + keyword + "' is not displayed"
	      );
	  }
	}
	
