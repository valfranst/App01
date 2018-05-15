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
public interface VeiculoDao {

    @Query("SELECT * FROM veiculo ORDER BY id ASC")
    LiveData<List<Veiculo>> getAllVeiculo();

    @Query("SELECT * FROM veiculo")
    List<Veiculo> getAll();

    @Query("SELECT * FROM veiculo WHERE id = :id")
    Veiculo getById(int id);

    @Query("SELECT id FROM veiculo WHERE placa LIKE :placa")
    int getIdByPlaca(String placa);


    @Query("SELECT placa FROM veiculo WHERE id = :id")
    String getPlacaById(int id);




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Veiculo> veiculos);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Veiculo veiculo);




    @Update
    void update(Veiculo veiculo);
    @Update
    void updateAll(List<Veiculo> veiculos);


    @Delete
    void delete(Veiculo veiculo);
    @Query("DELETE FROM veiculo")
    void deleteAll();




}
