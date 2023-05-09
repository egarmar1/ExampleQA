package com.hiberus.university.enrique.maven.first.run;

import com.hiberus.university.enrique.maven.first.inventario.Inventario;
import com.hiberus.university.enrique.maven.first.carrito.Carrito;
import com.hiberus.university.enrique.maven.first.checkout.Checkout;
import com.hiberus.university.enrique.maven.first.login.LoginSuiteTest;
import com.hiberus.university.enrique.maven.first.logout.Logout;
import com.hiberus.university.enrique.maven.first.model.InventoryItem;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginSuiteTest.class,
        Inventario.class,
        Carrito.class,
        Checkout.class,
        Logout.class
})
public class RunTest {

    InventoryItem inventoryItem = new InventoryItem("name","description","price");
}
