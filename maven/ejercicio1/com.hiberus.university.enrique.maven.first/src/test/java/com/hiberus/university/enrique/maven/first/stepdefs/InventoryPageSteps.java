package com.hiberus.university.enrique.maven.first.stepdefs;

import com.hiberus.university.enrique.maven.first.pages.InventoryPage;
import com.hiberus.university.enrique.maven.first.pages.LoginPage;
import com.hiberus.university.enrique.maven.first.pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class InventoryPageSteps {
    PagesFactory pagesFactory = PagesFactory.getInstance();
    InventoryPage inventoryPage = pagesFactory.getInventoryPage();
    @And("the user adds to cart {int} random products")
    public void theUserAddsToCartRandomProducts(int products) {
        inventoryPage.clickAddRandomButtons(products);
    }

    @And("the user clicks the cart icon")
    public void theUserClicksTheCartIcon() {
        inventoryPage.goToCart();
    }

    @Then("the user is in the inventory page where it is shown {int} products")
    public void theUserIsInTheInventoryPageWhereItIsShownProducts(int numProducts) {
        Assert.assertEquals("No hay 6 productos",numProducts,inventoryPage.getProductNames().size());

    }

    @Then("the user is in the inventory page where it is shown the product {string}")
    public void theUserIsInTheInventoryPageWhereItIsShownTheProduct(String product) {
        Assert.assertTrue("No se ha encontrado el producto", inventoryPage.existsProductByName(product));

    }

    @And("the user adds to the cart the product {string}")
    public void theUserAddsToTheCartTheProduct(String product) {
        inventoryPage.clickProductByName(product);
    }

    @Then("the icon of the cart shows the value {string}")
    public void theIconOfTheCartShowsTheValue(String  cartNum) {

        Assert.assertEquals("El icono del producto no muestra el numero 1",cartNum, inventoryPage.getNumCartIcon());
    }

    @And("the user clicks the button remove from the product {string}")
    public void theUserClicksTheButtonRemoveFromTheProduct(String product) {
        inventoryPage.removeProductByName(product);
    }

    @And("the user adds to the cart {int} random products")
    public void theUserAddsToTheCartRandomProducts(int num) {
        inventoryPage.clickAddRandomButtons(num);
    }

    @And("the user chooses the option {string}")
    public void theUserChoosesTheOption(String option) {
        if (option.equals("NAME (Z TO A)")){
            inventoryPage.clickSortZA();
            } else if (option.equals("PRICE (low to high)")) {
            inventoryPage.clickSortLohi();
        } else if (option.equals("PRICE (high to low)")) {
            inventoryPage.clickSortHilo();
        }
    }

    @Then("the user views the products sorted by {string}")
    public void theUserViewsTheProductsSortedBy(String option) {
        if (option.equals("NAME (Z TO A)")){
            List<WebElement> productos = inventoryPage.getProductNames();

            //Guardamos los nombres de los productos en dos arrays
            String[] expectedProdsName = new String[productos.size()];
            String[] actualProdsName = new String[productos.size()];

            for (int i = 0; i< productos.size(); i++){
                expectedProdsName[i]=productos.get(i).getText();
                actualProdsName[i]=productos.get(i).getText();
            }


            //Ordenamos alfabeticamente un array y lo comprobamos con el segundo array que es el de la página web
            Arrays.sort(expectedProdsName, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s2.compareTo(s1);
                }
            });
            Assert.assertArrayEquals("No se ha ordenado alfabeticamente de manera correcta",expectedProdsName,actualProdsName);
        } else if (option.equals("PRICE (low to high)")) {

            List<WebElement> precios = inventoryPage.getProductPrices();

            double previousPrice = Double.parseDouble(precios.get(0).getText().substring(1));

            for(WebElement precio : precios){
                double newPrice = Double.parseDouble(precio.getText().substring(1));

                Assert.assertFalse("The products are not listed from low to high price correctly",previousPrice > newPrice);
                previousPrice = newPrice;
            }

        } else if (option.equals("PRICE (high to low)")) {
            List<WebElement> precios = inventoryPage.getProductPrices();

            double previousPrice = Double.parseDouble(precios.get(0).getText().substring(1));

            for(WebElement precio : precios){
                double newPrice = Double.parseDouble(precio.getText().substring(1));

                Assert.assertFalse("The products are not listed from high to low price correctly",previousPrice < newPrice);
                previousPrice = newPrice;
            }
        }
    }
}
