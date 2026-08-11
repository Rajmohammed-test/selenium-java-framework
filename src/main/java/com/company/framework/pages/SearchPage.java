package com.company.framework.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	public WebDriver driver;

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	private WebElement searchTextField;

	@FindBy(xpath = "//input[@id='nav-search-submit-button']")
	private WebElement searchButton;
	
	 @FindBy(xpath = "//div[contains(@class,'s-suggestion')]")
	 private List<WebElement> searchSuggestions;
	 
	 @FindBy(xpath = "//div[@data-component-type='s-search-result']")
	    private WebElement searchResults;
	 
	 @FindBy(xpath = "//div[@id='nav-flyout-searchAjax']//div[contains(@class,'s-suggestion')]")
	 private List<WebElement> recentSearches;
	 
	 public int getSuggestionCount() {
	        return searchSuggestions.size();
	    }

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterSearchText(String searchText) {
		searchTextField.clear();
		searchTextField.sendKeys(searchText);
	}

	public void clickSearchButton() {
		searchButton.click();
	}

	public void searchProduct(String searchText) {
		enterSearchText(searchText);
		clickSearchButton();
	}

	public WebElement getSearchTextField() {
		return searchTextField;
	}

	public void setSearchTextField(WebElement searchTextField) {
		this.searchTextField = searchTextField;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(WebElement searchButton) {
		this.searchButton = searchButton;
	}

	public boolean isSearchPageDisplayed() {
		return driver.getTitle().toLowerCase().contains("amazon");
	}
	
	 public boolean areSuggestionsDisplayed() {
	        return !searchSuggestions.isEmpty();
	    }
	 public String getSuggestionText(int index) {
	        return searchSuggestions.get(index).getText();
	    }
	 public boolean isSearchResultsDisplayed() {
	        return searchResults.isDisplayed();
	    }
	 
	 public void clickSearchField() {
		    searchTextField.click();
		}
	 
	 public boolean verifyRecentSearch(String keyword) {

		    for (WebElement recentSearch : recentSearches) {

		        if (recentSearch.getText().trim().contains(keyword)) {
		            return true;
		        }
		    }

		    return false;
		}

	
}

