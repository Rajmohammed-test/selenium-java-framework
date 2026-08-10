package com.company.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage 
{
	@FindBy (xpath ="//select[@id='searchDropdownBox']")
	private WebElement searchFeildDropDown;
	
	@FindBy (xpath="//input[@id='twotabsearchtextbox']")
	private WebElement searchTextFeild;
	
	@FindBy (xpath="//input[@id='nav-search-submit-button']")
	private WebElement searchButton;
	
	@FindBy (xpath ="//div[@aria-label='oneplus nord ce 6 lite case']")
	private WebElement autoSuggestion;
	
	public SearchPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	public WebElement getSearchFeildDropDown() {
		return searchFeildDropDown;
	}

	public void setSearchFeildDropDown(WebElement searchFeildDropDown) {
		this.searchFeildDropDown = searchFeildDropDown;
	}

	public WebElement getSearchTextFeild() {
		return searchTextFeild;
	}

	public void setSearchTextFeild(WebElement searchTextFeild) {
		this.searchTextFeild = searchTextFeild;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(WebElement searchButton) {
		this.searchButton = searchButton;
	}

	public WebElement getAutoSuggestion() {
		return autoSuggestion;
	}

	public void setAutoSuggestion(WebElement autoSuggestion) {
		this.autoSuggestion = autoSuggestion;
	}
	
	
	
	
	

}
