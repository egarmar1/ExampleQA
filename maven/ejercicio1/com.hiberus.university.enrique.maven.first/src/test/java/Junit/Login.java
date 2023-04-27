package Junit;

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

public class Login {
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

        driver.get(url);
    }

    @Test
    public void testLoginCorrecto(){

        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("No nos encontramos en la página esperada","https://www.saucedemo.com/inventory.html",actualUrl);

    }


    @Test
    public void testLoginIncorrecto() {

        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standar_user");

        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();

        WebElement errorMessage = null;
        try {
            errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));

        } catch (NoSuchElementException e) {
            Assert.assertNotNull("No se ha encontrado el mensaje de error",errorMessage);
        }
    }


    @After
    public void tearDown(){
        driver.close();
    }
}
