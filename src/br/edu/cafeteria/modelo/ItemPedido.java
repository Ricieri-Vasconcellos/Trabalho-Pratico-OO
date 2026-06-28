package br.edu.cafeteria.modelo;

public class ItemPedido {

    private Produto produto;
    private int quantidade;
    private double subtotal;

    // ==================== MÉTODO CONSTRUTOR ====================

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = produto.getPrecoBase() * quantidade;
    }

    // ==================== MÉTODOS GETTERS ====================

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public String toString() {
        String resposta = "";
        resposta += produto.getNome() + " - " + quantidade + " - R$" + subtotal + "\n";
        return resposta;
    }

}
