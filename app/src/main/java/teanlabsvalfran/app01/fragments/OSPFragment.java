package teanlabsvalfran.app01.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import teanlabsvalfran.app01.AppDatabase;
import teanlabsvalfran.app01.Impressao.MyPrintDocumentAdapter;
import teanlabsvalfran.app01.R;
import teanlabsvalfran.app01.TelasActivity;
import teanlabsvalfran.app01.models.Categoria;
import teanlabsvalfran.app01.models.CategoriaDao;
import teanlabsvalfran.app01.models.OSPersonalizado;
import teanlabsvalfran.app01.models.Orcamento;
import teanlabsvalfran.app01.models.OrcamentoDao;
import teanlabsvalfran.app01.models.OrcamentoServico;
import teanlabsvalfran.app01.models.OrcamentoServicoDao;
import teanlabsvalfran.app01.models.Servico;
import teanlabsvalfran.app01.models.ServicoDao;
import teanlabsvalfran.app01.models.ServicoPrecoDao;
import teanlabsvalfran.app01.models.VeiculoDao;
import teanlabsvalfran.app01.viewholder.OSPAdapter;
import teanlabsvalfran.app01.viewholder.RecyclerTouchListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class OSPFragment extends Fragment {

    private View rootView;
    private FragmentActivity myContext;

    private String idOrcamento;

    private AppDatabase mDb;
    private VeiculoDao mVeiculoDao;
    private OrcamentoDao mOrcamento;
    private OrcamentoServicoDao mOServico;
    private ServicoDao mServico;
    private ServicoPrecoDao mSPreco;
    private CategoriaDao mCategoria;

    OSPersonalizado osp;

    private List<OSPersonalizado> osps;
    private OSPAdapter ospAdapter;
    private List<OrcamentoServico> oServicos;
    private OrcamentoServico oServico;

    TextView placa, data, valor;
    Button btImprimir, btVoltar, btEditar;

    private DateFormat df;

    private String placaV;
    Orcamento orcamento;


    public OSPFragment() {
        // Required empty public constructor
    }
    public void onAttach(Activity Telas) {
        myContext = (FragmentActivity) Telas;
        super.onAttach(Telas);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        idOrcamento = bundle.getString("idOrcamento");

        rootView = inflater.inflate(R.layout.fragment_osp, container, false);

        mDb = AppDatabase.getDatabase(myContext.getApplication());
        mVeiculoDao = mDb.veiculoDao();
        mOServico = mDb.orcamentoServicoDao();
        mServico = mDb.servicoDao();
        mSPreco = mDb.servicoPrecoDao();
        mOrcamento = mDb.orcamentoDao();
        mCategoria = mDb.categoriaDao();

        placa = (TextView) rootView.findViewById(R.id.idPlacaOS);
        data = (TextView) rootView.findViewById(R.id.idDataOS);
        valor = (TextView) rootView.findViewById(R.id.idValorOS);
        btImprimir = (Button) rootView.findViewById(R.id.btImprimirOS);
        btVoltar = (Button) rootView.findViewById(R.id.btVoltarOS);
        btEditar = (Button) rootView.findViewById(R.id.btEditarOS);

        df = new SimpleDateFormat("dd/MM/yyyy kk:mm");


        orcamento = mOrcamento.getById(idOrcamento);
        final int idVeiculo = orcamento.getVeiculoId();
        data.setText(df.format(orcamento.getDataAbertura()));
        placaV = mVeiculoDao.getPlacaById(idVeiculo);
        placa.setText(placaV);
        valor.setText(orcamento.getValorTotal()+"");

        btImprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(myContext.getApplicationContext(), "PREPARANDO IMPRESSÃO...", Toast.LENGTH_SHORT).show();
                printDocument();
            }
        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TelasActivity)getActivity()).getOrcamentoFragment();
            }
        });

        btEditar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                ((TelasActivity)getActivity()).clearDados();
                creatSelects();
                int[] result = povoarDados();



                ((TelasActivity)getActivity()).getTela02(idVeiculo, result[0], result[1], result[2]);
            }
        });

        //getTela02(int idVeiculo, int idServico, int idCategoria, int catPosition) + atulizar os dados de selecao



        iniciar();
        criarList();

        return rootView;
    }//fim onCreateView

    public void criarList() {
        RecyclerView recyclerOSP = (RecyclerView) rootView.findViewById(R.id.rvOrcamentoServico);
        ospAdapter = new OSPAdapter(osps);
        RecyclerView.LayoutManager opLayoutManager = new LinearLayoutManager(myContext.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerOSP.setLayoutManager(opLayoutManager);
        recyclerOSP.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerOSP.setItemAnimator(new DefaultItemAnimator());
        recyclerOSP.addItemDecoration(new DividerItemDecoration(myContext, LinearLayoutManager.VERTICAL));
        recyclerOSP.setAdapter(ospAdapter);
        recyclerOSP.addOnItemTouchListener(new RecyclerTouchListener(myContext.getApplicationContext(), recyclerOSP, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public void iniciar(){//lista completa
        osps = new ArrayList();
        oServicos = new ArrayList<>();
        oServicos = mOServico.getByIdOrcamento(idOrcamento);
        int idServico;
        int idServicoPreco;
        for(int i = 0; i < oServicos.size(); i++){
            osp = new OSPersonalizado();
            oServico = oServicos.get(i);

            idServico = oServico.getServicoId();
            idServicoPreco = oServico.getServicoprecoId();

            osp.setNomeServico(mServico.getNomeById(idServico));
            osp.setDescricao(mSPreco.getNomeById(idServicoPreco));
            osp.setValor(oServico.getValor());
            osps.add(osp);
        }

    }

    public void printDocument(){
       /* PrintManager printManager = (PrintManager) myContext.getSystemService(Context.PRINT_SERVICE);
        String jobName = myContext.getString(R.string.app_name)+" Document";
        printManager.print(jobName, new MyPrintDocumentAdapter((View.OnClickListener) this),null);*/

        // Get a PrintManager instance
        PrintManager printManager = (PrintManager) myContext.getSystemService(Context.PRINT_SERVICE);

        // Set job name, which will be displayed in the print queue
        String jobName = myContext.getString(R.string.app_name) + " Document";

        // Start a print job, passing in a PrintDocumentAdapter implementation
        // to handle the generation of a print document
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            printManager.print(jobName, new MyPrintDocumentAdapter(myContext, osps, orcamento, placaV),null); //
        }else{
            Toast.makeText(myContext.getApplicationContext(), "A impressão do ORÇAMENTO só é possível a partir do ANDROID VERSÂO 19 'KITKAT'", Toast.LENGTH_SHORT).show();
        }

    }


    private void creatSelects() {

        List<Categoria> categorias = mCategoria.getAll();
        List<Servico> servicos;

        List<SparseBooleanArray> selects = new ArrayList<>();
            for(int i = 0; i < categorias.size(); i++){
                Categoria categoria = categorias.get(i);
                servicos = mServico.getByCategoria(categoria.getId());
                SparseBooleanArray select = new SparseBooleanArray();
                for(int b = 0; b < servicos.size(); b++){
                    select.append(b, false);
                }
                selects.add(i, select);
            }
        TelasActivity.setSparseBooleanArray(selects);


        // Toast.makeText(myContext.getApplicationContext(), selects.size() + " pai!" + select.size() + " filho!", Toast.LENGTH_SHORT).show();
    }

    private int[] povoarDados(){

        orcamento = mOrcamento.getById(idOrcamento);
        TelasActivity.setOrcamento(orcamento);
        TelasActivity.setoServicos(oServicos);
        int catPosition = 0, catPosition2 = 0, servPosition = 0, idCategoria = 0, idServico = 0;


        //*******************atualizar seleção para true

        List<Categoria> categorias = mCategoria.getAll();
        for(int a = 0; a < categorias.size(); a++) {
            Categoria categoria = categorias.get(a);
            catPosition = a;
            idCategoria = categoria.getId();
            List<Servico> servicos = mServico.getByCategoria(idCategoria);
            SparseBooleanArray item = new SparseBooleanArray();

            for(int b = 0; b < servicos.size(); b++){
                Servico servico = servicos.get(b);
                servPosition = b;

                for(int c = 0; c < oServicos.size(); c++){
                    OrcamentoServico os = oServicos.get(c);
                    idServico = os.getServicoId();

                    if(idServico == servico.getId()){
                        item.put(servPosition, true);
                        servico.setPreco(String.valueOf(os.getValor()));
                        mServico.update(servico);
                        catPosition2 = a;
                    }

                }

            }
            TelasActivity.setSelects(item, catPosition);


        }

        int[] result = {idServico, idCategoria, catPosition2};
        return result;

    }







}//fim class OSPFragment
