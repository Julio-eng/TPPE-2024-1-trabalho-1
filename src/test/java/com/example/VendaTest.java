package com.example;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(Parameterized.class)
public class VendaTest {
    private Date dataVenda;
    private Cliente cliente;
    private List<Produto> produtos;
    private String formaPagamento;
    private double totalEsperado;
    private double cashbackEsperado;

    public VendaTest(Date dataVenda, Cliente cliente, List<Produto> produtos, String formaPagamento, double totalEsperado, double cashbackEsperado) {
        this.dataVenda = dataVenda;
        this.cliente = cliente;
        this.produtos = produtos;
        this.formaPagamento = formaPagamento;
        this.totalEsperado = totalEsperado;
        this.cashbackEsperado = cashbackEsperado;
    }

    @Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {new Date(), new Cliente("João", "padrão", "DF", true, 50.0, 0.0), 
                Arrays.asList(new Produto("001", "Produto 1", 100.0, "unidade")), 
                "dinheiro", 123.0, 0.0},
                
            {new Date(), new Cliente("Maria", "especial", "SE", false, 150.0, 0.0), 
                Arrays.asList(new Produto("002", "Produto 2", 200.0, "unidade")), 
                "cartao loja", 201.0, 0.0},
                
            {new Date(), new Cliente("Carlos", "prime", "SE", true, 200.0, 0.0), 
                Arrays.asList(new Produto("003", "Produto 3", 300.0, "unidade")), 
                "cartao loja", 348.0, 15.0}
        });
    }

    @Test
    public void testValorTotal() {
        Venda venda = new Venda(dataVenda, cliente, produtos, formaPagamento);
        double totalReal = venda.valorTotal();
        assertEquals(totalEsperado, totalReal, 0.01);
    }

    @Test
    public void testCashback() {
        Venda venda = new Venda(dataVenda, cliente, produtos, formaPagamento);
        double cashbackReal = venda.calcularCashback();
        assertEquals(cashbackEsperado, cashbackReal, 0.01);
    }
}
