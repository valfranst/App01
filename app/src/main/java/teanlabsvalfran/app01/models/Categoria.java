package teanlabsvalfran.app01.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Valfran on 25/12/2017.
 */


@Entity(tableName="categoria")
public class Categoria {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private int id;

    @ColumnInfo(name = "descricao")
    @NonNull
    private String descricao;


    @Ignore
    public Categoria(){}

    public Categoria(@NonNull int id, @NonNull String descricao) {
        this.id = id;
        this.descricao = descricao;
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




}
