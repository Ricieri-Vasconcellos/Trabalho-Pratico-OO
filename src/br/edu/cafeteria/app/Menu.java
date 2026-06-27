package br.edu.cafeteria.app;

import javax.swing.JOptionPane;

public class Menu {

    private Menu() {
    } // Para impedir instanciação da classe Menu

    public static int exibirMenuPrincipal() {
        String menu = "\n=== Byte & Brew - Sistema de Vendas ===\n";
        menu += "1. Abrir Pedido\n";
        menu += "2. Listar Pedidos\n";
        menu += "3. Listar Produtos\n";
        menu += "4. Listar Clientes\n";
        menu += "5. Cadastros\n";
        menu += "6. Pesquisas\n";
        menu += "7. Atualizar\n";
        menu += "8. Remover\n";
        menu += "0. Sair\n";
        menu += "Escolha uma opção: ";

        String strOp = JOptionPane.showInputDialog(menu);

        int op = Integer.parseInt(strOp);

        return op;
    }

    public static int exibirMenuPedido() {
        String menu = "\n=== Byte & Brew - Menu do Pedido ===\n";
        menu += "1. Adicionar Item\n";
        menu += "2. Remover Item\n";
        menu += "3. Finalizar Venda\n";
        menu += "4. Cancelar Pedido\n";
        menu += "0. Sair do Menu do Pedido\n";
        menu += "Escolha uma opção: ";

        String strOp = JOptionPane.showInputDialog(menu);

        int op = Integer.parseInt(strOp);

        return op;
    }

    public static int exibirMenuPesquisa() {
        String menu = "\n=== Byte & Brew - Menu de Pesquisas ===\n";
        menu += "1. Pesquisar Cliente por CPF\n";
        menu += "2. Pesquisar Cliente por Nome\n";
        menu += "3. Pesquisar Produto por Código\n";
        menu += "4. Pesquisar Produto por Nome\n";
        menu += "0. Sair do Menu de Pesquisas\n";
        menu += "Escolha uma opção: ";

        String strOp = JOptionPane.showInputDialog(menu);

        int op = Integer.parseInt(strOp);

        return op;
    }

    public static int exibirMenuAtualizar() {
        String menu = "\n=== Byte & Brew - Menu de Atualizações ===\n";
        menu += "1. Atualizar Produto\n";
        menu += "2. Atualizar Cliente\n";
        menu += "3. Atualizar EventoGeek\n";
        menu += "0. Sair/n";
        menu += "Escolha uma opção: ";

        String strOp = JOptionPane.showInputDialog(menu);

        int op = Integer.parseInt(strOp);

        return op;

    }

    public static int exibirMenuRemover() {
        String menu = "\n=== Byte & Brew - Menu de Remover ===\n";
        menu += "1. Remover Produto\n";
        menu += "2. Remover Cliente\n";
        menu += "0. Sair\n";
        menu += "Escolha uma opção: ";

        String strOp = JOptionPane.showInputDialog(menu);

        int op = Integer.parseInt(strOp);

        return op;

    }

    public static int exibirMenuCadastros() {
        String menu = "\n=== Byte & Brew - Menu de Cadastros ===\n";
        menu += "1. Cadastrar Produto\n";
        menu += "2. Cadastrar Cliente\n";
        menu += "0. Sair\n";
        menu += "Escolha uma opção: ";

        String strOp = JOptionPane.showInputDialog(menu);

        int op = Integer.parseInt(strOp);

        return op;

    }
}
