package com.company.categorytests;

import com.company.framework.base.BaseTest;
import com.company.framework.pages.CategoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryTest extends BaseTest {

	@Test(description = "CA001_Verify a user can navigate to Best Sellers page from Category navigation", groups = "Smoke")
	public void verifyNavigationToBestSellersPage() {

		CategoryPage categoryPage = new CategoryPage();
		categoryPage.clickOnBestSellersLink();
		Assert.assertTrue(categoryPage.isBestsellersTextDisplayed(), "Amazon Bestseller text should be displayed");

	}

	@Test(description = "CA002_Verify a user can navigate to Customer Service page from Category navigation", groups = "Smoke")
	public void verifyNavigationToCustomerServicePage() {

		CategoryPage categoryPage = new CategoryPage();
		categoryPage.clickOnCustomerServiceLink();
		Assert.assertTrue(categoryPage.isHelpTextDisplayed(), "What can we help you with? text should be displayed");

	}

	@Test(description = "CA003_Verify the user can add a product to the cart from Today's Deals Category", groups = "Smoke")
	public void verifyAddToCartFromCategory() {

		CategoryPage categoryPage = new CategoryPage();
		categoryPage.naviagteToTodaysDeals();
		categoryPage.clickOnFirstProductPLP();
		Assert.assertTrue(categoryPage.isAddToCartButtonDisplayed(), "Add to cart button should be displayed");
		categoryPage.addProductToCart();
		Assert.assertTrue(categoryPage.isAddedToCartTextDisplayed(), "Added to cart text should be displayed");

	}

	@Test(description = "CA004_Verify the user can navigate to sign in page from prime video category navigation", groups = "Smoke")
	public void verifyPrimeVideoSignInPage() {

		CategoryPage categoryPage = new CategoryPage();
		categoryPage.clickOnPrimeVideoLink();
		Assert.assertTrue(categoryPage.isSignInToPrimeButtonDisplayed(), "SignIn to prime button should be displayed");
		categoryPage.clickOnSignInToPrime();
		Assert.assertTrue(categoryPage.isSignInDisplayed(), "Sign in text should be displayed");

	}

}
