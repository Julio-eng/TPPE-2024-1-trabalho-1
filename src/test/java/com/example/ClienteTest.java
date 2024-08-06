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

    public ClienteTest(String tipo, String estado, boolean isCapital, String tipoEsperado, String estadoEsperado,
            boolean isCapitalEsperado, double descontoEsperado, double descontoFreteEsperado, boolean isCartaoEmpresa, double taxaCashbackEsperada) {
        this.tipo = tipo;
        this.estado = estado;
        this.isCapital = isCapital;
        this.tipoEsperado = tipoEsperado;
        this.estadoEsperado = estadoEsperado;
        this.isCapitalEsperado = isCapitalEsperado;
    }

    @Parameters
    public static Collection<Object[]> getParameters(){
        Object[][] resposta = new Object[][] {
            {"padrao", "SP", true, "padrao", "SP", true, 0.0, 0.0, false, 0.0},
            {"padrao", "SP", false, "padrao", "SP", false, 0.0, 0.0, false, 0.0},
            {"especial", "BA", true, "especial", "BA", true, 0.1, 0.3, true, 0.0},
            {"especial", "BA", false, "especial", "BA", false, 0.1, 0.3, false, 0.0},
            {"prime", "MG", true, "prime", "MG", true, 0.0, 0.0, true, 0.05},
            {"prime", "MG", false, "prime", "MG", false, 0.0, 0.0, false, 0.03}
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
}
