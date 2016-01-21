package br.com.leitor.financeiro;

import br.com.leitor.pessoa.Filial;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * <p>
 * <strong>Caixa</strong></p>
 * <p>
 * <strong>Detalhes:</strong>Define a filial, número e detalhes dos caixa que
 * poderão ser atribuídos a um computador da filial específica.</p>
 * <p>
 * <strong>Importante:</strong> Utilizar filiais somente caso houver um
 * endereçamento diferente da sede!</p>
 *
 * @author rtools
 */
@Entity
@Table(name = "fin_caixa",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_filial", "nr_caixa", "ds_descricao"})
)
@NamedQueries({
    @NamedQuery(name = "Caixa.pesquisaID", query = "SELECT C FROM Caixa AS C WHERE C.id = :pid"),
    @NamedQuery(name = "Caixa.findAll", query = "SELECT C FROM Caixa AS C ORDER BY C.filial.filial.pessoa.nome ASC, C.caixa ASC, C.descricao ASC ")
})
public class Caixa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nr_caixa")
    private int caixa;
    @Column(name = "ds_descricao", length = 100)
    private String descricao;
    @JoinColumn(name = "id_filial", referencedColumnName = "id")
    @ManyToOne
    private Filial filial;

    public Caixa() {
        this.id = -1;
        this.caixa = 0;
        this.descricao = "";
        this.filial = new Filial();
    }

    public Caixa(int id, int caixa, String descricao, Filial filial) {
        this.id = id;
        this.caixa = caixa;
        this.descricao = descricao;
        this.filial = filial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCaixa() {
        return caixa;
    }

    public void setCaixa(int caixa) {
        this.caixa = caixa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }
}
