package teanlabsvalfran.app01.models;


public class OSPersonalizado {

    private String nomeServico;
    private String descricao;
    private Double valor;

    public OSPersonalizado() {
    }


    public OSPersonalizado(String nomeServico, String descricao, Double valor) {
        this.nomeServico = nomeServico;
        this.descricao = descricao;
        this.valor = valor;
    }


    public String getNomeServico() {
        return nomeServico;
    }
    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }





}