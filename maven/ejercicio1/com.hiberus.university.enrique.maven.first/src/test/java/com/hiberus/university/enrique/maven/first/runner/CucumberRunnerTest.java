package com.hiberus.university.enrique.maven.first.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/surefire-reports/cucumber.json",
                    "html:target/cucumber-html-report",
        },
        glue = {"com.hiberus.university.enrique.maven.first.stepdefs",
                "com.hiberus.university.enrique.maven.first.support"},
        features = {"src/test/resources"}
)
public class CucumberRunnerTest {


}
