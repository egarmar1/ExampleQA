package com.hiberus.university.enrique.maven.first.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class CartPage extends AbstractPage{

    public static final String PAGE_URL = "https://www.saucedemo.com/";

    @FindBy(css ="[name='checkout']")
    private WebElement checkoutButton;
    @FindBy(css = "[name^='remove']")
    private List<WebElement> removeButtons;
    @FindBy(css = "button[data-test='continue-shopping']")
    private WebElement continueShoppingButton;
    @FindBy(css = "div[class='cart_item']")
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

    public String deleteRandomFromCart(){
        Random rand = new Random();
        int index = rand.nextInt(removeButtons.size());
        String productName = removeButtons.get(index).getAttribute("id");

        removeButtons.get(index).click();

        return productName;
    }

    public boolean existsById(String id){
        try{
            getDriver().findElement(By.cssSelector("#id")).isDisplayed();
            return true;
        }catch (NoSuchElementException ne){
            return false;
        }

    }

}
