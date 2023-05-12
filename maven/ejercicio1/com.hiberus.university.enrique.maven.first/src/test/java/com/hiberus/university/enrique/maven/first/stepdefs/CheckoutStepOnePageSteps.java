package com.hiberus.university.enrique.maven.first.stepdefs;

import com.hiberus.university.enrique.maven.first.pages.CartPage;
import com.hiberus.university.enrique.maven.first.pages.CheckOutStepOnePage;
import com.hiberus.university.enrique.maven.first.pages.PagesFactory;
import io.cucumber.java.en.And;

public class CheckoutStepOnePageSteps {
    PagesFactory pagesFactory = PagesFactory.getInstance();
    CheckOutStepOnePage checkOutStepOnePage = pagesFactory.getCheckOutStepOnePage();
    @And("the user fills out all the fields of the form")
    public void theUserFillsOutAllTheFieldsOfTheForm() {
        checkOutStepOnePage.enterFirstName("John");
        checkOutStepOnePage.enterLastName("Miller");
        checkOutStepOnePage.enterPostalCode("40174");

    }

    @And("the user clicks the button continue")
    public void theUserClicksTheButtonContinue() {
        checkOutStepOnePage.clickContinue();
    }
}
