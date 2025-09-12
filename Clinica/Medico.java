package Clinica;

import Base.Pessoa;

public class Medico extends Pessoa {
    private String especialidade;

    public Medico(String nome, String cpf, String contato, String especialidade) {
        super(nome, cpf, contato);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() { return especialidade; }

    @Override
    public String toString() {
        return "Dr(a). " + getNome() + " - " + especialidade;
    }
}
