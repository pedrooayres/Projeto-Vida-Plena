package Relatorios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import Base.*;
import Clinica.*;
import Eventos.*;
import Restaurante.*;

public class RelatoriosGerais {

    protected List<Consulta> consultas;
    private List<Evento> eventos;
    private List<Prato> restaurante;

    public RelatoriosGerais(List<Consulta> consultas, List<Evento> eventos, List<Pedido> pedidos) {
        this.consultas = consultas;
        this.eventos = eventos;
        this.restaurante = restaurante;
    }

    // ---- PERGUNTA 1 ----
    // Quais médicos e eventos têm maior ocupação nos mesmo dia?
    public void relatorioChoquesMedicoEvento() {

        // Map para contar quantos choques cada médico tem
        Map<Medico, Integer> choquesPorMedico = new HashMap<>();

        // Map para contar quantos choques cada evento tem
        Map<Evento, Integer> choquesPorEvento = new HashMap<>();

        // Para cada consulta e cada evento, verificamos se acontecem no mesmo "bloco de dia"
        // Regra simples: MESMO DIA (ex.: 2025-10-26 às 14h)
        for (Consulta consulta : consultas) {

            LocalDateTime diaConsulta = consulta.getAgenda().getDataHora();
            Medico medico = consulta.getMedico();

            for (Evento evento : eventos) {

                LocalDateTime diaEvento = evento.getData();

                if (diaConsulta.toLocalDate().equals(diaEvento.toLocalDate())) {
                    // contou conflito desse médico neste dia
                    choquesPorMedico.put(
                        medico,
                        choquesPorMedico.getOrDefault(medico, 0) + 1
                    );

                    // contou conflito desse evento neste dia
                    choquesPorEvento.put(
                        evento,
                        choquesPorEvento.getOrDefault(evento, 0) + 1
                    );
                }
            }
        }

        // Agora vamos imprimir um ranking
        System.out.println("==== RELATÓRIO: Choques de dia entre clínica e eventos ====");

        // Top médicos que mais "trabalham" em horários que também têm evento
        System.out.println("\n>> Médicos com mais consultas em dias que também têm evento:");
        choquesPorMedico.entrySet().stream()
            .sorted(Map.Entry.<Medico,Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(entry -> {
                Medico m = entry.getKey();
                Integer qtd = entry.getValue();
                System.out.println("- " + m.getNome() + " | " + qtd + " conflitos de dia com eventos");
            });

        // Top eventos que mais batem com consultas
        System.out.println("\n>> Eventos que mais coincidem com consultas médicas:");
        choquesPorEvento.entrySet().stream()
            .sorted(Map.Entry.<Evento,Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(entry -> {
                Evento e = entry.getKey();
                Integer qtd = entry.getValue();

                // participantes pode ser útil pra contextualizar "ocupação"
                int inscritos = e.getTotalInscritos();

                System.out.println("- " + e.getNome()
                    + " | " + qtd + " conflitos de dia com consultas"
                    + " | " + inscritos + " inscritos");
            });

        System.out.println("\n===============================================================\n");
    }

// ---- PERGUNTA 2 ----
public void relatorioPacientesFaltaramCompareceramEvento(
        List<Consulta> listaConsultas,
        List<Evento> listaEventos) {
    System.out.println("\n=== PACIENTES QUE FALTARAM E COMPARECERAM A EVENTOS ===\n");

    boolean encontrou = false;
    for (Consulta consulta : listaConsultas) {
        if (!consulta.getComparecimento_Consulta()) { // ou consulta.getCompareceu() == false
            String nomePaciente = consulta.getPaciente().getNome();

            for (Evento evento : listaEventos) {
                for (Participante participante : evento.getParticipantes()) {
                    if (participante.getNome().equalsIgnoreCase(nomePaciente)) {
                        System.out.println("- " + nomePaciente +
                            " → " + evento.getNome());
                        encontrou = true;
                    }
                }
            }
        }
    }

    if (!encontrou) {
        System.out.println("Nenhum paciente que faltou à consulta compareceu a eventos.");
    }

    System.out.println("\n=========================================================\n");
}
// ---- PERGUNTA 3 ----
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
// ---- PERGUNTA 4 ----
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
// ---- PERGUNTA 5 ----
public void relatorioServicoMaisLucrativo(List<Consulta> consultas, List<Pedido> pedidos, List<Evento> eventos) {
    System.out.println("\n========== RELATÓRIO: SERVIÇO MAIS LUCRATIVO ==========");

    double totalClinica = 0;
    for (Consulta c : consultas) {
        totalClinica += c.getValor();
    }

    double totalRestaurante = 0;
    for (Pedido p : pedidos) {
        totalRestaurante += p.getValorTotal();
    }

    double totalEventos = 0;
    for (Evento e : eventos) {
        totalEventos += e.getValorEvento();
    }

    System.out.println("Receita total da Clínica: R$ " + totalClinica);
    System.out.println("Receita total do Restaurante: R$ " + totalRestaurante);
    System.out.println("Receita total dos Eventos: R$ " + totalEventos);

    if (totalClinica > totalRestaurante && totalClinica > totalEventos) {
        System.out.println("➡ O setor mais lucrativo é a CLÍNICA.");
    } else if (totalRestaurante > totalClinica && totalRestaurante > totalEventos) {
        System.out.println("➡ O setor mais lucrativo é o RESTAURANTE.");
    } else if (totalEventos > totalClinica && totalEventos > totalRestaurante) {
        System.out.println("➡ O setor mais lucrativo é o de EVENTOS.");
    } else {
        System.out.println("➡ Há empate entre os setores.");
    }

    System.out.println("========================================================");
}
// ---- PERGUNTA 6 ----
public void relatorioFaixaHorarioMaisFrequente(List<Consulta> consultas,List<Pedido> pedidos,List<Evento> eventos) {

        Map<Integer, Integer> contadorConsultas = new HashMap<>();
        Map<Integer, Integer> contadorPedidos = new HashMap<>();
        Map<Integer, Integer> contadorEventos = new HashMap<>();

        // === CLÍNICA ===
        for (Consulta c : consultas) {
            try {
                if (c.getHorario() != null) {
                    int hora = ((LocalDateTime) c.getHorario()).getHour();
                    contadorConsultas.put(hora, contadorConsultas.getOrDefault(hora, 0) + 1);
                }
            } catch (Exception ignored) {}
        }

        // === RESTAURANTE ===
        for (Pedido p : pedidos) {
            try {
                if (p.getDataHora() != null) {
                    int hora = p.getDataHora().getHour();
                    contadorPedidos.put(hora, contadorPedidos.getOrDefault(hora, 0) + 1);
                }
            } catch (Exception ignored) {}
        }

        // === EVENTOS ===
        /* for (Evento e : eventos) {
            LocalDateTime horario = null;
            try {
                if (e.getHorario() != null)
                    horario = e.getHorario();
                else if (e.getDataHora() != null)
                    horario = e.getDataHora();
            } catch (Exception ignored) {}

            if (horario != null) {
                int hora = horario.getHour();
                contadorEventos.put(hora, contadorEventos.getOrDefault(hora, 0) + 1);
            }
        } */

        // === Determina o horário mais frequente em cada setor ===
        Integer horaMaisConsultas = contadorConsultas.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        Integer horaMaisPedidos = contadorPedidos.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        Integer horaMaisEventos = contadorEventos.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        // === Exibição ===
        System.out.println("\n========== RELATÓRIO DE FAIXA DE HORÁRIO MAIS FREQUENTADA ==========\n");

        if (horaMaisConsultas != null)
            System.out.printf("Clínica: %02dh (%d consultas)%n",
                    horaMaisConsultas, contadorConsultas.get(horaMaisConsultas));
        else
            System.out.println("Clínica: sem registros de consultas.");

        if (horaMaisPedidos != null)
            System.out.printf("Restaurante: %02dh (%d pedidos)%n",
                    horaMaisPedidos, contadorPedidos.get(horaMaisPedidos));
        else
            System.out.println("Restaurante: sem registros de pedidos.");

       /*/ if (horaMaisEventos != null)
            System.out.printf("Eventos: %02dh (%d eventos)%n",
                    horaMaisEventos, contadorEventos.get(horaMaisEventos));
        else
            System.out.println("Eventos: sem registros de eventos."); */
        System.out.println("\n====================================================================\n");
    }
        
// ---- PERGUNTA 7 ----

// ================== RELATÓRIO: MÉDIA DE PEDIDOS NO DIA DO EVENTO ==================
    public static void mediaPedidosEvento(List<Evento> listaEventos, List<Pedido> listaPedidos, Scanner sc) {
        if (listaEventos.isEmpty() || listaPedidos.isEmpty()) {
            System.out.println("É necessário ter ao menos um evento e um pedido registrados.");
            return;
        }

        System.out.print("Digite o nome do evento gastronômico: ");
        String nomeEvento = sc.nextLine();

        // Busca o evento pelo nome informado
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

        // Garante que o evento possui uma data registrada
        if (evento.getDataHoraEvento() == null) {
            System.out.println("Evento sem data registrada.");
            return;
        }

        LocalDate dataEvento = evento.getDataHoraEvento().toLocalDate();
        List<Pedido> pedidosMesmoDia = new ArrayList<>();

        // Filtra pedidos realizados no mesmo dia do evento
        for (Pedido pedido : listaPedidos) {
            if (pedido.getAgendaPedido() == null) continue; // Evita NullPointer
            LocalDate dataPedido = pedido.getAgendaPedido().getDataHora().toLocalDate();
            if (dataPedido.isEqual(dataEvento)) { // agora compara se é o mesmo dia
                pedidosMesmoDia.add(pedido);
            }
        }

        if (pedidosMesmoDia.isEmpty()) {
            System.out.println("Nenhum pedido foi feito no dia do evento (" + dataEvento + ").");
            return;
        }

        double soma = 0;
        int totalPratos = 0;

        // Calcula a soma e quantidade total de pratos
        for (Pedido pedido : pedidosMesmoDia) {
            for (Prato prato : pedido.getPratos()) {
                soma += prato.getPreco();
                totalPratos++;
            }
        }

        double media = soma / totalPratos;

        // Exibe o relatório final
        System.out.println("\n=== Relatório: Consumo Durante o Evento ===");
        System.out.println("Evento: " + evento.getNome());
        System.out.println("Data do evento: " + dataEvento);
        System.out.println("Pedidos feitos no mesmo dia: " + pedidosMesmoDia.size());
        System.out.println("Total de pratos analisados: " + totalPratos);
        System.out.printf("Preço médio dos pedidos durante o evento: R$ %.2f%n", media);
    }

// ---- PERGUNTA 8 ----
// Retorna um mapa: Nome -> (detalhes: qtdConsultas e eventos em que participou)
    public static Map<String, ClienteMultiServico> clientesEmMaisDeUmServico(List<Consulta> consultas, List<Evento> eventos) {

        Map<String, String> nomeOriginalPaciente = new HashMap<>(); // normalizado -> original
        Map<String, Long> consultasPorNome = new HashMap<>();       // normalizado -> qtd consultas

        // 1) Índice de pacientes (pelas consultas)
        for (Consulta c : consultas) {
            if (c == null || c.getPaciente() == null || c.getPaciente().getNome() == null) continue;
            String nome = c.getPaciente().getNome().trim();
            String key = nome.toLowerCase();
            nomeOriginalPaciente.putIfAbsent(key, nome);
            consultasPorNome.put(key, consultasPorNome.getOrDefault(key, 0L) + 1L);
        }

        // 2) Cruzar com participantes de eventos
        Map<String, ClienteMultiServico> resposta = new LinkedHashMap<>();
        for (Evento e : eventos) {
            if (e == null || e.getParticipantes() == null) continue;
            for (Participante p : e.getParticipantes()) {
                if (p == null || p.getNome() == null) continue;
                String nomePart = p.getNome().trim();
                String keyPart = nomePart.toLowerCase();

                if (consultasPorNome.containsKey(keyPart)) {
                    String nomeBonito = nomeOriginalPaciente.getOrDefault(keyPart, nomePart);

                    ClienteMultiServico info = resposta.computeIfAbsent(
                        nomeBonito, k -> new ClienteMultiServico(nomeBonito)
                    );
                    info.qtdConsultas = consultasPorNome.get(keyPart).intValue();
                    info.eventos.add(e.getNome());
                }
            }
        }
        resposta.entrySet().removeIf(en -> en.getValue().qtdConsultas <= 0);
        return resposta;
    }
    public static void listarClientesEmMaisDeUmServico(
            List<Consulta> consultas, List<Evento> eventos) {

        Map<String, ClienteMultiServico> mapa = clientesEmMaisDeUmServico(consultas, eventos);

        if (mapa.isEmpty()) {
            System.out.println("Nenhum cliente foi encontrado em mais de um serviço (clínica e eventos).");
            return;
        }

        System.out.println("\n=== Clientes presentes em mais de um serviço (Clínica e Eventos) ===");
        mapa.values().forEach(c -> {
            System.out.println("- " + c.nome +
                " | Consultas: " + c.qtdConsultas +
                " | Eventos: " + String.join(", ", c.eventos));
        });
        System.out.println("Total: " + mapa.size() + " cliente(s)");
    }
    public static class ClienteMultiServico {
        public String nome;
        public int qtdConsultas = 0;
        public List<String> eventos = new ArrayList<>();
        public ClienteMultiServico(String nome) { this.nome = nome; }
    }
// ---- PERGUNTA 9 ----


// === Percentual de comparecimento ===
// Observação IMPORTANTE sobre a semântica do boolean:
// • Se NO SEU CÓDIGO "true" significa QUE COMPARECEU, use "!c.getComparecimento_Consulta()" para contar faltas.
// • Se NO SEU CÓDIGO "true" significa QUE FALTOU, troque a condição conforme indicado nos comentários abaixo.

    public static double percentualComparecimentoClinica(List<Consulta> consultas) {
        if (consultas == null || consultas.isEmpty()) return 0.0;
        int total = consultas.size();

        // Supondo: true = compareceu. Então falta = !true
        // Se no seu projeto "true" for FALTOU, use esta linha em vez da acima:
        long faltas = consultas.stream().filter(c -> c != null && c.getComparecimento_Consulta()).count();

        double comparecimentos = total - faltas;
        return (comparecimentos / total) * 100.0;
    }

    public static double percentualComparecimentoEventos(List<Evento> eventos) {
        if (eventos == null || eventos.isEmpty()) return 0.0;

        int total = eventos.size();

        // Supondo: getComparecimentoEvento() == true significa que o evento "compareceu/confirmou".
        long faltas = eventos.stream()
                .filter(e -> e == null ? false : !e.getComparecimentoEvento())
                .count();

        // Se "true" no seu projeto significar FALTOU, troque pela linha abaixo:
        // long faltas = eventos.stream().filter(e -> e != null && e.getComparecimentoEvento()).count();

        double comparecimentos = total - faltas;
        return (comparecimentos / total) * 100.0;
    }

    public static double percentualComparecimentoGeral(List<Consulta> consultas, List<Evento> eventos) {
        int totalConsultas = (consultas == null ? 0 : consultas.size());
        int totalEventos   = (eventos   == null ? 0 : eventos.size());
        int totalAgendas   = totalConsultas + totalEventos;

        if (totalAgendas == 0) return 0.0;

        // --- faltas na clínica ---
        long faltasClinica = 0L;
        if (consultas != null) {
            // Se "true" = FALTOU, use:
            faltasClinica = consultas.stream().filter(c -> c != null && c.getComparecimento_Consulta()).count();
        }

        // --- faltas em eventos ---
        long faltasEventos = 0L;
        if (eventos != null) {
            faltasEventos = eventos.stream()
                    .filter(e -> e != null && !e.getComparecimentoEvento())
                    .count();
            // Se "true" = FALTOU, use:
            faltasEventos = eventos.stream().filter(e -> e != null && e.getComparecimentoEvento()).count();
        }

        long faltasTotais = faltasClinica + faltasEventos;
        double comparecimentos = totalAgendas - faltasTotais;

        return (comparecimentos / totalAgendas) * 100.0;
    }
    public static void listarPercentuaisComparecimento(List<Consulta> consultas, List<Evento> eventos) {
        int totalConsultas = (consultas == null ? 0 : consultas.size());
        int totalEventos   = (eventos   == null ? 0 : eventos.size());

        double pctClinica = percentualComparecimentoClinica(consultas);
        double pctEventos = percentualComparecimentoEventos(eventos);
        double pctGeral   = percentualComparecimentoGeral(consultas, eventos);

        System.out.println("\n=== Percentual de Comparecimento ===");
        System.out.printf("Clínica: %.2f%% (sobre %d consultas agendadas)\n", pctClinica, totalConsultas);
        System.out.printf("Eventos: %.2f%% (sobre %d eventos confirmados)\n", pctEventos, totalEventos);
        System.out.printf("Geral  : %.2f%% (sobre %d agendas totais)\n", pctGeral, (totalConsultas + totalEventos));
    }
}
