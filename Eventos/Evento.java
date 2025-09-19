package Eventos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nome;
    private int capacidadeMax;
    private List<Participante> participantes;
    private LocalDateTime dataCriacao;


    public Evento(String nome, int capacidadeMax) {
    this.nome = nome;
    this.capacidadeMax = capacidadeMax;
    this.participantes = new ArrayList<>();
    this.dataCriacao = LocalDateTime.now();
}

    public void adicionarParticipante(Participante p) {
        if (participantes.size() < capacidadeMax) {
            participantes.add(p);
        } else {
            System.out.println("Evento lotado, não é possível inscrever: " + p.getNome());
        }
    }

    public int getTotalInscritos() {
        return participantes.size();
    }

    public int getCapacidadeMax() {
        return capacidadeMax;
    }

    public String getNome() {
        return nome;
    }
    public LocalDateTime getDataCriacao() {
    return dataCriacao;
}

    public List<Participante> getParticipantes() {
        return participantes;
    }

    @Override
    public String toString() {
        return "Evento: " + nome + " | Capacidade: " + capacidadeMax + 
               " | Inscritos: " + participantes.size();
    }
}
