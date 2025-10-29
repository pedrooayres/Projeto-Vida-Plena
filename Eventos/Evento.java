package Eventos;

import Base.Local;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nome;
    private int capacidadeMax;
    private String tipo;
    private Local local;
    private LocalDateTime data;
    private LocalDateTime dataCriacao;
    private List<Participante> participantes;
    private int comparecimento_evento;

    public Evento(String nome, Local local, int capacidadeMax, String tipo, LocalDateTime data, int comparecimento_evento) {
        this.nome = nome;
        this.local = local;
        this.capacidadeMax = capacidadeMax;
        this.tipo = tipo;
        this.data = data;
        this.participantes = new ArrayList<>();
        this.dataCriacao = LocalDateTime.now();
        this.comparecimento_evento = comparecimento_evento;
    }

    public void inscrever(Participante p) {
        if (participantes.size() < capacidadeMax) {
            participantes.add(p);
        } else {
            System.out.println("Evento lotado, não é possível inscrever: " + p.getNome());
        }
    }

    public int getTotalInscritos() { return participantes.size(); }
    public int getCapacidadeMax() { return capacidadeMax; }
    public String getNome() { return nome; }
    public LocalDateTime getData() { return data; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public int getComparecimentoEvento() { return comparecimento_evento; }
}
