package br.com.leitor.pessoa;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "pes_biometria_atualiza_catraca")
public class BiometriaAtualizaCatraca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "is_aparelho1", columnDefinition = "boolean default false", nullable = false)
    private Boolean aparelho1;
    @Column(name = "is_aparelho2", columnDefinition = "boolean default false", nullable = false)
    private Boolean aparelho2;
    @Column(name = "is_aparelho3", columnDefinition = "boolean default false", nullable = false)
    private Boolean aparelho3;
    @Column(name = "is_aparelho4", columnDefinition = "boolean default false", nullable = false)
    private Boolean aparelho4;

    public BiometriaAtualizaCatraca() {
        this.id = null;
        this.aparelho1 = false;
        this.aparelho2 = false;
        this.aparelho3 = false;
        this.aparelho4 = false;
    }

    public BiometriaAtualizaCatraca(Integer id, Boolean aparelho1, Boolean aparelho2, Boolean aparelho3, Boolean aparelho4) {
        this.id = id;
        this.aparelho1 = aparelho1;
        this.aparelho2 = aparelho2;
        this.aparelho3 = aparelho3;
        this.aparelho4 = aparelho4;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAparelho1() {
        return aparelho1;
    }

    public void setAparelho1(Boolean aparelho1) {
        this.aparelho1 = aparelho1;
    }

    public Boolean getAparelho2() {
        return aparelho2;
    }

    public void setAparelho2(Boolean aparelho2) {
        this.aparelho2 = aparelho2;
    }

    public Boolean getAparelho3() {
        return aparelho3;
    }

    public void setAparelho3(Boolean aparelho3) {
        this.aparelho3 = aparelho3;
    }

    public Boolean getAparelho4() {
        return aparelho4;
    }

    public void setAparelho4(Boolean aparelho4) {
        this.aparelho4 = aparelho4;
    }

}
