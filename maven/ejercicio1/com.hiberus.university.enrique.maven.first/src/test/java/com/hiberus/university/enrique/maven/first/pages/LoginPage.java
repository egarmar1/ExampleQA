package com.hiberus.university.enrique.maven.first.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class LoginPage extends AbstractPage{

    public static final String PAGE_URL = "https://www.saucedemo.com/";
    @FindBy(id = "user-name")
    private WebElement usernameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "login-button")
    private WebElement loginButton;
    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);

    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return loginButton;
    }

    //Atributos


    public void clickLogin(){
        log.info("Loggin in ...");
        try{
            loginButton.click();
        } catch (TimeoutException timeoutException){
            log.info("Timeout clicking login: " + timeoutException.getClass().getSimpleName());
        }
    }

    public void enterUsername(String username){
        usernameInput.click();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordInput.click();
        passwordInput.sendKeys(password);
    }

    public boolean hasUsernamePasswordError(String password){
        return errorMessage.isDisplayed();
    }
}
