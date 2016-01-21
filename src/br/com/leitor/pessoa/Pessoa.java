package br.com.leitor.pessoa;

import br.com.leitor.utils.DataHoje;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "pes_pessoa")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "ds_nome", length = 150, nullable = false)
    private String nome;
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private TipoDocumento tipoDocumento;
    @Column(name = "ds_obs", length = 500, nullable = true)
    private String obs;
    @Column(name = "ds_site", length = 50, nullable = true)
    private String site;
    @Column(name = "ds_telefone1", length = 20, nullable = true)
    private String telefone1;
    @Column(name = "ds_telefone2", length = 20)
    private String telefone2;
    @Column(name = "ds_telefone3", length = 20)
    private String telefone3;
    @Column(name = "ds_email1", length = 50, nullable = true)
    private String email1;
    @Column(name = "ds_email2", length = 50)
    private String email2;
    @Column(name = "ds_email3", length = 50)
    private String email3;
    @Column(name = "ds_documento", length = 30, nullable = false)
    private String documento;
    @Column(name = "ds_login", length = 50, nullable = true)
    private String login;
    @Column(name = "ds_senha", length = 50, nullable = true)
    private String senha;
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_criacao")
    private Date dtCriacao;

    public Pessoa() {
        this.id = -1;
        this.nome = "";
        this.tipoDocumento = new TipoDocumento();
        this.obs = "";
        this.site = "";
        setCriacao(DataHoje.data());
        this.telefone1 = "";
        this.telefone2 = "";
        this.telefone3 = "";
        this.email1 = "";
        this.email2 = "";
        this.email3 = "";
        this.documento = "";
        this.login = "";
        this.senha = "";
    }

    public Pessoa(int id, String nome, TipoDocumento tipoDocumento, String obs, String site, String criacao,
            String telefone1, String telefone2, String telefone3, String email1, String email2, String email3, String documento, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.tipoDocumento = tipoDocumento;
        this.obs = obs;
        this.site = site;
        setCriacao(criacao);
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.telefone3 = telefone3;
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        this.documento = documento;
        this.login = login;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public String getCriacao() {
        return DataHoje.converteData(dtCriacao);
    }

    public void setCriacao(String criacao) {
        this.dtCriacao = DataHoje.converte(criacao);
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

//    public PessoaEndereco getPessoaEndereco() {
//        int idTipoEndereco = 1;
//        if (tipoDocumento.getId() == 2) {
//            idTipoEndereco = 2;
//        }
//        PessoaEndereco pessoaEndereco = new PessoaEnderecoDBToplink().pesquisaEndPorPessoaTipo(this.id, idTipoEndereco);
//        if (pessoaEndereco == null) {
//            pessoaEndereco = new PessoaEnderecoDBToplink().pesquisaEndPorPessoaTipo(this.id, 2);
//        }
//        return pessoaEndereco;
//    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 79 * hash + (this.tipoDocumento != null ? this.tipoDocumento.hashCode() : 0);
        hash = 79 * hash + (this.obs != null ? this.obs.hashCode() : 0);
        hash = 79 * hash + (this.site != null ? this.site.hashCode() : 0);
        hash = 79 * hash + (this.telefone1 != null ? this.telefone1.hashCode() : 0);
        hash = 79 * hash + (this.telefone2 != null ? this.telefone2.hashCode() : 0);
        hash = 79 * hash + (this.telefone3 != null ? this.telefone3.hashCode() : 0);
        hash = 79 * hash + (this.email1 != null ? this.email1.hashCode() : 0);
        hash = 79 * hash + (this.email2 != null ? this.email2.hashCode() : 0);
        hash = 79 * hash + (this.email3 != null ? this.email3.hashCode() : 0);
        hash = 79 * hash + (this.documento != null ? this.documento.hashCode() : 0);
        hash = 79 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 79 * hash + (this.senha != null ? this.senha.hashCode() : 0);
        hash = 79 * hash + (this.dtCriacao != null ? this.dtCriacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if (this.tipoDocumento != other.tipoDocumento && (this.tipoDocumento == null || !this.tipoDocumento.equals(other.tipoDocumento))) {
            return false;
        }
        if ((this.obs == null) ? (other.obs != null) : !this.obs.equals(other.obs)) {
            return false;
        }
        if ((this.site == null) ? (other.site != null) : !this.site.equals(other.site)) {
            return false;
        }
        if ((this.telefone1 == null) ? (other.telefone1 != null) : !this.telefone1.equals(other.telefone1)) {
            return false;
        }
        if ((this.telefone2 == null) ? (other.telefone2 != null) : !this.telefone2.equals(other.telefone2)) {
            return false;
        }
        if ((this.telefone3 == null) ? (other.telefone3 != null) : !this.telefone3.equals(other.telefone3)) {
            return false;
        }
        if ((this.email1 == null) ? (other.email1 != null) : !this.email1.equals(other.email1)) {
            return false;
        }
        if ((this.email2 == null) ? (other.email2 != null) : !this.email2.equals(other.email2)) {
            return false;
        }
        if ((this.email3 == null) ? (other.email3 != null) : !this.email3.equals(other.email3)) {
            return false;
        }
        if ((this.documento == null) ? (other.documento != null) : !this.documento.equals(other.documento)) {
            return false;
        }
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.senha == null) ? (other.senha != null) : !this.senha.equals(other.senha)) {
            return false;
        }
        if (this.dtCriacao != other.dtCriacao && (this.dtCriacao == null || !this.dtCriacao.equals(other.dtCriacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", tipoDocumento=" + tipoDocumento + ", obs=" + obs + ", site=" + site + ", telefone1=" + telefone1 + ", telefone2=" + telefone2 + ", telefone3=" + telefone3 + ", email1=" + email1 + ", email2=" + email2 + ", email3=" + email3 + ", documento=" + documento + ", login=" + login + ", senha=" + senha + ", dtCriacao=" + dtCriacao + '}';
    }

}
