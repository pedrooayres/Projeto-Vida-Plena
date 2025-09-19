package Eventos;

import Base.Pessoa;

public class Participante extends Pessoa {
    public Participante(String nome, String cpf, String contato) {
        super(nome, cpf, contato);
    }

    @Override
    public String toString() {
        return getNome() + " (CPF: " + getCpf() + ")";
    }
}
