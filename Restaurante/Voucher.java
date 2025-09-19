package Restaurante;

public class Voucher {
    private String codigo;
    private double desconto;

    public Voucher(String codigo, double desconto) {
        this.codigo = codigo;
        this.desconto = desconto;
    }

    public double aplicar(double valor) {
        return valor - (valor * desconto);
    }
}
