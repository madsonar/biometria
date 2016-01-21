package br.com.leitor.usuario;

import br.com.leitor.pessoa.Pessoa;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "seg_usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT U FROM Usuario U ORDER BY U.pessoa.nome ASC, U.login ASC ")
})
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Pessoa pessoa;
    @Column(name = "ds_login", length = 15, nullable = false, unique = true)
    private String login;
    @Column(name = "ds_senha", length = 6, nullable = false)
    private String senha;
    @Column(name = "is_ativo", columnDefinition = "boolean default false")
    private boolean ativo;
    @Column(name = "ds_email", length = 255)
    private String email;

    public Usuario() {
        this.id = null;
        this.pessoa = new Pessoa();
        this.login = "";
        this.senha = "";
        this.ativo = false;
        this.email = "";
    }

    public Usuario(int id, Pessoa pessoa, String login, String senha, boolean ativo, String email) {
        this.id = id;
        this.pessoa = pessoa;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", pessoa=" + pessoa + ", login=" + login + ", ativo=" + ativo + ", email=" + email + '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
