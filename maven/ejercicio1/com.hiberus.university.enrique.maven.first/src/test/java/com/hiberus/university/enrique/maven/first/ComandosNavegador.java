package com.hiberus.university.enrique.maven.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class ComandosNavegador {
    private static String originalUrl = "https://www.saucedemo.com/";

    public static void main(String[] args) throws InterruptedException{
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        String titulo= driver.getTitle();
        int titleLength= titulo.length();
        System.out.println("Title= " + titulo + ". Length=" + titleLength);

        String url = driver.getCurrentUrl();


        if (url.equals(originalUrl)){
            System.out.println(true);
        }



        String source = driver.getPageSource();
        int sourceLength= source.length();

        System.out.println("El codigo fuente tiene una longitud de " + sourceLength);
        driver.quit();
    }
}
