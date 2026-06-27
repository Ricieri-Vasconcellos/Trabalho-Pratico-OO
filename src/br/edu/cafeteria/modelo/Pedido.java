package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.ValorInvalido;
import br.edu.cafeteria.servico.PromocaoEventoGeek;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Pedido {

    private static int proximoNumero = 1;
    private final int numeroPedido;
    private final Cliente cliente; // Pode ser null (casual);
    private List<ItemPedido> itens;
    private double valorTotal;
    private String atendente;

    public Pedido(Cliente cliente, int op) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        try {
            this.valorTotal = 0.0;
            switch (op) {
                case 1:
                    this.atendente = "Roberto";
                    break;
                case 2:
                    this.atendente = "Paula";
                    break;
                case 3:
                    this.atendente = "Сristian";
                    break;
                default:
                    throw new ValorInvalido("Valor invalido selecione um dos atendentes(1 ou 2 ou 3)!");
            }
        } catch (ValorInvalido e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        this.numeroPedido = proximoNumero++;
    }

    public String toString() {
        String resposta = "Pedido #" + this.numeroPedido + "\n" + " - Cliente: "
                + (this.cliente != null ? this.cliente.getNome() : "Não cadastrado") + "\n";
        resposta += "==== Itens === \n";
        for (ItemPedido p : itens) {
            resposta += p.toString();
        }

        resposta += "\n" + " - Valor Total: R$"
                + this.valorTotal + "\n" +
                "Atendente: " + atendente + "\n";
        return resposta;
    }

    // Sobrescrita 1: adiciona uma unidade;
    public void adicionarItem(Produto p) {
        try {
            if (p.getQuantidadeEstoque() < 1) {
                throw new EstoqueInsuficienteException(
                        "Produto: " + p.getNome() + " | Estoque: " + p.getQuantidadeEstoque() + " | Solicitado: 1");
            } else {
                adicionarItem(p, 1);

            }
        } catch (EstoqueInsuficienteException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Sobrescrita 2: adiciona quantidade variavel;
    public void adicionarItem(Produto p, int quantidade) {
        // Verifica estoque antes
        try {
            if (quantidade <= 0) {
                throw new ValorInvalido("Quantidade deve ser maior que zero.");
            } else if (p.getQuantidadeEstoque() < quantidade) {
                throw new EstoqueInsuficienteException("Produto: " + p.getNome() + " | Estoque: "
                        + p.getQuantidadeEstoque() + " | Solicitado: " + quantidade);
            }
        } catch (EstoqueInsuficienteException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (ValorInvalido e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Cria o item e adiciona à lista;
        ItemPedido item = new ItemPedido(p, quantidade);
        itens.add(item);

        // Atualiza o valor total (sem descontos ainda);
        this.valorTotal += item.getSubtotal();
    }

    public void removerItem(Produto p) {
        ItemPedido itemARemover = null;
        for (ItemPedido item : itens) {
            if (item.getProduto().equals(p)) {
                itemARemover = item;
                break;
            }
        }

        if (itemARemover != null) {
            itens.remove(itemARemover);
            this.valorTotal -= itemARemover.getSubtotal();
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Produto não encontrado no pedido.");
        }
    }

    public double calcularDesconto(boolean isDiaEventoGeek) {
        if (!isDiaEventoGeek) {
            return 0;
        }

        // Aplica 10% de desconto APENAS nas bebidas (polimorfismo);
        double totalBebidas = 0.0;
        for (ItemPedido item : itens) {
            if (item.getProduto() instanceof Bebida) {
                totalBebidas += item.getSubtotal();
            }
        }
        double desconto = totalBebidas * 0.10;
        return desconto;
    }

    // Finaliza venda -> atualiza estoque e, se houver cliente, adiciona XP;
    public void finalizarVenda(boolean pagamentoComXP) {
        // 1) Atualiza o estoque do produto com o metodo reduzirEstoque da classe
        // Produto;
        boolean evento = PromocaoEventoGeek.isEvento();

        for (ItemPedido item : itens) {
            Produto p = item.getProduto();
            p.reduzirEstoque(item.getQuantidade());
        }

        // 2) Calcula o valor final (sem evento Geek);
        double desconto = calcularDesconto(evento);
        double valorFinal = this.valorTotal - desconto;

        // 3) Se Cliente for VIP e pagamentoComXP = true, tenta pagar com pontos;
        if (pagamentoComXP && cliente instanceof ClienteVIP) {
            ClienteVIP vip = (ClienteVIP) cliente; // Polimorfismo por coerção;
            vip.pagarComXP(valorFinal);
            // Não adicionar XP pois foi pago com pontos;
        } // 4) Se Cliente for cadastrado (Standard ou VIP) e NÃO pagou com XP, adiciona
          // XP;
        else if (cliente != null && !(pagamentoComXP && cliente instanceof ClienteVIP)) {
            cliente.adicionarXP(valorFinal); // Polimorfismo por inclusão;
        }

        String venda = "=== Pedido Finalizado ===\n";
        venda += toString();
        if (cliente != null) {
            venda += "Novo saldo de XP: " + cliente.getSaldoXP() + "\n";
        }
        venda += "Descontos: " + desconto + "\n";
        venda += "Valor final: " + valorFinal + "\n";

        JOptionPane.showConfirmDialog(null, venda, "Venda Finalizada", JOptionPane.INFORMATION_MESSAGE);
    }

    // Getter;
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getAtendente() {
        return atendente;
    }
}
