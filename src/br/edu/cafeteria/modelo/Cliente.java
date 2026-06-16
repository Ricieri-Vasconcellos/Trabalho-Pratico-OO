package br.edu.cafeteria.modelo;

public abstract class Cliente {

    private String nome;
    private String cpf;
    private int saldoXP; // Experiencia acomulada por pontos;

    //Construtor;
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.saldoXP = 0;
    }

    //Getter e Setter;
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getSaldoXP() {
        return saldoXP;
    }

    public void setSaldoXP(int saldoXP) {
        this.saldoXP = saldoXP;
    }

     //Método abstrato que será sobrescrito;
    public abstract int calcularPontos(double valorGasto);

     //Método para adicionar XP após compra;
    public void adicionarXP(double valorGasto) {
        int pontos = calcularPontos(valorGasto);
        this.saldoXP += pontos;
    }

}
