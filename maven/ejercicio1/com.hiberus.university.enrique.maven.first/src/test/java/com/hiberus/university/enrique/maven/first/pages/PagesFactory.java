package com.hiberus.university.enrique.maven.first.pages;

import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;

import javax.jws.WebService;

public class PagesFactory {

    private static PagesFactory pagesFactories;

    private final WebDriver driver;

    private final LoginPage loginPage;
    private final CartPage cartPage;
    private final InventoryPage inventoryPage;

    private PagesFactory(WebDriver driver){
        this.driver = driver;
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        inventoryPage = new InventoryPage(driver);
        //inicializar nuestras Pages
    }

    public static void start(WebDriver driver){
        pagesFactories = new PagesFactory(driver);
    }

    public WebDriver getDriver(){
        return driver;
    }
}
