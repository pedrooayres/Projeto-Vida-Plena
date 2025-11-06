package Base;

import Clinica.*;
import Eventos.*;
import Restaurante.*;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Carrega dados de Base/dados.txt nas listas do sistema.
 * Formatos aceitos no arquivo (linhas; separador ';'):
 *
 *  CONSULTA;Paciente;Medico;DD/MM/AAAA;HH:mm;valor;compareceu(boolean)
 *  EVENTO;NomeEvento;NomeLocal;Capacidade;ValorEvento;Tipo;DD/MM/AAAA;HH:mm;comparecimento(boolean)
 *  PARTICIPANTE;NomeEvento;Nome;CPF;Contato
 *  PEDIDO;Prato;Preco;Categoria;Calorias;DD/MM/AAAA;HH:mm
 *
 * Linhas em branco e que começam com '#' são ignoradas.
 */
public final class CarregadorDeDados {

    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter HF = DateTimeFormatter.ofPattern("HH:mm");

    private CarregadorDeDados() {}

    public static void carregarTudo(List<Consulta> outConsultas,
                                    List<Evento> outEventos,
                                    List<Pedido> outPedidos) throws IOException {

        System.out.println("==========================================");
        System.out.println("• Carregando dados automáticos do sistema");
        System.out.println("==========================================");

        Path caminho = resolverCaminhoDados();
        if (caminho == null) {
            throw new IOException("Não encontrei Base/dados.txt em nenhum dos caminhos esperados.");
        }

        System.out.println("→ Lendo arquivo: " + caminho.toAbsolutePath());
        List<String> linhas = Files.readAllLines(caminho, StandardCharsets.UTF_8);
        System.out.println("→ Linhas lidas: " + linhas.size());

        // Índices auxiliares para reuso de objetos por nome
        Map<String, Paciente> pacientes = new HashMap<>();
        Map<String, Medico> medicos = new HashMap<>();
        Map<String, Evento> eventosPorNome = new HashMap<>();

        int ctConsultas = 0, ctEventos = 0, ctPedidos = 0, ctParticipantes = 0;

        for (String raw : linhas) {
            if (raw == null) continue;
            String line = stripBOM(raw).trim();
            if (line.isEmpty() || line.startsWith("#")) continue;

            String[] p = line.split(";");
            String tipo = p[0].trim().toUpperCase(Locale.ROOT);

            try {
                switch (tipo) {
                    case "CONSULTA": {
                        // CONSULTA;Paciente;Medico;DD/MM/AAAA;HH:mm;valor;compareceu
                        if (p.length < 7) break;

                        String nomePac = p[1].trim();
                        String nomeMed = p[2].trim();
                        LocalDate data = LocalDate.parse(p[3].trim(), DF);
                        LocalTime hora = LocalTime.parse(p[4].trim(), HF);
                        double valor = Double.parseDouble(p[5].trim());
                        boolean compareceu = Boolean.parseBoolean(p[6].trim());

                        Paciente paciente = pacientes.computeIfAbsent(
                                key(nomePac), k -> new Paciente(nomePac, "000", "N/A"));
                        Medico medico = medicos.computeIfAbsent(
                                key(nomeMed), k -> new Medico(nomeMed, "000", "N/A", "GERAL", "CRM-000"));

                        LocalDateTime dt = LocalDateTime.of(data, hora);
                        Agenda agenda = new Agenda(dt, "REGISTRADO");
                        Consulta c = new Consulta(paciente, medico, agenda, valor, compareceu);
                        outConsultas.add(c);
                        ctConsultas++;
                        break;
                    }
                    case "EVENTO": {
                        // EVENTO;NomeEvento;NomeLocal;Capacidade;Valor;Tipo;DD/MM/AAAA;HH:mm;comparecimento
                        if (p.length < 9) break;

                        String nomeEv = p[1].trim();
                        String nomeLocal = p[2].trim();
                        int cap = Integer.parseInt(p[3].trim());
                        double valorEv = Double.parseDouble(p[4].trim());
                        String tipoEv = p[5].trim();
                        LocalDate data = LocalDate.parse(p[6].trim(), DF);
                        LocalTime hora = LocalTime.parse(p[7].trim(), HF);
                        boolean comp = Boolean.parseBoolean(p[8].trim());

                        Local local = new Local(nomeLocal, "Endereço " + nomeLocal, cap);
                        LocalDateTime dt = LocalDateTime.of(data, hora);
                        Evento ev = new Evento(nomeEv, local, cap, valorEv, tipoEv, dt, comp);
                        outEventos.add(ev);
                        eventosPorNome.put(key(nomeEv), ev);
                        ctEventos++;
                        break;
                    }
                    case "PARTICIPANTE": {
                        // PARTICIPANTE;NomeEvento;Nome;CPF;Contato
                        if (p.length < 5) break;

                        String nomeEv = p[1].trim();
                        String nomePart = p[2].trim();
                        String cpf = p[3].trim();
                        String contato = p[4].trim();

                        Evento ev = eventosPorNome.get(key(nomeEv));
                        if (ev == null) {
                            // tenta achar nos já carregados (ordem diferente no arquivo)
                            ev = buscarEventoPorNome(outEventos, nomeEv);
                            if (ev != null) {
                                eventosPorNome.put(key(nomeEv), ev);
                            }
                        }
                        if (ev != null) {
                            ev.inscrever(new Participante(nomePart, cpf, contato));
                            ctParticipantes++;
                        }
                        break;
                    }
                    case "PEDIDO": {
                        // PEDIDO;Prato;Preco;Categoria;Calorias;DD/MM/AAAA;HH:mm
                        if (p.length < 7) break;

                        String nomePrato = p[1].trim();
                        double preco = Double.parseDouble(p[2].trim());
                        String categoria = p[3].trim();
                        int calorias = Integer.parseInt(p[4].trim());
                        LocalDate data = LocalDate.parse(p[5].trim(), DF);
                        LocalTime hora = LocalTime.parse(p[6].trim(), HF);

                        Prato prato = new Prato(nomePrato, preco, categoria, calorias);
                        Agenda agenda = new Agenda(LocalDateTime.of(data, hora), "DATA DO PEDIDO CONFIRMADA");

                        Pedido pedido = new Pedido();
                        pedido.adicionarPrato(prato, agenda);
                        outPedidos.add(pedido);
                        ctPedidos++;
                        break;
                    }
                    default:
                        // ignora linhas desconhecidas
                        break;
                }
            } catch (Exception ex) {
                System.out.println("! Linha ignorada (erro de parsing): " + line);
                System.out.println("  Motivo: " + ex.getMessage());
            }
        }

        System.out.println("→ Consultas carregadas: " + ctConsultas);
        System.out.println("→ Eventos carregados: " + ctEventos + " | Participantes vinculados: " + ctParticipantes);
        System.out.println("→ Pedidos carregados: " + ctPedidos);
        System.out.println("✓ Dados carregados com sucesso!");
    }

    // ===== utilitários =====

    private static String key(String s) {
        return (s == null ? "" : s.trim().toLowerCase(Locale.ROOT));
    }

    private static String stripBOM(String s) {
        if (s != null && !s.isEmpty() && s.charAt(0) == '\uFEFF') {
            return s.substring(1);
        }
        return s;
    }

    private static Path buscarCaminho(Path baseDir) {
        if (baseDir == null) return null;
        Path p1 = baseDir.resolve("Base").resolve("dados.txt");
        if (Files.exists(p1)) return p1;
        Path p2 = baseDir.resolve("dados.txt");
        if (Files.exists(p2)) return p2;
        return null;
    }

    private static Path resolverCaminhoDados() {
        // 1) user.dir (raiz do projeto/execução)
        Path byUserDir = buscarCaminho(Paths.get(System.getProperty("user.dir", ".")));
        if (byUserDir != null) return byUserDir;

        // 2) pasta onde o .class de CarregadorDeDados está (sobe para achar Base/dados.txt)
        try {
            URL url = CarregadorDeDados.class.getProtectionDomain().getCodeSource().getLocation();
            if (url != null) {
                Path here = Paths.get(url.toURI());
                // tenta alguns níveis acima
                for (int i = 0; i < 5; i++) {
                    Path candidate = buscarCaminho(here);
                    if (candidate != null) return candidate;
                    here = here.getParent();
                    if (here == null) break;
                }
            }
        } catch (Exception ignored) {}

        // 3) tentativa bruta: ./Projeto-Vida-Plena-main/Base/dados.txt
        Path brute = Paths.get("Projeto-Vida-Plena-main", "Base", "dados.txt");
        if (Files.exists(brute)) return brute;

        return null;
    }

    private static Evento buscarEventoPorNome(List<Evento> lista, String nome) {
        if (lista == null) return null;
        for (Evento e : lista) {
            if (e != null && e.getNome() != null && e.getNome().equalsIgnoreCase(nome)) {
                return e;
            }
        }
        return null;
    }
}
