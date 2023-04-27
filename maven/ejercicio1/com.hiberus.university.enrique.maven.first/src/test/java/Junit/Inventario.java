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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Inventario {

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

        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();

    }

    @Test
    public void testNumProductos6(){

        List<WebElement> productosList = driver.findElements(By.xpath("//div[@class= 'inventory_list']/child::div"));

        Assert.assertEquals("No hay 6 productos",6,productosList.size());

    }

    @Test
    public void testExisteProducto(){

        WebElement producto = null;
        try {
            producto = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));

        } catch (NoSuchElementException e) {
            Assert.assertNotNull("No se ha encontrado el producto",producto);
        }

    }

    @Test
    public void testAnyadirProducto(){

        WebElement producto = null;
        String numCarrito = "";
        try {
            producto = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
            producto.click();

            numCarrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();

            Assert.assertEquals("No hay un solo producto en el carrito","1",numCarrito);

        } catch (NoSuchElementException e) {
            Assert.assertNotNull("No se ha encontrado el producto",producto);
        }

    }


    @After
    public void tearDown(){
        driver.close();
    }
}
