package Clinica;

import Base.Agenda;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private Agenda agenda;
    private double valor;

    public Consulta(Paciente paciente, Medico medico, Agenda agenda, double valor) {
        this.paciente = paciente;
        this.medico = medico;
        this.agenda = agenda;
        this.valor = valor;
    }

    public Medico getMedico() { return medico; }
    public Paciente getPaciente() { return paciente; }
    public Agenda getAgenda() { return agenda; }
    public double getValor() { return valor; }
}
