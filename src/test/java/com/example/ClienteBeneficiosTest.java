package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * A refatoração "Extrair Classe" foi aplicada para separar as responsabilidades da classe Cliente. Antes, a classe Cliente tinha métodos para calcular descontos, frete e cashback, o que aumentava sua complexidade e violava o princípio da responsabilidade única.
 * 
 * Após a refatoração, esses métodos foram movidos para uma nova classe ClienteBeneficios. Agora, a classe Cliente é responsável apenas por armazenar os dados do cliente, enquanto ClienteBeneficios lida com as regras de negócio associadas aos benefícios do cliente.
 * 
 * Benefícios da refatoração:
 * Código mais organizado e legível: Cada classe tem uma responsabilidade clara, tornando o código mais fácil de entender e manter.
 * Facilita a manutenção: Alterações nas regras de negócio podem ser feitas na classe ClienteBeneficios sem afetar a classe `Cliente`.
 * Isolamento de funcionalidades: As funcionalidades de cálculo de benefícios estão isoladas, o que facilita a reutilização e teste unitário.
 * Redução da complexidade da classe Cliente: A classe Cliente agora é mais simples e focada apenas em representar os dados do cliente.
 */

public class ClienteBeneficiosTest {

    @Test
    public void testDesconto() {
        Cliente cliente = new Cliente("Maria", "especial", "SP", true, 0, 0);
        ClienteBeneficios beneficios = new ClienteBeneficios(cliente);
        assertEquals(0.1, beneficios.getDesconto(), 0.001);
    }

    @Test
    public void testDescontoFrete() {
        Cliente cliente = new Cliente("Carlos", "especial", "SP", true, 0, 0);
        ClienteBeneficios beneficios = new ClienteBeneficios(cliente);
        assertEquals(0.3, beneficios.getDescontoFrete(), 0.001);
    }

    @Test
    public void testTaxaCashbackCartaoEmpresa() {
        Cliente cliente = new Cliente("Mario", "prime", "MG", true, 0, 0);
        ClienteBeneficios beneficios = new ClienteBeneficios(cliente);
        assertEquals(0.05, beneficios.getTaxaCashback(true), 0.001);
    }

    @Test
    public void testTaxaCashbackNaoCartaoEmpresa() {
        Cliente cliente = new Cliente("Mario", "prime", "MG", true, 0, 0);
        ClienteBeneficios beneficios = new ClienteBeneficios(cliente);
        assertEquals(0.03, beneficios.getTaxaCashback(false), 0.001);
    }

    @Test
    public void testSemBeneficios() {
        Cliente cliente = new Cliente("João", "padrao", "SP", true, 0, 0);
        ClienteBeneficios beneficios = new ClienteBeneficios(cliente);
        assertEquals(0.0, beneficios.getDesconto(), 0.001);
        assertEquals(0.0, beneficios.getDescontoFrete(), 0.001);
        assertEquals(0.0, beneficios.getTaxaCashback(true), 0.001);
    }
}
