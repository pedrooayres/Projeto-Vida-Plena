package Eventos;

import Base.Local;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Evento {
    private String nome;
    private int capacidadeMax;
    private double valor_evento;
    private String tipo;
    private Local local;
    private LocalDateTime data;
    private LocalDateTime dataCriacao;
    private List<Participante> participantes;
    private boolean comparecimento_evento;

    public Evento(String nome, Local local, int capacidadeMax, double valor_evento, String tipo, LocalDateTime data, boolean comparecimento_evento) {
        this.nome = nome;
        this.local = local;
        this.capacidadeMax = capacidadeMax;
        this.valor_evento = valor_evento;
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
    public double getValorEvento() { return valor_evento; }
    public String getNome() { return nome; }
    public LocalDateTime getData() { return data; }
    public List<Participante> getParticipantes() { return Collections.unmodifiableList(participantes); }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public boolean getComparecimentoEvento() { return comparecimento_evento; }

    // NOVO MÉTODO — compatível com o Main.java
    public LocalDateTime getDataHoraEvento() { return data; }
}
