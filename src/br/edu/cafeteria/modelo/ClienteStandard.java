package br.edu.cafeteria.modelo;

public class ClienteStandard extends Cliente {

    //Construtor;
    public ClienteStandard(String nome, String cpf) {
        super(nome, cpf);
    }

    //Sobrescrita do metodo abstrato para Standard;
    @Override
    public int calcularPontos(double valorGasto) {
        return (int) valorGasto; //1XP por cada real gasto;
    }

}
