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
