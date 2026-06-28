package br.edu.cafeteria.modelo;

public class ClienteStandard extends Cliente {

    // ==================== MÉTODO CONSTRUTOR ====================

    public ClienteStandard(String nome, String cpf) {
        super(nome, cpf);
    }

    // ==================== MÉTODO TOSTRING ====================

    public String toString() {
        return "Cliente Standard - Nome: " + getNome() + ", CPF: " + getCpf() + ", Saldo XP: " + getSaldoXP() + "\n";
    }

    // Sobrescrita do metodo abstrato para Standard;
    @Override
    public int calcularPontos(double valorGasto) {
        return (int) valorGasto; // 1XP por cada real gasto;
    }

}
