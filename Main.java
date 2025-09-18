import Base.*;
import Clinica.*;
import Eventos.*;
import Restaurante.*;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== CLÍNICA ===");
        Medico m1 = new Medico("João Silva", "111.111.111-11", "9999-1111", "Cardiologia");
        Paciente p1 = new Paciente("Maria Oliveira", "222.222.222-22", "9999-2222");
        Local consultorio = new Local("Consultório 1", "Av. Central, 123", 2);
        Agenda agenda1 = new Agenda(LocalDateTime.now().plusDays(1), consultorio, "Agendado");

        Consulta c1 = new Consulta(p1, m1, agenda1);
        c1.exibirDetalhes();

        System.out.println("\n=== EVENTOS ===");
        Local auditorio = new Local("Auditório Vida Plena", "Rua das Flores, 45", 50);
        Evento evento = new Evento("Palestra sobre Saúde", auditorio, 3);

        Participante pa1 = new Participante("Carlos", "333.333.333-33", "9999-3333");
        Participante pa2 = new Participante("Fernanda", "444.444.444-44", "9999-4444");

        evento.inscrever(pa1);
        evento.inscrever(pa2);

        System.out.println(evento);

        System.out.println("\n=== RESTAURANTE ===");
        Prato prato1 = new Prato("Salada", 15.0);
        Prato prato2 = new Prato("Lasanha", 30.0);

        Pedido pedido = new Pedido();
        pedido.adicionarPrato(prato1);
        pedido.adicionarPrato(prato2);

        System.out.println(pedido);

        Voucher v = new Voucher("DESC10", 0.1);
        double totalComDesconto = v.aplicar(pedido.calcularTotal());
        System.out.println("Total com desconto: R$" + totalComDesconto);
    }
}
