package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.ValorInvalido;

public class Comida extends Produto {

    private int tempoPreparo; // Em minutos;
    private boolean isVegano;
    private boolean haveGluten;

    // Construtor;
    public Comida(String codigo, String nome, double precoBase, int quantidadeEstoque, int tempoPreparo,
            boolean isVegano, boolean haveGluten) {
        super(codigo, nome, precoBase, quantidadeEstoque);
        this.tempoPreparo = tempoPreparo;
        this.isVegano = isVegano;
        this.haveGluten = haveGluten;
    }

    // Getter
    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public boolean Vegano() {
        return isVegano;
    }

    public boolean temGluten() {
        return haveGluten;
    }

    public void atualizarProduto() {
        String menu = "0. Atualizar Codigo/Nome/Preço/Estoque\n1. Atualizar Tempo de Preparo\n2. Atualizar se é Vegano\n3. Atualizar se tem Gluten\n4. Sair";
        int op = 0;
        do {
            String strOp = javax.swing.JOptionPane.showInputDialog(menu);
            op = Integer.parseInt(strOp);
            switch (op) {
                case 0:
                    super.atualizarProduto();
                    break;
                case 1:
                    String strTempo = javax.swing.JOptionPane
                            .showInputDialog("Digite o novo tempo de preparo (em minutos):");
                    int novoTempo = Integer.parseInt(strTempo);
                    try {
                        if (novoTempo < 0) {
                            throw new ValorInvalido("O tempo de preparo não pode ser negativo.");
                        } else {
                            this.tempoPreparo = novoTempo;
                        }
                    } catch (ValorInvalido e) {
                        javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;
                case 2:
                    String strVegano = javax.swing.JOptionPane.showInputDialog("A comida é vegana? (S/N):");
                    try {
                        if (strVegano.equalsIgnoreCase("S")) {
                            this.isVegano = true;
                        } else if (strVegano.equalsIgnoreCase("N")) {
                            this.isVegano = false;
                        } else {
                            throw new ValorInvalido("Entrada inválida. Use 'S' para sim ou 'N' para não.");
                        }
                    } catch (ValorInvalido e) {
                        javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;
                case 3:
                    String strGluten = javax.swing.JOptionPane.showInputDialog("A comida tem gluten? (S/N):");
                    try {
                        if (strGluten.equalsIgnoreCase("S")) {
                            this.haveGluten = true;
                        } else if (strGluten.equalsIgnoreCase("N")) {
                            this.haveGluten = false;
                        } else {
                            throw new ValorInvalido("Entrada inválida. Use 'S' para sim ou 'N' para não.");
                        }
                    } catch (ValorInvalido e) {
                        javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
                    }
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
