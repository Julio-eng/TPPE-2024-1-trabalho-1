package com.example;

public class Cliente {
    private String nome;
    private String tipo;
    private String estado;
    private boolean isCapital;
    private double totalComprasMensais;
    private double cashback;
    
    public Cliente(String nome, String tipo, String estado, boolean isCapital, double totalComprasMensais,
            double cashback) {
        this.nome = nome;
        this.tipo = tipo;
        this.estado = estado;
        this.isCapital = isCapital;
        this.totalComprasMensais = totalComprasMensais;
        this.cashback = cashback;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean isCapital) {
        this.isCapital = isCapital;
    }

    public double getTotalComprasMensais() {
        return totalComprasMensais;
    }

    public void setTotalComprasMensais(double totalComprasMensais) {
        this.totalComprasMensais = totalComprasMensais;
    }

    public double getCashback() {
        return cashback;
    }

    public void setCashback(double cashback) {
        this.cashback = cashback;
    }
}
