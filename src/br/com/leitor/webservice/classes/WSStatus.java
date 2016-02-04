package br.com.leitor.webservice.classes;


public class WSStatus {

    private Integer codigo;
    private String descricao;

    public WSStatus() {
        this.codigo = 0;
        this.descricao = "";
    }

    public WSStatus(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
