package Restaurante;

import Base.Pessoa;
import java.util.*;

public class RestauranteRelatorios {

    public static Prato pratoMaisVendidoPorPeriodo(List<Pedido> pedidos, String periodo) {
        Map<Prato, Integer> contador = new HashMap<>();
        for (Pedido p : pedidos) {
            for (Prato prato : p.getItens()) {
                contador.put(prato, contador.getOrDefault(prato, 0) + 1);
            }
        }
        return contador.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
     
    /* metodos respostas - restuarante
    4- dia da com maior concentração de eventos, 5-  renda mensal, 6- qual faixa de horario mais movimentada
    7- gasto de cada pessoa,  8-qual data com maior numero de atividade, 9- percentual de presença
    */
    public static Pessoa clienteMaisConsumiu(Map<Pessoa, List<Pedido>> pedidosPorCliente) {
        return pedidosPorCliente.entrySet().stream()
                .max(Comparator.comparingDouble(e -> e.getValue().stream()
                        .mapToDouble(Pedido::calcularTotal).sum()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    public static void faixaComMaisClientes(List <Pedido> pedidos ){
        System.out.println("Faixa de hora com mais clientes");
    }
}
