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
    System.out.println("\n--- CLÍNICA ---");
    System.out.println("1. Cadastrar Médico");
    System.out.println("2. Cadastrar Paciente");
    System.out.println("3. Agendar Consulta");
    System.out.println("0. Voltar");
    int opcao = sc.nextInt(); sc.nextLine();

    switch (opcao) {
        case 1 -> {
            System.out.print("Nome: "); String nome = sc.nextLine();
            System.out.print("CPF: "); String cpf = sc.nextLine();
            System.out.print("Contato: "); String contato = sc.nextLine();
            System.out.print("Especialidade: "); String esp = sc.nextLine();
            System.out.print("CRM: "); String crm = sc.nextLine();
            Medico m = new Medico(nome, cpf, contato, esp, crm);
            System.out.println("Médico cadastrado: " + m);
        }
        case 2 -> {
            System.out.print("Nome: "); String nome = sc.nextLine();
            System.out.print("CPF: "); String cpf = sc.nextLine();
            System.out.print("Contato: "); String contato = sc.nextLine();
            Paciente p = new Paciente(nome, cpf, contato);
            System.out.println("Paciente cadastrado: " + p);
        }
        case 3 -> {
            System.out.println("Agendamento de consulta ainda em construção...");
        }
    }
}
   private static void menuEventos(List<Evento> eventos) {
    System.out.println("\n--- EVENTOS ---");
    System.out.println("1. Criar Evento");
    System.out.println("2. Inscrever Participante");
    System.out.println("0. Voltar");
    int opcao = sc.nextInt(); sc.nextLine();

    switch (opcao) {
        case 1 -> {
            System.out.print("Nome do evento: "); String nome = sc.nextLine();
            System.out.print("Capacidade máxima: "); int cap = sc.nextInt(); sc.nextLine();
            System.out.print("Tipo: "); String tipo = sc.nextLine();
            System.out.println("Data : (DD/MM/AAAA)"); String data = sc.nextLine();
            Local local = new Local("Auditório", "Rua X", cap); 
            Evento ev = new Evento(nome, local, cap, tipo, LocalDateTime.now().plusDays(10));
            eventos.add(ev);
            System.out.println("Evento criado: " + ev.getNome());
        }
        case 2 -> {
            System.out.print("Nome do participante: "); String nome = sc.nextLine();
            System.out.print("CPF: "); String cpf = sc.nextLine();
            System.out.print("Contato: "); String contato = sc.nextLine();
            Participante part = new Participante(nome, cpf, contato);
            if (!eventos.isEmpty()) {
                eventos.get(0).inscrever(part); 
                System.out.println("Inscrito no evento " + eventos.get(0).getNome());
            }
        }
    }
}private static void menuRestaurante(List<Pedido> pedidos) {
    System.out.println("\n--- RESTAURANTE ---");
    System.out.println("1. Cadastrar Pedido");
    System.out.println("2. Adicionar Prato");
    System.out.println("0. Voltar");
    int opcao = sc.nextInt(); sc.nextLine();

    switch (opcao) {
        case 1 -> {
            Pedido pedido = new Pedido();
            pedidos.add(pedido);
            System.out.println("Novo pedido criado.");
        }
        case 2 -> {
            if (pedidos.isEmpty()) {
                System.out.println("Crie um pedido primeiro.");
                return;
            }
            System.out.print("Nome do prato: "); String nome = sc.nextLine();
            System.out.print("Preço: "); double preco = sc.nextDouble(); sc.nextLine();
            System.out.print("Categoria: "); String categoria = sc.nextLine();
            System.out.print("Calorias: "); int calorias = sc.nextInt(); sc.nextLine();
            Prato prato = new Prato(nome, preco, categoria, calorias);
            pedidos.get(0).adicionarPrato(prato);
            System.out.println("Prato adicionado ao pedido.");
            }
        }
    }
    private static void gerarRelatoriosFinais(List<Consulta> consultas, List<Evento> eventos, List<Pedido> pedidos) {
    System.out.println("\n=== RELATÓRIOS FINAIS ===");

    System.out.println("\n--- Clínica ---");
    Medico medicoOcupado = ClinicaRelatorios.medicoMaisOcupado(consultas);
    System.out.println(medicoOcupado != null ? "Médico mais ocupado: " + medicoOcupado.getNome(): "Nenhum médico cadastrado");
    String horaFaltas = ClinicaRelatorios.horarioComMaisFaltas(consultas);
    System.out.println("Horário com mais faltas: " + horaFaltas);

    System.out.println("\n--- Eventos ---");
    Evento eventoInscritos = EventosRelatorios.eventoComMaisInscritos(eventos);
    System.out.println(eventoInscritos != null ? "Evento com mais inscritos: " + eventoInscritos.getNome()
                                               : "Nenhum evento registrado");

    Evento eventoLotado = EventosRelatorios.eventoLotacaoMaisRapida(eventos);
    System.out.println(eventoLotado != null ? "Evento que lotou mais rápido: " + eventoLotado.getNome(): "Nenhum evento lotado");

    System.out.println("\n--- Restaurante ---");
    Prato pratoPopular = RestauranteRelatorios.pratoMaisVendidoPorPeriodo(pedidos, "geral");
    System.out.println(pratoPopular != null ? "Prato mais vendido: " + pratoPopular.getNome()
                                            : "Nenhum prato registrado");
}

}