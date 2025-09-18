package Eventos;

import Base.Local;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nome;
    private Local local;
    private int capacidadeMax;
    private String tipo;
    private LocalDateTime dataHora;
    private List<Participante> inscritos;

    public Evento(String nome, Local local, int capacidadeMax, String tipo, LocalDateTime dataHora) {
        this.nome = nome;
        this.local = local;
        this.capacidadeMax = capacidadeMax;
        this.tipo = tipo;
        this.dataHora = dataHora;
        this.inscritos = new ArrayList<>();
    }

    public boolean inscrever(Participante p) {
        if (inscritos.size() < capacidadeMax) {
            inscritos.add(p);
            return true;
        }
        return false;
    }

    public void cancelarInscricao(Participante p) {
        inscritos.remove(p);
    }

    @Override
    public String toString() {
        return "Evento: " + nome + " (" + tipo + ") em " + dataHora +
               " no " + local.getNome() + " [" + inscritos.size() + "/" + capacidadeMax + "]";
    }
}
