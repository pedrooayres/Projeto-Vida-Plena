package Restaurante;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Prato> itens;

    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public void adicionarPrato(Prato prato) {
        itens.add(prato);
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(Prato::getPreco).sum();
    }

    public List<Prato> getItens() {
        return itens;
    }
    
    @Override
    public String toString() {
        return "Pedido com " + itens.size() + " itens, total: R$" + calcularTotal();
    }
}
