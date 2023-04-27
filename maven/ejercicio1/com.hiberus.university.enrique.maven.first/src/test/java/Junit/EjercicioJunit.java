package Junit;

import org.junit.*;

public class EjercicioJunit {
    @BeforeClass
    public static void setUpClass(){

    }

    @Before
    public void setUp(){

    }


    @Test
    public void testArrayEquals(){
        String [] nombresEsperados = {"java","junit","jboss"};
        String [] nombresActuales = {"java", "junit","jboss"};

        Assert.assertArrayEquals("Fallo. Los arrays no son iguales",nombresEsperados,nombresActuales);
    }

    @Test
    public void testSumar(){

        Assert.assertEquals("Fallo. Los resultados no son iguales",(1+1),2);
    }

    @Test
    public void testAssertFalse(){

        Assert.assertFalse(false);
    }

    @After
    public void tearDown(){

    }

    @AfterClass
    public static void tearDownClass(){
    }

}
