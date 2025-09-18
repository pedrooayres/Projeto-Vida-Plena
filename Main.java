import Base.*;
import Clinica.*;
import Eventos.*;
import Restaurante.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        System.out.print("Cadastrar (1) Médico ou (2) Paciente? ");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha == 1) {
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Contato: ");
            String contato = sc.nextLine();
            System.out.print("Especialidade: ");
            String especialidade = sc.nextLine();
            System.out.print("CRM: ");
            String crm = sc.nextLine();

            System.out.print("Endereço da clínica: ");
            String endereco = sc.nextLine();
            System.out.print("Nome do consultório: ");
            String consultorioNome = sc.nextLine();
            Local local = new Local(consultorioNome, endereco, 2);

            Medico medico = new Medico(nome, cpf, contato, especialidade, crm);
            System.out.println("Médico cadastrado: " + medico);

        } else if (escolha == 2) {
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Contato: ");
            String contato = sc.nextLine();

            Paciente paciente = new Paciente(nome, cpf, contato);

            System.out.print("Nome do médico desejado: ");
            String nomeMedico = sc.nextLine();
            System.out.print("Especialidade do médico: ");
            String espMedico = sc.nextLine();
            System.out.print("CRM do médico: ");
            String crmMedico = sc.nextLine();
            Medico medico = new Medico(nomeMedico, "000.000.000-00", "sem contato", espMedico, crmMedico);

            System.out.print("Local da consulta: ");
            String localNome = sc.nextLine();
            Local local = new Local(localNome, "endereço não informado", 1);

            System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
            String dataHoraStr = sc.nextLine();
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            Agenda agenda = new Agenda(dataHora, local, "Agendado");
            Consulta consulta = new Consulta(paciente, medico, agenda, 200.0);
            consultas.add(consulta);

            consulta.exibirDetalhes();
        }
    }

    private static void menuEventos(List<Evento> eventos) {
        System.out.println("\n--- EVENTOS ---");
        System.out.print("Nome do evento: ");
        String nomeEvento = sc.nextLine();
        System.out.print("Tipo do evento (Palestra, Workshop, Show...): ");
        String tipo = sc.nextLine();
        System.out.print("Capacidade máxima: ");
        int capacidade = sc.nextInt();
        sc.nextLine();

        System.out.print("Local do evento: ");
        String nomeLocal = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        Local local = new Local(nomeLocal, endereco, capacidade);

        System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = sc.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        Evento evento = new Evento(nomeEvento, local, capacidade, tipo, dataHora);
        eventos.add(evento);

        System.out.println("Evento criado: " + evento);

        System.out.print("Quantos participantes deseja inscrever? ");
        int qtd = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < qtd; i++) {
            System.out.println("Participante " + (i+1) + ":");
            System.out.print("Nome: ");
            String nomeP = sc.nextLine();
            System.out.print("CPF: ");
            String cpfP = sc.nextLine();
            System.out.print("Contato: ");
            String contatoP = sc.nextLine();

            Participante part = new Participante(nomeP, cpfP, contatoP);
            if (evento.inscrever(part)) {
                System.out.println("Inscrição realizada com sucesso!");
            } else {
                System.out.println("Evento lotado.");
            }
        }

        System.out.println("Resumo final: " + evento);
    }

    private static void menuRestaurante(List<Pedido> pedidos) {
        System.out.println("\n--- RESTAURANTE ---");
        Pedido pedido = new Pedido();

        String continuar;
        do {
            System.out.print("Nome do prato: ");
            String nome = sc.nextLine();
            System.out.print("Preço: ");
            double preco = sc.nextDouble();
            sc.nextLine();
            System.out.print("Categoria (Entrada, Principal, Sobremesa): ");
            String categoria = sc.nextLine();
            System.out.print("Calorias: ");
            int calorias = sc.nextInt();
            sc.nextLine();

            Prato prato = new Prato(nome, preco, categoria, calorias);
            pedido.adicionarPrato(prato);

            System.out.print("Deseja adicionar outro prato? (s/n) ");
            continuar = sc.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        pedidos.add(pedido);
        System.out.println("Pedido realizado: " + pedido);

        System.out.print("Deseja aplicar voucher de 10%? (s/n) ");
        String aplicar = sc.nextLine();
        if (aplicar.equalsIgnoreCase("s")) {
            Voucher v = new Voucher("DESC10", 0.1);
            double totalComDesconto = v.aplicar(pedido.calcularTotal());
            System.out.println("Total com desconto: R$" + totalComDesconto);
        } else {
            System.out.println("Total: R$" + pedido.calcularTotal());
        }
    }
}
