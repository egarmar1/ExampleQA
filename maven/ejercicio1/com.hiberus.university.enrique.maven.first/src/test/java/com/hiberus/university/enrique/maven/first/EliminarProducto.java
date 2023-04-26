package com.hiberus.university.enrique.maven.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchContextException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class EliminarProducto {

    public static void main(String[] args) {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();



        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-onesie']")).click();



        driver.findElement(By.xpath("//button[@id='remove-sauce-labs-onesie']")).click();



        String numCarrito = driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).getText();

        System.out.println(numCarrito);

        if(numCarrito.equals("")){
            System.out.println("El carrito se ha vaciado correctamente");
        }else {
            System.out.println("El carrito no está vacio");
        }


        driver.close();

    }
}
