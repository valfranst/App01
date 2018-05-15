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

@Entity(tableName="orcamentoservico",
        foreignKeys = {@ForeignKey(entity = Orcamento.class, parentColumns = "OrcamentoId", childColumns = "OrcamentoId" ),
                      @ForeignKey(entity = Servico.class, parentColumns = "id", childColumns = "ServicoId" ),
                      @ForeignKey(entity = ServicoPreco.class, parentColumns = "id", childColumns = "ServicoprecoId" )} )
public class OrcamentoServico {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "OrcamentoservicoId")
    @NonNull
    private String OrcamentoservicoId;

    @ColumnInfo(name = "OrcamentoId")
    @NonNull
    private String OrcamentoId;

    @ColumnInfo(name = "ServicoId")
    @NonNull
    private int ServicoId;

    @ColumnInfo(name = "ServicoprecoId")
    @NonNull
    private int ServicoprecoId;

    @ColumnInfo(name = "Valor")
    @NonNull
    private double Valor;

    @ColumnInfo(name = "Status")
    @NonNull
    private int Status;



    @Ignore
    public OrcamentoServico() {  }

    public OrcamentoServico(@NonNull String OrcamentoservicoId, @NonNull String OrcamentoId, @NonNull int ServicoId, @NonNull int ServicoprecoId, @NonNull double Valor, @NonNull int Status) {
        this.OrcamentoservicoId = OrcamentoservicoId;
        this.OrcamentoId = OrcamentoId;
        this.ServicoId = ServicoId;
        this.ServicoprecoId = ServicoprecoId;
        this.Valor = Valor;
        this.Status = Status;
    }

    @NonNull
    public String getOrcamentoservicoId() {
        return OrcamentoservicoId;
    }

    public void setOrcamentoservicoId(@NonNull String orcamentoservicoId) {
        OrcamentoservicoId = orcamentoservicoId;
    }

    @NonNull
    public String getOrcamentoId() {
        return OrcamentoId;
    }

    public void setOrcamentoId(@NonNull String orcamentoId) {
        OrcamentoId = orcamentoId;
    }

    @NonNull
    public int getServicoId() {
        return ServicoId;
    }

    public void setServicoId(@NonNull int servicoId) {
        ServicoId = servicoId;
    }

    @NonNull
    public int getServicoprecoId() {
        return ServicoprecoId;
    }

    public void setServicoprecoId(@NonNull int servicoprecoId) {
        ServicoprecoId = servicoprecoId;
    }

    @NonNull
    public double getValor() {
        return Valor;
    }

    public void setValor(@NonNull double valor) {
        Valor = valor;
    }

    @NonNull
    public int getStatus() {
        return Status;
    }

    public void setStatus(@NonNull int status) {
        Status = status;
    }





    //*****
}
