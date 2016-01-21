package br.com.leitor.pessoa;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "pes_nacionalidade")
@NamedQueries({
    @NamedQuery(name = "Nacionalidade.findAll", query = "SELECT NAC FROM Nacionalidade AS NAC ORDER BY NAC.descricao ASC "),
    @NamedQuery(name = "Nacionalidade.findName", query = "SELECT NAC FROM Nacionalidade AS NAC WHERE UPPER(NAC.descricao) LIKE :pdescricao ORDER BY NAC.descricao ASC ")
})
public class Nacionalidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "ds_descricao", length = 50, nullable = false, unique = true)
    private String descricao;

    public Nacionalidade() {
        this.id = -1;
        this.descricao = "";
    }

    public Nacionalidade(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
        hash = 47 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
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
        final Nacionalidade other = (Nacionalidade) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.descricao == null) ? (other.descricao != null) : !this.descricao.equals(other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Nacionalidade{" + "id=" + id + ", descricao=" + descricao + '}';
    }

}
