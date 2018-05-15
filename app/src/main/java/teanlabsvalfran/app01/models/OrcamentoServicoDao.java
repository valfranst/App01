package teanlabsvalfran.app01.models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Valfran on 28/01/2018.
 */


@Dao
public interface OrcamentoServicoDao {

    @Query("SELECT * FROM orcamentoservico ORDER BY OrcamentoservicoId ASC")
    LiveData<List<OrcamentoServico>> getAllOrcamentoServico();

    @Query("SELECT * FROM orcamentoservico")
    List<OrcamentoServico> getAll();

    @Query("SELECT * FROM orcamentoservico WHERE OrcamentoservicoId LIKE :OrcamentoservicoId")
    OrcamentoServico getById(String OrcamentoservicoId);


    //***********************************
    @Query("SELECT * FROM orcamentoservico WHERE OrcamentoId LIKE :OrcamentoId")
    List<OrcamentoServico> getByIdOrcamento(String OrcamentoId);


    @Query("SELECT * FROM orcamentoservico WHERE Status = :Status")
    List<OrcamentoServico> getByStatus(int Status);

    //***********************************


    @Query("SELECT SUM(Valor) FROM orcamentoservico WHERE OrcamentoId LIKE :OrcamentoId")
    double getValorByIdOrcamentoServico(String OrcamentoId);





    @Query("SELECT * FROM orcamentoservico WHERE ServicoId = :ServicoId")
    List<OrcamentoServico> getByIdServico(int ServicoId);

    @Query("SELECT * FROM orcamentoservico WHERE ServicoprecoId = :ServicoprecoId")
    List<OrcamentoServico> getByIdServicoPreco(int ServicoprecoId);





    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<OrcamentoServico> orcamentoServicos);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrcamentoServico orcamentoServico);





    @Update
    void update(OrcamentoServico orcamentoServico);
    @Update
    void update(List<OrcamentoServico> orcamentoServicos);





    @Delete
    void delete(OrcamentoServico orcamentoServico);
    @Query("DELETE FROM orcamentoservico")
    void deleteAll();

    @Query("DELETE FROM orcamentoservico WHERE OrcamentoId LIKE :OrcamentoId")
    void deleteByIdOrcamento(String OrcamentoId);




}
