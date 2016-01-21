package br.com.leitor.pessoa;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "pes_tipo_documento")
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT TDOC FROM TipoDocumento AS TDOC ORDER BY TDOC.descricao ASC "),
    @NamedQuery(name = "TipoDocumento.findName", query = "SELECT TDOC FROM TipoDocumento AS TDOC WHERE UPPER(TDOC.descricao) LIKE :pdescricao ORDER BY TDOC.descricao ASC ")
})
public class TipoDocumento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "ds_descricao", length = 50, nullable = false, unique = true)
    private String descricao;

    public TipoDocumento() {
        this.id = -1;
        this.descricao = "";
    }

    public TipoDocumento(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
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
        hash = 17 * hash + this.id;
        hash = 17 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
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
        final TipoDocumento other = (TipoDocumento) obj;
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
        return "TipoDocumento{" + "id=" + id + ", descricao=" + descricao + '}';
    }

}
