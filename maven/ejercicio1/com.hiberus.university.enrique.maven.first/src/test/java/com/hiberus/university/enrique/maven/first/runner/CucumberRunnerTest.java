package com.hiberus.university.enrique.maven.first.runner;

import com.hiberus.university.enrique.maven.first.pages.PagesFactory;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"com.hiberus.university.enrique.maven.first.stepdefs",
                "com.hiberus.university.enrique.maven.first.support"},
        features = {"src/test/resources"}
)
public class CucumberRunnerTest {


}
