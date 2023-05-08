package com.hiberus.university.enrique.maven.first.Junit.Tests.ventanasEmergentes;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class VentanasEmergentes {

    WebDriver driver;
    String url = "https://demoqa.com/alerts";
    WebDriverWait wait;
    @Before
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver,5);

        //Paso 1. Ir a la página de saucedemo

    }

    @Test
    public void ventanaEmergente(){
        driver.get(url);

        driver.findElement(By.cssSelector("#promtButton")).click();

        driver.switchTo().alert().sendKeys("Kike");
        driver.switchTo().alert().accept();
    }

    @Test
    public void clickDerecho(){


        Actions actions = new Actions(driver);

        driver.get("https://the-internet.herokuapp.com/context_menu");

        WebElement element = driver.findElement(By.cssSelector("#hot-spot"));

        actions.contextClick(element).perform();

        driver.switchTo().alert().accept();

    }

    @Test
    public void dobleClick(){


        Actions actions = new Actions(driver);

        driver.get("https://demoqa.com/buttons");

        WebElement element = driver.findElement(By.cssSelector("#doubleClickBtn"));

        actions.doubleClick(element).perform();

    }

    @Test
    public void dragMe(){


        Actions actions = new Actions(driver);

        driver.get("https://demoqa.com/droppable/");

        WebElement elementFrom = driver.findElement(By.cssSelector("#draggable"));

        WebElement elementFinal = driver.findElement(By.cssSelector("#droppable"));

        actions.dragAndDrop(elementFrom, elementFinal).perform();

        Assert.assertEquals("No se ha dropeado","Dropped!",elementFinal.getText());

    }


    @Test
    public void mouseHover(){


        Actions actions = new Actions(driver);

        driver.get("https://demoqa.com/menu/");

        WebElement mainItem2 = driver.findElement(By.xpath("(//ul[@id='nav']//child::a)[2]"));
        WebElement subList = driver.findElement(By.xpath("(//ul[@id='nav']//child::a)[5]"));
        WebElement subSubItem2 = driver.findElement(By.xpath("(//ul[@id='nav']//child::a)[7]"));


        actions.moveToElement(mainItem2).perform();
        actions.moveToElement(subList).perform();
        actions.moveToElement(subSubItem2).perform();

        subSubItem2.click();

    }

    @Test
    public void keyboardEvents(){


        Actions actions = new Actions(driver);

        driver.get("https://demoqa.com/text-box");

        WebElement userNameInput = driver.findElement(By.cssSelector("#userName"));
        WebElement emailInput = driver.findElement(By.cssSelector("#userEmail"));


        actions.sendKeys(userNameInput, "texto").perform();


    }




    @After
    public void tearDown(){
        driver.close();
    }
}
