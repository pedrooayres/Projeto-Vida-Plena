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
