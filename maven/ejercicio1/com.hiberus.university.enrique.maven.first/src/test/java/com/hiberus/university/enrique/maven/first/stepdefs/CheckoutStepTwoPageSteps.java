package com.hiberus.university.enrique.maven.first.stepdefs;

import com.hiberus.university.enrique.maven.first.pages.CheckOutStepSecondPage;
import com.hiberus.university.enrique.maven.first.pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutStepTwoPageSteps {

    PagesFactory pagesFactory = PagesFactory.getInstance();
    CheckOutStepSecondPage checkOutStepSecondPage = pagesFactory.getCheckOutStepSecondPage();
    @Then("the order price\\(Item Total) is correctly displayed which is the sum of the products")
    public void theOrderPriceItemTotalIsCorrectlyDisplayedWhichIsTheSumOfTheProducts() {

        List<WebElement> precios = checkOutStepSecondPage.getItemPrices();
        WebElement itemTotalElement = checkOutStepSecondPage.getItemTotal();


        float itemTotal = Float.parseFloat(itemTotalElement.getText().substring(itemTotalElement.getText().length() - 5));

        float suma = 0.00F;

        for (WebElement precio : precios) {
            suma += Float.parseFloat(precio.getText().substring(1));
        }

        Assert.assertEquals("La suma de los productos no coincide con el itemTotal", suma, itemTotal, 0.01f);

    }

    @And("the user clicks the button finish")
    public void theUserClicksTheButtonFinish() {
        checkOutStepSecondPage.clickFinish();
    }
}
