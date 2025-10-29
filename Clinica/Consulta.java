package Clinica;

import Base.Agenda;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private Agenda agenda_consulta;
    private double valor_consulta;
    private int comparecimento_consulta;

    public Consulta(Paciente paciente, Medico medico, Agenda agenda_consulta, double valor_consulta, int comparecimento_consulta) {
        this.paciente = paciente;
        this.medico = medico;
        this.agenda_consulta = agenda_consulta;
        this.valor_consulta = valor_consulta;
        this.comparecimento_consulta = comparecimento_consulta;
    }

    public Medico getMedico() { return medico; }
    public Paciente getPaciente() { return paciente; }
    public Agenda getAgenda() { return agenda_consulta; }
    public double getValor() { return valor_consulta; }
    public int getComparecimento_Consulta() {return comparecimento_consulta; }
}
