package com.hiberus.university.enrique.maven.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class LoginIncorrecto {


    public static void main(String[] args) {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys("secret_sauce");

        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();

        try {
            WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
            System.out.println("Se ha encontrado el mensaje de login incorrecto");
        }catch (NoSuchElementException e){
            System.out.println("No se ha encontrado el mensaje de login incorrecto");
        }

        String actualUrl = driver.getCurrentUrl();



    }

}
