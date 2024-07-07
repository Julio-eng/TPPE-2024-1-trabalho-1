package com.example;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ProdutoTest {
    private String codigo;
    private String descricao;
    private double valor;
    private String unidade;

    private String codigoEsperado;
    private String descricaoEsperado;
    private double valorEsperado;
    private String unidadeEsperada;

    public ProdutoTest(String codigo, String descricao, double valor, String unidade, String codigoEsperado,
            String descricaoEsperado, double valorEsperado, String unidadeEsperada) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.unidade = unidade;
        this.codigoEsperado = codigoEsperado;
        this.descricaoEsperado = descricaoEsperado;
        this.valorEsperado = valorEsperado;
        this.unidadeEsperada = unidadeEsperada;
    }

    @Parameters
    public static Collection<Object[]> getParameter(){
        Object[][] resposta = new Object[][] {
            {"0", "Geladeira", 1500.37,"unidade", "0", "Geladeira", 1500.37,"unidade"},
            {"1", "Fio Elétrico", 10.00,"metro", "1", "Fio Elétrico", 10.00,"metro"},
            {"2", "Tinta", 15.32,"litro", "2", "Tinta", 15.32,"litro"},
            {"3", "Microondas", 345.50,"unidade", "3", "Microondas", 345.50,"unidade"},
        };
        return Arrays.asList(resposta);
    }

    @Test
    public void testCadastrarProduto(){
        Produto produto = new Produto(codigo, descricao, valor, unidade);
        assertEquals(codigoEsperado, produto.getCodigo());
        assertEquals(descricaoEsperado, produto.getDescricao());
        assertEquals(valorEsperado, produto.getValor(), 0.001);
        assertEquals(unidadeEsperada, produto.getUnidade());
    }
}