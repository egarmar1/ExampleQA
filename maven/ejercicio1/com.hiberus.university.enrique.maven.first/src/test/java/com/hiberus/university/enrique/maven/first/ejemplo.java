package com.hiberus.university.enrique.maven.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ejemplo {
    public static void main(String[] args) {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        driver = new FirefoxDriver(firefoxOptions);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");



        try {

            //WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@dta-test='username']")));
            WebElement element2 = driver.findElement(By.xpath("//input[@dta-test='username']"));
        } catch (TimeoutException e) {
            System.out.println("El elemento no es clickable después de 10 segundos.");
        } catch (NoSuchElementException e){
            System.out.println("No such element exception");
        }
    }
}
