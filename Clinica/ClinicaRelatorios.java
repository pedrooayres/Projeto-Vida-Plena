package Clinica;

import java.util.*;

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
}
