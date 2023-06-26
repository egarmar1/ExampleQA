package com.hiberus.university.enrique.maven.first.stepdefs;

import com.hiberus.university.enrique.maven.first.model.InventoryItem;
import com.hiberus.university.enrique.maven.first.pages.CartPage;
import com.hiberus.university.enrique.maven.first.pages.InventoryPage;
import com.hiberus.university.enrique.maven.first.pages.PagesFactory;
import com.hiberus.university.enrique.maven.first.support.TestDataContext;
import io.cucumber.java.bs.I;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class CartPageSteps {
    PagesFactory pagesFactory = PagesFactory.getInstance();
    CartPage cartPage = pagesFactory.getCartPage();


    @Then("the product removed does not appear in the cart")
    public void theProductRemovedDoesNotAppearInTheCart() {

        String buttonId = TestDataContext.getInventoryItemListInCart().get(0).getName();

        Assert.assertFalse("The product still exists",cartPage.existsById(buttonId));
    }

    @And("the user clicks remove to a random product")
    public void theUserClicksRemoveToARandomProduct() {
        String itemName = cartPage.deleteRandomFromCart();
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setName(itemName);
        TestDataContext.addItem(inventoryItem);
    }

    @And("the user clicks the button checkout")
    public void theUserClicksTheButtonCheckout() {
        cartPage.clickCheckout();
    }
}
