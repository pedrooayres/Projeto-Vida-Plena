import Base.*;
import Clinica.*;
import Eventos.*;
import Restaurante.*;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        List<Consulta> consultas = new ArrayList<>();
        List<Evento> eventos = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();

        int opcao;
        do {
            System.out.println("\n=== SISTEMA VIDA PLENA ===");
            System.out.println("1. Clínica");
            System.out.println("2. Eventos");
            System.out.println("3. Restaurante");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> menuClinica(consultas);
                case 2 -> menuEventos(eventos);
                case 3 -> menuRestaurante(pedidos);
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void menuClinica(List<Consulta> consultas) {
        System.out.println("\n--- CLÍNICA ---");
        Medico m = new Medico("João Silva", "111.111.111-11", "9999-1111", "Cardiologia", "CRM-12345");
        Paciente p = new Paciente("Maria Oliveira", "222.222.222-22", "9999-2222");
        Local local = new Local("Consultório 1", "Av. Central, 123", 2);
        Agenda agenda = new Agenda(LocalDateTime.now().plusDays(1), local, "Agendado");

        Consulta c = new Consulta(p, m, agenda, 250.0);
        consultas.add(c);

        c.exibirDetalhes();
        System.out.println("Pagamento gerado: " + new Pagamento(250.0, "Cartão", "Pago"));
    }

    private static void menuEventos(List<Evento> eventos) {
        System.out.println("\n--- EVENTOS ---");
        Local auditorio = new Local("Auditório Vida Plena", "Rua das Flores, 45", 50);
        Evento e = new Evento("Palestra sobre Saúde", auditorio, 100, "Palestra", LocalDateTime.now().plusDays(7));
        eventos.add(e);

        Participante p1 = new Participante("Carlos", "333.333.333-33", "9999-3333");
        Participante p2 = new Participante("Fernanda", "444.444.444-44", "9999-4444");

        e.inscrever(p1);
        e.inscrever(p2);

        System.out.println(e);
    }

    private static void menuRestaurante(List<Pedido> pedidos) {
        System.out.println("\n--- RESTAURANTE ---");
        Prato prato1 = new Prato("Salada", 15.0, "Entrada", 120);
        Prato prato2 = new Prato("Lasanha", 30.0, "Prato Principal", 800);
        Prato prato3 = new Prato("Sorvete", 12.0, "Sobremesa", 250);

        Pedido pedido = new Pedido();
        pedido.adicionarPrato(prato1);
        pedido.adicionarPrato(prato2);
        pedido.adicionarPrato(prato3);

        pedidos.add(pedido);

        System.out.println(pedido);

        Voucher v = new Voucher("DESC10", 0.1);
        double totalComDesconto = v.aplicar(pedido.calcularTotal());
        System.out.println("Total com desconto: R$" + totalComDesconto);
    }
}
