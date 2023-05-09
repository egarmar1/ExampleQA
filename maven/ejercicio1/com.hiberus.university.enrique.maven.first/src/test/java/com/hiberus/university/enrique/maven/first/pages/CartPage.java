package com.hiberus.university.enrique.maven.first.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.lang.ref.WeakReference;
import java.util.List;

public class CartPage extends AbstractPage{

    public static final String PAGE_URL = "https://www.saucedemo.com/";

    @FindBy(css ="[name='checkout']")
    private WebElement checkoutButton;
    @FindAll({
            @FindBy(css = "[name^='remove']")
    })
    private List<WebElement> removeButtons;
    @FindBy(css = "button[data-test='continue-shopping']")
    private WebElement continueShoppingButton;
    @FindAll({
            @FindBy(css = "div[class='cart_item']")
    })
    private List<WebElement> itemList;


    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }
    @Override
    public WebElement getPageLoadedTestElement() {
        return checkoutButton;
    }

    public void clickCheckout(){
        checkoutButton.click();
    }

    public List<WebElement> getItemList(){
        return itemList;
    }

    public List<WebElement> getRemoveButtons(){
        return removeButtons;
    }



}
