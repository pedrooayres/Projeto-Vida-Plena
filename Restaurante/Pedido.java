package Restaurante;

import java.util.ArrayList;
import java.util.List;

import Base.Agenda;

public class Pedido {
    private List<Prato> itens = new ArrayList<>();
    private Agenda agenda_pedido;

    public void adicionarPrato(Prato prato, Agenda agenda_pedido) {
        itens.add(prato);
        this.agenda_pedido = agenda_pedido;
    }

    public List<Prato> getItens() { return itens; }
    public Agenda getAgenda() { return agenda_pedido; }

    public double calcularTotal() {
        return itens.stream().mapToDouble(Prato::getPreco).sum();
    }
}
