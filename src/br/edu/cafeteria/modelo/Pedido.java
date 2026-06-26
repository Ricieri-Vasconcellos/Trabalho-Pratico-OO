package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.excecao.ValorInvalido;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private static int proximoNumero = 1;
    private final int numeroPedido;
    private final Cliente cliente; // Pode ser null (casual);
    private List<ItemPedido> itens;
    private double valorTotal;

    public Pedido(Cliente cliente) {
        this.numeroPedido = proximoNumero++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public String toString() {
        String resposta = "Pedido #" + this.numeroPedido + " - Cliente: "
                + (this.cliente != null ? this.cliente.getNome() : "Não cadastrado") + " - Valor Total: R$"
                + this.valorTotal + "\n";
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

    public double calcularTotalComDesconto(boolean isDiaEventoGeek) {
        if (!isDiaEventoGeek) {
            return this.valorTotal;
        }

        // Aplica 10% de desconto APENAS nas bebidas (polimorfismo);
        double totalBebidas = 0.0;
        for (ItemPedido item : itens) {
            if (item.getProduto() instanceof Bebida) {
                totalBebidas += item.getSubtotal();
            }
        }
        double desconto = totalBebidas * 0.10;
        return this.valorTotal - desconto;
    }

    // Finaliza venda -> atualiza estoque e, se houver cliente, adiciona XP;
    public void finalizarVenda(boolean pagamentoComXP)
            throws EstoqueInsuficienteException, PontosInsuficientesException {
        // 1) Atualiza o estoque do produto com o metodo reduzirEstoque da classe
        // Produto;
        for (ItemPedido item : itens) {
            Produto p = item.getProduto();
            p.reduzirEstoque(item.getQuantidade());
        }

        // 2) Calcula o valor final (sem evento Geek);
        double valorFinal = this.valorTotal;

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
}
