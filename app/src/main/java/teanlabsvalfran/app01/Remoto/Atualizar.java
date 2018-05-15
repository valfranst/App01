package teanlabsvalfran.app01.Remoto;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import teanlabsvalfran.app01.AppDatabase;
import teanlabsvalfran.app01.MyApplication;
import teanlabsvalfran.app01.models.Categoria;
import teanlabsvalfran.app01.models.CategoriaDao;
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
 * Created by Valfran on 17/02/2018.
 */

public class Atualizar {

    private final String BASE_URL_VEICULO = "http://caroffice20180214073646.azurewebsites.net/Veiculo/Get";
    private final String BASE_URL_CATEGORIA = "http://caroffice20180214073646.azurewebsites.net/Categoria/Get";
    private final String BASE_URL_SERVICO = "http://caroffice20180214073646.azurewebsites.net/Servico/Get";
    private final String BASE_URL_SERVICO_PRECO = "http://caroffice20180214073646.azurewebsites.net/Servicopreco/Get";
    private final String BASE_URL_ORCAMENTO = "http://caroffice20180214073646.azurewebsites.net/Orcamento/Get";
    private final String BASE_URL_ORCAMENTO_SERVICO = "http://caroffice20180214073646.azurewebsites.net/Orcamentoservico/Get";


    private  HttpGetRequest okHttpHandler= new HttpGetRequest();

    private  AppDatabase mDb;
    private  VeiculoDao mVeiculoDao;
    private CategoriaDao mCategoriaDao;
    private ServicoDao mServicoDao;
    private ServicoPrecoDao mServPrecoDao;
    private OrcamentoDao mOrcamento;
    private OrcamentoServicoDao mOServicoDao;

    private  List<Veiculo> veiculos = new ArrayList<Veiculo>();
    private  List<Categoria> categorias = new ArrayList<Categoria>();
    private  List<Servico> servicos = new ArrayList<Servico>();
    private  List<ServicoPreco> servicoPrecos = new ArrayList<ServicoPreco>();
    private  List<Orcamento> orcamentos = new ArrayList<Orcamento>();
    private  List<OrcamentoServico> orcamentoServicos = new ArrayList<OrcamentoServico>();


    private  Gson gson = new Gson();




    public Atualizar() {
        mDb = AppDatabase.getDatabase(MyApplication.getContext());
        mVeiculoDao = mDb.veiculoDao();
        mCategoriaDao = mDb.categoriaDao();
        mServicoDao = mDb.servicoDao();
        mServPrecoDao = mDb.servicoPrecoDao();
        mOrcamento = mDb.orcamentoDao();
        mOServicoDao = mDb.orcamentoServicoDao();
    }


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public int getVeiculo() throws ExecutionException, InterruptedException, JSONException {

        String result = okHttpHandler.execute(BASE_URL_VEICULO).get();
        if(result != null) {
            /*String resultB = result.substring(11);

            String[] resultA = resultB.split(",\"id\"");
            String resultC = "["+resultA[0];*/


            JSONArray t2 = new JSONArray(result);

            for (int i = 0; i < t2.length(); ++i) {
                Veiculo veiculo = new Veiculo();
                JSONObject t = t2.getJSONObject(i);
                veiculo.setPlaca(t.getString("placa"));
                veiculo.setModelo(t.getString("modelo"));
                veiculo.setCliente(t.getString("nomeCliente"));
                veiculo.setId(t.getInt("veiculoId"));
                veiculos.add(veiculo);
            }

            mOServicoDao.deleteAll();
            mOrcamento.deleteAll();
            mServPrecoDao.deleteAll();
            mServicoDao.deleteAll();
            mVeiculoDao.deleteAll();
            mCategoriaDao.deleteAll();

            mVeiculoDao.insert(veiculos);
            return 1;

        }
        return 0;

    }

    public int getCategoria() throws ExecutionException, InterruptedException, JSONException {

        String result = okHttpHandler.execute(BASE_URL_CATEGORIA).get();

        if(result != null) {

            /*String resultB = result.substring(11);

            String[] resultA = resultB.split(",\"id\"");
            String resultC = "[" + resultA[0];*/


            JSONArray t2 = new JSONArray(result);

            for (int i = 0; i < t2.length(); ++i) {
                Categoria categoria = new Categoria();
                JSONObject t = t2.getJSONObject(i);
                categoria.setId(t.getInt("categoriaId"));
                categoria.setDescricao(t.getString("descricao"));
                categorias.add(categoria);
            }
            mCategoriaDao.insert(categorias);
            return 1;

        }
        return 0;

    }

    public int getServico() throws ExecutionException, InterruptedException, JSONException {

        String result = okHttpHandler.execute(BASE_URL_SERVICO).get();
        if(result != null) {
            /*String resultB = result.substring(11);

            String[] resultA = resultB.split(",\"id\"");
            String resultC = "["+resultA[0];*/


            JSONArray t2 = new JSONArray(result);

            for (int i = 0; i < t2.length(); ++i) {
                Servico servico = new Servico();
                JSONObject t = t2.getJSONObject(i);
                servico.setId(t.getInt("servicoId"));
                servico.setDescricao(t.getString("descricao"));
                servico.setPreco("");
                servico.setId_categoria(t.getInt("categoriaId"));
                servicos.add(servico);
            }

            mServicoDao.insert(servicos);
            return 1;

        }
        return 0;


    }

    public int getServicoPreco() throws ExecutionException, InterruptedException, JSONException {

        String result = okHttpHandler.execute(BASE_URL_SERVICO_PRECO).get();
        if(result != null) {
            /*String resultB = result.substring(11);

            String[] resultA = resultB.split(",\"id\"");
            String resultC = "["+resultA[0];*/


            JSONArray t2 = new JSONArray(result);

            for (int i = 0; i < t2.length(); ++i) {
                ServicoPreco servicoPreco = new ServicoPreco();
                JSONObject t = t2.getJSONObject(i);
                servicoPreco.setId(t.getInt("servicoprecoId"));
                servicoPreco.setDescricao(t.getString("descricao"));
                servicoPreco.setValor(t.getDouble("valor"));
                servicoPreco.setId_servico(t.getInt("servicoId"));
                servicoPrecos.add(servicoPreco);
            }

            mServPrecoDao.insert(servicoPrecos);
            return 1;

        }
        return 0;


    }

    public int getOrcamento() throws ExecutionException, InterruptedException, JSONException {

        String result = okHttpHandler.execute(BASE_URL_ORCAMENTO).get();
        if(result != null) {
            /*String resultB = result.substring(11);

            String[] resultA = resultB.split(",\"id\"");
            String resultC = "["+resultA[0];*/


            JSONArray t2 = new JSONArray(result);

            for (int i = 0; i < t2.length(); ++i) {
                Orcamento orcamento = new Orcamento();
                JSONObject t = t2.getJSONObject(i);
                orcamento.setOrcamentoId(t.getString("orcamentoId"));
                orcamento.setDataAbertura(converteData(t.getString("dataAbertura")));
                orcamento.setStatus(t.getInt("status"));
                orcamento.setValorTotal(t.getDouble("valorTotal"));
                orcamento.setVeiculoId(t.getInt("veiculoId"));
                orcamentos.add(orcamento);
            }

            mOrcamento.insert(orcamentos);
            return 1;

        }
        return 0;


    }

    public int getOrcamentoServico() throws ExecutionException, InterruptedException, JSONException {

        String result = okHttpHandler.execute(BASE_URL_ORCAMENTO_SERVICO).get();
        if(result != null) {
            /*String resultB = result.substring(11);

            String[] resultA = resultB.split(",\"id\"");
            String resultC = "["+resultA[0];*/


            JSONArray t2 = new JSONArray(result);

            for (int i = 0; i < t2.length(); ++i) {
                OrcamentoServico orcamentoServico = new OrcamentoServico();
                JSONObject t = t2.getJSONObject(i);
                orcamentoServico.setOrcamentoservicoId(t.getString("orcamentoservicoId"));
                orcamentoServico.setOrcamentoId(t.getString("orcamentoId"));
                orcamentoServico.setServicoId(t.getInt("servicoId"));
                orcamentoServico.setServicoprecoId(t.getInt("servicoprecoId"));
                orcamentoServico.setValor(t.getDouble("valor"));
                orcamentoServico.setStatus(t.getInt("status"));
                orcamentoServicos.add(orcamentoServico);
            }

            mOServicoDao.insert(orcamentoServicos);
            return 1;

        }
        return 0;


    }

    public Date converteData(String bruta){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

        //String dateString = "03/26/2012 11:49:00 AM";
        //SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        Date data = new Date();
        try {
            data = df.parse(bruta);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;

    }




}
