package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.PontosInsuficientesException;

public class ClienteVIP extends Cliente {

    private static final int TAXA_CONVERSAO = 10; // 10 XP = R$ 1;

    // Construtor;
    public ClienteVIP(String nome, String cpf) {
        super(nome, cpf);
    }

    // Sobrescrita do metodo abstrato para o VIP;
    public int calcularPontos(double valorGasto) {
        return (int) (valorGasto * 2); // 2Xp por real gasto;
    }

    // Método exclusivo para pagar com XP;
    public void pagarComXP(double valorTotal) throws PontosInsuficientesException {
        int xpNecessario = (int) (valorTotal * TAXA_CONVERSAO);
        try {
            if (getSaldoXP() < xpNecessario) {
                throw new PontosInsuficientesException(
                        "Saldo de XP insuficiente. Necessário: " + xpNecessario + ", atual: " + getSaldoXP());
            } else {
                removerXP(xpNecessario);
            }
        } catch (PontosInsuficientesException e) {
            System.out.println(e.getMessage());
        }

    }
}
