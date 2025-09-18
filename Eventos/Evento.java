package Eventos;

import Base.Local;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nome;
    private Local local;
    private int capacidadeMaxima;
    private List<Participante> inscritos;

    public Evento(String nome, Local local, int capacidadeMaxima) {
        this.nome = nome;
        this.local = local;
        this.capacidadeMaxima = capacidadeMaxima;
        this.inscritos = new ArrayList<>();
    }

    public boolean inscrever(Participante p) {
        if (inscritos.size() < capacidadeMaxima) {
            inscritos.add(p);
            return true;
        }
        return false;
    }

    public String getNome() { return nome; }
    public int getTotalInscritos() { return inscritos.size(); }

    @Override
    public String toString() {
        return "Evento: " + nome + " no " + local.getNome() + " [" + inscritos.size() + "/" + capacidadeMaxima + "]";
    }
}
