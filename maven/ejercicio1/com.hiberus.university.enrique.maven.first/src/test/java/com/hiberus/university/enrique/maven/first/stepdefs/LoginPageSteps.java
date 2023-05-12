package com.hiberus.university.enrique.maven.first.stepdefs;

import com.hiberus.university.enrique.maven.first.pages.InventoryPage;
import com.hiberus.university.enrique.maven.first.pages.LoginPage;
import com.hiberus.university.enrique.maven.first.pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class LoginPageSteps {

    PagesFactory pagesFactory = PagesFactory.getInstance();
    LoginPage loginPage = pagesFactory.getLoginPage();
    InventoryPage inventoryPage = pagesFactory.getInventoryPage();

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        log.info("The user is on the home page");
        loginPage.navigateTo(LoginPage.PAGE_URL);

    }

    @And("the user provide the username {string} and password {string}")
    public void theUserProvideTheUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("the user is logged successfully and is into the inventory page")
    public void theUserIsLoggedSuccessfullyAndIsIntoTheInventoryPage() {
        Assert.assertEquals("The url is not inventory page",
                inventoryPage.PAGE_URL,PagesFactory.getInstance().getDriver().getCurrentUrl());
    }

    @Then("the user views a message error")
    public void theUserViewsAMessageError() {
        Assert.assertTrue(loginPage.hasErrorMessage());
    }

    @Then("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        Assert.assertEquals("No se ha redirigido a la pantalla de inicio tras cerrar sesion",loginPage.PAGE_URL,PagesFactory.getInstance().getDriver().getCurrentUrl());
    }
}
