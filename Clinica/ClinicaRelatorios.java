package Clinica;

import java.time.LocalDateTime;
import java.util.*;

import Base.Agenda;   

public class ClinicaRelatorios {

    public static Medico medicoMaisOcupado(List<Consulta> consultas) {
        Map<Medico, Integer> contador = new HashMap<>();
        for (Consulta c : consultas) {
            contador.put(c.getMedico(), contador.getOrDefault(c.getMedico(), 0) + 1);
        }
        return contador.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static String horarioComMaisFaltasConsultas(List<Consulta> consultas) {
    // Mapa: horário -> quantas faltas nesse horário
    Map<LocalDateTime, Integer> contadorFaltasPorHorario = new HashMap<>();

    for (Consulta c : consultas) {
        // AQUI: defina o que é "faltou"
        // Se no teu código comparecimento_consulta == true significa FALTOU,
        // troca a condição abaixo para: if (c.getComparecimento_Consulta())
        if (!c.getComparecimento_Consulta()) { // supondo false = faltou
            LocalDateTime dataHora = c.getAgenda().getDataHora();

            int atual = contadorFaltasPorHorario.getOrDefault(dataHora, 0);
            contadorFaltasPorHorario.put(dataHora, atual + 1);
        }
    }

    if (contadorFaltasPorHorario.isEmpty()) {
        return "Nenhuma falta registrada em clinica.";
    }

    // descobrir qual horário teve mais faltas (a 'moda')
    LocalDateTime horarioMaisFaltas = null;
    int maxFaltas = 0;

    for (Map.Entry<LocalDateTime, Integer> entry : contadorFaltasPorHorario.entrySet()) {
        if (entry.getValue() > maxFaltas) {
            maxFaltas = entry.getValue();
            horarioMaisFaltas = entry.getKey();
        }
    }

    // montar a string bonitinha pra imprimir no menu
    return String.format(
        "Horário com mais faltas consulta: %02d/%02d/%04d %02d:%02d - %d falta(s)",
        horarioMaisFaltas.getDayOfMonth(),
        horarioMaisFaltas.getMonthValue(),
        horarioMaisFaltas.getYear(),
        horarioMaisFaltas.getHour(),
        horarioMaisFaltas.getMinute(),
        maxFaltas
    );
}
    /* metodos respostas - clinica
     * perguntas 
     * 1 - horarios de ocupação para cada medico, 2- dia com mais falta, 3- medico mais requisito
     * 4 - qual  dia do semana tem mais atividade, 5 - renda mensal ,6 - faixa de horario mais movimentada
     *7- gasto de cada pessoa, 8 - qual data com maior numero de atividade, 9 - percentual de presença
    */
}
