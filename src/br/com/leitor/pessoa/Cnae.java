package br.com.leitor.pessoa;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "pes_cnae")
@NamedQueries({
    @NamedQuery(name = "Cnae.findAll", query = "SELECT CN FROM Cnae AS CN ORDER BY CN.cnae ASC, CN.numero ASC"),
    @NamedQuery(name = "Cnae.findName", query = "SELECT CN FROM Cnae AS CN WHERE UPPER(CN.cnae) LIKE :pdescricao ORDER BY CN.cnae ASC, CN.numero ASC ")
})
public class Cnae implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ds_cnae", length = 1000, nullable = false)
    private String cnae;
    @Column(name = "ds_numero", length = 50, nullable = false)
    private String numero;

    public Cnae() {
        this.id = null;
        this.cnae = "";
        this.numero = "";
    }

    public Cnae(int id, String cnae, String numero) {
        this.id = id;
        this.cnae = cnae;
        this.numero = numero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + (this.cnae != null ? this.cnae.hashCode() : 0);
        hash = 53 * hash + (this.numero != null ? this.numero.hashCode() : 0);
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
        final Cnae other = (Cnae) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.cnae == null) ? (other.cnae != null) : !this.cnae.equals(other.cnae)) {
            return false;
        }
        if ((this.numero == null) ? (other.numero != null) : !this.numero.equals(other.numero)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cnae{" + "id=" + id + ", cnae=" + cnae + ", numero=" + numero + '}';
    }
}
