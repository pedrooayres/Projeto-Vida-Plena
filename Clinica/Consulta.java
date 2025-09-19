package Clinica;

import Base.Agenda;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private Agenda agenda;
    private double valorConsulta;

    public Consulta(Paciente paciente, Medico medico, Agenda agenda,double valorConsulta) {
        this.paciente = paciente;
        this.medico = medico;
        this.agenda = agenda;
    }
    public Medico getMedico() {
        return medico;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void exibirDetalhes() {
        System.out.println("Consulta: " + paciente.getNome() +
                " com " + medico.toString() +
                " em " + agenda.getDataHora() +
                " no local " + agenda.getLocal().getNome());
    }
}