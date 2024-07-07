package com.example;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ClienteTest {
    private String tipo;
    private String estado;
    private boolean isCapital;

    private String tipoEsperado;
    private String estadoEsperado;
    private boolean isCapitalEsperado;
    private double descontoEsperado;
    private double descontoFreteEsperado;
    private boolean isCartaoEmpresa;
    private double taxaCashbackEsperada;

    public ClienteTest(String tipo, String estado, boolean isCapital, String tipoEsperado, String estadoEsperado,
            boolean isCapitalEsperado, double descontoEsperado, double descontoFreteEsperado, boolean isCartaoEmpresa, double taxaCashbackEsperada) {
        this.tipo = tipo;
        this.estado = estado;
        this.isCapital = isCapital;
        this.tipoEsperado = tipoEsperado;
        this.estadoEsperado = estadoEsperado;
        this.isCapitalEsperado = isCapitalEsperado;
        this.descontoEsperado = descontoEsperado;
        this.descontoFreteEsperado = descontoFreteEsperado;
        this.isCartaoEmpresa = isCartaoEmpresa;
        this.taxaCashbackEsperada = taxaCashbackEsperada;
    }

    @Parameters
    public static Collection<Object[]> getParameters(){
        Object[][] resposta = new Object[][] {
            {"padrao", "SP", true, "padrao", "SP", true, 0.0, 0.0, false, 0.0},
            {"padrao", "SP", false, "padrao", "SP", false, 0.0, 0.0, false, 0.0},
            {"especial", "BA", true, "especial", "BA", true, 10.0, 30.0, true, 0.0},
            {"especial", "BA", false, "especial", "BA", false, 10.0, 30.0, false, 0.0},
            {"prime", "MG", true, "prime", "MG", true, 0.0, 100.0, true, 0.5},
            {"prime", "MG", false, "prime", "MG", false, 0.0, 100.0, false, 0.3}
        };
        return Arrays.asList(resposta);
    }

    @Test
    public void testCadastrarCliente(){
        Cliente cliente = new Cliente("josé", tipo, estado, isCapital, 0, 0);
        assertEquals(tipoEsperado, cliente.getTipo());
        assertEquals(estadoEsperado, cliente.getEstado());
        assertEquals(isCapitalEsperado, cliente.isCapital());
    }

    @Test
    public void testDesconto(){
        Cliente cliente = new Cliente("Maria", tipo, estado, isCapital, 0, 0);
        assertEquals(descontoEsperado, cliente.getDisconto(), 0.001);
    }

    @Test
    public void testDescontoFrete(){
        Cliente cliente = new Cliente("Carlos", tipo, estado, isCapital, 0, 0);
        assertEquals(descontoFreteEsperado, cliente.getDiscontoFrete(), 0.001);
    }

    @Test
    public void testTaxaCashback(){
        Cliente cliente = new Cliente("Mario", tipo, estado, isCapital, 0, 0);
        assertEquals(taxaCashbackEsperada, cliente.getTaxaCashback(isCartaoEmpresa), 0.001);
    }
}
