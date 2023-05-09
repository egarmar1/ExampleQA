package com.hiberus.university.enrique.maven.first.login;

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



public class LoginSuiteTest {
    public static WebDriver driver;
    public LoginPage loginPage;

    @Before
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        PagesFactory.start(driver);

        driver.get(LoginPage.PAGE_URL);
        PagesFactory pagesFactory = PagesFactory.getInstance();
        loginPage = pagesFactory.getLoginPage();
    }

    @Test
    public void testLoginCorrecto(){

        //Paso 2. Escribir el username
        loginPage.enterUsername("standard_user");
        //Paso 3. Escribir la password
        loginPage.enterPassword("secret_sauce");
        //Paso 4. Pulsar el boton de login
        loginPage.clickLogin();

        //Paso 5. Validdar que la url es correcta

        Assert.assertEquals("No nos encontramos en la página esperada", InventoryPage.PAGE_URL, driver.getCurrentUrl());


    }


    @Test
    public void testLoginIncorrecto() {


        PagesFactory pagesFactory = PagesFactory.getInstance();
        LoginPage loginPage = pagesFactory.getLoginPage();

        //Paso 2. Escribir el username incorrectamente
        loginPage.enterUsername("standard_user");

        //Paso 3. Escribir la password
        loginPage.enterPassword("kalsjdfk");

        //Paso 4. Pulsar el boton de login
        loginPage.clickLogin();

        //Paso 5. Validar que aparece el mensaje de error
        Assert.assertTrue("El elemento no se encuentra",loginPage.hasErrorMessage());

    }


    @After
    public void tearDown(){
        driver.close();
    }
}
