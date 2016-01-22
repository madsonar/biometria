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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_atualiza_aparelho1", nullable = false)
    private Date dataAtualizacaoAparelho1;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_atualiza_aparelho2", nullable = false)
    private Date dataAtualizacaoAparelho2;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_atualiza_aparelho3", nullable = false)
    private Date dataAtualizacaoAparelho3;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_atualiza_aparelho4", nullable = false)
    private Date dataAtualizacaoAparelho4;

    public Biometria() {
        this.id = null;
        this.pessoa = null;
        this.biometria = null;
        this.lancamento = DataHoje.dataHoje();
        this.ativo = false;
        this.dataAtualizacaoAparelho1 = null;
        this.dataAtualizacaoAparelho2 = null;
        this.dataAtualizacaoAparelho3 = null;
        this.dataAtualizacaoAparelho4 = null;
    }

    public Biometria(Integer id, Pessoa pessoa, String biometria, String biometria2, Date lancamento, Boolean ativo, Boolean enviado, Date dataAtualizacaoAparelho1, Date dataAtualizacaoAparelho2, Date dataAtualizacaoAparelho3, Date dataAtualizacaoAparelho4) {
        this.id = id;
        this.pessoa = pessoa;
        this.biometria = biometria;
        this.biometria2 = biometria2;
        this.lancamento = lancamento;
        this.ativo = ativo;
        this.enviado = enviado;
        this.dataAtualizacaoAparelho1 = dataAtualizacaoAparelho1;
        this.dataAtualizacaoAparelho2 = dataAtualizacaoAparelho2;
        this.dataAtualizacaoAparelho3 = dataAtualizacaoAparelho3;
        this.dataAtualizacaoAparelho4 = dataAtualizacaoAparelho4;
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

    public Boolean getAtivo() {
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

    public Date getDataAtualizacaoAparelho1() {
        return dataAtualizacaoAparelho1;
    }

    public void setDataAtualizacaoAparelho1(Date dataAtualizacaoAparelho1) {
        this.dataAtualizacaoAparelho1 = dataAtualizacaoAparelho1;
    }

    public Date getDataAtualizacaoAparelho2() {
        return dataAtualizacaoAparelho2;
    }

    public void setDataAtualizacaoAparelho2(Date dataAtualizacaoAparelho2) {
        this.dataAtualizacaoAparelho2 = dataAtualizacaoAparelho2;
    }

    public Date getDataAtualizacaoAparelho3() {
        return dataAtualizacaoAparelho3;
    }

    public void setDataAtualizacaoAparelho3(Date dataAtualizacaoAparelho3) {
        this.dataAtualizacaoAparelho3 = dataAtualizacaoAparelho3;
    }

    public Date getDataAtualizacaoAparelho4() {
        return dataAtualizacaoAparelho4;
    }

    public void setDataAtualizacaoAparelho4(Date dataAtualizacaoAparelho4) {
        this.dataAtualizacaoAparelho4 = dataAtualizacaoAparelho4;
    }

    @Override
    public String toString() {
        return "Biometria{" + "id=" + id + ", pessoa=" + pessoa + ", biometria=" + biometria + ", biometria2=" + biometria2 + ", lancamento=" + lancamento + ", ativo=" + ativo + ", enviado=" + enviado + ", dataAtualizacaoAparelho1=" + dataAtualizacaoAparelho1 + ", dataAtualizacaoAparelho2=" + dataAtualizacaoAparelho2 + ", dataAtualizacaoAparelho3=" + dataAtualizacaoAparelho3 + ", dataAtualizacaoAparelho4=" + dataAtualizacaoAparelho4 + '}';
    }
    
    

}
