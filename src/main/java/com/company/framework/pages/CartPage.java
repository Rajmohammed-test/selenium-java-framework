package com.company.framework.pages;
import com.company.framework.base.BasePage;
import com.company.framework.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {

    @FindBy(xpath = "(//input[@name='submit.addToCart'])[1]")
    private WebElement addToCart;

    @FindBy(xpath = "(//input[@name='submit.addToCart'])[3]")
    private WebElement addToCartChild;

    @FindBy(xpath = "//span[@class='nav-cart-icon nav-sprite']")
    private WebElement cartIcon;

    @FindBy(xpath = "(//h2[normalize-space()='Shopping Cart'])[1]")
    private WebElement shoppingCart;

    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    private WebElement searchSubmitButton;

    @FindBy(xpath = "//span[@class='a-truncate-cut']")
    private WebElement productLink;

    @FindBy(xpath = "//span[@class='a-icon a-icon-small-trash']/..")
    private WebElement deleteButton;

    @FindBy(xpath = "//span[@class='a-icon a-icon-small-add']")
    private WebElement incrementButton;

    @FindBy(xpath = "//span[@id='sc-subtotal-label-activecart']")
    private WebElement subtotalLabel;


    public CartPage() {
        PageFactory.initElements(driver(), this);
    }

    public void enterProductInSearchBox(String product) {
        sendKeys(searchButton, product);
    }

    public void clickSearchSubmitButton() {
        click(searchSubmitButton);
    }

    public void clickAddToCart() {
        click(addToCart);
    }

    public void clickAddToCartOfProduct() {
        click(addToCartChild);
    }

    public void clickCartIcon() {
        scrollToElement(cartIcon);
        javaScriptClick(cartIcon);
    }

    public String getProductLinkText() {
        return getText(productLink);
    }

    public void removeProductFromCart() {
        click(deleteButton);
    }

    public boolean isItemRemovedFromCart() {
        return !isDisplayed(productLink);
    }

    public void incrementProductQuantity() {
        click(incrementButton);
    }

    public String getSubtotalLabelText()  {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return getText(subtotalLabel);
    }



    public boolean isShoppingCartDisplayed() {
        return isDisplayed(shoppingCart);
    }
}


