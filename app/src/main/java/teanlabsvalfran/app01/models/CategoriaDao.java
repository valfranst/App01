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
public interface CategoriaDao {

    @Query("SELECT * FROM categoria ORDER BY id ASC")
    LiveData<List<Categoria>> getAllCategoria();

    @Query("SELECT * FROM categoria")
    List<Categoria> getAll();

    @Query("SELECT * FROM categoria WHERE id = :id ORDER BY id ASC")
    Categoria getById(int id);

    @Query("SELECT MIN(id) FROM categoria")
    int getPrimeira();





    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Categoria categoria);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Categoria> categorias);





    @Update
    void update(Categoria categoria);
    @Update
    void update(List<Categoria> categorias);





    @Delete
    void delete(Categoria categoria);
    @Query("DELETE FROM categoria")
    void deleteAll();





}
