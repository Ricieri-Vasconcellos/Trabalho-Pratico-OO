package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.ValorInvalido;

public class Comida extends Produto {

    private int tempoPreparo; // Em minutos;
    private boolean isVegano;
    private boolean haveGluten;

    // ==================== MÉTODO CONSTRUTOR ====================

    public Comida(String codigo, String nome, double precoBase, int quantidadeEstoque, int tempoPreparo,
            boolean isVegano, boolean haveGluten) {
        super(codigo, nome, precoBase, quantidadeEstoque);
        this.tempoPreparo = tempoPreparo;
        this.isVegano = isVegano;
        this.haveGluten = haveGluten;
    }

    // ==================== MÉTODOS GETTERS ====================

    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public boolean Vegano() {
        return isVegano;
    }

    public boolean temGluten() {
        return haveGluten;
    }

    // ==================== MÉTODO PARA ATUALIZAR PRODUTO ====================

    public void atualizarProduto() {
        String menu = "0. Atualizar Codigo/Nome/Preço/Estoque\n1. Atualizar Tempo de Preparo\n2. Atualizar se é Vegano\n3. Atualizar se tem Gluten\n4. Sair";
        int op = 0;
        do {
            String strOp = javax.swing.JOptionPane.showInputDialog(menu);
            // Tratamento para caso o usuário cancele
            if (strOp == null) {
                op = 4; // Sai do loop
                break;
            }
            try {
                op = Integer.parseInt(strOp);
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Opção inválida. Digite um número.");
                continue;
            }

            switch (op) {
                case 0:
                    super.atualizarProduto();
                    break;
                case 1:
                    String strTempo = javax.swing.JOptionPane
                            .showInputDialog("Digite o novo tempo de preparo (em minutos):");
                    if (strTempo == null)
                        break; // cancelado
                    try {
                        int novoTempo = Integer.parseInt(strTempo);
                        if (novoTempo < 0) {
                            throw new ValorInvalido("O tempo de preparo não pode ser negativo.");
                        } else {
                            this.tempoPreparo = novoTempo;
                        }
                    } catch (ValorInvalido | NumberFormatException e) {
                        javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;
                case 2:
                    int respVegano = javax.swing.JOptionPane.showConfirmDialog(
                            null,
                            "A comida é vegana?",
                            "Vegano",
                            javax.swing.JOptionPane.YES_NO_OPTION);
                    // Se YES_OPTION (0) => true, caso contrário (NO ou CLOSED) => false
                    this.isVegano = (respVegano == javax.swing.JOptionPane.YES_OPTION);
                    break;
                case 3:
                    int respGluten = javax.swing.JOptionPane.showConfirmDialog(
                            null,
                            "A comida tem glúten?",
                            "Glúten",
                            javax.swing.JOptionPane.YES_NO_OPTION);
                    this.haveGluten = (respGluten == javax.swing.JOptionPane.YES_OPTION);
                    break;
                case 4:
                    javax.swing.JOptionPane.showMessageDialog(null, "Saindo da atualização de produto.");
                    break;
                default:
                    javax.swing.JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            }
        } while (op != 4);
    }

}
