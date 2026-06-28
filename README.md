# ☕ Byte & Brew – Sistema de Vendas para Cafeteria Geek

<div align="center">

# ☕ Byte & Brew

### Sistema de Vendas e Fidelidade para uma Cafeteria Geek

![Java](https://img.shields.io/badge/Java-21.0.11-orange)
![Paradigma](https://img.shields.io/badge/Paradigma-POO-blue)
![Status](https://img.shields.io/badge/Status-Testando-success)
![Licença](https://img.shields.io/badge/Licença-Acadêmica-green)

</div>

---

## 📖 Sobre o Projeto

O **Byte & Brew** é um sistema de gerenciamento de vendas desenvolvido para uma cafeteria temática geek, criado como trabalho prático da disciplina de **Orientação a Objetos** da **Universidade de Brasília (UnB/FGA)**.

O objetivo do projeto é aplicar conceitos fundamentais de **Programação Orientada a Objetos (POO)** por meio da implementação de um sistema completo de vendas, clientes, produtos, promoções e fidelização.

### Conceitos aplicados

* ✅ Herança
* ✅ Polimorfismo
* ✅ Encapsulamento
* ✅ Classes Abstratas
* ✅ Interfaces
* ✅ Tratamento de Exceções
* ✅ Organização em Camadas

---

## ✨ Funcionalidades

### 🛒 Gerenciamento de Pedidos

* Criar pedidos
* Adicionar itens
* Remover itens
* Finalizar vendas
* Cancelar pedidos

### 🍔 Cadastro de Produtos

* Cadastro de comidas
* Cadastro de bebidas
* Controle de estoque
* Atualização de informações
* Remoção de produtos

### 👥 Cadastro de Clientes

* Clientes Standard
* Clientes VIP
* Sistema de fidelidade por XP
* Pesquisa por nome ou CPF
* Atualização cadastral

### 📋 Listagens

* Todos os clientes cadastrados
* Todos os produtos cadastrados
* Todos os pedidos registrados

### 🎮 Evento Geek

Promoção especial que pode ser ativada ou desativada pelo sistema:

* 10% de desconto em bebidas
* Clientes VIP podem utilizar XP acumulado para pagamento

---

## ⚙️ Requisitos

### Java

O projeto foi desenvolvido e testado utilizando:

```text
JDK 21.0.11
```

> ⚠️ Atenção: versões anteriores do JDK 21 podem apresentar erros de compilação ou execução. Durante os testes foi constatado que o projeto não funcionou corretamente no JDK 21.0.10, sendo necessário utilizar o JDK 21.0.11.

Verifique sua versão:

```bash
java --version
javac --version
```

---

## 🛠 Tecnologias Utilizadas

| Tecnologia                      | Finalidade              |
| ------------------------------- | ----------------------- |
| Java 21.0.11                    | Linguagem principal     |
| Swing (JOptionPane)             | Interface gráfica       |
| Git/GitHub                      | Controle de versão      |
| Programação Orientada a Objetos | Estruturação do sistema |

---

## 📁 Estrutura do Projeto

```text
src/
└── br/
    └── edu/
        └── cafeteria/
            ├── app/
            │   ├── Menu.java
            │   └── principal.java
            │
            ├── modelo/
            │   ├── Produto.java
            │   ├── Bebida.java
            │   ├── Comida.java
            │   ├── Cliente.java
            │   ├── ClienteStandard.java
            │   ├── ClienteVIP.java
            │   ├── Pedido.java
            │   └── ItemPedido.java
            │
            ├── servico/
            │   ├── PromocaoEventoGeek.java
            │   └── Promocional.java
            │
            └── excecao/
                ├── EstoqueInsuficienteException.java
                ├── PontosInsuficientesException.java
                └── ValorInvalido.java
```

---

## 🚀 Como Executar

### 1. Clonar o repositório

```bash
git clone https://github.com/Ricieri-Vasconcellos/Trabalho-Pratico-OO.git
cd Trabalho-Pratico-OO
```

### 2. Compilar o projeto

```bash
javac src/br/edu/cafeteria/app/*.java ^
      src/br/edu/cafeteria/modelo/*.java ^
      src/br/edu/cafeteria/servico/*.java ^
      src/br/edu/cafeteria/excecao/*.java
```

### 3. Executar

```bash
java -cp src br.edu.cafeteria.app.principal
```

> Todas as interações do sistema são realizadas através de janelas do JOptionPane.

---

## 🧠 Conceitos de POO Aplicados

| Conceito                | Aplicação                                            |
| ----------------------- | ---------------------------------------------------- |
| Herança                 | Bebida e Comida estendem Produto                     |
| Herança                 | ClienteVIP e ClienteStandard estendem Cliente        |
| Polimorfismo            | Comportamentos específicos para cada tipo de cliente |
| Classes Abstratas       | Produto e Cliente                                    |
| Interfaces              | Promocional                                          |
| Encapsulamento          | Uso de atributos privados e métodos de acesso        |
| Exceções Personalizadas | EstoqueInsuficienteException e ValorInvalido         |

---

## 📦 Dados Iniciais

Ao iniciar o sistema são carregados automaticamente:

### 🍔 Produtos

* 10 comidas
* 10 bebidas

Exemplos:

* Hambúrguer do Mario
* Batatas do Sonic
* Salada da Zelda
* Café do Gandalf
* Chá da TARDIS
* Poção de Mana

### 👥 Clientes

* 6 clientes cadastrados
* Clientes Standard e VIP

---

## 🎓 Trabalho Acadêmico

**Universidade de Brasília (UnB)**
**Faculdade do Gama (FGA)**
**Disciplina:** Orientação a Objetos
**Professor:** André Luiz Peron Martins Lanna

---

## 👨‍💻 Contribuidores

| Integrante                        |
| --------------------------------- |
| Ricieri Vasconcellos - 252014410  |
| kazuowt - 252003847               |
| Jpsc14 - 252014724                |
| Akiojoao - 252014715              |

---

## 📄 Licença

Projeto desenvolvido exclusivamente para fins acadêmicos na disciplina de Orientação a Objetos da Universidade de Brasília.
