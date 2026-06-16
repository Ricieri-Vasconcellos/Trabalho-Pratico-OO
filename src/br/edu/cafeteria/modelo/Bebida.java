package br.edu.cafeteria.modelo;

public class Bebida extends Produto {

    char tamanho; //'P', 'M' ou 'G';
    int cafeinaMg;

    //Construtor
    public Bebida(String codigo, String nome, double precoBase, int quantidadeEstoque, char tamanho, int cafeinaMg) {
        super(codigo, nome, precoBase, quantidadeEstoque);
        this.tamanho = tamanho;
        this.cafeinaMg = cafeinaMg;
    }

    //Getter e Setter
    public char getTamanho() {
        return tamanho;
    }

    public void setTamanho(char tamanho) {
        this.tamanho = tamanho;
    }

    public int getCafeinaMg() {
        return cafeinaMg;
    }

    public void setCafeinaMg(int cafeinaMg) {
        this.cafeinaMg = cafeinaMg;
    }

}
