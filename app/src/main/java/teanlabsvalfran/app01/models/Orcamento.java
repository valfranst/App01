package teanlabsvalfran.app01.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Valfran on 25/12/2017.
 */

@Entity(tableName="orcamento", foreignKeys = @ForeignKey(entity = Veiculo.class, parentColumns = "id", childColumns = "VeiculoId" ) )
@TypeConverters(DateConverter.class)
public class Orcamento {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "OrcamentoId")
    @NonNull
    private String OrcamentoId;

    @ColumnInfo(name = "DataAbertura")
    @TypeConverters({DateConverter.class})
    @NonNull
    private Date DataAbertura;

    @ColumnInfo(name = "VeiculoId")
    @NonNull
    private int VeiculoId;

    @ColumnInfo(name = "ValorTotal")
    @NonNull
    private double ValorTotal;

    @ColumnInfo(name = "Status")
    @NonNull
    private int Status;




    @Ignore
    public Orcamento() {  }

    public Orcamento(@NonNull String OrcamentoId, @NonNull Date DataAbertura, @NonNull int VeiculoId, @NonNull double ValorTotal, @NonNull int Status) {
        this.OrcamentoId = OrcamentoId;
        this.DataAbertura = DataAbertura;
        this.VeiculoId = VeiculoId;
        this.ValorTotal = ValorTotal;
        this.Status = Status;
    }

    @Ignore
    public Orcamento(@NonNull Date DataAbertura, @NonNull int VeiculoId, @NonNull double valorTotal) {
        this.DataAbertura = DataAbertura;
        this.VeiculoId = VeiculoId;
        this.ValorTotal = valorTotal;
    }


    public String getOrcamentoId() {
        return OrcamentoId;
    }

    public void setOrcamentoId(String orcamentoId) {
        this.OrcamentoId = orcamentoId;
    }

    @NonNull
    public Date getDataAbertura() {
        return DataAbertura;
    }

    public void setDataAbertura(@NonNull Date dataAbertura) {
        this.DataAbertura = dataAbertura;
    }

    @NonNull
    public int getVeiculoId() {
        return VeiculoId;
    }

    public void setVeiculoId(@NonNull int veiculoId) {
        this.VeiculoId = veiculoId;
    }

    @NonNull
    public double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(@NonNull double valorTotal) {
        this.ValorTotal = valorTotal;
    }

    @NonNull
    public int getStatus() {
        return Status;
    }

    public void setStatus(@NonNull int status) {
        this.Status = status;
    }



}
