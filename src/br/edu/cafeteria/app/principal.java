package br.edu.cafeteria.app;

import br.edu.cafeteria.modelo.*;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class Principal {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private static List<Produto> produtos = new ArrayList<>();
    private static int aux;

    public static void main(String[] args) {
        int op;

        do {
            op = Menu.exibirMenuPrincipal();

            switch (op) {
                case 1:
                    // Abrir Pedido
                    abrirPedido();
                    break;
                case 2:
                    // Listar Pedidos
                    listarPedidos();
                    break;
                case 3:
                    // Listar Produtos
                    listarProdutos();
                    break;
                case 4:
                    // Cadastrar Cliente
                    cadastrarCliente();
                    break;
                case 5:
                    // Listar Clientes
                    listarClientes();
                    break;
                case 6: 
                    // Pesquisas
                    pesquisar();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    break;

            }
        } while (op != 0);

    }

    public static Cliente pesquisarClientePorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
        return null;
    }

    public static Produto pesquisarProdutoPorNome(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        return null;
    }

    public static void pesquisar() {
        int op = Menu.exibirMenuPesquisa();
        do 
    {
        switch (op) {

            case 1:
                String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");
                pesquisarClientePorCpf(cpf);
                JOptionPane.showMessageDialog(null, cpf.toString());
                break;
            case 2:
                String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
                String resposta;
                for(Cliente c : clientes)
                {
                    if(c.getNome().equalsIgnoreCase(nome)){
                        resposta = c.toString();
                        JOptionPane.showInputDialog(resposta);
                        break;
                    }
                    
                }
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                break;
                case 3:
                    String codigo = JOptionPane.showInputDialog("Digite o codigo do produto:");
                    String resposta1;
                    for(Produto p : produtos)
                    {
                        if(p.getCodigo().equalsIgnoreCase(codigo)){
                            resposta1 = p.toString();
                            JOptionPane.showMessageDialog(null, resposta1);
                            break;
                        }

                    }
                    JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                    break;
                case 4:
                    String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto:");
                    Produto p = pesquisarProdutoPorNome(nomeProduto);
                    JOptionPane.showMessageDialog(null, p.toString());
                    break;
                case 5:
                    aux = 0;
                    break;
            default:
                 JOptionPane.showMessageDialog(null, "Opção inválida.");
                break;
        }
    } while (op != 0);
}

    private static void listarProdutos() {
        String lista = "Lista de Produtos:\n";
        for (Produto p : produtos) {
            lista += p.getCodigo() + " - " + p.getClass().getSimpleName() + " - " + p.getNome() + " - R$" + p.getPrecoBase() + " - Estoque: "
                    + p.getQuantidadeEstoque() + "\n";
        }
        JOptionPane.showMessageDialog(null, lista, "Lista de Produtos", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void listarPedidos() {
        String lista = "Lista de Pedidos:\n";
        for (Pedido p : pedidos) {
            lista += p.toString();

        }
        JOptionPane.showMessageDialog(null, lista, "Lista de Pedidos", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void listarClientes() {
        String lista = "Lista de Clientes:\n";
        for (Cliente c : clientes) {
           lista += c.toString();
        }
        JOptionPane.showMessageDialog(null, lista, "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void abrirPedido() {
        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente (Ou em branco para cliente não cadastrado):");
        Cliente cliente = pesquisarClientePorCpf(cpf);
        pedidos.add(new Pedido(cliente));
        do {
            aux = Menu.exibirMenuPedido();
            switch (aux) {
                case 1:
                    // Adicionar Item
                    // Colocar Listar produtos aqui;
                    String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto:");
                    Produto produto = pesquisarProdutoPorNome(nomeProduto);
                    int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade:"));

                    if (quantidade <= 0) {
                        JOptionPane.showMessageDialog(null,
                                "Quantidade inválida. Deve ser maior que zero.");
                        break;
                    } else if (quantidade == 1) {

                        pedidos.get(pedidos.size() - 1).adicionarItem(produto);

                    } else {

                        pedidos.get(pedidos.size() - 1).adicionarItem(produto, quantidade);

                    }
                    break;
                case 2:
                    // Remover Item
                    String nomeProdutoRemover = JOptionPane.showInputDialog("Digite o nome do produto a ser removido:");
                    Produto produtoRemover = pesquisarProdutoPorNome(nomeProdutoRemover);
                    pedidos.get(pedidos.size() - 1).removerItem(produtoRemover);
                    break;
                case 3:
                    // Finalizar Venda
                    // Tem que passar o metodo de finalizar venda de pedido para a classe venda e
                    // atribuir a classe venda a um classe de atendente;
                    break;
                case 4:
                    // Cancelar Pedido
                    pedidos.remove(pedidos.size() - 1);
                    JOptionPane.showMessageDialog(null, "Pedido cancelado.");
                    break;
                case 0:
                    // Sair do Menu do Pedido
                    aux = 0;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    break;
            }
        } while (aux != 0);
    }

 private static void cadastrarCliente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");

        Cliente cliente = new ClienteStandard(nome, cpf);
        clientes.add(cliente);

        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
    }
}
