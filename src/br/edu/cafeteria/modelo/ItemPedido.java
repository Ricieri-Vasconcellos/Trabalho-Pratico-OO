package br.edu.cafeteria.modelo;

public class ItemPedido {

    private Produto produto;
    private int quantidade;
    private double subtotal;

    // Construtor
    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = produto.getPrecoBase() * quantidade;
    }

    // Getter
    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

}
