package com.example;

import java.util.Date;
import java.util.List;

public class Venda {
    private Date dataVenda;
    private Cliente cliente;
    private ClienteBeneficios clienteBeneficios;
    private List<Produto> produtos;
    private String formaPagamento;
    private ProcessarCashback processadorCashback;
    
    public Venda(Date dataVenda, Cliente cliente, List<Produto> produtos, String formaPagamento) {
        this.dataVenda = dataVenda;
        this.cliente = cliente;
        this.clienteBeneficios = new ClienteBeneficios(cliente);
        this.produtos = produtos;
        this.formaPagamento = formaPagamento;
        this.processadorCashback = new ProcessarCashback(cliente);
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double calcularTotalProdutos(){
        double valorTotal = 0.0;
        for(Produto produto : produtos){
            valorTotal += produto.getValor();
        }
        cliente.setTotalComprasMensais(cliente.getTotalComprasMensais() + valorTotal);
        return valorTotal;
    }

    public double calcularTotalImpostos(){
        double totalICMS = 0.0;
        double totalMunicipal = 0.0;
        for(Produto produto : produtos){
            if(cliente.getEstado().equals("DF")){
                totalICMS += (0.18 * produto.getValor());
            }else{
                totalICMS += (0.12 * produto.getValor());
                totalMunicipal += (0.04 * produto.getValor());
            }
        }

        return totalICMS + totalMunicipal;
    }

    public double calcularFrete() {
        if (cliente.getTipo().equals("prime")) return 0.0;
    
        double valorFrete = calcularValorFrete(cliente.getEstado(), cliente.isCapital());
        valorFrete -= (valorFrete * clienteBeneficios.getDescontoFrete());
    
        return valorFrete;
    }
    
    /*
     * Antes, o método calcularFrete() estava muito grande porque a lógica que definia o valor do frete, baseada no estado do cliente e se era capital ou não, estava dentro de calcularFrete(). 
     * Com a refatoração 'Extrair Método', foi possível retirar essa lógica de calcularFrete() e criar um método específico para essa lógica, calcularValorFrete(), que será chamado dentro de calcularFrete().
     * Benefícios:
     * Código mais legível
     * Facilita a manutenção do código
     * Isolamento de partes independentes do código
     * Diminuição da duplicação, caso o método calcularValorFrete() possa ser utilizado em outros lugares
    */
    private double calcularValorFrete(String estado, boolean isCapital) {
        switch (estado) {
            case "DF":
                return 5.0;
            case "C0":
                return isCapital ? 10.0 : 13.0;
            case "NE":
            case "N":
                return isCapital ? 15.0 : 18.0;
            case "SE":
                return isCapital ? 7.0 : 10.0;
            case "S":
                return isCapital ? 10.0 : 13.0;
            default:
                return 0.0;
        }
    }

    public double calcularDesconto(){
        double totalDesconto = 0.0;
        double totalProdutos = calcularTotalProdutos();

        if(cliente.getTipo().equals("especial")){
            totalDesconto = totalProdutos * 0.1;
            if(formaPagamento.equals("cartao loja")){
                totalDesconto += (totalProdutos - totalDesconto ) * 0.1;
            } 
        }
        return totalDesconto;
    }

    public double calcularCashback(){
        double totalCashback = 0.0;
        totalCashback = calcularTotalProdutos() * clienteBeneficios.getTaxaCashback(formaPagamento.equals("cartao loja"));
        cliente.setCashback(cliente.getCashback() + totalCashback);
        return totalCashback;
    }

    public double aplicarCashback(double valorCompra) {
        return processadorCashback.aplicarCashback(valorCompra);
    }

    public double valorTotal() {
        double totalProdutos = calcularTotalProdutos();
        double totalImpostos = calcularTotalImpostos();
        double totalDesconto = calcularDesconto();
        double valorFrete = calcularFrete();

        double totalSemCashback = totalProdutos - totalDesconto + totalImpostos + valorFrete;
        double totalComCashback = aplicarCashback(totalSemCashback);

        return totalComCashback;
    }
}
