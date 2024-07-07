package com.example;

import java.util.Date;
import java.util.List;

public class Venda {
    private Date dataVenda;
    private Cliente cliente;
    private List<Produto> produtos;
    private String formaPagamento;
    
    public Venda(Date dataVenda, Cliente cliente, List<Produto> produtos, String formaPagamento) {
        this.dataVenda = dataVenda;
        this.cliente = cliente;
        this.produtos = produtos;
        this.formaPagamento = formaPagamento;
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

    public double calcularFrete(){
        if(cliente.getTipo().equals("prime")) return 0.0;

        double valorFrete = 0.0;
        String estado = cliente.getEstado();

        if(estado.equals("DF")){
            valorFrete = 5.0;
        } else if(estado.equals("C0") && cliente.isCapital()){
            valorFrete = 10.0;
        } else if(estado.equals("C0") && !cliente.isCapital()){
            valorFrete = 13.0;
        } else if(estado.equals("NE") && cliente.isCapital()){
            valorFrete = 15.0;
        } else if(estado.equals("NE") && !cliente.isCapital()){
            valorFrete = 18.0;
        } else if(estado.equals("N") && cliente.isCapital()){
            valorFrete = 15.0;
        } else if(estado.equals("N") && !cliente.isCapital()){
            valorFrete = 18.0;
        } else if(estado.equals("SE") && cliente.isCapital()){
            valorFrete = 7.0;
        } else if(estado.equals("SE") && !cliente.isCapital()){
            valorFrete = 10.0;
        } else if(estado.equals("S") && cliente.isCapital()){
            valorFrete = 10.0;
        } else if(estado.equals("S") && !cliente.isCapital()){
            valorFrete = 13.0;
        }

        valorFrete -= (valorFrete * cliente.getDiscontoFrete());

        return valorFrete;
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
        totalCashback = calcularTotalProdutos() * cliente.getTaxaCashback(formaPagamento.equals("cartao loja"));
        cliente.setCashback(cliente.getCashback() + totalCashback);
        return totalCashback;
    }

    public double aplicarCashback(double valorCompra) {
        if (cliente.getTipo().equals("prime")) {
            double cashback = cliente.getCashback();
            if (cashback > valorCompra) {
                cliente.setCashback(cashback - valorCompra);
                return 0.0;
            } else {
                cliente.setCashback(0);
                return valorCompra - cashback;
            }
        }
        return valorCompra;
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
