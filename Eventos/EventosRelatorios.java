package Eventos;

import java.time.LocalDateTime;
import java.util.*;

public class EventosRelatorios {
    public static Evento eventoLotacaoMaisRapida(List<Evento> eventos) {
        return eventos.stream()
                .filter(e -> e.getTotalInscritos() >= e.getCapacidadeMax())
                .min(Comparator.comparing(Evento::getDataCriacao))
                .orElse(null);
    }
    public static Evento eventoComMaisInscritos(List<Evento> eventos) {
        return eventos.stream()
                .max(Comparator.comparingInt(Evento::getTotalInscritos))
                .orElse(null);
    }

     public static String horarioComMaisFaltasEventos(List<Evento> eventos) {
        // Mapa que conta quantas faltas aconteceram em cada data/hora
        Map<LocalDateTime, Integer> contadorFaltasPorHorario = new HashMap<>();

        for (Evento e : eventos) {

            if (!e.getComparecimentoEvento()) { // assumindo false = faltou
                LocalDateTime dataHora = e.getData(); // <- AQUI o ajuste

                int atual = contadorFaltasPorHorario.getOrDefault(dataHora, 0);
                contadorFaltasPorHorario.put(dataHora, atual + 1);
            }
        }

        if (contadorFaltasPorHorario.isEmpty()) {
            return "Nenhuma falta registrada em eventos.";
        }

        // Descobrir qual horário teve mais faltas
        LocalDateTime horarioMaisFaltas = null;
        int maxFaltas = 0;

        for (Map.Entry<LocalDateTime, Integer> entry : contadorFaltasPorHorario.entrySet()) {
            if (entry.getValue() > maxFaltas) {
                maxFaltas = entry.getValue();
                horarioMaisFaltas = entry.getKey();
            }
        }

        return String.format(
            "Horário com mais faltas em evento: %02d/%02d/%04d %02d:%02d - %d falta(s)",
            horarioMaisFaltas.getDayOfMonth(),
            horarioMaisFaltas.getMonthValue(),
            horarioMaisFaltas.getYear(),
            horarioMaisFaltas.getHour(),
            horarioMaisFaltas.getMinute(),
            maxFaltas
        );
    }
    public static void eventoComMaisFaltas(List <Evento> eventos){
        System.out.println("Dias com maior taxa de falta no eventos :");
    }
    public static void diaComMaiorPresenca (List <Evento> eventos){
        System.out.println("Dia com maior taxa de presença : ");
    }
}
