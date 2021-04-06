package br.com.bugoffice.organizese.models;

public class User {
    private String nome;
    private String email;
    private String senha;
    private String sexo;
    private String status;

    public User() { }

    public User(String nome, String email, String senha, String sexo, String status) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.sexo = sexo;
        this.status = status;
    }

    public User(String nome, String email, String sexo, String status) {
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


