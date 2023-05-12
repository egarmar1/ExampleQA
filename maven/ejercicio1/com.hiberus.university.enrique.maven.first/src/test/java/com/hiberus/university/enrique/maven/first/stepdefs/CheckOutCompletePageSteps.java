package com.hiberus.university.enrique.maven.first.stepdefs;

import com.hiberus.university.enrique.maven.first.pages.CheckOutCompletePage;
import com.hiberus.university.enrique.maven.first.pages.PagesFactory;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class CheckOutCompletePageSteps {

    PagesFactory pagesFactory = PagesFactory.getInstance();
    CheckOutCompletePage checkOutCompletePage = pagesFactory.getCheckOutCompletePage();
    @Then("the user is on a web page displaying the message: {string}")
    public void theUserIsOnAWebPageDisplayingTheMessage(String message) {
        Assert.assertEquals("El mensaje de pedido completado no coincide",message,checkOutCompletePage.getCompleteText());

    }
}
