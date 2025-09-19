package Eventos;

import Base.Pessoa;

public class Participante extends Pessoa {
    public Participante(String nome, String cpf, String contato) {
        super(nome, cpf, contato);
    }
    public void inscrever(Participante p) {
    adicionarParticipante(p);
}
    @Override
    public String toString() {
        return getNome() + " (CPF: " + getCpf() + ")";
    }
}
