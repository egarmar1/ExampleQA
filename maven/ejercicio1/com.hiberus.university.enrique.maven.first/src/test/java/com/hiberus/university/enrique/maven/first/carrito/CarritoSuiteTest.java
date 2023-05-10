package com.hiberus.university.enrique.maven.first.carrito;

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

    WebDriver driver;
    String url = "https://www.saucedemo.com/";
    WebDriverWait wait;
    @Before
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();


        driver.get(url);
    }

    @Test
    public void testCarrito(){

        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();



        WebElement botonEliminar = null;
        try {
            //Paso 5 Agregar al carrito 2 productos al azar
            List<WebElement> productos = driver.findElements(By.xpath("//div[@class='inventory_list']/child::div/descendant::button"));


            List<Integer> indices = new ArrayList<>();
            Random random = new Random();

            while (indices.size() < 2) {
                int indice = random.nextInt(productos.size());
                if (!indices.contains(indice)) {
                    indices.add(indice);
                }
            }

            WebElement producto1 = productos.get(indices.get(0));
            WebElement producto2 = productos.get(indices.get(1));


            producto1.click();
            producto2.click();
            //Paso 6. Ir al carrito
            driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

            int randomNum = random.nextInt(2) + 1;

            //Paso 7. Eliminar uno de los productos
            botonEliminar = driver.findElement(By.xpath("(//div[@class='item_pricebar']/child::button)["+ randomNum + "]"));
        } catch (NoSuchElementException e) {
            Assert.fail("No se ha encontrado el producto");
        }

            //Paso 8. Validar que el producto no aparece en el carrito
        String idBoton = botonEliminar.getAttribute("id");
        botonEliminar.click();
        try {
           driver.findElement(By.id(idBoton));
           Assert.fail("El producto continua en el carrito");
        }catch (NoSuchElementException e){
            System.out.println("Producto eliminado correctamente");
        }





    }


    @After
    public void tearDown(){

        driver.close();
    }
}
