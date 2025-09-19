package Eventos;
import java.util.*;;

class EventosRelatorios  {
     public static Evento eventoLotacaoMaisRapida(List<Evento> eventos) {
        return eventos.stream()
                .filter(e -> e.getTotalInscritos() >= e.getCapacidadeMax())
                .min(Comparator.comparing(Evento::getDataCriacao)) // precisa ter getDataCriacao() em Evento
                .orElse(null);
    }
    public static Evento eventoComMaisInscritos(List<Evento> eventos) {
        return eventos.stream()
                .max(Comparator.comparingInt(Evento::getTotalInscritos))
                .orElse(null);
    }
}