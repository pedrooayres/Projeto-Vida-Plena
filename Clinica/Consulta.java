package Clinica;

import Base.Agenda;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private Agenda agenda;

    public Consulta(Paciente paciente, Medico medico, Agenda agenda) {
        this.paciente = paciente;
        this.medico = medico;
        this.agenda = agenda;
    }

    public void exibirDetalhes() {
        System.out.println("Consulta: " + paciente.getNome() +
                " com " + medico.toString() +
                " em " + agenda.getDataHora() +
                " no local " + agenda.getLocal().getNome());
    }
}