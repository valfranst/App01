package teanlabsvalfran.app01.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Valfran on 25/12/2017.
 */


@Entity(tableName="veiculo")
public class Veiculo {


    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private int id;

    @ColumnInfo(name = "cliente")
    @NonNull
    private String cliente;

    @ColumnInfo(name = "placa")
    @NonNull
    private String placa;

    @ColumnInfo(name = "modelo")
    @NonNull
    private String modelo;

    @Ignore
    public Veiculo(){}

    public Veiculo(int id, String placa, String cliente, String modelo) {
        this.id = id;
        this.placa = placa;
        this.cliente = cliente;
        this.modelo = modelo;
    }



    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getCliente() {
        return cliente;
    }

    public void setCliente(@NonNull String cliente) {
        this.cliente = cliente;
    }

    @NonNull
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(@NonNull String placa) {
        this.placa = placa;
    }

    @NonNull
    public String getModelo() {
        return modelo;
    }

    public void setModelo(@NonNull String modelo) {
        this.modelo = modelo;
    }




}
