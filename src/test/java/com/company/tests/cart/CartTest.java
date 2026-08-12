package com.company.tests.cart;

import com.company.framework.base.BaseTest;
import com.company.framework.pages.CartPage;
import com.company.framework.testdata.TestDataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CartTest extends BaseTest {


    @Test(description = "Verify User can add product to cart and navigate to cart page")
    public void addProductToCart() {
        log.info("Starting test: addProductToCart");
        String item = TestDataUtils.getData("cartdata", "item");
        CartPage cartPage = new CartPage();
        cartPage.clickContinueShoppingIfPresent();
        cartPage.enterProductInSearchBox(item);
        cartPage.clickSearchSubmitButton();
        cartPage.clickAddToCart();
        cartPage.clickCartIcon();
        Assert.assertTrue(cartPage.isShoppingCartDisplayed(), "Shopping cart is not displayed");
    }

    @Test(description = "Verify User can add product to cart and Verify Correct Product is added to cart")
    public void VerifyAddedProductInCart() {
        log.info("Starting test: VerifyAddedProductInCart");
        CartPage cartPage = new CartPage();
        String product = TestDataUtils.getData("cartdata", "product");
        cartPage.clickContinueShoppingIfPresent();
        cartPage.enterProductInSearchBox(product);
        cartPage.clickSearchSubmitButton();
        cartPage.clickAddToCart();
        cartPage.clickCartIcon();
        Assert.assertTrue(cartPage.isShoppingCartDisplayed());
        String actualText=cartPage.getProductLinkText();
        Assert.assertTrue(
                actualText.toLowerCase().contains(product.toLowerCase()),
                "Expected text was not found. Actual text: " + actualText
        );

    }

    @Test(description = "Verify User can add product to cart and remove it from cart")
    public void VerifyRemovalOfProductFromCart() {
        log.info("Starting test: VerifyRemovalOfProductFromCart");
        CartPage cartPage = new CartPage();
        String product = TestDataUtils.getData("cartdata", "product");
        cartPage.clickContinueShoppingIfPresent();
        cartPage.enterProductInSearchBox(product);
        cartPage.clickSearchSubmitButton();
        cartPage.clickAddToCart();
        cartPage.clickCartIcon();
        Assert.assertTrue(cartPage.isShoppingCartDisplayed());
        cartPage.removeProductFromCart();
        Assert.assertTrue(cartPage.isItemRemovedFromCart(), "Item was not removed from cart");

    }

    @Test(description = "Verify User can add product to cart and increment the quantity of the product in the cart")
    public void VerifyIncrementOfProductQuantity() {
        log.info("Starting test: VerifyIncrementOfProductQuantity");
        CartPage cartPage = new CartPage();
        String item = TestDataUtils.getData("cartdata", "product");
        cartPage.clickContinueShoppingIfPresent();
        cartPage.enterProductInSearchBox(item);
        cartPage.clickSearchSubmitButton();
        cartPage.clickAddToCartOfProduct();
        cartPage.clickCartIcon();
        Assert.assertTrue(cartPage.isShoppingCartDisplayed());
        cartPage.incrementProductQuantity();
        String subtotalText = cartPage.getSubtotalLabelText();
        Assert.assertTrue(subtotalText.contains("2"), "Subtotal does not reflect the incremented quantity. Actual subtotal text: " + subtotalText);
    }

    @Test(description = "Verify User can add Multiple item to the cart")
    public void VerifyMultipleCartItemsCanAdded() {
        log.info("Starting test: VerifyMultipleCartItemsCanAdded");
        CartPage cartPage = new CartPage();
        cartPage.clickContinueShoppingIfPresent();
        String item = TestDataUtils.getData("cartdata", "item");
        cartPage.enterProductInSearchBox(item);
        cartPage.clickSearchSubmitButton();
        cartPage.clickAddToCartOfProduct();
        cartPage.clickAddToCart();
        cartPage.clickCartIcon();
        Assert.assertTrue(cartPage.isShoppingCartDisplayed());
        String subtotalText = cartPage.getSubtotalLabelText();
        Assert.assertTrue(subtotalText.contains("2"), "Subtotal does not reflect the Multiple product added: " + subtotalText);
    }

    @Test(description = "Verify deleting an item from cart does not remove other items")
    public void VerifyDeletingAnItemFromCartDoesNotRemoveOtherItems() {
        log.info("Starting test: VerifyDeletingAnItemFromCartDoesNotRemoveOtherItems");
        CartPage cartPage = new CartPage();
        cartPage.clickContinueShoppingIfPresent();
        String product = TestDataUtils.getData("cartdata", "product");
        cartPage.enterProductInSearchBox(product);
        cartPage.clickSearchSubmitButton();
        cartPage.clickAddToCart();
        cartPage.clickAddToCartOfProduct();
        cartPage.clickCartIcon();
        Assert.assertTrue(cartPage.isShoppingCartDisplayed());
        cartPage.removeProductFromCart();
        String subtotalText = cartPage.getSubtotalLabelText();
        Assert.assertTrue(subtotalText.contains("1"), "Subtotal does not reflected the deleted item count: " + subtotalText);
    }

    @Test(description = "Verify Refresh of cart page does not affect Cart item")
    public void VerifyRefreshCartPageDoesNotAffectCartItem() {
        log.info("Starting test: VerifyRefreshCartPageDoesNotAffectCartItem");
        CartPage cartPage = new CartPage();
        cartPage.clickContinueShoppingIfPresent();
        String product = TestDataUtils.getData("cartdata", "product");
        cartPage.enterProductInSearchBox(product);
        cartPage.clickSearchSubmitButton();
        cartPage.clickAddToCartOfProduct();
        cartPage.clickCartIcon();
        Assert.assertTrue(cartPage.isShoppingCartDisplayed());
        cartPage.refreshCartPage();
        String actualText=cartPage.getProductLinkText();
        Assert.assertTrue(
                actualText.toLowerCase().contains(product.toLowerCase()),
                "Expected text was not found. Actual text: " + actualText
        );

    }
}
