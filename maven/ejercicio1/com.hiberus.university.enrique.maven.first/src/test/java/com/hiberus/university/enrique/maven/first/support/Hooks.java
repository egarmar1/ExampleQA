package com.hiberus.university.enrique.maven.first.support;


import com.hiberus.university.enrique.maven.first.pages.PagesFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@Slf4j
public class Hooks {

    public static WebDriver driver;
    @Before
    public void before(Scenario scenario){
        log.info("starting" + scenario.getName());
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        PagesFactory.start(driver);
    }

    @After
    public void after(Scenario scenario){
        log.info("ending" + scenario.getName());
        driver.close();
    }

}
