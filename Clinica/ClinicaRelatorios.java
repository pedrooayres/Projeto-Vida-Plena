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
   public static void medicosMaisOcupados(List<Consulta> consultas) {
        System.out.println("Médicos  com maior ocupação no horario (HH:MM):");
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
     /* public static void tipoServicoMaisLucrativo(List<Consulta> consultas, List<Evento> eventos, List<Pedido> pedidos) {
        System.out.println("5️⃣ Tipo de serviço com maior receita mensal:");
        // Somar valores de pagamento em cada lista
    } 
         public static void datasComMaisAtividades(List<Consulta> consultas, List<Evento> eventos, List<Pedido> pedidos) {
        System.out.println("8️⃣ Datas com maior volume de atividades simultâneas:");
        // Comparar datas das agendas e contar quantas ocorrem no mesmo dia
    }
        public static void percentualComparecimento(List<Consulta> consultas, List<Evento> eventos, List<Pedido> pedidos) {
        System.out.println("9️⃣ Percentual de comparecimento geral:");
        // Calcular total de agendados x realizados (simulado)
    }*/
}
