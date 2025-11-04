package Relatorios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import Base.*;
import Clinica.*;
import Eventos.*;
import Restaurante.*;

public class RelatoriosGerais {

    protected List<Consulta> consultas;
    private List<Evento> eventos;
    private List<Prato> restaurante;

    public RelatoriosGerais(List<Consulta> consultas, List<Evento> eventos, List<Pedido> pedidos) {
        this.consultas = consultas;
        this.eventos = eventos;
        this.restaurante = restaurante;
    }

    // ---- PERGUNTA 1 ----
    // Quais médicos e eventos têm maior ocupação nos mesmo dia?
    public void relatorioChoquesMedicoEvento() {

        // Map para contar quantos choques cada médico tem
        Map<Medico, Integer> choquesPorMedico = new HashMap<>();

        // Map para contar quantos choques cada evento tem
        Map<Evento, Integer> choquesPorEvento = new HashMap<>();

        // Para cada consulta e cada evento, verificamos se acontecem no mesmo "bloco de dia"
        // Regra simples: MESMO DIA (ex.: 2025-10-26 às 14h)
        for (Consulta consulta : consultas) {

            LocalDateTime diaConsulta = consulta.getAgenda().getDataHora();
            Medico medico = consulta.getMedico();

            for (Evento evento : eventos) {

                LocalDateTime diaEvento = evento.getData();

                if (diaConsulta.toLocalDate().equals(diaEvento.toLocalDate())) {
                    // contou conflito desse médico neste dia
                    choquesPorMedico.put(
                        medico,
                        choquesPorMedico.getOrDefault(medico, 0) + 1
                    );

                    // contou conflito desse evento neste dia
                    choquesPorEvento.put(
                        evento,
                        choquesPorEvento.getOrDefault(evento, 0) + 1
                    );
                }
            }
        }

        // Agora vamos imprimir um ranking
        System.out.println("==== RELATÓRIO: Choques de dia entre clínica e eventos ====");

        // Top médicos que mais "trabalham" em horários que também têm evento
        System.out.println("\n>> Médicos com mais consultas em dias que também têm evento:");
        choquesPorMedico.entrySet().stream()
            .sorted(Map.Entry.<Medico,Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(entry -> {
                Medico m = entry.getKey();
                Integer qtd = entry.getValue();
                System.out.println("- " + m.getNome() + " | " + qtd + " conflitos de dia com eventos");
            });

        // Top eventos que mais batem com consultas
        System.out.println("\n>> Eventos que mais coincidem com consultas médicas:");
        choquesPorEvento.entrySet().stream()
            .sorted(Map.Entry.<Evento,Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(entry -> {
                Evento e = entry.getKey();
                Integer qtd = entry.getValue();

                // participantes pode ser útil pra contextualizar "ocupação"
                int inscritos = e.getTotalInscritos();

                System.out.println("- " + e.getNome()
                    + " | " + qtd + " conflitos de dia com consultas"
                    + " | " + inscritos + " inscritos");
            });

        System.out.println("\n===============================================================\n");
    }

// ---- PERGUNTA 2 ----



// ---- PERGUNTA 3 ----



// ---- PERGUNTA 4 ----



// ---- PERGUNTA 5 ----



// ---- PERGUNTA 6 ----



// ---- PERGUNTA 7 ----

// ================== RELATÓRIO: MÉDIA DE PEDIDOS NO DIA DO EVENTO ==================
    public static void mediaPedidosEvento(List<Evento> listaEventos, List<Pedido> listaPedidos, Scanner sc) {
        if (listaEventos.isEmpty() || listaPedidos.isEmpty()) {
            System.out.println("É necessário ter ao menos um evento e um pedido registrados.");
            return;
        }

        System.out.print("Digite o nome do evento gastronômico: ");
        String nomeEvento = sc.nextLine();

        // Busca o evento pelo nome informado
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

        // Garante que o evento possui uma data registrada
        if (evento.getDataHoraEvento() == null) {
            System.out.println("Evento sem data registrada.");
            return;
        }

        LocalDate dataEvento = evento.getDataHoraEvento().toLocalDate();
        List<Pedido> pedidosMesmoDia = new ArrayList<>();

        // Filtra pedidos realizados no mesmo dia do evento
        for (Pedido pedido : listaPedidos) {
            if (pedido.getAgendaPedido() == null) continue; // Evita NullPointer
            LocalDate dataPedido = pedido.getAgendaPedido().getDataHora().toLocalDate();
            if (dataPedido.isEqual(dataEvento)) { // agora compara se é o mesmo dia
                pedidosMesmoDia.add(pedido);
            }
        }

        if (pedidosMesmoDia.isEmpty()) {
            System.out.println("Nenhum pedido foi feito no dia do evento (" + dataEvento + ").");
            return;
        }

        double soma = 0;
        int totalPratos = 0;

        // Calcula a soma e quantidade total de pratos
        for (Pedido pedido : pedidosMesmoDia) {
            for (Prato prato : pedido.getPratos()) {
                soma += prato.getPreco();
                totalPratos++;
            }
        }

        double media = soma / totalPratos;

        // Exibe o relatório final
        System.out.println("\n=== Relatório: Consumo Durante o Evento ===");
        System.out.println("Evento: " + evento.getNome());
        System.out.println("Data do evento: " + dataEvento);
        System.out.println("Pedidos feitos no mesmo dia: " + pedidosMesmoDia.size());
        System.out.println("Total de pratos analisados: " + totalPratos);
        System.out.printf("Preço médio dos pedidos durante o evento: R$ %.2f%n", media);
    }

// ---- PERGUNTA 8 ----



// ---- PERGUNTA 9 ----



}
