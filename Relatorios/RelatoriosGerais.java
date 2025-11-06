package Relatorios;

import Clinica.*;
import Eventos.*;
import Restaurante.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class RelatoriosGerais {

    protected List<Consulta> consultas;
    private List<Evento> eventos;

    public RelatoriosGerais(List<Consulta> consultas, List<Evento> eventos, List<Pedido> pedidos) {
        this.consultas = consultas;
        this.eventos = eventos;
    }

    // ===== CASE 5: Choques de dia entre médicos e eventos =====
    public void relatorioChoquesMedicoEvento() {
        Map<Medico, Integer> choquesPorMedico = new HashMap<>();
        Map<Evento, Integer> choquesPorEvento = new HashMap<>();

        for (Consulta consulta : consultas) {
            if (consulta == null || consulta.getAgenda() == null) continue;
            LocalDate dataConsulta = consulta.getAgenda().getDataHora().toLocalDate();
            Medico medico = consulta.getMedico();
            for (Evento evento : eventos) {
                if (evento == null || evento.getData() == null) continue;
                LocalDate dataEvento = evento.getData().toLocalDate();
                if (dataConsulta.equals(dataEvento)) {
                    choquesPorMedico.put(medico, choquesPorMedico.getOrDefault(medico, 0) + 1);
                    choquesPorEvento.put(evento, choquesPorEvento.getOrDefault(evento, 0) + 1);
                }
            }
        }

        System.out.println("\n==== RELATORIO: Choques de dia entre clínica e eventos ====");
        System.out.println("\nMédico                         | Conflitos");
        System.out.println("-------------------------------------------");
        choquesPorMedico.entrySet().stream()
                .sorted(Map.Entry.<Medico,Integer>comparingByValue().reversed())
                .limit(10)
                .forEach(e -> System.out.printf("%-30s | %d%n", e.getKey().getNome(), e.getValue()));

        System.out.println("\nEvento                         | Conflitos");
        System.out.println("-------------------------------------------");
        choquesPorEvento.entrySet().stream()
                .sorted(Map.Entry.<Evento,Integer>comparingByValue().reversed())
                .limit(10)
                .forEach(e -> System.out.printf("%-30s | %d%n", e.getKey().getNome(), e.getValue()));

        System.out.println("\n===============================================================\n");
    }

    // ===== CASE 6 =====
    public void relatorioPacientesFaltaramCompareceramEvento(List<Consulta> listaConsultas, List<Evento> listaEventos) {
        System.out.println("\n=== PACIENTES QUE FALTARAM CONSULTAS E COMPARECERAM A EVENTOS ===\n");
        boolean encontrou = false;

        for (Consulta consulta : listaConsultas) {
            if (consulta == null) continue;
            if (!consulta.getComparecimento_Consulta()) {
                String nomePaciente = consulta.getPaciente().getNome();
                for (Evento evento : listaEventos) {
                    if (evento == null || evento.getParticipantes() == null) continue;
                    for (Participante participante : evento.getParticipantes()) {
                        if (participante != null && participante.getNome().equalsIgnoreCase(nomePaciente)) {
                            System.out.printf("%-30s -> %s%n", nomePaciente, evento.getNome());
                            encontrou = true;
                        }
                    }
                }
            }
        }

        if (!encontrou)
            System.out.println("Nenhum paciente que faltou à consulta compareceu a eventos.");
        System.out.println("\n=========================================================\n");
    }

    // ===== CASE 7 =====
    public static void compararGastosCliente(List<Consulta> consultas, List<Evento> eventos) {
        Map<String, Double> gastoClinica = new HashMap<>();
        Map<String, Double> gastoEventos = new HashMap<>();

        for (Consulta c : consultas) {
            if (c == null || c.getPaciente() == null) continue;
            String nome = c.getPaciente().getNome().trim().toLowerCase();
            gastoClinica.put(nome, gastoClinica.getOrDefault(nome, 0.0) + c.getValor());
        }

        for (Evento e : eventos) {
            if (e == null || e.getParticipantes() == null) continue;
            double valorEvento = e.getValorEvento();
            for (Participante p : e.getParticipantes()) {
                if (p == null) continue;
                String nome = p.getNome().trim().toLowerCase();
                gastoEventos.put(nome, gastoEventos.getOrDefault(nome, 0.0) + valorEvento);
            }
        }

        class Linha {
            String nome;
            double clinica, eventos, total;
            Linha(String n, double c, double e) { nome=n; clinica=c; eventos=e; total=c+e; }
        }
        List<Linha> linhas = new ArrayList<>();
        for (String nome : gastoClinica.keySet()) {
            if (gastoEventos.containsKey(nome))
                linhas.add(new Linha(nome, gastoClinica.get(nome), gastoEventos.get(nome)));
        }

        linhas.sort((a,b) -> Double.compare(b.total, a.total));

        System.out.println("\n=== RELATORIO: Comparação de Gastos (Clínica x Eventos) ===");
        System.out.println("Cliente                        | Gasto Clínica | Gasto Eventos | Maior Gasto");
        System.out.println("--------------------------------------------------------------------------");
        for (Linha l : linhas) {
            String maior = (l.clinica > l.eventos) ? "Clínica" : (l.eventos > l.clinica ? "Eventos" : "Empate");
            System.out.printf("%-30s | R$ %11.2f | R$ %12.2f | %s%n",
                    capitalize(l.nome), l.clinica, l.eventos, maior);
        }
        System.out.println();
    }

    // ===== CASE 8 =====
    public static void impactoRecomendacaoMedicaAuto(List<Pedido> pedidos) {
        if (pedidos == null || pedidos.isEmpty()) {
            System.out.println("Não há pedidos registrados para realizar o relatório.");
            return;
        }

        List<LocalDate> datas = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getAgendaPedido() != null)
                datas.add(p.getAgendaPedido().getDataHora().toLocalDate());
        }
        datas.sort(Comparator.naturalOrder());
        LocalDate dataRecomendacao = datas.get(datas.size()/2);

        Map<String, Integer> contagem = new HashMap<>();
        for (Pedido p : pedidos)
            for (Prato pr : p.getPratos())
                contagem.merge(pr.getNome().trim().toLowerCase(), 1, Integer::sum);

        String pratoMais = contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get().getKey();

        int pedidosApos = 0;
        for (Pedido p : pedidos)
            if (p.getAgendaPedido().getDataHora().toLocalDate().isAfter(dataRecomendacao))
                for (Prato pr : p.getPratos())
                    if (pr.getNome().trim().equalsIgnoreCase(pratoMais)) pedidosApos++;

        System.out.println("\n=== RELATORIO: Impacto da Recomendaçao Médica ===");
        System.out.printf("Prato recomendado: %s%n", capitalize(pratoMais));
        System.out.printf("Data da recomendação: %s%n", dataRecomendacao);
        System.out.printf("Pedidos desse prato após essa data: %d%n", pedidosApos);
        System.out.println();
    }

    // ===== CASE 9 =====
    public void relatorioServicoMaisLucrativo(List<Consulta> consultas, List<Pedido> pedidos, List<Evento> eventos) {
        double totalClinica = consultas.stream().mapToDouble(Consulta::getValor).sum();
        double totalEventos = eventos.stream().mapToDouble(Evento::getValorEvento).sum();
        double totalRestaurante = 0;
        for (Pedido p : pedidos)
            for (Prato pr : p.getPratos())
                totalRestaurante += pr.getPreco();

        System.out.println("\n=== RELATORIO: Serviço mais lucrativo ===");
        System.out.printf("Clínica     : R$ %.2f%n", totalClinica);
        System.out.printf("Eventos     : R$ %.2f%n", totalEventos);
        System.out.printf("Restaurante : R$ %.2f%n", totalRestaurante);
        System.out.println("------------------------------------------");

        double max = Math.max(totalClinica, Math.max(totalEventos, totalRestaurante));
        if (max == totalClinica) System.out.println("Serviço mais lucrativo: CLÍNICA");
        else if (max == totalEventos) System.out.println("Serviço mais lucrativo: EVENTOS");
        else System.out.println("Serviço mais lucrativo: RESTAURANTE");
    }

    // ===== CASE 10 =====
    public void relatorioFaixaHorarioMaisFrequente(List<Consulta> consultas, List<Pedido> pedidos, List<Evento> eventos) {
        Map<String, Integer> contagem = new HashMap<>();

        for (Consulta c : consultas)
            contagem.merge(faixa(c.getAgenda().getDataHora().getHour()), 1, Integer::sum);

        for (Pedido p : pedidos)
            contagem.merge(faixa(p.getAgendaPedido().getDataHora().getHour()), 1, Integer::sum);

        for (Evento e : eventos)
            contagem.merge(faixa(e.getDataHoraEvento().getHour()), 1, Integer::sum);

        String maisFrequente = contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("Indefinido");

        System.out.println("\n=== RELATORIO: Faixa de horário mais frequente ===");
        for (Map.Entry<String,Integer> e : contagem.entrySet())
            System.out.printf("%-15s : %d ocorrências%n", e.getKey(), e.getValue());
        System.out.println("--------------------------------------------");
        System.out.println("Faixa mais frequentada: " + maisFrequente);
    }

    private String faixa(int h) {
        if (h < 12) return "Manhã";
        else if (h < 18) return "Tarde";
        else return "Noite";
    }

    // ===== CASE 11 =====
    public static void mediaPedidosEventoTodos(List<Evento> listaEventos, List<Pedido> listaPedidos) {
        System.out.println("\n=== RELATORIO: Consumo Durante os Eventos ===");
        System.out.println("Evento                         | Data        | Pedidos no dia | Total Pratos | Média (R$)");
        System.out.println("--------------------------------------------------------------------------------------");
        for (Evento evento : listaEventos) {
            LocalDate dataEvento = evento.getDataHoraEvento().toLocalDate();
            List<Pedido> noDia = listaPedidos.stream()
                    .filter(p -> p.getAgendaPedido().getDataHora().toLocalDate().isEqual(dataEvento))
                    .collect(Collectors.toList());
            double soma = 0;
            int total = 0;
            for (Pedido p : noDia)
                for (Prato pr : p.getPratos()) {
                    soma += pr.getPreco();
                    total++;
                }
            double media = total > 0 ? soma / total : 0;
            System.out.printf("%-30s | %s | %14d | %12d | %9.2f%n",
                    evento.getNome(), dataEvento, noDia.size(), total, media);
        }
    }

    // ===== CASE 12 =====
    public static void listarClientesEmMaisDeUmServico(List<Consulta> consultas, List<Evento> eventos) {
        Set<String> clientesClinica = consultas.stream()
                .map(c -> c.getPaciente().getNome().toLowerCase()).collect(Collectors.toSet());
        Set<String> clientesEventos = new HashSet<>();
        for (Evento e : eventos)
            for (Participante p : e.getParticipantes())
                clientesEventos.add(p.getNome().toLowerCase());

        clientesClinica.retainAll(clientesEventos);
        System.out.println("\n=== RELATORIO: Clientes em mais de um serviço ===");
        if (clientesClinica.isEmpty())
            System.out.println("Nenhum cliente participa de mais de um serviço.");
        else
            clientesClinica.forEach(c -> System.out.println(capitalize(c)));
    }

    // ===== CASE 13 =====
    public static void listarPercentuaisComparecimento(List<Consulta> consultas, List<Evento> eventos) {
        long totalConsultas = consultas.size();
        long compareceramConsultas = consultas.stream().filter(Consulta::getComparecimento_Consulta).count();
        double pctConsultas = totalConsultas > 0 ? (compareceramConsultas * 100.0 / totalConsultas) : 0;

        long totalEventos = eventos.size();
        long compareceramEventos = eventos.stream().filter(Evento::getComparecimentoEvento).count();
        double pctEventos = totalEventos > 0 ? (compareceramEventos * 100.0 / totalEventos) : 0;

        System.out.println("\n=== RELATORIO: Percentual de Comparecimento ===");
        System.out.printf("Consultas realizadas: %.1f%% (%d/%d)%n", pctConsultas, compareceramConsultas, totalConsultas);
        System.out.printf("Eventos confirmados: %.1f%% (%d/%d)%n", pctEventos, compareceramEventos, totalEventos);
    }

    // ===== Helpers =====
    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }
}
