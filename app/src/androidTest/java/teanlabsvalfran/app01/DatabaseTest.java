package teanlabsvalfran.app01;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import teanlabsvalfran.app01.models.CategoriaDao;
import teanlabsvalfran.app01.models.Orcamento;
import teanlabsvalfran.app01.models.OrcamentoDao;
import teanlabsvalfran.app01.models.ServicoDao;
import teanlabsvalfran.app01.models.ServicoPrecoDao;
import teanlabsvalfran.app01.models.Veiculo;
import teanlabsvalfran.app01.models.VeiculoDao;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Valfran on 31/01/2018.
 */

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private VeiculoDao mVeiculoDao;
    private CategoriaDao mCategoriaDao;
    private ServicoDao mServicoDao;
    private ServicoPrecoDao mServPrecoDao;
    private OrcamentoDao mOrcamentoDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mVeiculoDao = mDb.veiculoDao();
        mCategoriaDao = mDb.categoriaDao();
        mServicoDao = mDb.servicoDao();
        mServPrecoDao = mDb.servicoPrecoDao();
        mOrcamentoDao = mDb.orcamentoDao();
    }


/*

    @Test
    public void writeVeiculoAndReadInList() throws Exception {

        mVeiculoDao.deleteAll();

        List<Veiculo> veiculos = mVeiculoDao.getAll();
        assertThat(veiculos.size(), equalTo(0));

        Veiculo veiculo = new Veiculo(1, "Name 1", "Address 1", "111111");
        veiculos.add(veiculo);
        mVeiculoDao.insert(veiculo);
        veiculo = new Veiculo(2, "Name 2", "Address 2", "22222");
        veiculos.add(veiculo);
        mVeiculoDao.insert(veiculo);
        veiculo = new Veiculo(3, "Name 3", "Address 3", "33333");
        veiculos.add(veiculo);
        mVeiculoDao.insert(veiculo);

        mVeiculoDao.deleteAll();
        mVeiculoDao.insert(veiculos);


        veiculos = mVeiculoDao.getAll();
        assertThat(veiculos.size(), equalTo(3));

        veiculo = mVeiculoDao.getById(1);
        assertThat(veiculo.getOrcamentoId(), equalTo(1));

        veiculo = mVeiculoDao.getById(2);
        assertThat(veiculo.getOrcamentoId(), equalTo(2));

        veiculo = mVeiculoDao.getById(3);
        assertThat(veiculo.getOrcamentoId(), equalTo(3));


        veiculo.setModelo("agora");
        mVeiculoDao.update(veiculo);
        veiculo = mVeiculoDao.getById(3);
        assertThat(veiculo.getModelo(), equalTo("agora"));


        mVeiculoDao.delete(veiculo);

        veiculos = mVeiculoDao.getAll();
        assertThat(veiculos.size(), equalTo(2));

        mVeiculoDao.deleteAll();
        veiculos = mVeiculoDao.getAll();
        assertThat(veiculos.size(), equalTo(0));


    }

    @Test
    public void writeCategoriaAndReadInList() throws Exception {

        mCategoriaDao.deleteAll();

        List<Categoria> categorias = mCategoriaDao.getAll();
        assertThat(categorias.size(), equalTo(0));

        Categoria categoria = new Categoria(1, "Categoria 01");
        mCategoriaDao.insert(categoria);
        categoria = new Categoria(2, "Categoria 01");
        mCategoriaDao.insert(categoria);
        categoria = new Categoria(3, "Categoria 01");
        mCategoriaDao.insert(categoria);


        categorias = mCategoriaDao.getAll();
        assertThat(categorias.size(), equalTo(3));
        categoria = mCategoriaDao.getById(1);
        assertThat(categoria.getOrcamentoId(), equalTo(1));
        categoria = mCategoriaDao.getById(2);
        assertThat(categoria.getOrcamentoId(), equalTo(2));
        categoria = mCategoriaDao.getById(3);
        assertThat(categoria.getOrcamentoId(), equalTo(3));


        categoria.setDescricao("agora");
        mCategoriaDao.update(categoria);
        categoria = mCategoriaDao.getById(3);
        assertThat(categoria.getDescricao(), equalTo("agora"));


        mCategoriaDao.delete(categoria);

        categorias = mCategoriaDao.getAll();
        assertThat(categorias.size(), equalTo(2));

        mCategoriaDao.deleteAll();
        categorias = mCategoriaDao.getAll();
        assertThat(categorias.size(), equalTo(0));


    }

    @Test
    public void writeServicoAndReadInList() throws Exception {

        mServicoDao.deleteAll();

        List<Servico> servicos = mServicoDao.getAll();
        assertThat(servicos.size(), equalTo(0));

        Categoria categoria = new Categoria(1, "Categoria 01");
        mCategoriaDao.insert(categoria);
        categoria = new Categoria(2, "Categoria 01");
        mCategoriaDao.insert(categoria);



        Servico servico = new Servico(1, "Name 1", "preco 1", 1);
        mServicoDao.insert(servico);
        servico = new Servico(2, "Name 2", "preco 2", 1);
        mServicoDao.insert(servico);
        servico = new Servico(3, "Name 3", "preco 3", 1);
        mServicoDao.insert(servico);


        servicos = mServicoDao.getAll();
        assertThat(servicos.size(), equalTo(3));

        servico = new Servico(4, "Name 3", "preco 3", 2);
        mServicoDao.insert(servico);

        servicos = mServicoDao.getAll();
        assertThat(servicos.size(), equalTo(4));

        servicos = mServicoDao.getByCategoria(2);
        assertThat(servicos.size(), equalTo(1));


        servico = mServicoDao.getById(1);
        assertThat(servico.getOrcamentoId(), equalTo(1));
        servico = mServicoDao.getById(2);
        assertThat(servico.getOrcamentoId(), equalTo(2));
        servico = mServicoDao.getById(3);
        assertThat(servico.getOrcamentoId(), equalTo(3));
        servico = mServicoDao.getById(4);
        assertThat(servico.getId_categoria(), equalTo(2));


        servico.setDescricao("agora");
        mServicoDao.update(servico);
        servico = mServicoDao.getById(4);
        assertThat(servico.getDescricao(), equalTo("agora"));


        mServicoDao.delete(servico);

        servicos = mServicoDao.getAll();
        assertThat(servicos.size(), equalTo(3));

        mServicoDao.deleteAll();
        servicos = mServicoDao.getAll();
        assertThat(servicos.size(), equalTo(0));


    }


    @Test
    public void writeServicoPrecoAndReadInList() throws Exception {

        mServPrecoDao.deleteAll();

        List<ServicoPreco> servs = mServPrecoDao.getAll();
        assertThat(servs.size(), equalTo(0));

        Categoria categoria = new Categoria(1, "Categoria 01");
        mCategoriaDao.insert(categoria);

        Servico servico = new Servico(1, "Name 1", "preco 1", 1);
        mServicoDao.insert(servico);
        servico = new Servico(2, "Name 2", "preco 2", 1);
        mServicoDao.insert(servico);

        ServicoPreco serv = new ServicoPreco(1, "nome 01", 10.5, 1);
        mServPrecoDao.insert(serv);
        serv = new ServicoPreco(2, "nome 01", 20.5, 1);
        mServPrecoDao.insert(serv);
        serv = new ServicoPreco(3, "nome 01", 30.5, 1);
        mServPrecoDao.insert(serv);


        servs = mServPrecoDao.getAll();
        assertThat(servs.size(), equalTo(3));

        serv = new ServicoPreco(4, "nome 01", 30.5, 2);
        mServPrecoDao.insert(serv);

        servs = mServPrecoDao.getAll();
        assertThat(servs.size(), equalTo(4));

        servs = mServPrecoDao.getByIdServico(2);
        assertThat(servs.size(), equalTo(1));

        serv = mServPrecoDao.getById(1);
        assertThat(serv.getOrcamentoId(), equalTo(1));
        serv = mServPrecoDao.getById(2);
        assertThat(serv.getOrcamentoId(), equalTo(2));
        serv = mServPrecoDao.getById(3);
        assertThat(serv.getOrcamentoId(), equalTo(3));
        serv = mServPrecoDao.getById(4);
        assertThat(serv.getId_servico(), equalTo(2));


        serv.setDescricao("agora");
        mServPrecoDao.update(serv);
        serv = mServPrecoDao.getById(4);
        assertThat(serv.getDescricao(), equalTo("agora"));


        mServPrecoDao.delete(serv);

        servs = mServPrecoDao.getAll();
        assertThat(servs.size(), equalTo(3));

        mServPrecoDao.deleteAll();
        servs = mServPrecoDao.getAll();
        assertThat(servs.size(), equalTo(0));
    }
*/


    @Test
    public void writeOrcamentoAndReadInList() throws Exception {

        mOrcamentoDao.deleteAll();

        List<Orcamento> orcamentos = mOrcamentoDao.getAll();
        assertThat(orcamentos.size(), equalTo(0));


        Veiculo veiculo = new Veiculo(1, "Name 1", "Address 1", "111111");
        mVeiculoDao.insert(veiculo);


        Orcamento orcamento = new Orcamento();

        Date data = Calendar.getInstance().getTime();

        orcamento = new Orcamento(data, 1, 258.458);
        orcamentos.add(orcamento);
        orcamento = new Orcamento(data, 1, 258.458);
        orcamentos.add(orcamento);
        orcamento = new Orcamento(data, 1, 258.458);
        orcamentos.add(orcamento);

        mOrcamentoDao.insert(orcamentos);


        mOrcamentoDao.delete(2);

        orcamento = new Orcamento(data, 1, 258.458);
        orcamentos.add(orcamento);
        mOrcamentoDao.insert(orcamento);

        orcamentos = mOrcamentoDao.getAll();
        assertThat(orcamentos.size(), equalTo(3));

        int i = mOrcamentoDao.getUltimo();
        assertThat(i, equalTo(4));


    }






    @After
    public void closeDb() throws IOException {
        mDb.close();
    }



}
