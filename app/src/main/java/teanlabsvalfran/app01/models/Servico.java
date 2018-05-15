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


@Entity(tableName="servico", foreignKeys = @ForeignKey(entity = Categoria.class, parentColumns = "id", childColumns = "id_categoria" ) )
public class Servico {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private int id;

    @ColumnInfo(name = "descricao")
    @NonNull
    private String descricao;

    @ColumnInfo(name = "preco")
    @NonNull
    private String preco;

    @ColumnInfo(name = "id_categoria")
    @NonNull
    private int id_categoria;


    @Ignore
    public Servico(){}


    public Servico(@NonNull int id, @NonNull String descricao, @NonNull String preco,@NonNull int id_categoria) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.id_categoria = id_categoria;
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
    public String getPreco() {
        return preco;
    }
    public void setPreco(@NonNull String preco) {
        this.preco = preco;
    }
    @NonNull
    public int getId_categoria() {
        return id_categoria;
    }
    public void setId_categoria(@NonNull int id_categoria) {
        this.id_categoria = id_categoria;
    }






}
