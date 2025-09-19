package Restaurante;

public class Prato {
    private String nome;
    private double preco;
    private String categoria;
    private int calorias;

    public Prato(String nome, double preco, String categoria, int calorias) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.calorias = calorias;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getCategoria() { return categoria; }
    public int getCalorias() { return calorias; }
}
