package projetoLojaInfo;

import java.util.Objects;

public class Usuario {

    private static long contadorId = 1;
    private long id;
    private String nome;
    private String cpf;
    private String senha;


    public Usuario(String nome, String cpf, String senha) {
        this.id = contadorId++;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    // Obter informações do usuário
    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }

    
    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }

    @Override
    public String toString() {
        return "Usuário: " + nome + " | CPF: " + cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(cpf, usuario.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}

