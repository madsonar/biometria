package br.com.leitor.usuario;

public class Usuarios {

    private Integer id;
    private Integer pessoa;
    private String login;
    private String senha;
    private Boolean ativo;

    public Usuarios() {
        this.id = null;
        this.pessoa = null;
        this.login = "";
        this.senha = "";
        this.ativo = false;
    }

    public Usuarios(Integer id, Integer pessoa, String usuario, String senha, Boolean ativo) {
        this.id = id;
        this.pessoa = pessoa;
        this.login = usuario;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getPessoa() {
        return pessoa;
    }

    public void setPessoa(Integer pessoa) {
        this.pessoa = pessoa;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
