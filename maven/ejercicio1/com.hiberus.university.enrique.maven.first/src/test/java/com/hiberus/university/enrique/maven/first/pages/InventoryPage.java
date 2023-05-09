package com.hiberus.university.enrique.maven.first.pages;

import com.hiberus.university.enrique.maven.first.model.InventoryItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage  extends AbstractPage{

    @FindAll({
            @FindBy(css = "select[class='product_sort_container'] > option")
    })
    private List<WebElement> productSortOptions;

    @FindAll({
            @FindBy(css = "div[class='inventory_item_name']")
    })
    private List<WebElement> productNames;


    @FindAll({
            @FindBy(css = "div[class='inventory_item_price']")
    })
    private List<WebElement> productPrices;

    @FindAll({
            @FindBy(css = "button[data-test^='add-to-cart']")
    })
    private List<WebElement> addToCartButtons;

    @FindAll({
            @FindBy(css = "button[data-test^='remove']")
    })
    private List<WebElement> removeButtons;

    @FindBy(id= "react-burger-menu-btn")
    private WebElement openMenuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(css = "a[class='shopping_cart_link']")
    private WebElement shoppingCartLink;



    public InventoryPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }
    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }

    public List<WebElement> getProductSortOptions(){
        return productSortOptions;
    }

    public List<WebElement> getProductPrices(){
        return productPrices;
    }

    public List<WebElement> getAddToCartButtons(){
        return addToCartButtons;
    }

    public List<WebElement> getRemoveButtons(){
        return removeButtons;
    }

    public List<WebElement> getProductNames(){
        return productNames;
    }

    public WebElement getOpenMenuButton(){
        return openMenuButton;
    }

    public WebElement getLogoutButton(){
        return logoutButton;
    }

    public WebElement getShoppingCartLink(){
        return shoppingCartLink;
    }
}
