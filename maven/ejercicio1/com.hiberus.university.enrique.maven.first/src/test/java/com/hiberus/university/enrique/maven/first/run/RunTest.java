package com.hiberus.university.enrique.maven.first.run;

import com.hiberus.university.enrique.maven.first.inventario.InventarioSuiteTest;
import com.hiberus.university.enrique.maven.first.carrito.CarritoSuiteTest;
import com.hiberus.university.enrique.maven.first.checkout.CheckoutSuiteTest;
import com.hiberus.university.enrique.maven.first.login.LoginSuiteTest;
import com.hiberus.university.enrique.maven.first.logout.LogoutSuiteTest;
import com.hiberus.university.enrique.maven.first.model.InventoryItem;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginSuiteTest.class,
        InventarioSuiteTest.class,
        CarritoSuiteTest.class,
        CheckoutSuiteTest.class,
        LogoutSuiteTest.class
})
public class RunTest {

    InventoryItem inventoryItem = new InventoryItem("name","description","price");
}
