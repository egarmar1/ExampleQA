package com.hiberus.university.enrique.maven.first.Junit.Tests.run;

import com.hiberus.university.enrique.maven.first.Junit.Tests.inventario.Inventario;
import com.hiberus.university.enrique.maven.first.Junit.Tests.carrito.Carrito;
import com.hiberus.university.enrique.maven.first.Junit.Tests.checkout.Checkout;
import com.hiberus.university.enrique.maven.first.Junit.Tests.login.Login;
import com.hiberus.university.enrique.maven.first.Junit.Tests.logout.Logout;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Login.class,
        Inventario.class,
        Carrito.class,
        Checkout.class,
        Logout.class
})
public class RunTest {

}
