package br.com.leitor.endereco;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "end_bairro")
@NamedQueries({
    @NamedQuery(name = "Bairro.findAll", query = "SELECT BAI FROM Bairro AS BAI ORDER BY BAI.descricao ASC "),
    @NamedQuery(name = "Bairro.findName", query = "SELECT BAI FROM Bairro AS BAI WHERE UPPER(BAI.descricao) LIKE :pdescricao ORDER BY BAI.descricao ASC ")
})
public class Bairro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "ds_descricao", length = 70, nullable = false, unique = true)
    private String descricao;
    @Column(name = "is_ativo", nullable = false, columnDefinition = "boolean default true")
    private boolean ativo;

    public Bairro() {
        this.id = -1;
        this.descricao = "";
        this.ativo = true;
    }

    public Bairro(int id, String descricao, boolean ativo) {
        this.id = id;
        this.descricao = descricao;
        this.ativo = ativo;
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
    
    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
