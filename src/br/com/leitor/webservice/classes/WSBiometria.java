package br.com.leitor.webservice.classes;

import java.util.Date;

public class WSBiometria {

    /**
     * ID da pessoa
     */
    private Integer codigo;
    private Integer codigo_biometria;
    private String biometria;
    private Boolean ativo;
    private Date dataAtualizacaoAparelho1;
    private Date dataAtualizacaoAparelho2;
    private Date dataAtualizacaoAparelho3;
    private Date dataAtualizacaoAparelho4;

    public WSBiometria(Integer codigo, Integer codigo_biometria, String biometria, Boolean ativo, Date dataAtualizacaoAparelho1, Date dataAtualizacaoAparelho2, Date dataAtualizacaoAparelho3, Date dataAtualizacaoAparelho4) {
        this.codigo = codigo;
        this.codigo_biometria = codigo_biometria;
        this.biometria = biometria;
        this.ativo = ativo;
        this.dataAtualizacaoAparelho1 = dataAtualizacaoAparelho1;
        this.dataAtualizacaoAparelho2 = dataAtualizacaoAparelho2;
        this.dataAtualizacaoAparelho3 = dataAtualizacaoAparelho3;
        this.dataAtualizacaoAparelho4 = dataAtualizacaoAparelho4;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getBiometria() {
        return biometria;
    }

    public void setBiometria(String biometria) {
        this.biometria = biometria;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataAtualizacaoAparelho1() {
        return dataAtualizacaoAparelho1;
    }

    public void setDataAtualizacaoAparelho1(Date dataAtualizacaoAparelho1) {
        this.dataAtualizacaoAparelho1 = dataAtualizacaoAparelho1;
    }

    public Date getDataAtualizacaoAparelho2() {
        return dataAtualizacaoAparelho2;
    }

    public void setDataAtualizacaoAparelho2(Date dataAtualizacaoAparelho2) {
        this.dataAtualizacaoAparelho2 = dataAtualizacaoAparelho2;
    }

    public Date getDataAtualizacaoAparelho3() {
        return dataAtualizacaoAparelho3;
    }

    public void setDataAtualizacaoAparelho3(Date dataAtualizacaoAparelho3) {
        this.dataAtualizacaoAparelho3 = dataAtualizacaoAparelho3;
    }

    public Date getDataAtualizacaoAparelho4() {
        return dataAtualizacaoAparelho4;
    }

    public void setDataAtualizacaoAparelho4(Date dataAtualizacaoAparelho4) {
        this.dataAtualizacaoAparelho4 = dataAtualizacaoAparelho4;
    }

    public Integer getCodigo_biometria() {
        return codigo_biometria;
    }

    public void setCodigo_biometria(Integer codigo_biometria) {
        this.codigo_biometria = codigo_biometria;
    }

}
