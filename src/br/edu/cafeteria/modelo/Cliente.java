package br.edu.cafeteria.modelo;

import javax.swing.JOptionPane;

import br.edu.cafeteria.excecao.ValorInvalido;

public abstract class Cliente {

    private String nome;
    private String cpf;
    private int saldoXP; // Experiencia acomulada por pontos;

    // Construtor;
    public Cliente(String nome, String cpf) {
        try {
            if (nome.isBlank() || nome.trim().isEmpty() || cpf.isBlank() || cpf.trim().isEmpty()) {
                throw new ValorInvalido("Nem o nome nem o CPF pode ser nulos!");

            } else {

                this.nome = nome;
                this.cpf = cpf;
                this.saldoXP = 0;
            }
        } catch (ValorInvalido e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Getter
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getSaldoXP() {
        return saldoXP;
    }

    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Saldo XP: " + saldoXP + "\n";
    }

    // Método abstrato que será sobrescrito;
    protected abstract int calcularPontos(double valorGasto);

    // Método para adicionar XP após compra;
    protected void adicionarXP(double valorGasto) {
        int pontos = calcularPontos(valorGasto);
        this.saldoXP += pontos;
    }

    protected void removerXP(int pontos) {
        this.saldoXP -= pontos;
    }

    // Metodo de atualização de cliente;
    public void atualizarCliente() {
        String menu = "0. Atualizar Nome\n1. Atualizar CPF\n2. Sair";
        int op = 0;
        do {
            String strOp = javax.swing.JOptionPane.showInputDialog(menu);
            op = Integer.parseInt(strOp);
            switch (op) {
                case 0:
                    String novoNome = javax.swing.JOptionPane.showInputDialog("Digite o novo nome:");
                    try {
                        if (novoNome == null || novoNome.trim().isEmpty()) {
                            throw new ValorInvalido("O nome não pode ser vazio.");
                        } else {
                            this.nome = novoNome;
                        }

                    } catch (ValorInvalido e) {
                        javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;
                case 1:
                    String novoCpf = javax.swing.JOptionPane.showInputDialog("Digite o novo CPF:");
                    try {
                        if (novoCpf == null || novoCpf.trim().isEmpty()) {
                            throw new ValorInvalido("O CPF não pode ser vazio.");
                        } else {
                            this.cpf = novoCpf;
                        }
                    } catch (ValorInvalido e) {
                        javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    break;
                case 2:
                    javax.swing.JOptionPane.showMessageDialog(null, "Saindo da atualização de Cliente.");
                    break;
                default:
                    javax.swing.JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                    break;
            }
        } while (op != 2);
    }
}
