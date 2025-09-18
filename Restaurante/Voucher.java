package Restaurante;

public class Voucher {
    private String codigo;
    private double desconto;
    private boolean usado;

    public Voucher(String codigo, double desconto) {
        this.codigo = codigo;
        this.desconto = desconto;
        this.usado = false;
    }

    public double aplicar(double valor) {
        if (!usado) {
            usado = true;
            return valor * (1 - desconto);
        }
        return valor; 
    }

    public boolean isUsado() { return usado; }
}
