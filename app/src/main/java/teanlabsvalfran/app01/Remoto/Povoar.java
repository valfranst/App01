package teanlabsvalfran.app01.Remoto;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import teanlabsvalfran.app01.AppDatabase;
import teanlabsvalfran.app01.models.Categoria;
import teanlabsvalfran.app01.models.CategoriaDao;
import teanlabsvalfran.app01.models.Servico;
import teanlabsvalfran.app01.models.ServicoDao;
import teanlabsvalfran.app01.models.ServicoPreco;
import teanlabsvalfran.app01.models.ServicoPrecoDao;
import teanlabsvalfran.app01.models.Veiculo;
import teanlabsvalfran.app01.models.VeiculoDao;

/**
 * Created by Valfran on 31/01/2018.
 */

public class Povoar {

    private static Povoar singleton;

    private AppDatabase mDb;
    private VeiculoDao mVeiculoDao;
    private CategoriaDao mCategoriaDao;
    private ServicoDao mServicoDao;
    private ServicoPrecoDao mServPrecoDao;

    HttpGetRequest okHttpHandler= new HttpGetRequest();

    List<Veiculo> veiculos = new ArrayList<>();
    Veiculo veiculo;

    List<Categoria> categorias = new ArrayList<>();
    Categoria categoria;

    List<Servico> servicos = new ArrayList<>();
    Servico servico;

    List<ServicoPreco> servs = new ArrayList<>();
    ServicoPreco serv;



    private Povoar(Context context){

        mDb = AppDatabase.getDatabase(context);
        mVeiculoDao = mDb.veiculoDao();
        mCategoriaDao = mDb.categoriaDao();
        mServicoDao = mDb.servicoDao();
        mServPrecoDao = mDb.servicoPrecoDao();


    }

    public static Povoar getInstance(Context context){
        if(singleton == null){
            singleton = new Povoar(context);
        }
        return singleton;
    }







    public void prepareoData() {




        Veiculo objetoVeiculo = new Veiculo();

        veiculo = new Veiculo(30, "FRG-589", "Carlos Carvalho", "Vectra");
        veiculos.add(veiculo);
        veiculo = new Veiculo(31, "KIS-698", "Marcos Rezende", "Onu Mille");
        veiculos.add(veiculo);
        veiculo = new Veiculo(32, "HSP-976", "Paula Vieira", "Fusion");
        veiculos.add(veiculo);
        veiculo = new Veiculo(41, "DGT-961", "Adriana Carvalho", "Astra GL");
        veiculos.add(veiculo);
        veiculo = new Veiculo(51, "TYU-369", "Ronaldo Lessa", "New Civic");
        veiculos.add(veiculo);
        veiculo = new Veiculo(61, "QRV-157", "Pietro D'avila", "Doblô");
        veiculos.add(veiculo);
        veiculo = new Veiculo(71, "TIL-624", "Ronald Viana", "Camaro");
        veiculos.add(veiculo);
        veiculo = new Veiculo(81, "OOK-985", "Gabriele Maranhão", "Mustang GTO");
        veiculos.add(veiculo);

        //mVeiculoDao.deleteAll();
        mVeiculoDao.insert(veiculos);
       // mVeiculoDao.insert(veiculos2);





        categoria = new Categoria(1, "Cat 01");
        categorias.add(categoria);
        categoria = new Categoria(2, "Cat 02");
        categorias.add(categoria);
        categoria = new Categoria(3, "Cat 03");
        categorias.add(categoria);
        categoria = new Categoria(4, "Cat 04");
        categorias.add(categoria);
        categoria = new Categoria(5, "Cat 05");
        categorias.add(categoria);
        categoria = new Categoria(6, "Cat 06");
        categorias.add(categoria);
        categoria = new Categoria(7, "Cat 07");
        categorias.add(categoria);
        categoria = new Categoria(8, "Cat 08");
        categorias.add(categoria);
        categoria = new Categoria(9, "Cat 09");
        categorias.add(categoria);
        categoria = new Categoria(10, "Cat 10");
        categorias.add(categoria);
        categoria = new Categoria(11, "Cat 11");
        categorias.add(categoria);
        categoria = new Categoria(12, "Cat 12");
        categorias.add(categoria);
        categoria = new Categoria(13, "Cat 13");
        categorias.add(categoria);

        mCategoriaDao.insert(categorias);



        servicos.clear();
        servico = new Servico(1, "Servico 0101", "", 1);
        servicos.add(servico);
        servico = new Servico(2, "Servico 0102","", 1);
        servicos.add(servico);
        servico = new Servico(3, "Servico 0103", "", 1);
        servicos.add(servico);
        servico = new Servico(4, "Servico 0104", "", 1);
        servicos.add(servico);
        servico = new Servico(5, "Servico 0105",  "", 1);
        servicos.add(servico);
        servico = new Servico(6, "Servico 0106",  "", 1);
        servicos.add(servico);
        servico = new Servico(7, "Servico 0107",  "", 1);
        servicos.add(servico);
        servico = new Servico(8, "Servico 0108",  "", 1);
        servicos.add(servico);
        servico = new Servico(9, "Servico 0109",  "", 1);
        servicos.add(servico);
        servico = new Servico(10, "Servico 0110",  "", 1);
        servicos.add(servico);
        servico = new Servico(11, "Servico 0111",  "", 1);
        servicos.add(servico);

        servico = new Servico(12,"Servico 0212",  "", 2);
        servicos.add(servico);
        servico = new Servico(13, "Servico 0213",  "", 2);
        servicos.add(servico);
        servico = new Servico(22, "Servico 0214",  "", 2);
        servicos.add(servico);

        servico = new Servico(14, "Servico 0315",  "0003", 3);
        servicos.add(servico);
        servico = new Servico(15, "Servico 0316",  "", 3);
        servicos.add(servico);
        servico = new Servico(16, "Servico 0317",  "", 3);
        servicos.add(servico);

        servico = new Servico(17,"Servico 0417",  "", 4);
        servicos.add(servico);
        servico = new Servico(18, "Servico 0418",  "", 4);
        servicos.add(servico);
        servico = new Servico(19, "Servico 0419",  "", 4);
        servicos.add(servico);
        servico = new Servico(20, "Servico 0420",  "", 4);
        servicos.add(servico);
        servico = new Servico(21, "Servico 0421",  "", 4);
        servicos.add(servico);

        mServicoDao.insert(servicos);





        serv = new ServicoPreco(1, "Descricao 1", 100.00, 1);
        servs.add(serv);
        serv = new ServicoPreco(2, "Descricao 2", 150.00, 1);
        servs.add(serv);
        serv = new ServicoPreco(3, "Descricao 3", 200.00, 1);
        servs.add(serv);

        serv = new ServicoPreco(4, "Descricao 1", 100.00, 2);
        servs.add(serv);
        serv = new ServicoPreco(5, "Descricao 2", 150.00, 2);
        servs.add(serv);

        serv = new ServicoPreco(6, "Descricao 1", 100.00, 3);
        servs.add(serv);

        serv = new ServicoPreco(7, "Descricao 1", 180, 4);
        servs.add(serv);

        serv = new ServicoPreco(8, "Descricao 1", 300, 5);
        servs.add(serv);

        serv = new ServicoPreco(9, "Descricao 1", 120.00, 6);
        servs.add(serv);
        serv = new ServicoPreco(10, "Descricao 2", 190.00, 6);
        servs.add(serv);

        serv = new ServicoPreco(11, "Descricao 1", 50.00, 7);
        servs.add(serv);
        serv = new ServicoPreco(12, "Descricao 2", 80.00, 7);
        servs.add(serv);

        serv = new ServicoPreco(13, "Descricao 1", 550.00, 8);
        servs.add(serv);
        serv = new ServicoPreco(14, "Descricao 2", 700.00, 8);
        servs.add(serv);

        serv = new ServicoPreco(15, "Descricao 1", 350.00, 9);
        servs.add(serv);

        serv = new ServicoPreco(16, "Descricao 1", 300.00, 10);
        servs.add(serv);
        serv = new ServicoPreco(17, "Descricao 2", 40.00, 10);
        servs.add(serv);

        serv = new ServicoPreco(18, "Descricao 1", 160.00, 11);
        servs.add(serv);

        mServPrecoDao.insert(servs);





        /*for(int i = 0; i < servicos.size(); i++) {
            Servico servico3 = servicos.get(i);
            List<ServicoPreco> servs3 = mServPrecoDao.getByIdServico(servico3.getId());
            if(servs3.size() == 1){
                ServicoPreco serv3 = servs3.get(0);
                servico3.setPreco(String.valueOf(serv3.getValor()));
                mServicoDao.update(servico3);
            }

        }

        mServicoDao.update(servicos);*/



        //servicoAdapter.notifyDataSetChanged();




    }





}
