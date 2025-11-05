import Base.*;
import Clinica.*;
import Eventos.*;
import Relatorios.*;
import Restaurante.*;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    static List<Consulta> listaConsultas = new ArrayList<>();
    static List<Evento> listaEventos = new ArrayList<>();
    static List<Pedido> listaPedidos = new ArrayList<>();

    static RelatoriosGerais relatorios = new RelatoriosGerais(listaConsultas, listaEventos, listaPedidos);

    static List<Paciente> listaPacientes = new ArrayList<>();
    static List<Medico> listaMedicos = new ArrayList<>();

    private static Paciente buscarPacientePorNome(String nome) {
        for (Paciente p : listaPacientes) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    private static Medico buscarMedicoPorNome(String nome) {
        for (Medico m : listaMedicos) {
            if (m.getNome().equalsIgnoreCase(nome)) {
                return m;
            }
        }
        return null;
    }

    private static Evento buscarEventoPorNome(String nome) {
        for (Evento e : listaEventos) {
            if (e.getNome().equalsIgnoreCase(nome)) {
                return e;
            }
        }
        return null;
    }

    public static void main(String[] args) {
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
                case 1 -> menuClinica(listaConsultas);
                case 2 -> menuEventos(listaEventos);
                case 3 -> menuRestaurante(listaPedidos);
                case 4 -> menuRelatorios(listaConsultas, listaEventos, listaPedidos);
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }



    /*
        Menu relatórios com perguntas escritas literalmente nos prints (versão antiga) !!!

        System.out.println("1. Quais medicos e eventos tem maior ocupaçao nos mesmos horarios?");
        System.out.println("2. Horario com mais faltas");
        System.out.println("3. Evento com mais inscritos");
        System.out.println("4. Prato mais vendido (manha)");
        System.out.println("5. Quais médicos e eventos têm maior ocupação no mesmo dia?");
        System.out.println("6. Algum paciente que faltou na clínica, compareceu ao evento? (exibir apenas um a escolha)*");
        System.out.println("7. O cliente da clínica que visita pelo menos um evento, tem um gasto maior na clínica ou no evento ?");
        System.out.println("8. Um medico da clínica fez a recomendação de um prato do restaurante em um determinado dia, após esse dia quantos pedidos desse prato foram feitos ?");
        System.out.println("9. Qual tipo de serviço (clínico, evento ou restaurante) gera mais receita mensal?");
        System.out.println("10. Qual horário mais frequentado no restaurante e consultório");
        System.out.println("11. Em determinado evento com participação do restaurante em um certo dia, qual o preço médio dos pedidos feitos para o evento nesse dia ? (levando em consideração que no dia desse evento o restaurante só fez pedidos para o evento)*");
        System.out.println("12. Quais clientes estão envolvidos em mais de um serviço (clínica e eventos)*");
        System.out.println("13. Qual é o percentual de comparecimento em relação às agendas criadas (consultas realizadas e eventos confirmados)?"); 

        Menu relatórios com perguntas escritas literalmente nos prints (versão antiga) !!!
     */
    private static void menuRelatorios(List<Consulta> listaConsultas, List<Evento> listaEventos, List<Pedido> listaPedidos) {
        System.out.println("\n--- RELATÓRIOS ---");
        System.out.println("1. Médico mais ocupado");
        System.out.println("2. Horário com mais faltas");
        System.out.println("3. Evento com mais inscritos");
        System.out.println("4. Prato mais vendido (manhã)");
        System.out.println("5. Choques de dia entre médicos e eventos");
        System.out.println("6. Paciente que faltou na clínica e compareceu a evento");
        System.out.println("7. Cliente da clínica que também visita eventos: gasto maior onde?");
        System.out.println("8. Impacto da recomendação médica de um prato");
        System.out.println("9. Tipo de serviço com maior receita mensal");
        System.out.println("10. Horário mais frequentado");
        System.out.println("11. Média dos pedidos no dia do evento");
        System.out.println("12. Clientes presentes em mais de um serviço");
        System.out.println("13. Percentual de comparecimento (consultas e eventos)");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> {
                Medico m = ClinicaRelatorios.medicoMaisOcupado(listaConsultas);
                System.out.println("Médico mais ocupado: " + m);
            }
            case 2 -> {
                System.out.println(ClinicaRelatorios.horarioComMaisFaltasConsultas(listaConsultas));
                System.out.println(EventosRelatorios.horarioComMaisFaltasEventos(listaEventos));
            }
            case 3 -> {
                Evento e = EventosRelatorios.eventoComMaisInscritos(listaEventos);
                System.out.println(e != null ? "Evento com mais inscritos: " + e.getNome() : "Nenhum evento encontrado");
            }
            case 4 -> {
                Prato p = RestauranteRelatorios.pratoMaisVendidoPorPeriodo(listaPedidos, "manha");
                System.out.println(p != null ? "Prato mais vendido de manhã: " + p.getNome() : "Nenhum prato encontrado");
            }
            case 5 -> {
                relatorios.relatorioChoquesMedicoEvento();
            }
            case 6 -> {
                // ainda não implementado
            }
            case 7 -> {
                RelatoriosGerais.compararGastosCliente(listaConsultas, listaEventos);
            }
            case 8 -> {
                RelatoriosGerais.pratosRecomendadosPorMedico(listaPedidos, sc);
            }
            case 9 -> {
                // ainda não implementado
            }
            case 10 -> {
                // ainda não implementado
            }
            case 11 -> {
                RelatoriosGerais.mediaPedidosEvento(listaEventos, listaPedidos, sc);
            }
            case 12 -> {
                RelatoriosGerais.listarClientesEmMaisDeUmServico(listaConsultas, listaEventos);
            }
            case 13 -> {
                RelatoriosGerais.listarPercentuaisComparecimento(listaConsultas, listaEventos);
            }
            case 0 -> System.out.println("Voltando ao menu principal...");
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void menuClinica(List<Consulta> listaConsultas) {
        System.out.println("\n--- CLÍNICA ---");
        System.out.println("1. Cadastrar Médico");
        System.out.println("2. Cadastrar Paciente");
        System.out.println("3. Agendar Consulta");
        System.out.println("0. Voltar");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> {
                System.out.print("Nome: "); String nome = sc.nextLine();
                System.out.print("CPF: "); String cpf = sc.nextLine();
                System.out.print("Contato: "); String contato = sc.nextLine();
                System.out.print("Especialidade: "); String esp = sc.nextLine();
                System.out.print("CRM: "); String crm = sc.nextLine();
                Medico m = new Medico(nome, cpf, contato, esp, crm);
                listaMedicos.add(m);
                System.out.println("Médico cadastrado: " + m);
            }
            case 2 -> {
                System.out.print("Nome: "); String nome = sc.nextLine();
                System.out.print("CPF: "); String cpf = sc.nextLine();
                System.out.print("Contato: "); String contato = sc.nextLine();
                Paciente p = new Paciente(nome, cpf, contato);
                listaPacientes.add(p);
                System.out.println("Paciente cadastrado: " + p);
            }
            case 3 -> {
                System.out.print("Nome do paciente: "); String nome = sc.nextLine();
                Paciente paciente = buscarPacientePorNome(nome);
                if (paciente == null) {
                    System.out.println("Paciente não encontrado. Cadastre-o primeiro (opção 2).");
                    break;
                }

                System.out.print("Data da consulta (DD/MM/AAAA): "); String data = sc.nextLine();
                System.out.print("Horário da consulta (HH:MM): "); String hora = sc.nextLine();
                System.out.print("Nome do médico: "); String nomemed = sc.nextLine();
                Medico medico = buscarMedicoPorNome(nomemed);
                if (medico == null) {
                    System.out.println("Médico não encontrado. Cadastre-o primeiro (opção 1).");
                    break;
                }

                String[] partesData = data.split("/");
                int dia = Integer.parseInt(partesData[0]);
                int mes = Integer.parseInt(partesData[1]);
                int ano = Integer.parseInt(partesData[2]);
                String[] partesHora = hora.split(":");
                int hh = Integer.parseInt(partesHora[0]);
                int mm = Integer.parseInt(partesHora[1]);
                LocalDateTime dataHoraConsulta = LocalDateTime.of(ano, mes, dia, hh, mm);

                Agenda agenda_consulta = new Agenda(dataHoraConsulta, "REGISTRADO");
                double valorConsulta = 300.00;
                boolean comparecimento_consulta = true;
                System.out.print("O paciente faltou à consulta? (S/N): ");
                char opcao_s_ou_n = sc.next().charAt(0);
                if (opcao_s_ou_n == 'S' || opcao_s_ou_n == 's') comparecimento_consulta = false;

                Consulta c = new Consulta(paciente, medico, agenda_consulta, valorConsulta, comparecimento_consulta);
                listaConsultas.add(c);
                System.out.println("Consulta registrada com sucesso.");
            }
            case 0 -> System.out.println("Voltando...");
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void menuEventos(List<Evento> listaEventos) {
        System.out.println("\n--- EVENTOS ---");
        System.out.println("1. Criar Evento");
        System.out.println("2. Inscrever Participante");
        System.out.println("0. Voltar");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> {
                System.out.print("Nome do evento: "); String nome = sc.nextLine();
                System.out.print("Capacidade máxima: "); int cap = sc.nextInt(); sc.nextLine();
                System.out.print("Valor do evento: "); double valorEvento = sc.nextDouble(); sc.nextLine();
                System.out.print("Tipo: "); String tipo = sc.nextLine();
                System.out.print("Data (DD/MM/AAAA): "); String data = sc.nextLine();
                String[] partesData = data.split("/");
                int dia = Integer.parseInt(partesData[0]);
                int mes = Integer.parseInt(partesData[1]);
                int ano = Integer.parseInt(partesData[2]);

                Local local = new Local("Auditório", "Rua X", cap);
                LocalDateTime dataHoraEvento = LocalDateTime.of(ano, mes, dia, 0, 0);
                boolean comparecimento_evento = true;

                System.out.print("Confirmar participação (S/N): ");
                char opcao_s_ou_n = sc.next().charAt(0);
                comparecimento_evento = (opcao_s_ou_n == 'S' || opcao_s_ou_n == 's');

                Evento ev = new Evento(nome, local, cap, valorEvento, tipo, dataHoraEvento, comparecimento_evento);
                listaEventos.add(ev);
                System.out.println("Evento criado: " + ev.getNome());
                break;
            }
            case 2 -> {
                System.out.print("Nome do participante: "); String nome = sc.nextLine();
                System.out.print("CPF: "); String cpf = sc.nextLine();
                System.out.print("Contato: "); String contato = sc.nextLine();
                System.out.print("Evento em que deseja se inscrever: "); String nomeEvento = sc.nextLine();
                Evento evento = buscarEventoPorNome(nomeEvento);
                if (evento == null) {
                    System.out.println("Evento não encontrado.");
                } else {
                    Participante part = new Participante(nome, cpf, contato);
                    evento.inscrever(part);
                    System.out.println("Inscrição realizada com sucesso.");
                }
            }
            case 0 -> System.out.println("Voltando...");
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void menuRestaurante(List<Pedido> listaPedidos) {
        System.out.println("\n--- RESTAURANTE ---");
        System.out.println("1. Criar novo pedido");
        System.out.println("2. Adicionar prato a pedido existente");
        System.out.println("0. Voltar");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> {
                Pedido pedido = new Pedido();
                listaPedidos.add(pedido);
                System.out.println("Novo pedido criado com sucesso.");
            }
            case 2 -> {
                if (listaPedidos.isEmpty()) {
                    System.out.println("Crie um pedido primeiro (opção 1).");
                    return;
                }

                System.out.print("Nome do prato: "); String nome = sc.nextLine();
                System.out.print("Preço: "); double preco = sc.nextDouble(); sc.nextLine();
                System.out.print("Categoria: "); String categoria = sc.nextLine();
                System.out.print("Calorias: "); int calorias = sc.nextInt(); sc.nextLine();

                System.out.print("Data do pedido (DD/MM/AAAA): "); String data = sc.nextLine();
                System.out.print("Horário do pedido (HH:MM): "); String hora = sc.nextLine();

                String[] partesData = data.split("/");
                int dia = Integer.parseInt(partesData[0]);
                int mes = Integer.parseInt(partesData[1]);
                int ano = Integer.parseInt(partesData[2]);
                String[] partesHora = hora.split(":");
                int hh = Integer.parseInt(partesHora[0]);
                int mm = Integer.parseInt(partesHora[1]);
                LocalDateTime dataHoraPedido = LocalDateTime.of(ano, mes, dia, hh, mm);
                Agenda agenda_pedido = new Agenda(dataHoraPedido, "DATA DO PEDIDO CONFIRMADA");

                Prato prato = new Prato(nome, preco, categoria, calorias);
                Pedido ultimoPedido = listaPedidos.get(listaPedidos.size() - 1);
                ultimoPedido.adicionarPrato(prato, agenda_pedido);
                System.out.println("Prato adicionado ao pedido mais recente.");
            }
            case 0 -> System.out.println("Voltando...");
            default -> System.out.println("Opção inválida.");
        }
    }
}
