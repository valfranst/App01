package teanlabsvalfran.app01.models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

/**
 * Created by Valfran on 28/01/2018.
 */


@Dao
public interface OrcamentoDao {

    @Query("SELECT * FROM orcamento ORDER BY OrcamentoId ASC")
    LiveData<List<Orcamento>> getAllOrcamento();
    @Query("SELECT * FROM orcamento")
    List<Orcamento> getAll();

    @Query("SELECT * FROM orcamento WHERE OrcamentoId LIKE :OrcamentoId")
    Orcamento getById(String OrcamentoId);

    @Query("SELECT OrcamentoId FROM orcamento WHERE OrcamentoId = (SELECT MAX(OrcamentoId)  FROM orcamento);")
    int getUltimo();


    //***********************************
    @Query("SELECT * FROM orcamento WHERE DataAbertura BETWEEN :DataAbertura AND :DataAbertura")
    List<Orcamento> getByData(Date DataAbertura);

    @Query("SELECT * FROM orcamento WHERE VeiculoId = :VeiculoId")
    List<Orcamento> getByVeiculoId(int VeiculoId);


    @Query("SELECT * FROM orcamento WHERE DataAbertura BETWEEN :DataAbertura AND :DataAbertura AND VeiculoId = :VeiculoId")
    List<Orcamento> getByDataVeiculo(Date DataAbertura, int VeiculoId);

    @Query("SELECT * FROM orcamento WHERE Status = :Status")
    List<Orcamento> getByStatus(int Status);

    //***********************************




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Orcamento orcamento);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Orcamento> orcamentos);




    @Update
    void update(Orcamento orcamento);
    @Update
    void update(List<Orcamento> orcamentos);





    @Query("DELETE FROM orcamento WHERE OrcamentoId = :OrcamentoId")
    void delete(int OrcamentoId);

    @Query("DELETE FROM orcamento")
    void deleteAll();




}
