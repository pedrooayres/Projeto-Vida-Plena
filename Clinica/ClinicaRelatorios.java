package Clinica;

import java.util.*;

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
     * 1, 2, 3,4,5,6,8,9
    */
   public static void medicosMaisOcupados(List<Consulta> consultas) {
        System.out.println("Médicos  com maior ocupação no horario :");
    }
         public static void diaComMaisFaltas(List<Consulta> consultas) {
        System.out.println("Dia com mais faltas na clínica :");
    }
        public static void medicoMaisRequisitados(List<Consulta> consultas) {
        System.out.println("Médicos mais requisitados:");
    } 
         public static void diasMaisMovimentados(List<Consulta> consultas) {
        System.out.println("Dias da semana com mais consultas:");
    }
    public static void percentalDeComparecimento(List <Consulta> consultas){
        System.out.println("Percentual de Presença : ");
    }
}
