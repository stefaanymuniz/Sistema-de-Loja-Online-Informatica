package projetoLojaInfo;

import java.util.Objects;

public class Produto{

 private String codigo;
 private String nome;
 private double preco;
 private String categoria;

 public Produto(String codigo, String nome, double preco, String categoria) {
     this.codigo = codigo;
     this.nome = nome;
     this.preco = preco;
     this.categoria = categoria;
 }


 public String getCodigo() {
     return codigo;
 }

 
 public String getNome() {
     return nome;
 }

 
 public double getPreco() {
     return preco;
 }

 public String getCategoria() {
     return categoria;
 }


 @Override
 public String toString() {
     return String.format("Produto: %s | Categoria: %s | Preço: R$ %.2f", nome, categoria, preco);
 }

 
 @Override
 public boolean equals(Object o) {
	    if (this == o) {
	        return true;
	    }
	    if (o == null || this.getClass() != o.getClass()) {
	        return false;
	    }
	    
	    Produto produto = (Produto) o;
	    
	    return Objects.equals(codigo, produto.codigo);
 }

 @Override
 public int hashCode() {
     return Objects.hash(codigo);
 }
}