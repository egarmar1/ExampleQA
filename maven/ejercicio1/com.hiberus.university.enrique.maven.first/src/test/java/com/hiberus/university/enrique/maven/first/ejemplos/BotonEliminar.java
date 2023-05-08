package com.hiberus.university.enrique.maven.first.ejemplos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class BotonEliminar {

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

        try {
            WebElement removeElement = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-onesie']"));

            if(removeElement.getText().equals("Remove")){
                System.out.println("El botón dice Remove correctamente");
            }else {
                System.out.println("El botón no dice Remove");
            }

        }catch (NoSuchElementException e){
            System.out.println("El botón o no existe o no estaba añadido");
        }







    }
}
