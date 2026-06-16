package br.edu.cafeteria.modelo;

public class Comida extends Produto {

    private int tempoPreparo; //Em minutos;
    private boolean isVegano;
    private boolean haveGluten;

    //Construtor;
    public Comida(String codigo, String nome, double precoBase, int quantidadeEstoque, int tempoPreparo, boolean isVegano, boolean haveGluten) {
        super(codigo, nome, precoBase, quantidadeEstoque);
        this.tempoPreparo = tempoPreparo;
        this.isVegano = isVegano;
        this.haveGluten = haveGluten;
    }

    //Getter e Setter;
    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(int tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public boolean isVegano() {
        return isVegano;
    }

    public void setVegano(boolean isVegano) {
        this.isVegano = isVegano;
    }

    public boolean temGluten(){
        return haveGluten;
    }

    public void setGluten(boolean haveGluten)
    {
        this.haveGluten = haveGluten;
    }

}
