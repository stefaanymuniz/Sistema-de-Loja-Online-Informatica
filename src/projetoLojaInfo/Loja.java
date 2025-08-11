package projetoLojaInfo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//gerenciamento de dados
public class Loja {

private Map<Produto, Integer> estoque;
private List<Produto> carrinhoDeCompras;
private Map<String, Usuario> usuariosCadastrados; // CPF -> Usuario
private List<Venda> historicoDeVendas;

 public Loja() {
     this.estoque = new HashMap<>();
     this.carrinhoDeCompras = new ArrayList<>();
     this.usuariosCadastrados = new HashMap<>(); 
     this.historicoDeVendas = new ArrayList<>(); 
     inicializarEstoque();
 }

 // Cadastrar um novo usuário
public boolean cadastrarUsuario(String nome, String cpf, String senha) {
    if (usuariosCadastrados.containsKey(cpf)) {
        System.out.println("Erro: CPF já cadastrado.");
        return false;
    }
    Usuario novoUsuario = new Usuario(nome, cpf, senha);
    usuariosCadastrados.put(cpf, novoUsuario);
    System.out.println("Usuário " + nome + " cadastrado com sucesso!");
    return true;
}

// Método para autenticar um usuário
public Usuario fazerLogin(String cpf, String senha) {
    // Chamando o método Get passando o CPF (String) para o HashMap retornar um usuário
    Usuario usuario = usuariosCadastrados.get(cpf);
    if (usuario != null && usuario.verificarSenha(senha)) {
        System.out.println("Login bem-sucedido! Bem-vindo(a), " + usuario.getNome() + ".");
        return usuario;
    }
    System.out.println("Erro: CPF ou senha incorretos.");
    return null;
}

// Método para o admin ver o histórico
public void exibirHistoricoDeVendas() {
    System.out.println("\n--- HISTÓRICO COMPLETO DE VENDAS ---");
    if (historicoDeVendas.isEmpty()) {
        System.out.println("Nenhuma venda foi registrada até o momento.");
    } else {
        for (Venda venda : historicoDeVendas) {
            System.out.println(venda);
        }
    }
    System.out.println("------------------------------------");
}


 private void inicializarEstoque() {
    //adição de produtos e quant. ao mapa estoque
	 estoque.put(new Produto("NB001", "Notebook Dell XPS 15", 8500.00, "Computadores"), 15);
     estoque.put(new Produto("MNT005", "Monitor Gamer LG UltraGear", 1800.00, "Periféricos"), 20);
     estoque.put(new Produto("RT010", "Roteador Wi-Fi TP-Link Archer C6", 350.00, "Redes e Conectividade"), 30);
     estoque.put(new Produto("PEN020", "Pen Drive SanDisk Ultra 64GB", 75.00, "Acessórios e Suprimentos"), 50);
     estoque.put(new Produto("GPU003", "Placa de Vídeo NVIDIA RTX 4070", 4500.00, "Componentes de PC"), 10);
     estoque.put(new Produto("SMART001", "Smartphone Samsung Galaxy S24", 4200.00, "Smartphones e Tablets"), 25);
 }
 
 public void listarProdutos() {
     System.out.println("--- PRODUTOS DISPONÍVEIS ---");
     
     if (estoque.isEmpty()) {
         System.out.println("Nenhum produto em estoque no momento.");
         return;
     }
     
     //percorre entradas do mapa 
     for (Map.Entry<Produto, Integer> entry : estoque.entrySet()) {
         Produto produto = entry.getKey();
         Integer quantidade = entry.getValue();
         System.out.printf("%s | Em estoque: %d%n", produto.toString(), quantidade);
     }
     System.out.println("----------------------------");
 }

 public void adicionarAoCarrinho(Produto produto) {
    //se existe no estoque e sua quant
	 if (estoque.containsKey(produto) && estoque.get(produto) > 0) {
         carrinhoDeCompras.add(produto);
         System.out.printf("'%s' foi adicionado ao carrinho!%n", produto.getNome());
     } else {
         System.out.printf("Desculpe, '%s' não está disponível em estoque.%n", produto.getNome());
     }
 }
 
  public List<Produto> getProdutosDisponiveis() {
      return new ArrayList<>(estoque.keySet());
  }
  

  public void verCarrinho() {
      System.out.println("\n--- MEU CARRINHO DE COMPRAS ---");
     
      if (carrinhoDeCompras.isEmpty()) {
          System.out.println("Seu carrinho está vazio.");
      } else {
          double totalParcial = 0.0;
          System.out.println("Itens no carrinho:");
           //percorre a lista
          for (Produto item : carrinhoDeCompras) {
              System.out.println(" - " + item);
              totalParcial += item.getPreco();
          }
          System.out.println("---------------------------------");
          System.out.printf("Total parcial: R$ %.2f%n", totalParcial);
      }
      System.out.println("-----------------------------");
  }
  

 public void processarCompra(Usuario comprador) {
    if (carrinhoDeCompras.isEmpty()) {
        System.out.println("Seu carrinho de compras está vazio.");
        return;
    }

    System.out.println("\n--- FINALIZANDO COMPRA ---");
    double total = 0.0;
    
    // Validar estoque antes de processar
    for (Produto item : carrinhoDeCompras) {
        if (estoque.getOrDefault(item, 0) < 1) {
            System.out.println("[ERRO] Produto sem estoque: " + item.getNome());
            System.out.println("Compra cancelada. Remova o item do carrinho e tente novamente.");
            return;
        }
    }
    
    // Processar a compra
    for (Produto item : carrinhoDeCompras) {
        //atualização de estoque
        estoque.computeIfPresent(item, (produto, quantidade) -> quantidade - 1);
        total += item.getPreco();
    }
    
    // Criar e registrar a venda!
    Venda novaVenda = new Venda(comprador, new ArrayList<>(carrinhoDeCompras), total);
    historicoDeVendas.add(novaVenda);

    System.out.printf("Total da compra: R$ %.2f%n", total);
    System.out.println("Compra realizada com sucesso!");
    
    carrinhoDeCompras.clear(); 
    System.out.println("--------------------------");
}

 public void listarCategorias() {
    //coleções de elementos unicos
     Set<String> categorias = new HashSet<>();
     for (Produto produto : estoque.keySet()) {
         categorias.add(produto.getCategoria());
     }

     System.out.println("\n--- CATEGORIAS DE PRODUTOS ---");
     for (String categoria : categorias) {
         System.out.println(" - " + categoria);
     }
     System.out.println("------------------------------");
 }
}