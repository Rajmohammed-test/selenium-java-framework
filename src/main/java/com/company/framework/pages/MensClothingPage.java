package com.company.framework.pages;

import com.company.framework.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MensClothingPage extends BasePage {

    @FindBy(xpath = "//h2[contains(text(), 'Men')]")
    private WebElement mensbutton;

    public MensClothingPage() { PageFactory.initElements(driver(), this);}
    public boolean isMensClothigDisplayed() {
        return isDisplayed(mensbutton);
    }

}
