package Restaurante;

import Base.Agenda;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Prato> itens = new ArrayList<>();
    private Agenda agenda_pedido;

    public void adicionarPrato(Prato prato, Agenda agenda_pedido) {
        itens.add(prato);
        this.agenda_pedido = agenda_pedido;
    }

    public List<Prato> getItens() {
        return itens;
    }

    public Agenda getAgenda() {
        return agenda_pedido;
    }

    // 🔹 Alias para compatibilidade com Main.java
    public List<Prato> getPratos() {
        return getItens();
    }

    // 🔹 Alias para compatibilidade com Main.java
    public Agenda getAgendaPedido() {
        return getAgenda();
    }

    // ✅ Versão corrigida com loop for
    public double calcularTotal() {
        double total = 0.0;
        for (Prato p : itens) {
            total += p.getPreco();
        }
        return total;
    }
}
