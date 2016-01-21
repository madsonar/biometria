package br.com.leitor.seguranca;

import br.com.leitor.financeiro.Caixa;
import br.com.leitor.pessoa.Filial;
import br.com.leitor.seguranca.dao.MacFilialDao;
import br.com.leitor.utils.Mac;
import javax.persistence.*;

@Entity
@Table(name = "seg_mac_filial")
public class MacFilial implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @JoinColumn(name = "id_departamento", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Departamento departamento;
    @JoinColumn(name = "id_filial", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Filial filial;
    @Column(name = "ds_mac", nullable = false)
    private String mac;
    @Column(name = "nr_mesa")
    private int mesa;
    @JoinColumn(name = "id_caixa", referencedColumnName = "id")
    @ManyToOne
    private Caixa caixa;

    public MacFilial() {
        this.id = -1;
        this.departamento = new Departamento();
        this.filial = new Filial();
        this.mac = "";
        this.mesa = 0;
        this.caixa = new Caixa();
    }

    public MacFilial(int id, Departamento departamento, Filial filial, String mac, int mesa, Caixa caixa) {
        this.id = id;
        this.departamento = departamento;
        this.filial = filial;
        this.mac = mac;
        this.mesa = mesa;
        this.caixa = caixa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public static MacFilial getAcessoFilial() {
        String mac = Mac.getInstance();
        MacFilialDao macFilialDao = new MacFilialDao();
        MacFilial mf = macFilialDao.findMacFilial(mac);
        if (mf == null) {
            return null;
        }
        return mf;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }
}
