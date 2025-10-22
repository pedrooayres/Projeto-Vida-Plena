package Eventos;

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
     
    /* metodos respostas - eventos
     * perguntas 
     * 1, 2, 3,4,5,6,7,8,9
    */
    public static void eventoComMaisFaltas(List <Evento> eventos){
        System.out.println("Dias com maior taxa de falta no eventos :");
    }
    public static void diaComMaiorPresenca (List <Evento> eventos){
        System.out.println("Dia com maior taxa de presença : ");
    }
}
