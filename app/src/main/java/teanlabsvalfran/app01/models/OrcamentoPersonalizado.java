package teanlabsvalfran.app01.models;


public class OrcamentoPersonalizado {

    private String placa;
    private String data;
    private Double valor;
    private String idOrcamento;

    public OrcamentoPersonalizado(){}

    public OrcamentoPersonalizado(String placa, String data, Double valor, String idOrcamento) {
        this.placa = placa;
        this.data = data;
        this.valor = valor;
        this.idOrcamento = idOrcamento;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getIdOrcamento() {
        return idOrcamento;
    }
    public void setIdOrcamento(String idOrcamento) {
        this.idOrcamento = idOrcamento;
    }






}
