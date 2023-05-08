package com.hiberus.university.enrique.maven.first.ejemplos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EliminarProducto {

    public static void main(String[] args) {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();



        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-onesie']")).click();







        try { //Imaginemonos que tarda más en mostrarse el carrito actualizado que el resto de la página
            driver.findElement(By.xpath("//button[@id='remove-sauce-labs-onesie']")).click();
            WebElement carrito = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='shopping_cart_link']")));

            if(carrito.getText().equals("")){
                System.out.println("El carrito se ha vaciado correctamente");
            }else {
                System.out.println("El carrito no está vacio");
            }

        }catch (NoSuchElementException ne){
            System.out.println("No se encuentra el botón para eliminar el producto");
        }catch (TimeoutException te){
            System.out.println("No se encuentra el número del carrito");
        }


    }
}
