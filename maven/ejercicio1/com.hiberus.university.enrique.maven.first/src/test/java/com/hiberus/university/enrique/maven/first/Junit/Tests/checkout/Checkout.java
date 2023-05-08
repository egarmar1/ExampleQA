package com.hiberus.university.enrique.maven.first.Junit.Tests.checkout;

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

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Checkout {
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
    public void testFinalPrice(){ //No se si un try que incluye tantos webElements merece la pena ya que genera mas dudas de las que da

        //Paso 5. Agregar al carrito 3 productos elegidos al azar
        List<WebElement> productos = driver.findElements(By.xpath("//div[@class='inventory_list']/child::div/descendant::button"));


        List<Integer> indices = new ArrayList<>();
        Random random = new Random();

        while (indices.size() < 3) {
            int indice = random.nextInt(productos.size());
            if (!indices.contains(indice)) {
                indices.add(indice);
            }
        }

        WebElement producto1 = productos.get(indices.get(0));
        WebElement producto2 = productos.get(indices.get(1));
        WebElement producto3 = productos.get(indices.get(2));


        producto1.click();
        producto2.click();
        producto3.click();

        try {


            //Paso 6. Ir al carrito
            driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

            //Paso 7. Clickar Checkout
            driver.findElement(By.xpath("//button[@id='checkout']")).click();

            //Paso 8. Rellenar datos del checkout y continuar
            WebElement inputName = driver.findElement(By.xpath("//input[@id='first-name']"));
            WebElement inputLastName = driver.findElement(By.xpath("//input[@id='last-name']"));
            WebElement inputPostal = driver.findElement(By.xpath("//input[@id='postal-code']"));

            inputName.sendKeys("Kike");
            inputLastName.sendKeys("García");
            inputPostal.sendKeys("48102");


            driver.findElement(By.xpath("//input[@id='continue']")).click();

            //Paso 10. Comprobar que la suma del importe es correcta


            List<WebElement> precios = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
            WebElement itemTotalString = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));


            float itemTotal = Float.parseFloat(itemTotalString.getText().substring(itemTotalString.getText().length() - 5));

            float suma = 0.00F;

            for (WebElement precio : precios) {
                suma += Float.parseFloat(precio.getText().substring(1));
            }

            Assert.assertEquals("La suma de los productos no coincide con el itemTotal", suma, itemTotal, 0.01f);

            //Paso 9. Finalizar checkout
            driver.findElement(By.xpath("//button[@id='finish']")).click();
        }catch (NoSuchElementException e){
            Assert.fail("Algun elemento no se ha podido encontrar");

        }
    }

    @Test
    public void testPedido(){//No se si un try que incluye tantos webElements merece la pena ya que genera mas dudas de las que da

        //Paso 5. Agregar al carrito 3 productos al azar
        List<WebElement> productos = driver.findElements(By.xpath("//div[@class='inventory_list']/child::div/descendant::button"));

        Random random = new Random();
        WebElement producto = productos.get(random.nextInt(productos.size()));

        producto.click();

        //Paso 6. Ir al carrito
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        //Paso 7. Clickar Checkout
        driver.findElement(By.xpath("//button[@id='checkout']")).click();

        //Paso 8. Rellenar datos del checkout y continuar
        WebElement inputName = driver.findElement(By.xpath("//input[@id='first-name']"));
        WebElement inputLastName = driver.findElement(By.xpath("//input[@id='last-name']"));
        WebElement inputPostal = driver.findElement(By.xpath("//input[@id='postal-code']"));

        inputName.sendKeys("Kike");
        inputLastName.sendKeys("García");
        inputPostal.sendKeys("48102");


        driver.findElement(By.xpath("//input[@id='continue']")).click();

        //Paso 9. Finalizar checkout
        driver.findElement(By.xpath("//button[@id='finish']")).click();

        //Paso 10. Validar que el pedido ha finalizado correctamente mostrando el mensaje adecuado
        WebElement comentario = driver.findElement(By.xpath("//div[@class='complete-text']"));

        Assert.assertEquals("El mensaje de pedido completado no coincide","Your order has been dispatched, and will arrive just as fast as the pony can get there!",comentario.getText());
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
