package com.example;

/*
 * A refatoração "Substituir Método por Objeto-Método" foi aplicada para melhorar a organização e encapsulamento da lógica de aplicação de cashback.
 *
 * Antes da refatoração, a lógica de cashback estava diretamente no método aplicarCashback da classe Venda. Isso fazia com que o método fosse responsável por várias tarefas, violando o princípio da responsabilidade única.
 *
 * Após a refatoração:
 * Criação da Classe CashbackProcessor: A lógica de cálculo de cashback foi movida para uma nova classe CashbackProcessor, que é responsável por aplicar o cashback. Isso melhora a modularidade e a organização do código.
 * Delegação de Responsabilidade: A classe Venda agora delega a tarefa de aplicar o cashback para a instância de CashbackProcessor. Isso torna a classe Venda mais focada em sua responsabilidade principal e permite que a lógica de cashback seja alterada independentemente.
 * Melhoria na Manutenibilidade: Com a lógica de cashback encapsulada em uma classe separada, futuras mudanças na forma como o cashback é aplicado podem ser feitas sem afetar diretamente a classe Venda.
 * Facilita o Teste: A classe CashbackProcessor pode ser testada isoladamente, o que facilita a criação de testes unitários e a validação da lógica de cashback de forma independente.
 */

public class ProcessarCashback {
    private Cliente cliente;
    private double cashbackAtual;

    public ProcessarCashback(Cliente cliente) {
        this.cliente = cliente;
        this.cashbackAtual = cliente.getCashback();
    }

    public double aplicarCashback(double valorCompra) {
        if (!cliente.getTipo().equals("prime")) return valorCompra;

        if (cashbackAtual > valorCompra) {
            cliente.setCashback(cashbackAtual - valorCompra);
            return 0.0;
        }
            
        cliente.setCashback(0);
        return valorCompra - cashbackAtual;
    }
}
