package Restaurante;

import Base.Pessoa;
import Eventos.Evento;
import java.time.LocalDate;
import java.util.*;

public class RestauranteRelatorios {
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

    public static Prato pratoMaisVendidoPorPeriodo(List<Pedido> listaPedidos, String string) {
        Map<Prato, Integer> contadorPratos = new HashMap<>();
        for (Pedido pedido : listaPedidos) {
            if (pedido.getAgendaPedido() == null) continue; // evita NullPointer
            int horaPedido = pedido.getAgendaPedido().getDataHora().getHour();
            boolean noPeriodo = false;
            switch (string.toLowerCase()) {
                case "manha" -> noPeriodo = (horaPedido >= 6 && horaPedido < 12);
                case "tarde" -> noPeriodo = (horaPedido >= 12 && horaPedido < 18);
                case "noite" -> noPeriodo = (horaPedido >= 18 && horaPedido < 24);
            }
            if (noPeriodo) {
                for (Prato prato : pedido.getPratos()) {
                    contadorPratos.put(prato, contadorPratos.getOrDefault(prato, 0) + 1);
                }
            }
        }
        Prato pratoMaisVendido = null;
        int maxVendas = 0;
        for (Map.Entry<Prato, Integer> entry : contadorPratos.entrySet()) {
            if (entry.getValue() > maxVendas) {
                maxVendas = entry.getValue();
                pratoMaisVendido = entry.getKey();
            }
        }
        return pratoMaisVendido;
    }
}
