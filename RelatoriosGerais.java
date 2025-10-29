package Relatorios;

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
}