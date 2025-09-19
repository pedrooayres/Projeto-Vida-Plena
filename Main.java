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
            System.out.println("4. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> menuClinica(consultas);
                case 2 -> menuEventos(eventos);
                case 3 -> menuRestaurante(pedidos);
                case 4 -> menuRelatorios(consultas, eventos, pedidos);
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void menuRelatorios(List<Consulta> consultas, List<Evento> eventos, List<Pedido> pedidos) {
        System.out.println("\n--- RELATÓRIOS ---");
        System.out.println("1. Médico mais ocupado");
        System.out.println("2. Horário com mais faltas");
        System.out.println("3. Evento com mais inscritos");
        System.out.println("4. Prato mais vendido (manhã)");
        System.out.println("0. Voltar");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> {
                Medico m = ClinicaRelatorios.medicoMaisOcupado(consultas);
                System.out.println(m != null ? "Médico mais ocupado: " + m.getNome() : "Nenhum médico encontrado");
            }
            case 2 -> System.out.println("Horário com mais faltas: " + ClinicaRelatorios.horarioComMaisFaltas(consultas));
            case 3 -> {
                Evento e = EventosRelatorios.eventoComMaisInscritos(eventos);
                System.out.println(e != null ? "Evento com mais inscritos: " + e.getNome() : "Nenhum evento encontrado");
            }
            case 4 -> {
                Prato p = RestauranteRelatorios.pratoMaisVendidoPorPeriodo(pedidos, "manhã");
                System.out.println(p != null ? "Prato mais vendido de manhã: " + p.getNome() : "Nenhum prato encontrado");
            }
        }
    }

    private static void menuClinica(List<Consulta> consultas) {
        System.out.println("\n[CLÍNICA] Cadastro/Agendamento aqui...");
    }

    private static void menuEventos(List<Evento> eventos) {
        System.out.println("\n[EVENTOS] Cadastro de eventos/inscrições aqui...");
    }

    private static void menuRestaurante(List<Pedido> pedidos) {
        System.out.println("\n[RESTAURANTE] Criação de pedidos aqui...");
    }
}
