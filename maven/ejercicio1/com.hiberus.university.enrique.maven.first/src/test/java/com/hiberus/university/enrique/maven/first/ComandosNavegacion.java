package com.hiberus.university.enrique.maven.first;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class ComandosNavegacion {
    public static void main(String[] args) {

        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();

        String OriginalUrl = "https://www.hiberus.com/";
        driver.get(OriginalUrl);
        driver.findElement(By.xpath("//button[@id = 'CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")).click();

        driver.findElement(By.xpath("//ul[@class = 'menu--colossal menu--main_menu']/child::li[6]/child::a[@href='/contacto']")).click();



        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().to(OriginalUrl);
        driver.navigate().refresh();
        driver.quit();
    }
}
