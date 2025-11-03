package Restaurante;

import Base.Pessoa;
import Eventos.Evento;
import java.time.LocalDate;
import java.util.*;

public class RestauranteRelatorios {

    // ================== RELATÓRIO: PRATO MAIS VENDIDO ==================
    public static Prato pratoMaisVendidoPorPeriodo(List<Pedido> pedidos, String periodo) {
        Map<Prato, Integer> contador = new HashMap<>();

        for (Pedido p : pedidos) {
            for (Prato prato : p.getItens()) {
                contador.put(prato, contador.getOrDefault(prato, 0) + 1);
            }
        }

        return contador.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // ================== RELATÓRIO: CLIENTE QUE MAIS CONSUMIU ==================
    public static Pessoa clienteMaisConsumiu(Map<Pessoa, List<Pedido>> pedidosPorCliente) {
        return pedidosPorCliente.entrySet().stream()
                .max(Comparator.comparingDouble(e -> e.getValue().stream()
                        .mapToDouble(Pedido::calcularTotal).sum()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // ================== RELATÓRIO: FAIXA DE HORÁRIO COM MAIS CLIENTES ==================
    public static void faixaComMaisClientes(List<Pedido> pedidos) {
        System.out.println("Faixa de hora com mais clientes (ainda não implementada).");
    }

    // ================== RELATÓRIO: MÉDIA DE PEDIDOS APÓS UM EVENTO ==================
    public static void mediaPedidosEvento(List<Evento> listaEventos, List<Pedido> listaPedidos, Scanner sc) {
        if (listaEventos.isEmpty() || listaPedidos.isEmpty()) {
            System.out.println("É necessário ter ao menos um evento e um pedido registrados.");
            return;
        }

        System.out.print("Digite o nome do evento gastronômico: ");
        String nomeEvento = sc.nextLine();

        // 🔹 Busca o evento pelo nome (sem depender do Main)
        Evento evento = null;
        for (Evento e : listaEventos) {
            if (e.getNome().equalsIgnoreCase(nomeEvento)) {
                evento = e;
                break;
            }
        }

        if (evento == null) {
            System.out.println("Evento não encontrado.");
            return;
        }

        // 🔹 Garante que o evento tem data antes de acessar
        if (evento.getDataHoraEvento() == null) {
            System.out.println("Evento sem data registrada.");
            return;
        }

        LocalDate dataEvento = evento.getDataHoraEvento().toLocalDate();
        List<Pedido> pedidosPosteriores = new ArrayList<>();

        // 🔹 Filtra os pedidos realizados após o evento
        for (Pedido pedido : listaPedidos) {
            if (pedido.getAgendaPedido() == null) continue; // evita NullPointer
            LocalDate dataPedido = pedido.getAgendaPedido().getDataHora().toLocalDate();
            if (dataPedido.isAfter(dataEvento)) {
                pedidosPosteriores.add(pedido);
            }
        }

        if (pedidosPosteriores.isEmpty()) {
            System.out.println("Nenhum pedido foi feito após o evento (" + dataEvento + ").");
            return;
        }

        double soma = 0;
        int totalPratos = 0;

        for (Pedido pedido : pedidosPosteriores) {
            for (Prato prato : pedido.getPratos()) {
                soma += prato.getPreco();
                totalPratos++;
            }
        }

        double media = soma / totalPratos;

        System.out.println("\n=== Relatório: Impacto Pós-Evento ===");
        System.out.println("Evento: " + evento.getNome());
        System.out.println("Data do evento: " + dataEvento);
        System.out.println("Pedidos feitos após o evento: " + pedidosPosteriores.size());
        System.out.println("Total de pratos analisados: " + totalPratos);
        System.out.printf("Preço médio dos pedidos após o evento: R$ %.2f%n", media);
    }
}
