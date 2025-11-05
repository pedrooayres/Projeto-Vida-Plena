package Clinica;

import java.time.LocalDateTime;

import Base.Agenda;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    public Agenda agenda_consulta;
    private double valor_consulta;
    public boolean comparecimento_consulta;
    public LocalDateTime horario;

    public Consulta(Paciente paciente, Medico medico, Agenda agenda_consulta, double valor_consulta, boolean comparecimento_consulta,LocalDateTime horario) {
        this.paciente = paciente;
        this.medico = medico;
        this.agenda_consulta = agenda_consulta;
        this.valor_consulta = valor_consulta;
        this.comparecimento_consulta = comparecimento_consulta;
        this.horario = horario;
    }
    public Medico getMedico() { return medico; }
    public Paciente getPaciente() { return paciente; }
    public Agenda getAgenda() { return agenda_consulta; }
    public double getValor() { return valor_consulta; }
    public boolean getComparecimento_Consulta() {return comparecimento_consulta; }
    public Object getHorario() {return horario;};
    }
