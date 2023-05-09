package com.hiberus.university.enrique.maven.first.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.jws.WebService;

public class CheckOutCompletePage extends AbstractPage{

    @FindBy(css = "div[class='complete-text']")
    private WebElement completeText;

    public CheckOutCompletePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }

    public String getCompleteText(){
        return completeText.toString();
    }
}
