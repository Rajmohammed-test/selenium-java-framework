package com.company.framework.pages;

import com.company.framework.base.BasePage;
import com.company.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
	
	
	public WebDriver driver;
	
	//Object Initialization
	
	public HomePage(WebDriver driver) {
		
	PageFactory.initElements(driver, this);
	this.driver = driver;

	}
	

    @FindBy(xpath = "//span[@class='hm-icon-label' and text()='All']")
    private WebElement all;

    @FindBy(xpath ="//a[@class='hmenu-item'] /div[text()=\"Men's Fashion\"]" )
    private WebElement mensfashion;

    @FindBy(xpath ="//a[@class='hmenu-item' and text()='Shirts'][1]" )
    private WebElement shirts;
    
    @FindBy(id="twotabsearchtextbox")
    private WebElement searchField;
    
    
    






	

	public WebElement getMensfashion() {
		return mensfashion;
	}

	public void setMensfashion(WebElement mensfashion) {
		this.mensfashion = mensfashion;
	}

	public WebElement getShirts() {
		return shirts;
	}

	public void setShirts(WebElement shirts) {
		this.shirts = shirts;
	}

	public WebElement getSearchField() {
		return searchField;
	}

	public void setSearchField(WebElement searchField) {
		this.searchField = searchField;
	}

	public HomePage() {
        PageFactory.initElements(driver(), this);
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
