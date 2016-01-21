package br.com.leitor.pessoa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * <p>
 * <strong>Filial</strong></p>
 * <p>
 * <strong>Definição:</strong> Estabelecimento dependente de outro.</p>
 * <p>
 * <strong>Importante:</strong> Utilizar filiais somente caso houver um
 * endereçamento diferente da sede!</p>
 *
 * @author rtools
 */
@Entity
@Table(name = "pes_filial",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_matriz", "id_filial", "nr_centro_custo"})
)
@NamedQueries({
    @NamedQuery(name = "Filial.findAll", query = "SELECT FIL FROM Filial AS FIL ORDER BY FIL.filial.pessoa.nome ASC ")
})
public class Filial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @JoinColumn(name = "id_matriz", referencedColumnName = "id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Juridica matriz;
    @JoinColumn(name = "id_filial", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Juridica filial;
    @Column(name = "nr_centro_custo")
    private int centroCusto;

    public Filial() {
        this.id = -1;
        this.matriz = new Juridica();
        this.filial = new Juridica();
        this.centroCusto = 0;
    }

    public Filial(int id, Juridica matriz, Juridica filial, int centroCusto) {
        this.id = id;
        this.matriz = matriz;
        this.filial = filial;
        this.centroCusto = centroCusto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Juridica getMatriz() {
        return matriz;
    }

    public void setMatriz(Juridica matriz) {
        this.matriz = matriz;
    }

    public Juridica getFilial() {
        return filial;
    }

    public void setFilial(Juridica filial) {
        this.filial = filial;
    }

    public int getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(int centroCusto) {
        this.centroCusto = centroCusto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        hash = 29 * hash + (this.matriz != null ? this.matriz.hashCode() : 0);
        hash = 29 * hash + (this.filial != null ? this.filial.hashCode() : 0);
        hash = 29 * hash + this.centroCusto;
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
        final Filial other = (Filial) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.matriz != other.matriz && (this.matriz == null || !this.matriz.equals(other.matriz))) {
            return false;
        }
        if (this.filial != other.filial && (this.filial == null || !this.filial.equals(other.filial))) {
            return false;
        }
        if (this.centroCusto != other.centroCusto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Filial{" + "id=" + id + ", matriz=" + matriz.getId() + ", filial=" + filial.getId() + ", centroCusto=" + centroCusto + '}';
    }
}
