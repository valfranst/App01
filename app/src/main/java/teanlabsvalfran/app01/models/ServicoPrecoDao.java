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
public interface ServicoPrecoDao {

    @Query("SELECT * FROM servicopreco ORDER BY id ASC")
    LiveData<List<ServicoPreco>> getAllServicoPreco();

    @Query("SELECT * FROM servicopreco")
    List<ServicoPreco> getAll();

    @Query("SELECT * FROM servicopreco WHERE id = :id")
    ServicoPreco getById(int id);

    @Query("SELECT descricao FROM servicopreco WHERE id = :id")
    String getNomeById(int id);

    @Query("SELECT valor FROM servicopreco WHERE id = :id")
    double getValorById(int id);

    @Query("SELECT * FROM servicopreco WHERE id_servico = :id_servico")
    List<ServicoPreco> getByIdServico(int id_servico);

    @Query("SELECT COUNT(*) FROM servicopreco WHERE id_servico = :id_servico")
    int getCountByIdServico(int id_servico);





    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ServicoPreco> servicoPrecos);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ServicoPreco servicoPreco);





    @Update
    void update(ServicoPreco servicopreco);
    @Update
    void update(List<ServicoPreco> servicoPreco);





    @Delete
    void delete(ServicoPreco servicopreco);
    @Query("DELETE FROM servicopreco")
    void deleteAll();




}
