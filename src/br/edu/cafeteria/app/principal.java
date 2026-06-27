package br.edu.cafeteria.app;

import br.edu.cafeteria.modelo.*;
import br.edu.cafeteria.servico.PromocaoEventoGeek;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class Principal {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private static List<Produto> produtos = new ArrayList<>();
    private static int aux;

    public static void main(String[] args) {
        inicializarDados();

        int op;

        do {
            op = Menu.exibirMenuPrincipal();

            switch (op) {
                case 1:
                    // Abrir Pedido
                    gerenciarPedidos();
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
                    // Listar Clientes
                    listarClientes();
                    break;
                case 5:
                    // Cadastros
                    cadastrar();
                    break;
                case 6:
                    // Pesquisas
                    pesquisar();
                    break;
                case 7:
                    // Atualizações
                    atualizar();
                    break;
                case 8:
                    // Remover
                    remover();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Saindo!");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    break;

            }
        } while (op != 0);

    }

    // ==================== PESQUISAS ====================
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
        int op;
        do {
            op = Menu.exibirMenuPesquisa();
            switch (op) {

                case 1:
                    String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");
                    Cliente cp = pesquisarClientePorCpf(cpf);
                    if (cp == null) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, cp.toString());
                        break;
                    }
                case 2:
                    String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
                    String resposta;
                    int teste = 0;
                    for (Cliente c : clientes) {
                        if (c.getNome().equalsIgnoreCase(nome)) {
                            resposta = c.toString();
                            JOptionPane.showMessageDialog(null, resposta);
                            teste = 1;
                            break;
                        }
                    }
                    if (teste == 0) {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                    }
                    break;
                case 3:
                    String codigo = JOptionPane.showInputDialog("Digite o codigo do produto:");
                    String resposta1;
                    int teste2 = 0;
                    for (Produto p : produtos) {
                        if (p.getCodigo().equalsIgnoreCase(codigo)) {
                            resposta1 = p.toString();
                            JOptionPane.showMessageDialog(null, resposta1);
                            teste2 = 1;
                            break;
                        }

                    }
                    if (teste2 == 0)
                        JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                    break;
                case 4:
                    String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto:");
                    Produto p = pesquisarProdutoPorNome(nomeProduto);
                    if (p == null) {
                        break;
                    }
                    JOptionPane.showMessageDialog(null, p.toString());
                    break;
                case 0:
                    aux = 0;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    break;
            }
        } while (op != 0);
    }

    // ==================== MÉTODOS DE LISTAR ====================

    private static void listarProdutos() {
        String lista = "Lista de Produtos:\n";
        for (Produto p : produtos) {
            lista += p.getCodigo() + " - " + p.getClass().getSimpleName() + " - " + p.getNome() + " - R$"
                    + p.getPrecoBase() + " - Estoque: "
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

    // ==================== MÉTODO PARA GERENCIAR PEDIDOS ====================

    private static void gerenciarPedidos() {
        Pedido pedidoAtual = null;
        int opcaoInicial = JOptionPane.showConfirmDialog(null,
                "Deseja criar um novo pedido? (Sim = novo, Não = gerenciar existente)",
                "Gerenciar Pedidos", JOptionPane.YES_NO_OPTION);

        if (opcaoInicial == JOptionPane.CLOSED_OPTION) {
            return;
        }

        if (opcaoInicial == JOptionPane.YES_OPTION) {
            // Criar novo pedido
            String cpf = JOptionPane
                    .showInputDialog("Digite o CPF do cliente (Ou em branco para cliente não cadastrado):");
            Cliente cliente = null;
            if (cpf != null && !cpf.trim().isEmpty()) {
                cliente = pesquisarClientePorCpf(cpf);
            }
            String StrAtendente = JOptionPane
                    .showInputDialog("Digite qual o atendente: \n1 - Roberto\n 2 - Paula\n 3 - Cristian");
            if (StrAtendente == null || StrAtendente.trim().isEmpty())
                return;
            int opAtendente;
            try {
                opAtendente = Integer.parseInt(StrAtendente);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Atendente inválido.");
                return;
            }
            pedidoAtual = new Pedido(cliente, opAtendente);
            pedidos.add(pedidoAtual);
            JOptionPane.showMessageDialog(null, "Novo pedido criado! Número: " + pedidoAtual.getNumeroPedido());
        } else {
            // Gerenciar pedido existente
            if (pedidos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há pedidos cadastrados.");
                return;
            }
            // Listar pedidos resumidos
            String lista = "Pedidos existentes:\n";
            for (Pedido p : pedidos) {
                lista += "Nº " + p.getNumeroPedido() + " - Cliente: "
                        + (p.getCliente() != null ? p.getCliente().getNome() : "Casual")
                        + " - Total: R$" + p.getValorTotal() + "\n";
            }
            JOptionPane.showMessageDialog(null, lista);

            String numStr = JOptionPane.showInputDialog("Digite o número do pedido que deseja gerenciar:");
            if (numStr == null || numStr.trim().isEmpty())
                return;
            int numero;
            try {
                numero = Integer.parseInt(numStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Número inválido.");
                return;
            }
            for (Pedido p : pedidos) {
                if (p.getNumeroPedido() == numero) {
                    pedidoAtual = p;
                    break;
                }
            }
            if (pedidoAtual == null) {
                JOptionPane.showMessageDialog(null, "Pedido não encontrado.");
                return;
            }
        }

        // Agora temos um pedidoAtual, vamos ao menu de operações
        do {
            aux = Menu.exibirMenuPedido();
            switch (aux) {
                case 1:
                    // Adicionar Item
                    listarProdutos();
                    String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto:");
                    if (nomeProduto == null || nomeProduto.trim().isEmpty())
                        break;
                    Produto produto = pesquisarProdutoPorNome(nomeProduto);
                    if (produto == null)
                        break;
                    String qtdStr = JOptionPane.showInputDialog("Digite a quantidade:");
                    if (qtdStr == null)
                        break;
                    int quantidade;
                    try {
                        quantidade = Integer.parseInt(qtdStr);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Quantidade inválida.");
                        break;
                    }
                    if (quantidade <= 0) {
                        JOptionPane.showMessageDialog(null, "Quantidade inválida. Deve ser maior que zero.");
                        break;
                    }
                    if (quantidade == 1) {
                        pedidoAtual.adicionarItem(produto);
                    } else {
                        pedidoAtual.adicionarItem(produto, quantidade);
                    }
                    break;
                case 2:
                    // Remover Item
                    String nomeProdutoRemover = JOptionPane.showInputDialog("Digite o nome do produto a ser removido:");
                    if (nomeProdutoRemover == null || nomeProdutoRemover.trim().isEmpty())
                        break;
                    Produto produtoRemover = pesquisarProdutoPorNome(nomeProdutoRemover);
                    if (produtoRemover != null) {
                        pedidoAtual.removerItem(produtoRemover);
                    }
                    break;
                case 3:
                    // Finalizar Venda
                    JOptionPane.showMessageDialog(null, "Finalizando pedido Nº " + pedidoAtual.getNumeroPedido());
                    boolean pagarXP = false;
                    if (pedidoAtual.getCliente() instanceof ClienteVIP) {
                        int resposta = JOptionPane.showConfirmDialog(
                                null,
                                "Deseja pagar com XP?",
                                "Pagamento com XP",
                                JOptionPane.YES_NO_OPTION);
                        // Se o usuário clicar em "Sim", pagarXP = true; caso contrário (Não ou fechar),
                        // fica false
                        pagarXP = (resposta == JOptionPane.YES_OPTION);
                    }
                    pedidoAtual.finalizarVenda(pagarXP);
                    aux = 0; // sai do menu após finalizar
                    pedidos.remove(pedidoAtual);
                    break;
                case 4:
                    // Cancelar Pedido
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Tem certeza que deseja cancelar o pedido Nº " + pedidoAtual.getNumeroPedido() + "?",
                            "Cancelar Pedido", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        pedidos.remove(pedidoAtual);
                        JOptionPane.showMessageDialog(null, "Pedido cancelado.");
                        aux = 0; // sai do menu após cancelar
                    }
                    break;
                case 0:
                    // Sair do menu
                    aux = 0;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    break;
            }
        } while (aux != 0);
    }

    // ==================== MÉTODO PARA ATUALIZAR ====================

    private static void atualizar() {
        int op;
        do {
            op = Menu.exibirMenuAtualizar();
            switch (op) {
                case 1:
                    listarProdutos();
                    String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto que deseja atualizar:");
                    Produto p = pesquisarProdutoPorNome(nomeProduto);
                    p.atualizarProduto();
                    break;
                case 2:
                    listarClientes();
                    String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente que deseja atualizar:");
                    Cliente c = pesquisarClientePorCpf(cpf);
                    c.atualizarCliente();
                case 3:
                    PromocaoEventoGeek.setEvento();
                    break;
                case 0:
                    op = 0;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Valor invalido! Digite 1, 2 ou 0!");
                    break;
            }
        } while (op != 0);
    }

    // ==================== MÉTODO PARA REMOVER ====================
    private static void remover() {
        int op;
        do {
            op = Menu.exibirMenuRemover();
            switch (op) {
                case 1:
                    // Remover Produto
                    listarProdutos();
                    String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto que deseja remover:");
                    Produto p = pesquisarProdutoPorNome(nomeProduto);
                    if (p != null) {
                        int confirm = JOptionPane.showConfirmDialog(null,
                                "Deseja realmente remover o produto \"" + p.getNome() + "\"?",
                                "Confirmar remoção", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            produtos.remove(p);
                            JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Remoção cancelada.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                    }
                    break;

                case 2:
                    // Remover Cliente
                    listarClientes();
                    String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente que deseja remover:");
                    Cliente c = pesquisarClientePorCpf(cpf);
                    if (c != null) {
                        int confirm = JOptionPane.showConfirmDialog(null,
                                "Deseja realmente remover o cliente \"" + c.getNome() + "\"?",
                                "Confirmar remoção", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            clientes.remove(c);
                            JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Remoção cancelada.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                    }
                    break;
                case 3:
                    int numero = Integer
                            .parseInt(JOptionPane.showInputDialog("Digite o numero do pedido que deseja remover: "));

                    Pedido pedidoRemover = null;
                    for (Pedido pedi : pedidos) {
                        if (pedi.getNumeroPedido() == numero) {
                            pedidoRemover = pedi;
                            break;
                        }
                    }

                    if (pedidoRemover == null) {
                        JOptionPane.showMessageDialog(null, "Pedido não encontrado.");
                        return;
                    }

                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Tem certeza que deseja remover o pedido Nº " + numero + "?",
                            "Confirmar remoção", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        pedidos.remove(pedidoRemover);
                        JOptionPane.showMessageDialog(null, "Pedido removido com sucesso.");
                    }
                    break;

                case 0:
                    // Sair do menu de remoção
                    JOptionPane.showMessageDialog(null, "Voltando ao menu principal.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida! Digite 1, 2 ou 0.");
                    break;
            }
        } while (op != 0);
    }

    private static void cadastrar() {
        int op;
        do {
            op = Menu.exibirMenuCadastros();

            switch (op) {
                case 1:
                    // Cadastrar Produto
                    cadastrarProduto();
                    break;
                case 2:
                    // Cadastrar Cliente
                    cadastrarCliente();
                    break;
                case 0:
                    // Sair
                    JOptionPane.showMessageDialog(null, "Saindo do menu cadastrar!");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Valor invalido! Digite 1 ou 2 ou 0!");
                    break;
            }
        } while (op != 0);
    }

    // ==================== MÉTODO PARA CADASTRAR ====================
    private static void cadastrarProduto() {
        String tipo = JOptionPane.showInputDialog("Digite o tipo do produto:\n1 - Comida\n2 - Bebida");
        if (tipo == null)
            return;
        int tipoInt;
        try {
            tipoInt = Integer.parseInt(tipo);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Tipo inválido.");
            return;
        }
        if (tipoInt != 1 && tipoInt != 2) {
            JOptionPane.showMessageDialog(null, "Tipo inválido. Digite 1 ou 2.");
            return;
        }

        String codigo = JOptionPane.showInputDialog("Digite o código do produto:");
        if (codigo == null || codigo.trim().isEmpty())
            return;
        // Verifica se código já existe
        for (Produto prod : produtos) {
            if (prod.getCodigo().equalsIgnoreCase(codigo)) {
                JOptionPane.showMessageDialog(null, "Código já cadastrado!");
                return;
            }
        }

        String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
        if (nome == null || nome.trim().isEmpty())
            return;

        String precoStr = JOptionPane.showInputDialog("Digite o preço base (R$):");
        if (precoStr == null)
            return;
        double preco;
        try {
            preco = Double.parseDouble(precoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Preço inválido.");
            return;
        }

        String estoqueStr = JOptionPane.showInputDialog("Digite a quantidade em estoque:");
        if (estoqueStr == null)
            return;
        int estoque;
        try {
            estoque = Integer.parseInt(estoqueStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Estoque inválido.");
            return;
        }

        if (tipoInt == 1) { // Comida
            String tempoStr = JOptionPane.showInputDialog("Digite o tempo de preparo (minutos):");
            if (tempoStr == null)
                return;
            int tempo;
            try {
                tempo = Integer.parseInt(tempoStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tempo inválido.");
                return;
            }
            int veganoOpt = JOptionPane.showConfirmDialog(null, "É vegano?", "Vegano", JOptionPane.YES_NO_OPTION);
            boolean isVegano = (veganoOpt == JOptionPane.YES_OPTION);
            int glutenOpt = JOptionPane.showConfirmDialog(null, "Contém glúten?", "Glúten", JOptionPane.YES_NO_OPTION);
            boolean haveGluten = (glutenOpt == JOptionPane.YES_OPTION);

            produtos.add(new Comida(codigo, nome, preco, estoque, tempo, isVegano, haveGluten));
            JOptionPane.showMessageDialog(null, "Comida cadastrada com sucesso!");

        } else { // Bebida
            String tamanhoStr = JOptionPane.showInputDialog("Digite o tamanho (P, M, G):");
            if (tamanhoStr == null || tamanhoStr.trim().isEmpty())
                return;
            char tamanho = tamanhoStr.toUpperCase().charAt(0);
            if (tamanho != 'P' && tamanho != 'M' && tamanho != 'G') {
                JOptionPane.showMessageDialog(null, "Tamanho inválido. Use P, M ou G.");
                return;
            }
            String cafeinaStr = JOptionPane.showInputDialog("Digite a quantidade de cafeína (mg):");
            if (cafeinaStr == null)
                return;
            int cafeina;
            try {
                cafeina = Integer.parseInt(cafeinaStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor inválido.");
                return;
            }
            int quenteOpt = JOptionPane.showConfirmDialog(null, "É uma bebida quente?", "Quente",
                    JOptionPane.YES_NO_OPTION);
            boolean quente = (quenteOpt == JOptionPane.YES_OPTION);

            produtos.add(new Bebida(codigo, nome, preco, estoque, tamanho, cafeina, quente));
            JOptionPane.showMessageDialog(null, "Bebida cadastrada com sucesso!");
        }
    }

    private static void cadastrarCliente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");

        Cliente cliente = new ClienteStandard(nome, cpf);
        clientes.add(cliente);

        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
    }

    // ==================== INICIALIZAÇÃO DE DADOS ====================
    private static void inicializarDados() {
        // ========== COMIDAS ==========
        produtos.add(new Comida("C001", "Hambúrguer do Mario", 18.50, 15, 12, false, false));
        produtos.add(new Comida("C002", "Batatas do Sonic", 12.00, 20, 8, true, false));
        produtos.add(new Comida("C003", "Sanduíche do Link", 16.00, 10, 10, false, false));
        produtos.add(new Comida("C004", "Salada da Zelda", 14.50, 8, 5, true, true));
        produtos.add(new Comida("C005", "Torta de Limão do Goku", 9.00, 12, 0, false, false));
        produtos.add(new Comida("C006", "Lembas Bread", 7.50, 18, 15, true, false));
        produtos.add(new Comida("C007", "Portal Cake", 11.00, 10, 20, false, false));
        produtos.add(new Comida("C008", "Pizza do Mario", 22.00, 6, 18, true, false));
        produtos.add(new Comida("C009", "Nuggets do Pikachu", 13.00, 14, 7, false, false));
        produtos.add(new Comida("C010", "Waffle do Thor", 10.50, 9, 12, false, false));

        // ========== BEBIDAS ==========
        produtos.add(new Bebida("B001", "Café do Gandalf", 8.00, 25, 'M', 120, true));
        produtos.add(new Bebida("B002", "Chá da TARDIS", 7.50, 18, 'G', 60, true));
        produtos.add(new Bebida("B003", "Refrigerante do Deadpool", 6.00, 30, 'P', 30, false));
        produtos.add(new Bebida("B004", "Suco do Hulk", 9.00, 10, 'G', 0, false));
        produtos.add(new Bebida("B005", "Cappuccino do Batman", 10.00, 15, 'M', 150, true));
        produtos.add(new Bebida("B006", "Café do Programador", 9.50, 20, 'P', 180, true));
        produtos.add(new Bebida("B007", "Poção de Mana", 7.00, 12, 'G', 0, false));
        produtos.add(new Bebida("B008", "Leite Quente da Hermione", 6.50, 22, 'M', 0, true));
        produtos.add(new Bebida("B009", "Suco do Groot", 8.00, 10, 'G', 0, false));
        produtos.add(new Bebida("B010", "Chocolate Quente do Naruto", 11.00, 8, 'G', 25, true));

        // ========== CLIENTES ==========
        clientes.add(new ClienteVIP("Ricieri", "252014410"));
        clientes.add(new ClienteStandard("João Pedro", "252014724"));
        clientes.add(new ClienteVIP("Breno", "252003847"));
        clientes.add(new ClienteStandard("Luana Silva", "123.456.789-00"));
        clientes.add(new ClienteVIP("Bruno Nerd", "987.654.321-00"));

        JOptionPane.showMessageDialog(null, "Cardápio e clientes de exemplo carregados!");
    }
}