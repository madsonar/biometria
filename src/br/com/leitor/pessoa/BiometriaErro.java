package br.com.leitor.pessoa;

import br.com.leitor.seguranca.MacFilial;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "pes_biometria_erro")
public class BiometriaErro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_mac_filial", referencedColumnName = "id", nullable = false)
    @OneToOne
    private MacFilial macFilial;
    @Column(name = "nr_dispositivo")
    private Integer nrDispositivo;
    @Column(name = "nr_codigo_erro")
    private Integer nrCodigoErro;

    public BiometriaErro() {
        this.id = null;
        this.macFilial = null;
        this.nrDispositivo = 0;
        this.nrCodigoErro = 0;
    }

    public BiometriaErro(Integer id, MacFilial macFilial, Integer nrDispositivo, Integer nrCodigoErro) {
        this.id = id;
        this.macFilial = macFilial;
        this.nrDispositivo = nrDispositivo;
        this.nrCodigoErro = nrCodigoErro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNrDispositivo() {
        return nrDispositivo;
    }

    public void setNrDispositivo(Integer nrDispositivo) {
        this.nrDispositivo = nrDispositivo;
    }

    public Integer getNrCodigoErro() {
        return nrCodigoErro;
    }

    public void setNrCodigoErro(Integer nrCodigoErro) {
        this.nrCodigoErro = nrCodigoErro;
    }

    public MacFilial getMacFilial() {
        return macFilial;
    }

    public void setMacFilial(MacFilial macFilial) {
        this.macFilial = macFilial;
    }

    @Override
    public String toString() {
        return "BiometriaErro{" + "id=" + id + ", macFilial=" + macFilial + ", nrDispositivo=" + nrDispositivo + ", nrCodigoErro=" + nrCodigoErro + '}';
    }

}
