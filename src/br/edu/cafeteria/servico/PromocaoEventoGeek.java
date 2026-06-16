package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelo.Bebida;
import br.edu.cafeteria.modelo.ItemPedido;
import br.edu.cafeteria.modelo.Pedido;

public class PromocaoEventoGeek {

    public double aplicarDesconto(Pedido pedido)
    {
        double totalBebidas = 0.0;

        for(ItemPedido item : pedido.getItens()){
            if(item.getProduto() instanceof Bebida)
            {
                totalBebidas += item.getSubtotal();
            }
        }
        return totalBebidas *0.10; //Retorna o valor do desconto;
    }
}
