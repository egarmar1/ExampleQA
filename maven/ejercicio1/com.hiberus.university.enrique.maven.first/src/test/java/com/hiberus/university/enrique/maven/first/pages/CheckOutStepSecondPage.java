package com.hiberus.university.enrique.maven.first.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckOutStepSecondPage extends AbstractPage{


    @FindBy(css = "button[data-test='finish']")
    private WebElement finishButton;
    @FindBy(css = "div[class='summary_subtotal_label']")
    private WebElement itemTotal;

    @FindAll({
            @FindBy(css = "div[class='inventory_item_price']")
    })
    private List<WebElement> itemPrices;
    public CheckOutStepSecondPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }

    public void clickFinish(){
        finishButton.click();
    }

    public WebElement getItemTotal(){
        return itemTotal;
    }

    public List<WebElement> getItemPrices(){
        return itemPrices;
    }


}
