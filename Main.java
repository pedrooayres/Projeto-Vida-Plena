import Base.*;
import Clinica.*;
import Eventos.*;
import Relatorios.*;
import Restaurante.*;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    static List<Consulta> listaConsultas = new ArrayList<>();
    static List<Evento> listaEventos = new ArrayList<>();
    static List<Pedido> listaPedidos = new ArrayList<>();

    static RelatoriosGerais relatorios = new RelatoriosGerais(listaConsultas, listaEventos, listaPedidos);

    public static void main(String[] args) {
        // Carregar dados automaticamente
        System.out.println("==========================================");
        System.out.println("Carregando dados automáticos do sistema");
        System.out.println("==========================================");
        try {
            CarregadorDeDados.carregarTudo(listaConsultas, listaEventos, listaPedidos);
            System.out.println("Dados carregados com sucesso!\n");
            System.out.println("Resumo do carregamento:");
            System.out.println("Consultas: " + listaConsultas.size());
            System.out.println("Eventos  : " + listaEventos.size());
            System.out.println("Pedidos  : " + listaPedidos.size());
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }

        int opcao;
        do {
            System.out.println("\n=== RELATÓRIOS VIDA PLENA ===");
            System.out.println("1. Médico mais ocupado");
            System.out.println("2. Horário com mais faltas (clínica) + (eventos)");
            System.out.println("3. Evento com mais inscritos");
            System.out.println("4. Prato mais vendido (manha) + contagem");
            System.out.println("5. Choques de dia entre médicos e eventos");
            System.out.println("6. Pacientes que faltaram consultas e compareceram a eventos");
            System.out.println("7. Comparar gastos (clínica x eventos) por cliente");
            System.out.println("8. Impacto de recomendação médica (automático)");
            System.out.println("9. Serviço mais lucrativo (clínica x restaurante x eventos)");
            System.out.println("10. Faixa de horário mais frequentada (clínica/restaurante/eventos)");
            System.out.println("11. Média dos pedidos no dia de cada evento (todos)");
            System.out.println("12. Clientes presentes em mais de um serviço");
            System.out.println("13. Percentual de comparecimento (consultas e eventos)");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            if (!sc.hasNextInt()) return;
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    Medico m = ClinicaRelatorios.medicoMaisOcupado(listaConsultas);
                    System.out.println(m != null ? ("Médico mais ocupado: " + m.getNome()) : "Sem dados.");
                }
                case 2 -> {
                    System.out.println(ClinicaRelatorios.horarioComMaisFaltasConsultas(listaConsultas));
                    System.out.println(EventosRelatorios.horarioComMaisFaltasEventos(listaEventos));
                }
                case 3 -> {
                    Evento e = EventosRelatorios.eventoComMaisInscritos(listaEventos);
                    if (e != null) {
                        System.out.println("Evento com mais inscritos: " + e.getNome() + " (" + e.getTotalInscritos() + " inscritos)");
                    } else {
                        System.out.println("Nenhum evento encontrado.");
                    }
                }
                case 4 -> {
                    Prato p = RestauranteRelatorios.pratoMaisVendidoPorPeriodo(listaPedidos, "manha");
                    if (p == null) {
                        System.out.println("Nenhum prato encontrado.");
                    } else {
                        int cont = 0;
                        for (Pedido ped : listaPedidos) {
                            for (Prato pr : ped.getPratos()) {
                                if (pr.getNome().equalsIgnoreCase(p.getNome())) cont++;
                            }
                        }
                        System.out.println("Prato mais vendido (manha): " + p.getNome() + " | Quantidade pedida: " + cont);
                    }
                }
                case 5 -> {
                    relatorios.relatorioChoquesMedicoEvento();
                }
                case 6 -> {
                    relatorios.relatorioPacientesFaltaramCompareceramEvento(listaConsultas, listaEventos);
                }
                case 7 -> {
                    RelatoriosGerais.compararGastosCliente(listaConsultas, listaEventos);
                }
                case 8 -> {
                    RelatoriosGerais.impactoRecomendacaoMedicaAuto(listaPedidos);
                }
                case 9 -> {
                    relatorios.relatorioServicoMaisLucrativo(listaConsultas, listaPedidos, listaEventos);
                }
                case 10 -> {
                    relatorios.relatorioFaixaHorarioMaisFrequente(listaConsultas, listaPedidos, listaEventos);
                }
                case 11 -> {
                    RelatoriosGerais.mediaPedidosEventoTodos(listaEventos, listaPedidos);
                }
                case 12 -> {
                    RelatoriosGerais.listarClientesEmMaisDeUmServico(listaConsultas, listaEventos);
                }
                case 13 -> {
                    RelatoriosGerais.listarPercentuaisComparecimento(listaConsultas, listaEventos);
                }
                case 0 -> System.out.println("Encerrando...");
                default -> { /* nada */ }
            }
        } while (opcao != 0);
    }
}
