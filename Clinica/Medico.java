package Clinica;

import Base.Pessoa;

public class Medico extends Pessoa {
    private String especialidade;
    private String crm;

    public Medico(String nome, String cpf, String contato, String especialidade, String crm) {
        super(nome, cpf, contato);
        this.especialidade = especialidade;
        this.crm = crm;
    }

    public String getEspecialidade() { return especialidade; }
    public String getCrm() { return crm; }

    @Override
    public String toString() {
        return super.toString() + " | Especialidade: " + especialidade + " | CRM: " + crm;
    }
}
