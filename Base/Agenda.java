package Base;

import java.time.LocalDateTime;

public class Agenda {
    private LocalDateTime dataHora;
    private Local local;
    private String status; // agendado, concluído, cancelado

    public Agenda(LocalDateTime dataHora, Local local, String status) {
        this.dataHora = dataHora;
        this.local = local;
        this.status = status;
    }

    public LocalDateTime getDataHora() { return dataHora; }
    public Local getLocal() { return local; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Agendado em " + dataHora + " no local " + local.getNome() + " (" + status + ")";
    }
}
