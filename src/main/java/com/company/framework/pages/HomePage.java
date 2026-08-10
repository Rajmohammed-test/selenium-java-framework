package com.company.framework.pages;

import com.company.framework.base.BasePage;
import com.company.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(xpath = "//span[@class='hm-icon-label' and text()='All']")
    private WebElement all;

    @FindBy(xpath ="//a[@class='hmenu-item'] /div[text()=\"Men's Fashion\"]" )
    private WebElement mensfashion;

    @FindBy(xpath ="//a[@class='hmenu-item' and text()='Shirts'][1]" )
    private WebElement shirts;





    public HomePage() {
        PageFactory.initElements(driver(), this);
    }

    public void click1(){
        WebElement ele= driver().findElement(By.xpath("//span[@class='hm-icon-label' and text()='All']"));
        WaitUtils.waitForElementClickable(ele);
        ele.click();
    }

    public void clickAll(){
        click(all);
    }

    public void clickMen(){
        scrollToElement(mensfashion);
        WaitUtils.waitForElementClickable(mensfashion);
        mensfashion.click();
    }

    public MensClothingPage clickShirt(){
        scrollToElement(shirts);
        WaitUtils.waitForElementClickable(shirts);
        clickWithJsFallback(shirts);
        return new MensClothingPage();
    }

    //
}
