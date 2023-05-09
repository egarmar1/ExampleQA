package com.hiberus.university.enrique.maven.first.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutStepOnePage extends AbstractPage{


    @FindBy(css = "input[data-test='firstName']")
    private WebElement firstNameInput;

    @FindBy(css = "input[data-test='lastName']")
    private WebElement lastNameInput;

    @FindBy(css = "input[data-test='postalCode']")
    private WebElement postalCodeInput;

    @FindBy(css = "input[data-test='continue']")
    private WebElement continueButton;



    public CheckOutStepOnePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }

    public void enterFirstName(String firstName){
        firstNameInput.click();
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameInput.click();
        lastNameInput.sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode){
        postalCodeInput.click();
        postalCodeInput.sendKeys(postalCode);
    }

    public void clickContinue(){
        continueButton.click();
    }


}
