package teanlabsvalfran.app01.Remoto;

import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.ExecutionException;

import teanlabsvalfran.app01.AppDatabase;
import teanlabsvalfran.app01.MyApplication;
import teanlabsvalfran.app01.models.Orcamento;
import teanlabsvalfran.app01.models.OrcamentoDao;
import teanlabsvalfran.app01.models.OrcamentoServico;
import teanlabsvalfran.app01.models.OrcamentoServicoDao;

/**
 * Created by Valfran on 26/02/2018.
 */

public class Enviar {

    private HttpPostResponse okHttpHandler;
    private final String BASE_URL_ORCAMENTO = "http://caroffice20180214073646.azurewebsites.net/Orcamento/Post";
    private final String BASE_URL_ORCAMENTOSERVICO = "http://caroffice20180214073646.azurewebsites.net/Orcamentoservico/Post";


    private AppDatabase mDb;
    private OrcamentoDao mOrcamento;
    private OrcamentoServicoDao mOServicoDao;

    public Enviar(){
        mDb = AppDatabase.getDatabase(MyApplication.getContext());
        mOrcamento = mDb.orcamentoDao();
        mOServicoDao = mDb.orcamentoServicoDao();
    }


    public String postOrcamento() throws ExecutionException, InterruptedException {
        okHttpHandler = new HttpPostResponse();
        List<Orcamento> orcamentos = mOrcamento.getByStatus(0);
        String json = "";
        String result = "";
        if(orcamentos.size() > 0) {
            json = new Gson().toJson(orcamentos);
            result = okHttpHandler.execute(BASE_URL_ORCAMENTO, json).get();

        }

        return result;


    }


    public String postOrcamentoServico() throws ExecutionException, InterruptedException {

        okHttpHandler = new HttpPostResponse();
        List<OrcamentoServico> orcamentos = mOServicoDao.getByStatus(0);
        String json = "";
        String result = "";
        if(orcamentos.size() > 0) {
            json = new Gson().toJson(orcamentos);
            result = okHttpHandler.execute(BASE_URL_ORCAMENTOSERVICO, json).get();
        }
        return result;

    }





}
