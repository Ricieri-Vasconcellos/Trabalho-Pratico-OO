package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelo.Pedido;

public interface Promocional {

    public static void setEvento() {}

    double aplicarDesconto(Pedido pedido);

}
