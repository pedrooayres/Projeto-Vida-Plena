package Base;

import java.time.LocalDateTime;

public class Agenda {
    private LocalDateTime dataHora;
    private Local local;
    private String status;

    public Agenda(LocalDateTime dataHora, Local local, String status) {
        this.dataHora = dataHora;
        this.local = local;
        this.status = status;
    }

    public LocalDateTime getDataHora() { return dataHora; }
    public Local getLocal() { return local; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Data/Hora: " + dataHora + " | Local: " + local.getNome() + " | Status: " + status;
    }
}
