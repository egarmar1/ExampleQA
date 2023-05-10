package com.hiberus.university.enrique.maven.first.carrito;

import com.hiberus.university.enrique.maven.first.pages.CartPage;
import com.hiberus.university.enrique.maven.first.pages.InventoryPage;
import com.hiberus.university.enrique.maven.first.pages.LoginPage;
import com.hiberus.university.enrique.maven.first.pages.PagesFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CarritoSuiteTest {

    public static WebDriver driver;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    WebDriverWait wait;
    @Before
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        PagesFactory.start(driver);



    }

    @Test
    public void testCarrito(){

        //Paso 1. Ir a la página de saucedemo
        driver.get(loginPage.PAGE_URL);

        PagesFactory pagesFactory = PagesFactory.getInstance();
        loginPage = pagesFactory.getLoginPage();
        inventoryPage = pagesFactory.getInventoryPage();
        cartPage = pagesFactory.getCartPage();

        //Paso 2. Escribir el username
        loginPage.enterUsername("standard_user");
        //Paso 3. Escribir la password
        loginPage.enterPassword("secret_sauce");
        //Paso 4. Pulsar el boton de login
        loginPage.clickLogin();


        //Paso 5 Agregar al carrito 2 productos al azar
        inventoryPage.clickAddRandomButtons(2);

        //Paso 6. Ir al carrito
        inventoryPage.goToCart();

        //Paso 7. Eliminar uno de los productos
        String buttonId = cartPage.deleteRandomFromCart();


        //Paso 8. Validar que el producto no aparece en el carrito

        Assert.assertFalse("The product still exists",cartPage.existsById(buttonId));

    }


    @After
    public void tearDown(){

        driver.close();
    }
}
