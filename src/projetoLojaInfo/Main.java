package projetoLojaInfo;

import java.util.List;
import java.util.Scanner;


public class Main {

 public static void main(String[] args) {
    Loja minhaLoja = new Loja();
    Scanner scanner = new Scanner(System.in);
    Usuario usuarioLogado = null; 

    while (true) {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Acessar Loja");
        if (usuarioLogado == null) {
            System.out.println("2. Fazer Login");
        } else { 
            System.out.println("2. Logout");
        }
        System.out.println("3. Cadastrar Novo Usuário");
        System.out.println("4. Painel do Administrador");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");

        int opcaoPrincipal = scanner.nextInt();
        scanner.nextLine(); 

        if (opcaoPrincipal == 0) {
            System.out.println("Obrigado por visitar nossa loja. Volte sempre!");
            break;
        }

        switch (opcaoPrincipal) {
            case 1:
                menuLoja(minhaLoja, usuarioLogado, scanner);
                break;
            case 2:
                if (usuarioLogado == null) {
                    System.out.print("Digite seu CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Digite sua senha: ");
                    String senha = scanner.nextLine();
                    usuarioLogado = minhaLoja.fazerLogin(cpf, senha);
                } else {
                    usuarioLogado = null;
                    System.out.println("Deslogado!");
                }
                break;
            case 3:
                System.out.print("Digite seu nome completo: ");
                String nome = scanner.nextLine();
                System.out.print("Digite seu CPF: ");
                String novoCpf = scanner.nextLine();
                System.out.print("Crie uma senha: ");
                String novaSenha = scanner.nextLine();
                minhaLoja.cadastrarUsuario(nome, novoCpf, novaSenha);
                break;
            case 4:
                painelAdmin(minhaLoja, scanner);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
    
    scanner.close(); 
}

    private static void menuLoja(Loja minhaLoja, Usuario usuario, Scanner scanner) {
    int opcao;
    do {
        System.out.println("\n--- MENU DA LOJA ---");
        System.out.println("1. Listar todos os produtos");
        System.out.println("2. Adicionar produto ao carrinho");
        System.out.println("3. Ver categorias de produtos");
        System.out.println("4. Ver meu carrinho");
        System.out.println("5. Finalizar compra");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");

        opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                minhaLoja.listarProdutos();
                break;
            case 2:
                adicionarProduto(minhaLoja, scanner);
                break;
            case 3:
                minhaLoja.listarCategorias();
                break;
            case 4:
                minhaLoja.verCarrinho();
                break;
            case 5:
                minhaLoja.processarCompra(usuario);
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    } while (opcao != 0);
}

 private static void adicionarProduto(Loja loja, Scanner scanner) {
     System.out.println("\n--- ESCOLHA UM PRODUTO PARA ADICIONAR ---");
     List<Produto> produtos = loja.getProdutosDisponiveis();
     
     if (produtos.isEmpty()) {
         System.out.println("Não há produtos para adicionar.");
         return;
     }

     for (int i = 0; i < produtos.size(); i++) {
         System.out.printf("%d. %s%n", (i + 1), produtos.get(i).getNome());
     }
     
     System.out.print("Digite o número do produto: ");
     int escolha = scanner.nextInt();
     
     if (escolha > 0 && escolha <= produtos.size()) {
         Produto produtoEscolhido = produtos.get(escolha - 1);
         loja.adicionarAoCarrinho(produtoEscolhido);
     } else {
         System.out.println("Seleção inválida.");
     }
 }

 private static void painelAdmin(Loja loja, Scanner scanner) {
    int opcao;
    do {
        System.out.println("\n--- PAINEL DE ADMINISTRADOR ---");
        System.out.println("1. Ver Histórico de Vendas");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");

        opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                loja.exibirHistoricoDeVendas();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida.");
        }
    } while (opcao != 0);
}
}