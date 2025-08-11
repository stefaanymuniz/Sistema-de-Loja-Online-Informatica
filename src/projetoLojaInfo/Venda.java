package projetoLojaInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Venda {
    private Usuario comprador; // Atributo do tipo Usuario
    private List<Produto> produtosComprados;
    private double totalCompra;
    private LocalDateTime dataHora;


    public Venda(Usuario comprador, List<Produto> produtosComprados, double totalCompra) {
        this.comprador = comprador;
        this.produtosComprados = produtosComprados;
        this.totalCompra = totalCompra;
        this.dataHora = LocalDateTime.now(); // Inicializa data e hora atual
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = dataHora.format(formatter);

        
        String infoComprador = (comprador != null)
                ? "ID Comprador: " + comprador.getId() + " | CPF: " + comprador.getCpf()
                : "ID Comprador: 0 | Usuário Não Identificado";

        // Lista dos itens comprados
        StringBuilder detalhesProdutos = new StringBuilder();
        for (Produto p : produtosComprados) {
            detalhesProdutos.append("\n    - ").append(p.getNome());
        }

        return String.format(
            "--- Registro da Venda ---\n" +
            "Data/Hora: %s\n" +
            "Comprador: %s\n" +
            "Valor Total: R$ %.2f\n" +
            "Itens Comprados:%s\n" +
            "--------------------------",
            dataFormatada, infoComprador, totalCompra, detalhesProdutos.toString()
        );
    }
}
