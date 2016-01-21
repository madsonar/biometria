package br.com.leitor.pessoa;

import br.com.leitor.seguranca.MacFilial;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "pes_biometria_captura")
public class BiometriaCaptura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
    @OneToOne
    private Pessoa pessoa;
    @JoinColumn(name = "id_mac_filial", referencedColumnName = "id", nullable = false)
    @OneToOne
    private MacFilial macFilial;

    public BiometriaCaptura() {
        this.id = null;
        this.pessoa = null;
        this.macFilial = null;
    }

    public BiometriaCaptura(Integer id, Pessoa pessoa, MacFilial macFilial) {
        this.id = id;
        this.pessoa = pessoa;
        this.macFilial = macFilial;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        return "BiometriaServidor{" + "id=" + id + ", pessoa=" + pessoa + ", macFilial=" + macFilial + '}';
    }

}
