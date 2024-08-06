# Trabalho 1: TDD

Este é um projeto Java Maven que utiliza Test Driven Development (TDD) para implementar uma aplicação de comércio varejista.

## Pré-requisitos

- JDK 11 ou superior
- Apache Maven 3.6.0 ou superior

## Instalação

1. Para compilar o projeto, execute o seguinte comando na raiz do diretório do projeto:

   ```bash
   mvn clean install

2. Para rodar os testes do projeto, execute o seguinte comando:

   ```bash
   mvn clean install

# Trabalho 2: Refatoração

**Obs**: Os comentários também estão presentes no código

## Extrair método - Venda::calcularFrete()
Antes, o método `calcularFrete()` estava muito grande porque a lógica que definia o valor do frete, baseada no estado do cliente e se era capital ou não, estava dentro de dele. Com a refatoração "Extrair Método", foi possível retirar essa lógica de `calcularFrete()` e criar um método específico para essa lógica, `calcularValorFrete()`, que será chamado dentro de `calcularFrete()`.

Benefícios:
- Código mais legível
- Facilita a manutenção do código
- Isolamento de partes independentes do código
- Diminuição da duplicação, caso o método `calcularValorFrete()` possa ser utilizado em outros lugares

## Extrair classe - Cliente

A refatoração "Extrair Classe" foi aplicada para separar as responsabilidades da classe `Cliente`. Antes, a classe `Cliente` tinha métodos para calcular descontos, frete e cashback, o que aumentava sua complexidade e quebrava o princípio da responsabilidade única.

Após a refatoração, esses métodos foram movidos para uma nova classe `ClienteBeneficios`. Agora, a classe `Cliente` é responsável apenas por armazenar os dados do cliente, enquanto `ClienteBeneficios` lida com as regras de negócio associadas aos benefícios do cliente.

Benefícios da refatoração:
- Código mais organizado e legível: Cada classe tem uma responsabilidade clara, tornando o código mais fácil de entender e manter.
- Facilita a manutenção: Alterações nas regras de negócio podem ser feitas na classe ClienteBeneficios sem afetar a classe `Cliente`.
- Isolamento de funcionalidades: As funcionalidades de cálculo de benefícios estão isoladas, o que facilita a reutilização e teste unitário.
- Redução da complexidade da classe Cliente: A classe Cliente agora é mais simples e focada apenas em representar os dados do cliente.

## Substituir método por objeto-método - Venda::aplicarCashback()

A refatoração "Substituir Método por Objeto-Método" foi aplicada para melhorar a organização e encapsulamento da lógica de aplicação de cashback.

Antes da refatoração, a lógica de cashback estava diretamente no método aplicarCashback da classe Venda. Isso fazia com que o método fosse responsável por várias tarefas, violando o princípio da responsabilidade única.

Após a refatoração:
- Criação da Classe CashbackProcessor: A lógica de cálculo de cashback foi movida para uma nova classe CashbackProcessor, que é responsável por aplicar o cashback. Isso melhora a modularidade e a organização do código.
- Delegação de Responsabilidade: A classe Venda agora delega a tarefa de aplicar o cashback para a instância de CashbackProcessor. Isso torna a classe Venda mais focada em sua responsabilidade principal e permite que a lógica de cashback seja alterada independentemente.
- Melhoria na Manutenibilidade: Com a lógica de cashback encapsulada em uma classe separada, futuras mudanças na forma como o cashback é aplicado podem ser feitas sem afetar diretamente a classe Venda.
- Facilita o Teste: A classe CashbackProcessor pode ser testada isoladamente, o que facilita a criação de testes unitários e a validação da lógica de cashback de forma independente.

