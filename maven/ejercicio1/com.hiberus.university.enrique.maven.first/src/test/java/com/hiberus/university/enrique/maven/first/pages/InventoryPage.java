package com.hiberus.university.enrique.maven.first.pages;

import com.hiberus.university.enrique.maven.first.model.InventoryItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage  extends AbstractPage{


    @FindBy(css = "select[data-test='product_sort_container']")
    private WebElement productSortContainerSelect;


    public InventoryPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }
    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }
}
