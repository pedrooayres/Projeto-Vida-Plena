package Restaurante;

import Base.Agenda;
import java.util.*;

public class Pedido {
    private List<Prato> pratos;
    private Agenda agendaPedido;

    public Pedido() { this.pratos = new ArrayList<>(); this.agendaPedido=null; }
    public void adicionarPrato(Prato p, Agenda a) { if(p!=null) pratos.add(p); if(a!=null) this.agendaPedido=a; }
    public List<Prato> getPratos() { return pratos; }
    public List<Prato> getItens() { return pratos; } // compatibilidade
    public Agenda getAgendaPedido() { return agendaPedido; }
    public java.time.LocalDateTime getDataHora() { return agendaPedido==null?null:agendaPedido.getDataHora(); }
    public double calcularTotal() { return pratos.stream().mapToDouble(Prato::getPreco).sum(); }
    public double getValorTotal() { return calcularTotal(); }
}
