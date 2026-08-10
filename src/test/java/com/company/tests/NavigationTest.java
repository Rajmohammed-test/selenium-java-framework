package com.company.tests;

import com.company.framework.base.BaseTest;
import com.company.framework.pages.HomePage;
import com.company.framework.pages.MensClothingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test(description = "Verify a user can navigate to mens clothing page from home page")
    public void validLogin() {

        HomePage homepage = new HomePage();
        homepage.clickAll();
        homepage.clickMen();
        MensClothingPage mensClothingPage=homepage.clickShirt();

        Assert.assertTrue(mensClothingPage.isMensClothigDisplayed());
        //Assert.assertEquals(mensClothingPage.isMensClothigDisplayed(), "Men's Clothing - Buy Men's Wear Online at Best Prices on Amazon.in");


    }
}
