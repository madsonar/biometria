package br.com.leitor.pessoa;

import br.com.leitor.seguranca.MacFilial;
import java.io.Serializable;
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
    private boolean ativo;

    public BiometriaServidor() {
        this.id = null;
        this.macFilial = null;
        this.ativo = false;
    }

    public BiometriaServidor(Integer id, MacFilial macFilial, boolean ativo) {
        this.id = id;
        this.macFilial = macFilial;
        this.ativo = ativo;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final BiometriaCaptura other = (BiometriaCaptura) obj;
        return true;
    }

    @Override
    public String toString() {
        return "BiometriaServidor{" + "id=" + id + ", macFilial=" + macFilial + ", ativo=" + ativo + '}';
    }

}
