package Eventos;

import java.util.*;

public class EventosRelatorios  {
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
}
