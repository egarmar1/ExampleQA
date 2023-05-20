package com.hiberus.university.enrique.maven.first.support;


import com.hiberus.university.enrique.maven.first.pages.PagesFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
@Slf4j
public class Hooks {

    public static WebDriver driver;
    @Before
    public void before(Scenario scenario){
        log.info("starting" + scenario.getName());

        String browser = System.getProperty("browser");
        String headless = System.getProperty("headless");

        if (browser == null || browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions optionsFirefox = new FirefoxOptions();

            if(headless != null && headless.equalsIgnoreCase("true")){
                optionsFirefox.addArguments("--headless");
            }

            driver = new FirefoxDriver(optionsFirefox);

        } else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions optionsChrome = new ChromeOptions();

            if(headless != null && headless.equalsIgnoreCase("true")){
                optionsChrome.addArguments("--headless");
            }

            driver = new ChromeDriver(optionsChrome);
        } else {
            throw new IllegalArgumentException("El navegador especificado no es válido");
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        PagesFactory.start(driver);
    }

    @After
    public void after(Scenario scenario){
        if (scenario.isFailed()) {
            // Capturar captura de pantalla y adjuntarla al reporte
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Captura de pantalla");
        }

        log.info("ending" + scenario.getName());
        driver.close();
    }

}
