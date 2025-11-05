package Relatorios;

import Clinica.*;
import Eventos.*;
import Restaurante.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class RelatoriosGerais {

    protected List<Consulta> consultas;
    private List<Evento> eventos;
    private List<Prato> restaurante;

    public RelatoriosGerais(List<Consulta> consultas, List<Evento> eventos, List<Pedido> pedidos) {
        this.consultas = consultas;
        this.eventos = eventos;
        this.restaurante = restaurante;
    }

    // ---- PERGUNTA 5 ----
    // Quais médicos e eventos têm maior ocupação nos mesmo dia?
    public void relatorioChoquesMedicoEvento() {
        Map<Medico, Integer> choquesPorMedico = new HashMap<>();
        Map<Evento, Integer> choquesPorEvento = new HashMap<>();

        for (Consulta consulta : consultas) {
            LocalDateTime diaConsulta = consulta.getAgenda().getDataHora();
            Medico medico = consulta.getMedico();

            for (Evento evento : eventos) {
                LocalDateTime diaEvento = evento.getData();
                if (diaConsulta.toLocalDate().equals(diaEvento.toLocalDate())) {
                    choquesPorMedico.put(medico, choquesPorMedico.getOrDefault(medico, 0) + 1);
                    choquesPorEvento.put(evento, choquesPorEvento.getOrDefault(evento, 0) + 1);
                }
            }
        }

        System.out.println("==== RELATÓRIO: Choques de dia entre clínica e eventos ====");
        System.out.println("\n>> Médicos com mais consultas em dias que também têm evento:");
        choquesPorMedico.entrySet().stream()
            .sorted(Map.Entry.<Medico, Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(entry -> System.out.println("- " + entry.getKey().getNome() + " | " + entry.getValue() + " conflitos"));

        System.out.println("\n>> Eventos que mais coincidem com consultas médicas:");
        choquesPorEvento.entrySet().stream()
            .sorted(Map.Entry.<Evento, Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(entry -> {
                Evento e = entry.getKey();
                System.out.println("- " + e.getNome() + " | " + entry.getValue() + " conflitos | " + e.getTotalInscritos() + " inscritos");
            });

        System.out.println("\n===============================================================\n");
    }

    // ---- PERGUNTA 7 ----
    // O cliente da clínica que visita pelo menos um evento, tem um gasto maior na clínica ou no evento ?
    public static void compararGastosCliente(List<Consulta> consultas, List<Evento> eventos) {
        if (consultas.isEmpty() || eventos.isEmpty()) {
            System.out.println("É necessário ter consultas e eventos cadastrados para realizar a comparação.");
            return;
        }

        Map<String, Double> gastoClinica = new HashMap<>();
        Map<String, Double> gastoEventos = new HashMap<>();

        // Gasto total na clínica por paciente
        for (Consulta c : consultas) {
            if (c == null || c.getPaciente() == null) continue;
            String nome = c.getPaciente().getNome().trim().toLowerCase();
            gastoClinica.put(nome, gastoClinica.getOrDefault(nome, 0.0) + c.getValor());
        }

        // Gasto total em eventos por participante
        for (Evento e : eventos) {
            for (Participante p : e.getParticipantes()) {
                if (p == null) continue;
                String nome = p.getNome().trim().toLowerCase();
                gastoEventos.put(nome, gastoEventos.getOrDefault(nome, 0.0) + e.getValorEvento());
            }
        }

        System.out.println("\n=== RELATÓRIO: Comparação de Gastos Clínica x Evento ===");
        boolean encontrou = false;

        for (String nome : gastoClinica.keySet()) {
            if (gastoEventos.containsKey(nome)) {
                encontrou = true;
                double valorClinica = gastoClinica.get(nome);
                double valorEvento = gastoEventos.get(nome);

                System.out.println("\nCliente: " + nome);
                System.out.printf(" - Gasto na clínica: R$ %.2f\n", valorClinica);
                System.out.printf(" - Gasto em eventos: R$ %.2f\n", valorEvento);

                if (valorClinica > valorEvento)
                    System.out.println(" → Gastou mais na CLÍNICA.");
                else if (valorEvento > valorClinica)
                    System.out.println(" → Gastou mais em EVENTOS.");
                else
                    System.out.println(" → Gastos iguais em ambos.");
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum cliente foi encontrado em ambos os serviços (clínica e eventos).");
        }
    }

    // ---- PERGUNTA 8 ----
    // Um médico fez a recomendação de um prato em determinado dia; após esse dia, quantos pedidos desse prato foram feitos?
    public static void pratosRecomendadosPorMedico(List<Pedido> pedidos, Scanner sc) {
        if (pedidos.isEmpty()) {
            System.out.println("Não há pedidos registrados para realizar o relatório.");
            return;
        }

        System.out.print("Digite o nome do prato recomendado pelo médico: ");
        String nomePrato = sc.nextLine().trim().toLowerCase();

        System.out.print("Digite a data da recomendação (DD/MM/AAAA): ");
        String dataStr = sc.nextLine();
        String[] partesData = dataStr.split("/");
        int dia = Integer.parseInt(partesData[0]);
        int mes = Integer.parseInt(partesData[1]);
        int ano = Integer.parseInt(partesData[2]);
        LocalDate dataRecomendacao = LocalDate.of(ano, mes, dia);

        int contador = 0;

        for (Pedido pedido : pedidos) {
            if (pedido.getAgendaPedido() == null) continue;
            LocalDate dataPedido = pedido.getAgendaPedido().getDataHora().toLocalDate();
            if (dataPedido.isAfter(dataRecomendacao)) {
                for (Prato prato : pedido.getPratos()) {
                    if (prato.getNome().trim().toLowerCase().equals(nomePrato)) {
                        contador++;
                    }
                }
            }
        }

        System.out.println("\n=== RELATÓRIO: Impacto da Recomendação Médica ===");
        System.out.println("Prato recomendado: " + nomePrato);
        System.out.println("Data da recomendação: " + dataRecomendacao);
        System.out.println("Pedidos do prato após essa data: " + contador);
    }

    // ---- RELATÓRIO DE PEDIDOS NO DIA DO EVENTO ----
    public static void mediaPedidosEvento(List<Evento> listaEventos, List<Pedido> listaPedidos, Scanner sc) {
        if (listaEventos.isEmpty() || listaPedidos.isEmpty()) {
            System.out.println("É necessário ter ao menos um evento e um pedido registrados.");
            return;
        }

        System.out.print("Digite o nome do evento gastronômico: ");
        String nomeEvento = sc.nextLine();

        Evento evento = null;
        for (Evento e : listaEventos) {
            if (e.getNome().equalsIgnoreCase(nomeEvento)) {
                evento = e;
                break;
            }
        }

        if (evento == null) {
            System.out.println("Evento não encontrado.");
            return;
        }

        if (evento.getDataHoraEvento() == null) {
            System.out.println("Evento sem data registrada.");
            return;
        }

        LocalDate dataEvento = evento.getDataHoraEvento().toLocalDate();
        List<Pedido> pedidosMesmoDia = new ArrayList<>();

        for (Pedido pedido : listaPedidos) {
            if (pedido.getAgendaPedido() == null) continue;
            LocalDate dataPedido = pedido.getAgendaPedido().getDataHora().toLocalDate();
            if (dataPedido.isEqual(dataEvento)) {
                pedidosMesmoDia.add(pedido);
            }
        }

        if (pedidosMesmoDia.isEmpty()) {
            System.out.println("Nenhum pedido foi feito no dia do evento (" + dataEvento + ").");
            return;
        }

        double soma = 0;
        int totalPratos = 0;

        for (Pedido pedido : pedidosMesmoDia) {
            for (Prato prato : pedido.getPratos()) {
                soma += prato.getPreco();
                totalPratos++;
            }
        }

        double media = soma / totalPratos;

        System.out.println("\n=== Relatório: Consumo Durante o Evento ===");
        System.out.println("Evento: " + evento.getNome());
        System.out.println("Data do evento: " + dataEvento);
        System.out.println("Pedidos feitos no mesmo dia: " + pedidosMesmoDia.size());
        System.out.println("Total de pratos analisados: " + totalPratos);
        System.out.printf("Preço médio dos pedidos durante o evento: R$ %.2f%n", media);
    }

    // ---- CLIENTES EM MAIS DE UM SERVIÇO ----
    public static Map<String, ClienteMultiServico> clientesEmMaisDeUmServico(List<Consulta> consultas, List<Evento> eventos) {
        Map<String, String> nomeOriginalPaciente = new HashMap<>();
        Map<String, Long> consultasPorNome = new HashMap<>();

        for (Consulta c : consultas) {
            if (c == null || c.getPaciente() == null || c.getPaciente().getNome() == null) continue;
            String nome = c.getPaciente().getNome().trim();
            String key = nome.toLowerCase();
            nomeOriginalPaciente.putIfAbsent(key, nome);
            consultasPorNome.put(key, consultasPorNome.getOrDefault(key, 0L) + 1L);
        }

        Map<String, ClienteMultiServico> resposta = new LinkedHashMap<>();
        for (Evento e : eventos) {
            if (e == null || e.getParticipantes() == null) continue;
            for (Participante p : e.getParticipantes()) {
                if (p == null || p.getNome() == null) continue;
                String keyPart = p.getNome().trim().toLowerCase();

                if (consultasPorNome.containsKey(keyPart)) {
                    String nomeBonito = nomeOriginalPaciente.getOrDefault(keyPart, p.getNome());
                    ClienteMultiServico info = resposta.computeIfAbsent(nomeBonito, k -> new ClienteMultiServico(nomeBonito));
                    info.qtdConsultas = consultasPorNome.get(keyPart).intValue();
                    info.eventos.add(e.getNome());
                }
            }
        }

        resposta.entrySet().removeIf(en -> en.getValue().qtdConsultas <= 0);
        return resposta;
    }

    public static void listarClientesEmMaisDeUmServico(List<Consulta> consultas, List<Evento> eventos) {
        Map<String, ClienteMultiServico> mapa = clientesEmMaisDeUmServico(consultas, eventos);

        if (mapa.isEmpty()) {
            System.out.println("Nenhum cliente foi encontrado em mais de um serviço (clínica e eventos).");
            return;
        }

        System.out.println("\n=== Clientes presentes em mais de um serviço (Clínica e Eventos) ===");
        mapa.values().forEach(c -> {
            System.out.println("- " + c.nome + " | Consultas: " + c.qtdConsultas + " | Eventos: " + String.join(", ", c.eventos));
        });
        System.out.println("Total: " + mapa.size() + " cliente(s)");
    }

    public static class ClienteMultiServico {
        public String nome;
        public int qtdConsultas = 0;
        public List<String> eventos = new ArrayList<>();
        public ClienteMultiServico(String nome) { this.nome = nome; }
    }

    // ---- PERCENTUAL DE COMPARECIMENTO ----
    public static double percentualComparecimentoClinica(List<Consulta> consultas) {
        if (consultas == null || consultas.isEmpty()) return 0.0;
        int total = consultas.size();
        long faltas = consultas.stream().filter(c -> c == null ? false : !c.getComparecimento_Consulta()).count();
        double comparecimentos = total - faltas;
        return (comparecimentos / total) * 100.0;
    }

    public static double percentualComparecimentoEventos(List<Evento> eventos) {
        if (eventos == null || eventos.isEmpty()) return 0.0;
        int total = eventos.size();
        long faltas = eventos.stream().filter(e -> e == null ? false : !e.getComparecimentoEvento()).count();
        double comparecimentos = total - faltas;
        return (comparecimentos / total) * 100.0;
    }

    public static double percentualComparecimentoGeral(List<Consulta> consultas, List<Evento> eventos) {
        int totalConsultas = (consultas == null ? 0 : consultas.size());
        int totalEventos = (eventos == null ? 0 : eventos.size());
        int totalAgendas = totalConsultas + totalEventos;
        if (totalAgendas == 0) return 0.0;

        long faltasClinica = consultas == null ? 0L : consultas.stream().filter(c -> c != null && !c.getComparecimento_Consulta()).count();
        long faltasEventos = eventos == null ? 0L : eventos.stream().filter(e -> e != null && !e.getComparecimentoEvento()).count();
        long faltasTotais = faltasClinica + faltasEventos;
        double comparecimentos = totalAgendas - faltasTotais;
        return (comparecimentos / totalAgendas) * 100.0;
    }

    public static void listarPercentuaisComparecimento(List<Consulta> consultas, List<Evento> eventos) {
        int totalConsultas = (consultas == null ? 0 : consultas.size());
        int totalEventos = (eventos == null ? 0 : eventos.size());
        double pctClinica = percentualComparecimentoClinica(consultas);
        double pctEventos = percentualComparecimentoEventos(eventos);
        double pctGeral = percentualComparecimentoGeral(consultas, eventos);

        System.out.println("\n=== Percentual de Comparecimento ===");
        System.out.printf("Clínica: %.2f%% (%d consultas)\n", pctClinica, totalConsultas);
        System.out.printf("Eventos: %.2f%% (%d eventos)\n", pctEventos, totalEventos);
        System.out.printf("Geral  : %.2f%% (%d agendas)\n", pctGeral, (totalConsultas + totalEventos));
    }
}
