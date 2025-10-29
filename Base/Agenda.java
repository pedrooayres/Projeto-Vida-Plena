package Base;

import java.time.LocalDateTime;

public class Agenda {
    private LocalDateTime dataHora;
    private String status;

    public Agenda(LocalDateTime dataHora, String status) {
        this.dataHora = dataHora;
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
