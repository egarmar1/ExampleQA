package com.hiberus.university.enrique.maven.first.pages;

import com.hiberus.university.enrique.maven.first.model.InventoryItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPage  extends AbstractPage{

    public static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";
    @FindBy(css = "option[value='za']")
    private WebElement sortZA;
    @FindBy(css = "option[value='lohi']")
    private WebElement sortLohi;
    @FindBy(css = "option[value='hilo']")
    private WebElement sortHilo;
    @FindBy(css = "div[class='inventory_item_name']")
    private List<WebElement> productNames;
    @FindBy(css = "div[class='inventory_item_price']")
    private List<WebElement> productPrices;
    @FindBy(css = "button[data-test^='add-to-cart']")
    private List<WebElement> addToCartButtons;
    @FindBy(css = "button[data-test^='remove']")
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

    public List<WebElement> getProductPrices(){
        return productPrices;
    }

    public List<WebElement> getAddToCartButtons(){
        return addToCartButtons;
    }

    public List<WebElement> getRemoveButtons(){
        return removeButtons;
    }

    public void clickRemoveRandomButtons(int num){//Num of random items you want to remove
        if(num <=0){
            return;
        }
        List<WebElement> randomButtons = new ArrayList<>();
        for (WebElement removeButton : removeButtons) {
            randomButtons.add(removeButton);
        }
        Collections.shuffle(randomButtons);

        for(int i=0; i<num; i++){
            randomButtons.get(i).click();
        }
    }
    public void clickAddRandomButtons(int num){//Num of random items you want to remove
        if(num <=0){
            return;
        }
        List<WebElement> randomButtons = new ArrayList<>();
        for (WebElement addToCartButton : addToCartButtons) {
            randomButtons.add(addToCartButton);
        }
        Collections.shuffle(randomButtons);

        for(int i=0; i<num; i++){
            randomButtons.get(i).click();
        }
    }

    public String getNumCartIcon(){
        return shoppingCartLink.getText();
    }

    public void clickSortZA(){
        sortZA.click();
    }

    public void clickSortLohi(){
        sortLohi.click();
    }

    public void clickSortHilo(){
        sortHilo.click();
    }

    public List<WebElement> getProductNames(){
        return productNames;
    }

    public boolean existsProductByName(String name){
        return getDriver().findElement(By.xpath("//div[contains(text(),'" + name + "')]")).isDisplayed();
    }

    public void clickProductByName(String name){
        name = name.toLowerCase().replace(" ","-");
        getDriver().findElement(By.cssSelector("button[data-test='add-to-cart-" +name + "']")).click();
    }
    public void removeProductByName(String name){
        name = name.toLowerCase().replace(" ","-");
        getDriver().findElement(By.cssSelector("button[data-test='remove-" +name + "']")).click();
    }

    public void openMenu(){
        openMenuButton.click();
    }

    public void clickLogout(){
        logoutButton.click();
    }

    public WebElement getShoppingCartLink(){
        return shoppingCartLink;
    }
}
