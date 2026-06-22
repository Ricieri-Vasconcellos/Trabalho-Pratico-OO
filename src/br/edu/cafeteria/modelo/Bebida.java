package br.edu.cafeteria.modelo;

import javax.swing.JOptionPane;

import br.edu.cafeteria.excecao.ValorInvalido;

public class Bebida extends Produto {

    char tamanho; // 'P', 'M' ou 'G';
    int cafeinaMg;

    // Construtor
    public Bebida(String codigo, String nome, double precoBase, int quantidadeEstoque, char tamanho, int cafeinaMg) {
        super(codigo, nome, precoBase, quantidadeEstoque);
        this.tamanho = tamanho;
        this.cafeinaMg = cafeinaMg;
    }

    // Getter
    public char getTamanho() {
        return tamanho;
    }

    public int getCafeinaMg() {
        return cafeinaMg;
    }

    // Método para atualizar os atributos do produto
    public void atualizarProduto() {
        String menu = "0. Atualizar Codigo/Nome/Preço/Estoque\n1. Atualizar tamanho\n2. Atualizar cafeinaMg\n3. Sair";
        int op = 0;
        do {
            String strOp = JOptionPane.showInputDialog(menu);
            op = Integer.parseInt(strOp);
            switch (op) {
                case 0:
                    super.atualizarProduto();
                    break;
                case 1:
                    String strTamanho = JOptionPane.showInputDialog("Digite o novo tamanho da bebida (P, M ou G):");
                    try {
                        char novoTamanho = strTamanho.charAt(0);
                        if (novoTamanho == 'P' || novoTamanho == 'M' || novoTamanho == 'G') {
                            this.tamanho = novoTamanho;
                        } else {
                            throw new ValorInvalido("Tamanho inválido. Use 'P', 'M' ou 'G'.");
                        }
                    } catch (ValorInvalido e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;
                case 2:
                    String strCafeinaMg = JOptionPane.showInputDialog("Digite a nova quantidade de cafeína em mg:");
                    try {
                        if (Integer.parseInt(strCafeinaMg) < 0) {
                            throw new ValorInvalido("A quantidade de cafeína não pode ser negativa.");
                        } else {
                            this.cafeinaMg = Integer.parseInt(strCafeinaMg);
                        }
                    } catch (ValorInvalido e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Saindo da atualização de produto.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            }
        } while (op != 3);
    }
}
