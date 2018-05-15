package teanlabsvalfran.app01.models;

/**
 * Created by Valfran on 25/12/2017.
 */

public class SubServico {

    private String id;
    private String nome;
    private String valor;
    private String servico;


    public SubServico() {  }

    public SubServico(String id, String nome, String valor, String servico) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.servico = servico;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }
    public String getServico() {
        return servico;
    }
    public void setServico(String servico) {
        this.servico = servico;
    }




}
