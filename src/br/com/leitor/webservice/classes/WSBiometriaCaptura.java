package br.com.leitor.webservice.classes;

public class WSBiometriaCaptura {

    private Integer codigo;
    private Integer codigo_pessoa;
    private Integer codigo_mac_filial;
    private Integer codigo_biometria;
    private String digital1;
    private String digital2;
    private Boolean ativa;

    public WSBiometriaCaptura() {
        this.codigo = null;
        this.codigo_pessoa = null;
        this.codigo_mac_filial = null;
        this.codigo_biometria = null;
        this.digital1 = "";
        this.digital2 = "";
        this.ativa = false;
    }

    public WSBiometriaCaptura(Integer codigo, Integer codigo_pessoa, Integer codigo_mac_filial, Integer codigo_biometria, String digital1, String digital2, Boolean ativa) {
        this.codigo = codigo;
        this.codigo_pessoa = codigo_pessoa;
        this.codigo_mac_filial = codigo_mac_filial;
        this.codigo_biometria = codigo_biometria;
        this.digital1 = digital1;
        this.digital2 = digital2;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo_pessoa() {
        return codigo_pessoa;
    }

    public void setCodigo_pessoa(Integer codigo_pessoa) {
        this.codigo_pessoa = codigo_pessoa;
    }

    public Integer getCodigo_mac_filial() {
        return codigo_mac_filial;
    }

    public void setCodigo_mac_filial(Integer codigo_mac_filial) {
        this.codigo_mac_filial = codigo_mac_filial;
    }

    public Integer getCodigo_biometria() {
        return codigo_biometria;
    }

    public void setCodigo_biometria(Integer codigo_biometria) {
        this.codigo_biometria = codigo_biometria;
    }

    public String getDigital1() {
        return digital1;
    }

    public void setDigital1(String digital1) {
        this.digital1 = digital1;
    }

    public String getDigital2() {
        return digital2;
    }

    public void setDigital2(String digital2) {
        this.digital2 = digital2;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

}
