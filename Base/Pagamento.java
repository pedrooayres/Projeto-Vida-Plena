package Base;

public class Pagamento {
    private double valor;
    private String metodo; // cartão, dinheiro, pix
    private String status; // pago, pendente, cancelado

    public Pagamento(double valor, String metodo, String status) {
        this.valor = valor;
        this.metodo = metodo;
        this.status = status;
    }

    public double getValor() { return valor; }
    public String getMetodo() { return metodo; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Pagamento de R$" + valor + " via " + metodo + " [" + status + "]";
    }
}
