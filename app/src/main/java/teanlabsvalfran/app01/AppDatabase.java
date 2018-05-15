package teanlabsvalfran.app01;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import teanlabsvalfran.app01.models.Categoria;
import teanlabsvalfran.app01.models.CategoriaDao;
import teanlabsvalfran.app01.models.DateConverter;
import teanlabsvalfran.app01.models.Orcamento;
import teanlabsvalfran.app01.models.OrcamentoDao;
import teanlabsvalfran.app01.models.OrcamentoServico;
import teanlabsvalfran.app01.models.OrcamentoServicoDao;
import teanlabsvalfran.app01.models.Servico;
import teanlabsvalfran.app01.models.ServicoDao;
import teanlabsvalfran.app01.models.ServicoPreco;
import teanlabsvalfran.app01.models.ServicoPrecoDao;
import teanlabsvalfran.app01.models.Veiculo;
import teanlabsvalfran.app01.models.VeiculoDao;

/**
 * Created by Valfran on 28/01/2018.
 */

@Database(entities = {Servico.class, Categoria.class, Veiculo.class, ServicoPreco.class, Orcamento.class, OrcamentoServico.class}, version = 7)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static final String DB_NAME = "app_db";

    public abstract ServicoDao servicoDao();
    public abstract CategoriaDao categoriaDao();
    public abstract VeiculoDao veiculoDao();
    public abstract ServicoPrecoDao servicoPrecoDao();
    public abstract OrcamentoDao orcamentoDao();
    public abstract OrcamentoServicoDao orcamentoServicoDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static AppDatabase getMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }



    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }




}
