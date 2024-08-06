package com.example;

public class ClienteBeneficios {
    private Cliente cliente;

    public ClienteBeneficios(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getDesconto() {
        if (cliente.getTipo().equals("especial")) return 0.1;
        return 0.0;
    }

    public double getDescontoFrete() {
        if (cliente.getTipo().equals("especial")) return 0.3;
        return 0.0;
    }

    public double getTaxaCashback(boolean isCartaoEmpresa) {
        if (cliente.getTipo().equals("prime")) {
            return isCartaoEmpresa ? 0.05 : 0.03;
        }
        return 0.0;
    }
}
