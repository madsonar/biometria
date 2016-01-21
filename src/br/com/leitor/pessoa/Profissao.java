package br.com.leitor.pessoa;

import javax.persistence.*;

@Entity
@Table(name = "pes_profissao")
@NamedQuery(name = "Profissao.pesquisaID", query = "select prof from Profissao prof where prof.id=:pid")
public class Profissao implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ds_profissao", length = 200, nullable = false)
    private String profissao;
    @Column(name = "ds_cbo", length = 10, nullable = true)
    private String cbo;

    public Profissao() {
        this.id = -1;
        this.profissao = "";
        this.cbo = "";
    }

    public Profissao(Integer id, String profissao, String cbo) {
        this.id = id;
        this.profissao = profissao;
        this.cbo = cbo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getCbo() {
        return cbo;
    }

    public void setCbo(String cbo) {
        this.cbo = cbo;
    }
}