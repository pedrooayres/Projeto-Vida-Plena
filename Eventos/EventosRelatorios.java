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

            // IMPORTANTE:
            // Se getComparecimentoEvento() == true significa que a pessoa COMPARECEU,
            // então falta é !true (== false). Nesse caso usamos "!e.getComparecimentoEvento()".
            //
            // Se no teu código true quer dizer "FALTOU", então troca a linha abaixo para:
            // if (e.getComparecimentoEvento()) { ... }

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
    /* metodos respostas - eventos
    1- horarios de ocupação para cada evento, 2 - dia com mais falta, 3- evento com mais participantes
    4- dia da com maior concentração de eventos, 5- renda mensal, 6- qual faixa de horario mais movimentada
    7- gasto de cada pessoa, 8-qual data com maior numero de atividade, 9- percentual de presença
    */
    public static void eventoComMaisFaltas(List <Evento> eventos){
        System.out.println("Dias com maior taxa de falta no eventos :");
    }
    public static void diaComMaiorPresenca (List <Evento> eventos){
        System.out.println("Dia com maior taxa de presença : ");
    }
}
