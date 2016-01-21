package br.com.leitor.pessoa;

import br.com.leitor.utils.DataHoje;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "pes_biometria")
public class Biometria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
    @OneToOne
    private Pessoa pessoa;
    @Column(name = "ds_biometria", length = 1000)
    private String biometria;
    @Column(name = "ds_biometria2", length = 1000)
    private String biometria2;
    @Column(name = "dt_lancamento")
    @Temporal(TemporalType.DATE)
    private Date lancamento;
    @Column(name = "is_ativo", columnDefinition = "boolean default true")
    private Boolean ativo;
    @Column(name = "is_enviado", columnDefinition = "boolean default true")
    private Boolean enviado;

    public Biometria() {
        this.id = null;
        this.pessoa = null;
        this.biometria = null;
        this.lancamento = DataHoje.dataHoje();
        this.ativo = false;
        this.enviado = false;
    }

    public Biometria(int id, Pessoa pessoa, String biometria, Date lancamento, Boolean ativo, Boolean enviado) {
        this.id = id;
        this.pessoa = pessoa;
        this.biometria = biometria;
        this.lancamento = lancamento;
        this.ativo = ativo;
        this.enviado = enviado;
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

    public String getBiometria() {
        return biometria;
    }

    public void setBiometria(String biometria) {
        this.biometria = biometria;
    }

    public String getBiometria2() {
        return biometria2;
    }

    public void setBiometria2(String biometria2) {
        this.biometria2 = biometria2;
    }

    public Date getLancamento() {
        return lancamento;
    }

    public void setLancamento(Date lancamento) {
        this.lancamento = lancamento;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Biometria other = (Biometria) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Biometria{" + "id=" + id + ", pessoa=" + pessoa + ", biometria=" + biometria + ", biometria2=" + biometria2 + ", lancamento=" + lancamento + ", ativo=" + ativo + ", enviado=" + enviado + '}';
    }

}
