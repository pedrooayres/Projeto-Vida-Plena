package Restaurante;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Prato> itens = new ArrayList<>();

    public void adicionarPrato(Prato prato) {
        itens.add(prato);
    }

    public List<Prato> getItens() { return itens; }

    public double calcularTotal() {
        return itens.stream().mapToDouble(Prato::getPreco).sum();
    }
}
