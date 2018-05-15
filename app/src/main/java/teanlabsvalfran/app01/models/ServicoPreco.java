package teanlabsvalfran.app01.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Valfran on 25/12/2017.
 */


@Entity(tableName="servicopreco", foreignKeys = @ForeignKey(entity = Servico.class, parentColumns = "id", childColumns = "id_servico" ) )
public class ServicoPreco {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private int id;

    @ColumnInfo(name = "descricao")
    @NonNull
    private String descricao;

    @ColumnInfo(name = "valor")
    @NonNull
    private double valor;

    @ColumnInfo(name = "id_servico")
    @NonNull
    private int id_servico;




    @Ignore
    public ServicoPreco(){}
    public ServicoPreco(@NonNull int id, @NonNull String descricao, @NonNull double valor, @NonNull int id_servico) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.id_servico = id_servico;
    }





    @NonNull
    public int getId() {
        return id;
    }
    public void setId(@NonNull int id) {
        this.id = id;
    }
    @NonNull
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(@NonNull String descricao) {
        this.descricao = descricao;
    }
    @NonNull
    public double getValor() {
        return valor;
    }
    public void setValor(@NonNull double valor) {
        this.valor = valor;
    }
    @NonNull
    public int getId_servico() {
        return id_servico;
    }
    public void setId_servico(@NonNull int id_servico) {
        this.id_servico = id_servico;
    }








}
