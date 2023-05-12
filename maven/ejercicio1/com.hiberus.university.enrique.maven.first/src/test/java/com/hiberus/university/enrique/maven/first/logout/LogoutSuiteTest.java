package com.hiberus.university.enrique.maven.first.logout;

import com.hiberus.university.enrique.maven.first.pages.InventoryPage;
import com.hiberus.university.enrique.maven.first.pages.LoginPage;
import com.hiberus.university.enrique.maven.first.pages.PagesFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@Ignore
public class LogoutSuiteTest {

    public static WebDriver driver;

    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    @Before
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        PagesFactory.start(driver);
    }

    @Test
    public void testLogout(){
        //Paso 1. Ir a la página de saucedemo
        driver.get(loginPage.PAGE_URL);

        PagesFactory pagesFactory = PagesFactory.getInstance();
        loginPage = pagesFactory.getLoginPage();
        inventoryPage = pagesFactory.getInventoryPage();


        //Paso 2. Escribir el username
        loginPage.enterUsername("standard_user");
        //Paso 3. Escribir la password
        loginPage.enterPassword("secret_sauce");
        //Paso 4. Pulsar el boton de login
        loginPage.clickLogin();

        //Paso 5. Realizar el Logout
        inventoryPage.openMenu();
        inventoryPage.clickLogout();

        //Paso 6. Validar que el logout se ha realizado correctamente
        Assert.assertEquals("No se ha redirigido a la pantalla de inicio tras cerrar sesion",loginPage.PAGE_URL,driver.getCurrentUrl());
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
