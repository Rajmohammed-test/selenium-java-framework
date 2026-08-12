package com.company.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.company.framework.base.BasePage;

public class CategoryPage extends BasePage {

	public CategoryPage() {
		PageFactory.initElements(driver(), this);
	}

	@FindBy(xpath = "//a[text()='Bestsellers' and @class='nav-a  ']")
	private WebElement bestSellersLink;

	@FindBy(xpath = "//a[text()='Mobiles' and @class='nav-a  ']")
	private WebElement mobilesLink;

	@FindBy(xpath = "//h1[text()='Amazon Bestsellers']")
	private WebElement amazonBestsellersText;

	@FindBy(xpath = "//a[text()='Customer Service' and @class='nav-a  ']")
	private WebElement customerServiceLink;

	@FindBy(xpath = "//a[contains(text(),'Deals') and @class='nav-a  ']")
	private WebElement toadysDealsLink;

	@FindBy(xpath = "//h1[contains(text(),'Hello. What can we help you with?')]")
	private WebElement helpText;

	@FindBy(xpath = "(//a[@data-testid='product-card-link'])[1]")
	private WebElement firstProductPLP;

	@FindBy(xpath = "//input[@id='add-to-cart-button']")
	private WebElement addToCartButton;

	@FindBy(xpath = "//h1[contains(text(),'Added to cart')]")
	private WebElement addedToCartText;

	@FindBy(xpath = "//a[text()='Prime Video']")
	private WebElement primeVideoLink;

	@FindBy(xpath = "//a[text()='Sign in to join Prime']")
	private WebElement signInToPrimeButton;

	@FindBy(xpath = "//h1[contains(text(),'Sign in')]")
	private WebElement signInText;

	@FindBy(xpath = "//span[text()='Smartwatches']/..")
	private WebElement smartWatchesLink;

	@FindBy(xpath = "//b[text()='Mobile Smart Watch']")
	private WebElement smartWatchesText;

	public void clickOnBestSellersLink() {

		click(bestSellersLink);

	}

	public boolean isBestsellersTextDisplayed() {

		return isDisplayed(amazonBestsellersText);

	}

	public void naviagteToTodaysDeals() {

		click(toadysDealsLink);

	}

	public void clickOnCustomerServiceLink() {

		click(customerServiceLink);

	}

	public boolean isHelpTextDisplayed() {

		return isDisplayed(helpText);

	}

	public void clickOnFirstProductPLP() {

		click(firstProductPLP);

	}

	public boolean isAddToCartButtonDisplayed() {

		return isDisplayed(addToCartButton);

	}

	public void addProductToCart() {

		click(addToCartButton);
	}

	public boolean isAddedToCartTextDisplayed() {

		return isDisplayed(addedToCartText);

	}

	public void clickOnPrimeVideoLink() {

		click(primeVideoLink);
	}

	public boolean isSignInToPrimeButtonDisplayed() {

		return isDisplayed(signInToPrimeButton);

	}

	public void clickOnSignInToPrime() {

		click(signInToPrimeButton);
	}

	public boolean isSignInDisplayed() {

		return isDisplayed(signInText);

	}

	public void clickOnMobilesLink() {

		click(mobilesLink);

	}

	public boolean isSmartWatchesLinkDisplayed() {

		return isDisplayed(smartWatchesLink);

	}

	public boolean clickAndVerfiySmartWatchesNavigation() {

		click(smartWatchesLink);
		return isDisplayed(smartWatchesText);

	}
}
