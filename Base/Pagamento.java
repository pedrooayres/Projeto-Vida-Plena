package Base;

public class Pagamento {
    private double valor;
    private String forma;
    private String status;

    public Pagamento(double valor, String forma, String status) {
        this.valor = valor;
        this.forma = forma;
        this.status = status;
    }

    public double getValor() { return valor; }
    public String getForma() { return forma; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "R$ " + valor + " - " + forma + " (" + status + ")";
    }
}
