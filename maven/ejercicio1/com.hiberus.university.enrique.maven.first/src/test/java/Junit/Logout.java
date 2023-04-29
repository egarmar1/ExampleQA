package Junit;

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

import java.util.concurrent.TimeUnit;

public class Logout {

    WebDriver driver;
    String url = "https://www.saucedemo.com/";
    WebDriverWait wait;

    @Before
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver,5);

        //Paso 1. Ir a la página de saucedemo
        driver.get(url);
    }

    @Test
    public void testLogout(){
        //Paso 2. Escribir el username
        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");

        //Paso 3. Escribir la password
        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

        //Paso 4. Pulsar el boton del Login
        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();

        //Paso 5. Realizar el Logout
        try {
            driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
            driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
        }catch (NoSuchElementException e){
            Assert.fail("No se encuentra algun clickable");
        }

        //Paso 6. Validar que el logout se ha realizado correctamente
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals("No se ha redirigido a la pantalla de inicio tras cerrar sesion",url,actualUrl);
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
