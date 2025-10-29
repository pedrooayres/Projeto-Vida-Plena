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
            System.out.println("1. Clinica");
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
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opçao invalida.");
            }
        } while (opcao != 0);
    }

    private static void menuRelatorios(List<Consulta> listaConsultas, List<Evento> listaEventos, List<Pedido> listaPedidos) {
        System.out.println("\n--- RELATORIOS ---");
        System.out.println("1. Quais medicos e eventos tem maior ocupaçao nos mesmos horarios?");
        System.out.println("2. Horario com mais faltas");
        System.out.println("3. Evento com mais inscritos");
        System.out.println("4. Prato mais vendido (manha)");
        System.out.println("5. Que tipo de serviço gera maior renda mensal");
        System.out.println("6. Qual faixa de horario mais frenquentada em cada setor");
        System.out.println("7. Quais clientes possuem mais gastos total (clinica + eventos + restaurante)");
        System.out.println("8. Quais datas apresentam maior volume de atividades simultaneas entre areas diferentes");
        System.out.println("9. Qual eh o percentual de comparecimento em relação as agendas criadas");
        System.out.println("0. Voltar");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> {
                Medico m = ClinicaRelatorios.medicoMaisOcupado(listaConsultas);
                System.out.println(" Medico :" + m);
            }
            case 2 -> System.out.println("Horario com mais faltas: " + ClinicaRelatorios.horarioComMaisFaltas(listaConsultas));
            case 3 -> {
                Evento e = EventosRelatorios.eventoComMaisInscritos(listaEventos);
                System.out.println(e != null ? "Evento com mais inscritos: " + e.getNome() : "Nenhum evento encontrado");
            }
            case 4 -> {
                Prato p = RestauranteRelatorios.pratoMaisVendidoPorPeriodo(listaPedidos, "manha");
                System.out.println(p != null ? "Prato mais vendido de manha: " + p.getNome() : "Nenhum prato encontrado");
            }

            case 5 -> {
                
            }

            case 6 -> {

            }

            case 7 -> {

            }

            case 8 -> {

            }

            case 9 -> {

            }
        }
    }

   private static void menuClinica(List<Consulta> listaConsultas) {
    System.out.println("\n--- CLINICA ---");
    System.out.println("1. Cadastrar Medico");
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
            listaMedicos.add(m);
            System.out.println("Medico cadastrado: " + m);
            break;
        }
        case 2 -> {
            System.out.print("Nome: "); String nome = sc.nextLine();
            System.out.print("CPF: "); String cpf = sc.nextLine();
            System.out.print("Contato: "); String contato = sc.nextLine();
            Paciente p = new Paciente(nome, cpf, contato);
            listaPacientes.add(p);
            System.out.println("Paciente cadastrado: " + p);
            break;
        }
        case 3 -> {
            System.out.println("Qual seu nome :"); String nome = sc.nextLine();
            Paciente paciente = buscarPacientePorNome(nome);
            if (paciente == null) {
                System.out.println("Paciente nao encontrado. Cadastre o paciente primeiro (opçao 2 do menu Clínica).");
                break;
            }
            System.out.println("Qual dia deseja marcar a consulta (DD/MM/AAAA)"); String data = sc.nextLine();
            System.out.println("Que horas deseja marcar a consulta (HH/MM)"); String hora = sc.nextLine();
            System.out.println("Que medico deseja se consultar "); String nomemed = sc.nextLine();
            Medico medico = buscarMedicoPorNome(nomemed);
            if (medico == null) {
                System.out.println("Medico não encontrado. Cadastre o medico primeiro (opção 1 do menu Clinica).");
                break;
            }
            String[] partesData = data.split("/"); // ["28","10","2025"]
            int dia = Integer.parseInt(partesData[0]);
            int mes = Integer.parseInt(partesData[1]);
            int ano = Integer.parseInt(partesData[2]);
            String[] partesHora = hora.split(":"); // ["14","30"]
            int hh = Integer.parseInt(partesHora[0]);
            int mm = Integer.parseInt(partesHora[1]);
            LocalDateTime dataHoraConsulta = LocalDateTime.of(ano, mes, dia, hh, mm);
            Agenda agenda_consulta = new Agenda(dataHoraConsulta, "AGENDADA");
            double valorConsulta = 300.00;
            int comparecimento_consulta = 0;
            char opcao_s_ou_n;
            System.out.println("O paciente faltou a consulta?: S/N"); opcao_s_ou_n = sc.next().charAt(0);
            if(opcao_s_ou_n != ('S') && opcao_s_ou_n != ('N')) {
                System.out.println("Opcao invalida, digite S para sim, N para nao");
            } else {
                if(opcao_s_ou_n == ('S')) {
                    comparecimento_consulta = 1;
                } else if(opcao_s_ou_n == ('N')) {
                    comparecimento_consulta = -1;
                }
            }

            Consulta c = new Consulta(paciente, medico, agenda_consulta, valorConsulta, comparecimento_consulta);
            listaConsultas.add(c);
            System.out.println("Consulta marcada com sucesso");
            break;
            }
        }
    }
   private static void menuEventos(List<Evento> listaEventos) {
    System.out.println("\n--- EVENTOS ---");
    System.out.println("1. Criar Evento");
    System.out.println("2. Inscrever Participante");
    System.out.println("0. Voltar");
    int opcao = sc.nextInt(); sc.nextLine();

    switch (opcao) {
        case 1 -> {
            System.out.print("Nome do evento: "); String nome = sc.nextLine();
            System.out.print("Capacidade maxima: "); int cap = sc.nextInt(); sc.nextLine();
            System.out.print("Valor do evento: "); double valorEvento = sc.nextDouble();
            System.out.print("Tipo: "); String tipo = sc.nextLine();
            System.out.println("Data: (DD/MM/AAAA)"); String data = sc.nextLine();
            // quebra "28/10/2025" em [ "28", "10", "2025" ]
            String[] partesData = data.split("/");
            int dia = Integer.parseInt(partesData[0]);
            int mes = Integer.parseInt(partesData[1]);
            int ano = Integer.parseInt(partesData[2]);

            Local local = new Local("Auditorio", "Rua X", cap); 
            LocalDateTime dataHoraEvento = LocalDateTime.of(ano, mes, dia, 0, 0);
            int comparecimento_evento = 0;
            char opcao_s_ou_n;
            System.out.println("O participante faltou o evento?: S/N"); opcao_s_ou_n = sc.next().charAt(0);

            if(opcao_s_ou_n != ('S') && opcao_s_ou_n != ('N')) {
                System.out.println("Opcao invalida, digite S para sim, N para nao");
            } else {
                if(opcao_s_ou_n == ('S')) {
                    comparecimento_evento = 1;
                } else if(opcao_s_ou_n == ('N')) {
                    comparecimento_evento = -1;
                }
            }
            Evento ev = new Evento(nome, local, cap, valorEvento, tipo, dataHoraEvento, comparecimento_evento);
            listaEventos.add(ev);
            System.out.println("Evento criado: " + ev.getNome());
        }
        case 2 -> {
            System.out.print("Nome do participante: "); String nome = sc.nextLine();
            System.out.print("CPF: "); String cpf = sc.nextLine();
            System.out.print("Contato: "); String contato = sc.nextLine();
            System.out.print("Qual evento voce deseja se inscrever: "); String nomeEvento = sc.nextLine();
            Evento evento = buscarEventoPorNome(nomeEvento);
            if (evento == null) {
                System.out.print("Evento nao encontrado.");
            } else {
                Participante part = new Participante(nome, cpf, contato);
                evento.inscrever(part);
                System.out.println("Inscrito no evento " + evento.getNome());
            }
        }
    }
}

    private static void menuRestaurante(List<Pedido> listaPedidos) {
        System.out.println("\n--- RESTAURANTE ---");
        System.out.println("1. Cadastrar Pedido");
        System.out.println("2. Adicionar Prato");
        System.out.println("0. Voltar");
        int opcao = sc.nextInt(); sc.nextLine();

        switch (opcao) {
            case 1 -> {
                Pedido pedido = new Pedido();
                listaPedidos.add(pedido);
                System.out.println("Novo pedido criado.");
            }
            case 2 -> {
                if (listaPedidos.isEmpty()) {
                    System.out.println("Crie um pedido primeiro.");
                    return;
                }
                System.out.print("Nome do prato: "); String nome = sc.nextLine();
                System.out.print("Preço: "); double preco = sc.nextDouble(); sc.nextLine();
                System.out.print("Categoria: "); String categoria = sc.nextLine();
                System.out.print("Calorias: "); int calorias = sc.nextInt(); sc.nextLine();
                System.out.println("Qual dia foi feito o pedido (DD/MM/AAAA)"); String data = sc.nextLine();
                System.out.println("Qual horario foi feito o pedido (DD/MM/AAAA)"); String hora = sc.nextLine();
                String[] partesData = data.split("/"); // ["28","10","2025"]
                int dia = Integer.parseInt(partesData[0]);
                int mes = Integer.parseInt(partesData[1]);
                int ano = Integer.parseInt(partesData[2]);
                String[] partesHora = hora.split(":"); // ["14","30"]
                int hh = Integer.parseInt(partesHora[0]);
                int mm = Integer.parseInt(partesHora[1]);
                LocalDateTime dataHoraPedido = LocalDateTime.of(ano, mes, dia, hh, mm);
                Agenda agenda_pedido = new Agenda(dataHoraPedido, "DATA DO PEDIDO CONFIRMADA");
                Prato prato = new Prato(nome, preco, categoria, calorias);
                listaPedidos.get(0).adicionarPrato(prato, agenda_pedido);
                System.out.println("Prato adicionado ao pedido.");
            }
        }
    }
}
