package br.edu.cafeteria.servico;

import javax.swing.JOptionPane;

import br.edu.cafeteria.modelo.Bebida;
import br.edu.cafeteria.modelo.ItemPedido;
import br.edu.cafeteria.modelo.Pedido;

public class PromocaoEventoGeek implements Promocional {

    public static boolean evento;

    // ==================== MÉTODO PARA APLICAR DESCONTO ====================

    public double aplicarDesconto(Pedido pedido) {
        double totalBebidas = 0.0;

        for (ItemPedido item : pedido.getItens()) {
            if (item.getProduto() instanceof Bebida) {
                totalBebidas += item.getSubtotal();
            }
        }
        return totalBebidas * 0.10; // Retorna o valor do desconto;
    }

    // ==================== MÉTODOS GETTERS ====================

    public static boolean isEvento() {
        return evento;
    }

    // ==================== MÉTODO PARA ATIVAR OU DESATIVAR EVENTO ====================
    
    public static void setEvento() {
        int resposta = JOptionPane.showConfirmDialog(
                null,
                "Deseja ativar o evento?",
                "Ativação de Evento",
                JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            evento = true;
            JOptionPane.showMessageDialog(null, "Evento ativado!");
        } else if (resposta == JOptionPane.NO_OPTION) {
            evento = false;
            JOptionPane.showMessageDialog(null, "Evento desativado!");
        } else {
            // CLOSED_OPTION (fechou a janela) – mantém o estado atual
            JOptionPane.showMessageDialog(null, "Operação cancelada. O evento não foi alterado.");
        }
    }

}
