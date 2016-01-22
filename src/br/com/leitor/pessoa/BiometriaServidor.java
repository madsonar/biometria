package br.com.leitor.pessoa;

import br.com.leitor.seguranca.MacFilial;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "pes_biometria_servidor")
public class BiometriaServidor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_mac_filial", referencedColumnName = "id", nullable = false)
    @OneToOne
    private MacFilial macFilial;
    @Column(name = "is_ativo", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean ativo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_ativo", nullable = false)
    private Date dataAtivo;

    public BiometriaServidor() {
        this.id = null;
        this.macFilial = null;
        this.ativo = false;
        this.dataAtivo = new Date();
    }

    public BiometriaServidor(Integer id, MacFilial macFilial, Boolean ativo, Date dataAtivo) {
        this.id = id;
        this.macFilial = macFilial;
        this.ativo = ativo;
        this.dataAtivo = dataAtivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MacFilial getMacFilial() {
        return macFilial;
    }

    public void setMacFilial(MacFilial macFilial) {
        this.macFilial = macFilial;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataAtivo() {
        return dataAtivo;
    }

    public void setDataAtivo(Date dataAtivo) {
        this.dataAtivo = dataAtivo;
    }

    @Override
    public String toString() {
        return "BiometriaServidor{" + "id=" + id + ", macFilial=" + macFilial + ", ativo=" + ativo + ", dataAtivo=" + dataAtivo + '}';
    }

}
