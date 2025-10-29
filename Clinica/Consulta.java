package Clinica;

import Base.Agenda;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private Agenda agenda;
    private double valor;
    private int comparecimento_consulta;

    public Consulta(Paciente paciente, Medico medico, Agenda agenda, double valor, int comparecimento_consulta) {
        this.paciente = paciente;
        this.medico = medico;
        this.agenda = agenda;
        this.valor = valor;
        this.comparecimento_consulta = comparecimento_consulta;
    }

    public Medico getMedico() { return medico; }
    public Paciente getPaciente() { return paciente; }
    public Agenda getAgenda() { return agenda; }
    public double getValor() { return valor; }
    public int getComparecimento_Consulta() {return comparecimento_consulta; }
}
