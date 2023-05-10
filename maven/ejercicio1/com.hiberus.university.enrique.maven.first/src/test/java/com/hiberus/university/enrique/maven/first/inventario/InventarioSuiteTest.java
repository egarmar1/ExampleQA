package com.hiberus.university.enrique.maven.first.inventario;

import com.hiberus.university.enrique.maven.first.pages.InventoryPage;
import com.hiberus.university.enrique.maven.first.pages.LoginPage;
import com.hiberus.university.enrique.maven.first.pages.PagesFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class InventarioSuiteTest {

    public static WebDriver driver;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;

    @Before
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        PagesFactory.start(driver);

        //Paso 1. Ir a la página de saucedemo
        driver.get(loginPage.PAGE_URL);

        PagesFactory pagesFactory = PagesFactory.getInstance();
        loginPage = pagesFactory.getLoginPage();
        inventoryPage = pagesFactory.getInventoryPage();

        //Paso 2. Escribir el username
        loginPage.enterUsername("standard_user");
        //Paso 3. Escribir la password
        loginPage.enterPassword("secret_sauce");
        //Paso 4. Pulsar el boton de login
        loginPage.clickLogin();

    }

    @Test
    public void testNumProductos6(){

        //Paso 5. Validar que el numero de productos es 6
        Assert.assertEquals("No hay 6 productos",6,inventoryPage.getProductNames().size());

    }

    @Test
    public void testExisteProducto(){

        //Paso 5. Validar que el producto Sauce Labs Bolt T-shirt aparece
        Assert.assertTrue("No se ha encontrado el producto", inventoryPage.existsProductByName("Sauce Labs Bolt T-Shirt"));

    }

    @Test
    public void addProductTest(){

        //Paso 5. Agregar al carrito el producto Sauce Labs Bolt T-shirt
        inventoryPage.clickProductByName("Sauce Labs Bolt T-Shirt");

        String num = inventoryPage.getNumCartIcon();
        Assert.assertEquals("El icono del producto no muestra el numero 1","1", num);

    }

    @Test
    public void deleteProductTest(){

        //Paso 5. Agregar al carrito el producto Sauce Labs Bolt T-shirt
        inventoryPage.clickProductByName("Sauce Labs Bolt T-Shirt");

        //Paso 6. Eliminar el producto
        inventoryPage.removeProductByName("Sauce Labs Bolt T-Shirt");

        //Paso 7. Validar que en el icono del carrito de ha eliminado
        Assert.assertEquals("El carrito no está vacío","",inventoryPage.getNumCartIcon());



    }

    @Test
    public void testAnyadir3Productos(){

        //Paso 5. Agregar al carrito 3 productos elegidos al azar
        inventoryPage.clickAddRandomButtons(3);
        //Paso 6. Validar que, en el icono del carrito se han agregado los 3 productos
        Assert.assertEquals("No hay 3 productos en el icono del carrito","3",inventoryPage.getNumCartIcon());

    }

    @Test
    public void testOrdenAlfabetico(){ //En este ejercicio he hecho de más comprobando que de verdad los productos están ordenados alfabeticamente de la z a la A

        // Paso 5 Seleccionar el filtro NAME (Z TO A)
        inventoryPage.clickSortZA();
        //Paso 6 Validar que el filtro ordena por orden alfabético de la Z a la A

        //Comprobamos que los productos están ordenados alfabéticamente
        //Obtenemos todos los productos
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
        System.out.println(expectedProdsName);
        System.out.println(actualProdsName);

        Assert.assertArrayEquals("No se ha ordenado alfabeticamente de manera correcta",expectedProdsName,actualProdsName);

    }

    @Test
    public void testMenorAMayor(){

        // Paso 5. Seleccionar el filtro low to high
        inventoryPage.clickSortLohi();

        //Paso 6. Validar el filtro
        List<WebElement> precios = inventoryPage.getProductPrices();

        double previousPrice = Double.parseDouble(precios.get(0).getText().substring(1));

        for(WebElement precio : precios){
            double newPrice = Double.parseDouble(precio.getText().substring(1));

            Assert.assertFalse("The products are not listed from low to high price correctly",previousPrice > newPrice);
            previousPrice = newPrice;
        }

    }

    @Test
    public void testMayorAMenor(){

        // Paso 5. Seleccionar el filtro high to low
        inventoryPage.clickSortHilo();

        //Paso 6. Validar el filtro
        List<WebElement> precios = inventoryPage.getProductPrices();

        double previousPrice = Double.parseDouble(precios.get(0).getText().substring(1));

        for(WebElement precio : precios){
            double newPrice = Double.parseDouble(precio.getText().substring(1));

            Assert.assertFalse("The products are not listed from high to low price correctly",previousPrice < newPrice);
            previousPrice = newPrice;
        }
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
