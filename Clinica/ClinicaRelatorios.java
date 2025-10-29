package Clinica;

import java.time.LocalDateTime;
import java.util.*;

import Base.Agenda;
import Eventos.Evento;
import Restaurante.Pedido;

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

    public static String horarioComMaisFaltas(List<Consulta> consultas) {
        Map<String, Integer> faltas = new HashMap<>();
        for (Consulta c : consultas) {
            if ("Falta".equalsIgnoreCase(c.getAgenda().getStatus())) {
                String hora = c.getAgenda().getDataHora().getHour() + "h";
                faltas.put(hora, faltas.getOrDefault(hora, 0) + 1);
            }
        }
        return faltas.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum registro de faltas");
    } 
    /* metodos respostas - clinica
     * perguntas 
     * 1 - horarios de ocupação para cada medico, 2- dia com mais falta, 3- medico mais requisito
     * 4 - qual  dia do semana tem mais atividade, 5 - renda mensal ,6 - faixa de horario mais movimentada
     *7- gasto de cada pessoa, 8 - qual data com maior numero de atividade, 9 - percentual de presença
    */
}
