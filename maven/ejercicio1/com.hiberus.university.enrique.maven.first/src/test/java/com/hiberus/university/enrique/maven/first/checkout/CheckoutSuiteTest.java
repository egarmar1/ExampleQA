package com.hiberus.university.enrique.maven.first.checkout;

import com.hiberus.university.enrique.maven.first.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Ignore
public class CheckoutSuiteTest {
    public static WebDriver driver;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public CheckOutStepOnePage checkOutStepOnePage;
    public CheckOutStepSecondPage checkOutStepSecondPage;
    public CheckOutCompletePage checkOutCompletePage;


    @Before
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();;

        PagesFactory.start(driver);
        //Paso 1. Ir a la página de saucedemo
        driver.get(loginPage.PAGE_URL);

        PagesFactory pagesFactory = PagesFactory.getInstance();
        loginPage = pagesFactory.getLoginPage();
        inventoryPage = pagesFactory.getInventoryPage();
        cartPage = pagesFactory.getCartPage();
        checkOutStepOnePage = pagesFactory.getCheckOutStepOnePage();
        checkOutStepSecondPage = pagesFactory.getCheckOutStepSecondPage();
        checkOutCompletePage = pagesFactory.getCheckOutCompletePage();

        //Paso 2. Escribir el username
        loginPage.enterUsername("standard_user");
        //Paso 3. Escribir la password
        loginPage.enterPassword("secret_sauce");
        //Paso 4. Pulsar el boton de login
        loginPage.clickLogin();


    }

    @Test
    public void finalPriceTest(){

        //Paso 5. Agregar al carrito 3 productos elegidos al azar
        inventoryPage.clickAddRandomButtons(3);

        //Paso 6. Ir al carrito
        inventoryPage.goToCart();

        //Paso 7. Clickar Checkout
        cartPage.clickCheckout();

        //Paso 8. Rellenar datos del checkout y continuar
        checkOutStepOnePage.enterFirstName("Jhon");
        checkOutStepOnePage.enterLastName("Miller");
        checkOutStepOnePage.enterPostalCode("37192");

        checkOutStepOnePage.clickContinue();

        //Paso 10. Comprobar que la suma del importe es correcta


        List<WebElement> precios = checkOutStepSecondPage.getItemPrices();
        WebElement itemTotalElement = checkOutStepSecondPage.getItemTotal();


        float itemTotal = Float.parseFloat(itemTotalElement.getText().substring(itemTotalElement.getText().length() - 5));

        float suma = 0.00F;

        for (WebElement precio : precios) {
            suma += Float.parseFloat(precio.getText().substring(1));
        }

        Assert.assertEquals("La suma de los productos no coincide con el itemTotal", suma, itemTotal, 0.01f);

        //Paso 9. Finalizar checkout
        checkOutStepSecondPage.clickFinish();
    }

    @Test
    public void orderTest(){

        //Paso 5. Agregar al carrito 1 productos elegidos al azar
        inventoryPage.clickAddRandomButtons(1);

        //Paso 6. Ir al carrito
        inventoryPage.goToCart();

        //Paso 7. Clickar Checkout
        cartPage.clickCheckout();

        //Paso 8. Rellenar datos del checkout y continuar
        checkOutStepOnePage.enterFirstName("Jhon");
        checkOutStepOnePage.enterLastName("Miller");
        checkOutStepOnePage.enterPostalCode("37192");

        checkOutStepOnePage.clickContinue();

        //Paso 9. Finalizar checkout
        checkOutStepSecondPage.clickFinish();

        //Paso 10. Validar que el pedido ha finalizado correctamente mostrando el mensaje adecuado
        Assert.assertEquals("El mensaje de pedido completado no coincide","Your order has been dispatched, and will arrive just as fast as the pony can get there!",checkOutCompletePage.getCompleteText());
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
