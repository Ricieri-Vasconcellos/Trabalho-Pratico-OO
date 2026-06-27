package br.edu.cafeteria.modelo;

import javax.swing.JOptionPane;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.ValorInvalido;

public abstract class Produto {

    private String codigo;
    private String nome;
    private double precoBase;
    private int quantidadeEstoque;

    // Construtor;
    protected Produto(String codigo, String nome, double precoBase, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoBase = precoBase;
        this.quantidadeEstoque = quantidadeEstoque;

    }

    // Getter;
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public String toString() {
        String resposta = "=== " + getClass() + " ===\n";
        resposta += "Nome: " + getNome() + "\n";
        resposta += "Codigo: " + getCodigo() + "\n";
        resposta += "Preço: " + getPrecoBase() + "\n";
        resposta += "Quantidade em estoque: " + getQuantidadeEstoque() + "\n";
        return resposta;
    }

    // Método para reduzir estoque (usado na venda)
    public void reduzirEstoque(int quantidade) {
        try {
            if (this.quantidadeEstoque < quantidade) {
                throw new EstoqueInsuficienteException("Estoque insuficiente de " + this.nome);
            } else {
                this.quantidadeEstoque -= quantidade;
            }
        } catch (EstoqueInsuficienteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Método para atualizar produto
    public void atualizarProduto() {
        int op = 0;
        String menu = "0. Atualizar Codigo\n1. Atualizar nome\n2. Atualizar preço\n3. Atualizar estoque\n4. Sair";
        do {
            String strOp = JOptionPane.showInputDialog(menu);
            op = Integer.parseInt(strOp);
            switch (op) {
                case 0:
                    String novoCodigo = JOptionPane.showInputDialog("Digite o novo código do produto:");
                    try {
                        if (novoCodigo == null || novoCodigo.trim().isEmpty()) {
                            throw new ValorInvalido("Código do produto não pode ser vazio.");
                        } else {
                            this.codigo = novoCodigo;
                        }
                    } catch (ValorInvalido e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());

                    }
                    break;
                case 1:
                    String novoNome = JOptionPane.showInputDialog("Digite o novo nome do produto:");
                    try {
                        if (novoNome == null || novoNome.trim().isEmpty()) {
                            throw new ValorInvalido("Nome do produto não pode ser vazio.");
                        } else {
                            this.nome = novoNome;
                        }
                    } catch (ValorInvalido e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());

                    }
                    break;
                case 2:
                    String strPreco = JOptionPane.showInputDialog("Digite o novo preço do produto:");
                    double novoPreco = Double.parseDouble(strPreco);
                    try {
                        if (novoPreco < 0) {
                            throw new ValorInvalido("Preço não pode ser negativo.");
                        } else {
                            this.precoBase = novoPreco;
                        }
                    } catch (ValorInvalido e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());

                    }
                    break;
                case 3:
                    String strQuantidade = JOptionPane
                            .showInputDialog("Digite a quantidade do estoque:");
                    int novaQuantidade = Integer.parseInt(strQuantidade);
                    try {
                        if (novaQuantidade < 0) {
                            throw new ValorInvalido("Quantidade não pode ser negativa.");
                        } else {
                            this.quantidadeEstoque = novaQuantidade;
                        }
                    } catch (ValorInvalido e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Saindo da atualização do produto.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");

            }

        } while (op != 4);

    }

}
