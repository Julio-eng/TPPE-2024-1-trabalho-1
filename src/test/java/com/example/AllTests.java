package com.example;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    ClienteTestsSuite.class, VendaTestsSuite.class, ProdutoTest.class
})
public class AllTests {
    
}
