package br.com.leitor.pessoa;

import javax.persistence.*;

@Entity
@Table(name = "pes_tipo_endereco")
@NamedQueries({
    @NamedQuery(name = "TipoEndereco.findAll", query = "SELECT TEND FROM TipoEndereco AS TEND ORDER BY TEND.descricao ASC "),
    @NamedQuery(name = "TipoEndereco.findName", query = "SELECT TEND FROM TipoEndereco AS TEND WHERE UPPER(TEND.descricao) LIKE :pdescricao ORDER BY TEND.descricao ASC ")
})
public class TipoEndereco implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "ds_descricao", length = 50, nullable = false, unique = true)
    private String descricao;

    public TipoEndereco() {
        this.id = -1;
        this.descricao = "";
    }

    public TipoEndereco(int id, String descricao) {
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
}
