package br.edu.cafeteria.modelo;

import javax.swing.JOptionPane;

import br.edu.cafeteria.excecao.ValorInvalido;

public class Bebida extends Produto {

    char tamanho; // 'P', 'M' ou 'G';
    int cafeinaMg;
    boolean quente;

    // Construtor
    public Bebida(String codigo, String nome, double precoBase, int quantidadeEstoque, char tamanho, int cafeinaMg,
            boolean quente) {
        super(codigo, nome, precoBase, quantidadeEstoque);
        this.tamanho = tamanho;
        this.cafeinaMg = cafeinaMg;
        this.quente = quente;
    }

    // Getter
    public char getTamanho() {
        return tamanho;
    }

    public int getCafeinaMg() {
        return cafeinaMg;
    }

    public boolean isQuente() {
        return quente;
    }

    // Método para atualizar os atributos do produto
    public void atualizarProduto() {
        String menu = "0. Atualizar Codigo/Nome/Preço/Estoque\n1. Atualizar tamanho\n2. Atualizar cafeinaMg\n3. Atualizar temperatura\n4. Sair";
        int op = 0;
        do {
            String strOp = JOptionPane.showInputDialog(menu);
            // Tratamento para cancelamento
            if (strOp == null) {
                op = 4; // Sai do loop
                break;
            }
            try {
                op = Integer.parseInt(strOp);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opção inválida. Digite um número.");
                continue;
            }

            switch (op) {
                case 0:
                    super.atualizarProduto();
                    break;
                case 1:
                    String strTamanho = JOptionPane.showInputDialog("Digite o novo tamanho da bebida (P, M ou G):");
                    if (strTamanho == null || strTamanho.trim().isEmpty())
                        break; // cancelado
                    try {
                        char novoTamanho = strTamanho.toUpperCase().charAt(0);
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
                    if (strCafeinaMg == null)
                        break; // cancelado
                    try {
                        int novaCafeina = Integer.parseInt(strCafeinaMg);
                        if (novaCafeina < 0) {
                            throw new ValorInvalido("A quantidade de cafeína não pode ser negativa.");
                        } else {
                            this.cafeinaMg = novaCafeina;
                        }
                    } catch (ValorInvalido | NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;
                case 3:
                    // Já utiliza botões Sim/Não (substitui entrada de texto)
                    int resposta = JOptionPane.showConfirmDialog(
                            null,
                            "A bebida é quente?",
                            "Temperatura",
                            JOptionPane.YES_NO_OPTION);
                    this.quente = (resposta == JOptionPane.YES_OPTION);
                    break; // <-- ADICIONADO: evita cair no case 4
                case 4:
                    JOptionPane.showMessageDialog(null, "Saindo da atualização de produto.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            }
        } while (op != 4); // <-- CORRIGIDO: antes estava op != 3
    }
}
