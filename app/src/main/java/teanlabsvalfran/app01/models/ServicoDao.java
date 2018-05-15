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
public interface ServicoDao {

    @Query("SELECT * FROM servico ORDER BY id ASC")
    LiveData<List<Servico>> getAllServico();
    @Query("SELECT * FROM servico")
    List<Servico> getAll();

    @Query("SELECT * FROM servico WHERE id = :id ORDER BY id ASC")
    Servico getById(int id);

    @Query("SELECT descricao FROM servico WHERE id = :id ORDER BY id ASC")
    String getNomeById(int id);

    @Query("SELECT * FROM servico WHERE id_categoria = :id_categoria ORDER BY id ASC")
    List<Servico> getByCategoria(int id_categoria);

    @Query("SELECT COUNT(*) FROM servico WHERE id_categoria = :id_categoria ORDER BY id ASC")
    int getCountByCategoria(int id_categoria);

    @Query("SELECT COUNT(*) FROM servico")
    int getCount();

    @Query("SELECT id_categoria FROM servico WHERE id = :id")
    int getidCategoriaById(int id);




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Servico servico);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Servico> servicos);




    @Query("UPDATE servico SET preco=''")
    void resetServicos();

    @Update
    void update(Servico servico);
    @Update
    void update(List<Servico> servico);





    @Delete
    void delete(Servico servico);
    @Query("DELETE FROM servico")
    void deleteAll();




}
