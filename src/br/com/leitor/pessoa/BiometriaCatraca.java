package br.com.leitor.pessoa;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "pes_biometria_catraca")
public class BiometriaCatraca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = true)
    @OneToOne
    private Pessoa pessoa;
    @Column(name = "ds_ip", length = 50)
    private String ip;

    public BiometriaCatraca() {
        this.id = null;
        this.pessoa = null;
        this.ip = "";
    }

    public BiometriaCatraca(Integer id, Pessoa pessoa, String ip) {
        this.id = id;
        this.pessoa = pessoa;
        this.ip = ip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
