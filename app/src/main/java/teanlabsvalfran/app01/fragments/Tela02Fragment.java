package teanlabsvalfran.app01.fragments;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import teanlabsvalfran.app01.AppDatabase;
import teanlabsvalfran.app01.R;
import teanlabsvalfran.app01.TelasActivity;
import teanlabsvalfran.app01.models.Categoria;
import teanlabsvalfran.app01.models.CategoriaDao;
import teanlabsvalfran.app01.models.Orcamento;
import teanlabsvalfran.app01.models.OrcamentoDao;
import teanlabsvalfran.app01.models.OrcamentoServico;
import teanlabsvalfran.app01.models.OrcamentoServicoDao;
import teanlabsvalfran.app01.models.Servico;
import teanlabsvalfran.app01.models.ServicoDao;
import teanlabsvalfran.app01.models.ServicoPrecoDao;
import teanlabsvalfran.app01.models.Veiculo;
import teanlabsvalfran.app01.models.VeiculoDao;
import teanlabsvalfran.app01.viewholder.CategoriaAdapter;
import teanlabsvalfran.app01.viewholder.RecyclerTouchListener;
import teanlabsvalfran.app01.viewholder.ServicoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tela02Fragment extends Fragment {

    View rootView;
    private FragmentActivity myContext;
    int idVeiculo, idServico, idCategoria, catPosition, servPosition;

    private int parar = 0;


    private AppDatabase mDb;
    private VeiculoDao mVeiculoDao;
    private CategoriaDao mCategoriaDao;
    private ServicoDao mServicoDao;
    private ServicoPrecoDao mServPrecoDao;
    private OrcamentoDao mOrcamento;
    private OrcamentoServicoDao mOServicoDao;

    private Button btConcluir, btVoltar;
    TextView cliente, veiculo, placa, id;

    private List<Servico> servicos = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList<>();
    private CategoriaAdapter categoriaAdapter;
    private ServicoAdapter servicoAdapter;

    private Orcamento orcamento;
    private OrcamentoServico oServico;
    private List<OrcamentoServico> oServicos;

    private DateFormat df;



    public Tela02Fragment() {
        // Required empty public constructor
    }
    public void onAttach(Activity Telas) {
        myContext = (FragmentActivity) Telas;
        super.onAttach(Telas);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tela02, container, false);

        Bundle bundle = getArguments();
        idVeiculo = bundle.getInt("idVeiculo");
        idServico = bundle.getInt("idServico");
        idCategoria = bundle.getInt("idCategoria");
        catPosition = bundle.getInt("catPosition");


        mDb = AppDatabase.getDatabase(myContext.getApplication());
        mVeiculoDao = mDb.veiculoDao();
        mCategoriaDao = mDb.categoriaDao();
        mServicoDao = mDb.servicoDao();
        mServPrecoDao = mDb.servicoPrecoDao();
        mOrcamento = mDb.orcamentoDao();
        mOServicoDao = mDb.orcamentoServicoDao();



        Veiculo veiculoA = mVeiculoDao.getById(idVeiculo);

        cliente = (TextView) rootView.findViewById(R.id.clienteT2);
        veiculo = (TextView) rootView.findViewById(R.id.veiculoT2);
        placa = (TextView) rootView.findViewById(R.id.placaT2);
        id = (TextView) rootView.findViewById(R.id.idVeiculoT2);
        cliente.setText(veiculoA.getCliente());
        veiculo.setText(veiculoA.getModelo().toString());
        placa.setText(veiculoA.getPlaca());
        id.setText(String.valueOf(veiculoA.getId()));


        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        orcamento = new Orcamento();
        orcamento = TelasActivity.getOrcamento();
        if(orcamento == null) {
            orcamento = new Orcamento();

            Date data = Calendar.getInstance().getTime();
            //Date data2 = new Date("2000/02/09");
            Date data2 = new Date(System.currentTimeMillis());

            String idOrcamento = TelasActivity.idNow +" "+ df.format(data2);
            //Toast.makeText(myContext.getApplicationContext(), idOrcamento, Toast.LENGTH_SHORT).show();
            orcamento.setOrcamentoId(idOrcamento);
            orcamento.setDataAbertura(data2);
            orcamento.setVeiculoId(idVeiculo);
            orcamento.setValorTotal(0.0);
            orcamento.setStatus(0);
            TelasActivity.setOrcamento(orcamento);
        }







        if(idServico > 0){

            Servico servicoR = mServicoDao.getById(idServico);
            idCategoria = servicoR.getId_categoria();
            //Toast.makeText(myContext.getApplicationContext(), "categoria "+idCategoria, Toast.LENGTH_SHORT).show();
        }else{
            idCategoria = mCategoriaDao.getPrimeira();
            //Toast.makeText(myContext.getApplicationContext(), "Categoria primeira "+idCategoria, Toast.LENGTH_SHORT).show();
        }

        creatCategoria();
        creatServico();





        ///////////////////////////////////////////////////////////////////////////////////////////////
        btVoltar = (Button) rootView.findViewById(R.id.btVoltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                AlertDialog.Builder builder2 = new AlertDialog.Builder(myContext);
                builder2.setTitle("CANCELAR ORÇAMENTO?");
                builder2.setMessage("Você gostaria de voltar a tela inicial\n" +
                        "e excluir o orçamento em andamento?");

                // add the buttons
                builder2.setPositiveButton("VOLTAR E EXCLUIR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((TelasActivity)getActivity()).getTela01();
                    }
                });
                builder2.setNegativeButton("CONTINUAR ORÇAMENTO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

                // create and show the alert dialog
                AlertDialog dialog = builder2.create();
                dialog.show();

            }
        });

        btConcluir = (Button) rootView.findViewById(R.id.btConcluir);
        btConcluir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
                builder.setTitle("CONCLUIR ORÇAMENTO?");
                builder.setMessage("VOCÊ SERÁ REDIRECIONADO PARA A TELA DE VISUALIZAÇÃO E IMPRESSÃO.");

                // add the buttons
                builder.setPositiveButton("CONCLUIR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String idOrcamentoE = concluirOrcamento();
                        if(idOrcamentoE == null) {
                            Toast.makeText(myContext.getApplicationContext(), "Erro Interno", Toast.LENGTH_SHORT).show();

                        }else{
                            //Orcamento orcamento = TelasActivity.getOrcamento();
                            ((TelasActivity) getActivity()).getOSPFragment(idOrcamentoE);

                        }

                    }
                });
                builder.setNegativeButton("VOLTAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////





        return rootView;
    }



    private void creatSelects(List<Categoria> categorias) {
        List<SparseBooleanArray> selects = TelasActivity.getSparseBooleanArray();
        if(selects == null){
            selects = new ArrayList<>();
            for(int i = 0; i < categorias.size(); i++){
                Categoria categoria = categorias.get(i);
                servicos = mServicoDao.getByCategoria(categoria.getId());
                SparseBooleanArray select = new SparseBooleanArray();
                for(int b = 0; b < servicos.size(); b++){
                    select.append(b, false);
                }
                selects.add(i, select);
            }
            TelasActivity.setSparseBooleanArray(selects);
        }

        // Toast.makeText(myContext.getApplicationContext(), selects.size() + " pai!" + select.size() + " filho!", Toast.LENGTH_SHORT).show();
    }
    public void creatCategoria(){

        RecyclerView recyclerViewCategoria = (RecyclerView) rootView.findViewById(R.id.rvCategoria);

        categorias = mCategoriaDao.getAll();
        creatSelects(categorias);

        categoriaAdapter = new CategoriaAdapter(categorias, catPosition);
        RecyclerView.LayoutManager categoriaLayoutManager = new LinearLayoutManager(myContext.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoria.setLayoutManager(categoriaLayoutManager);
        recyclerViewCategoria.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerViewCategoria.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCategoria.addItemDecoration(new DividerItemDecoration(myContext, LinearLayoutManager.HORIZONTAL));
        recyclerViewCategoria.setAdapter(categoriaAdapter);
        recyclerViewCategoria.addOnItemTouchListener(new RecyclerTouchListener(myContext.getApplicationContext(), recyclerViewCategoria, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Categoria categoria = categorias.get(position);
                idCategoria = categoria.getId();
                catPosition = position;
                categoriaAdapter.setCatPosition(catPosition);

                creatServico();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }





    public List<Servico> getServicoToCat(int idCategoria){
        List<Servico> ssA2 = new ArrayList<>();
        for(int i = 0; i < servicos.size(); i++){
            Servico sA1 = servicos.get(i);
            if(sA1.getId_categoria() == idCategoria){
                ssA2.add(sA1);
            }
        }
        return ssA2;
    }
    public void setValorServico(Servico servico){
        List<Servico> ssA2 = TelasActivity.getServicos();
        for(int i = 0; i < ssA2.size(); i++){
            Servico sA1 = ssA2.get(i);
            if(sA1.getId() == servico.getId()){
                ssA2.set(i, servico);
                TelasActivity.setServicos(ssA2);
                break;
            }
        }
    }

    public void creatServico(){

        /*servicos = TelasActivity.getServicos();
        if(servicos == null){
            servicos = mServicoDao.getAll();
            TelasActivity.setServicos(servicos);
        }*/
        servicos = mServicoDao.getByCategoria(idCategoria);

        RecyclerView recyclerViewServico = (RecyclerView) rootView.findViewById(R.id.rvServicos);
        //servicoAdapter = new ServicoAdapter(getServicoToCat(idCategoria), catPosition);
        servicoAdapter = new ServicoAdapter(servicos, catPosition);
        recyclerViewServico.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerViewServico.setHasFixedSize(true);
        RecyclerView.LayoutManager servicoLayoutManager = new LinearLayoutManager(myContext.getApplicationContext(), LinearLayoutManager.VERTICAL, true);
        recyclerViewServico.setLayoutManager(servicoLayoutManager);
        recyclerViewServico.setItemAnimator(new DefaultItemAnimator());
        recyclerViewServico.setAdapter(servicoAdapter);
        recyclerViewServico.addOnItemTouchListener(new RecyclerTouchListener(myContext.getApplicationContext(), recyclerViewServico, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                Servico servico = servicos.get(position);
                idServico = servico.getId();
                servPosition = position;
                SparseBooleanArray item = TelasActivity.getSelects(catPosition);

                oServicos = TelasActivity.getoServicos();
                if(oServicos == null){// || oServicos.size() == 0
                    parar = 1;
                    ((TelasActivity) getActivity()).getDialogFragmento(idVeiculo, idServico, idCategoria, catPosition, servPosition);
                }

                if(oServicos != null){

                    for (int i = 0; i < oServicos.size(); i++) {
                        oServico = oServicos.get(i);
                        if (oServico.getServicoId() == idServico) {
                            parar = 2;
                            oServicos.remove(i);
                            TelasActivity.setoServicos(oServicos);
                            //********************************* atualizar como falso para selecao de servico removido.
                            item.put(position, false);
                            TelasActivity.setSelects(item, catPosition);
                            //*********************************

                            servico.setPreco("");
                            mServicoDao.update(servico);



                            //*********************************

                            Toast.makeText(myContext.getApplicationContext(), "Serviço REMOVIDO!", Toast.LENGTH_SHORT).show();
                            ((TelasActivity) getActivity()).getTela02(idVeiculo, idServico, idCategoria, catPosition);
                            break;
                        }
                    }

                }

                if(parar == 0){
                    ((TelasActivity) getActivity()).getDialogFragmento(idVeiculo, idServico, idCategoria, catPosition, servPosition);
                }




            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }




    private String concluirOrcamento() {

        orcamento = new Orcamento();
        orcamento = TelasActivity.getOrcamento();
        oServicos = TelasActivity.getoServicos();
        double valorTotal = 0.0;
        if(oServicos == null){

            AlertDialog.Builder builder3 = new AlertDialog.Builder(myContext);
            builder3.setTitle("IMPOSSÍVEL CADASTRAR!");
            builder3.setMessage("Não há SERVIÇOS cadastrados, por favor selecione um SERVIÇO...");

            // add the buttons
            builder3.setPositiveButton("OK ENTENDI", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {


                }
            });

            // create and show the alert dialog
            AlertDialog dialog = builder3.create();
            dialog.show();


        }else{
            Orcamento orcamento2 = mOrcamento.getById(orcamento.getOrcamentoId());
            if(orcamento2 != null){
                mOServicoDao.deleteByIdOrcamento(orcamento.getOrcamentoId());
                orcamento.setValorTotal(0.0);
                mOrcamento.update(orcamento);
            }else {
                mOrcamento.insert(orcamento);
            }

            String idOrcamento = orcamento.getOrcamentoId();
            List<OrcamentoServico> o2 = new ArrayList<>();

            for(int i = 0; i < oServicos.size(); i++){
                oServico = oServicos.get(i);
                oServico.setOrcamentoId(idOrcamento);
                oServico.setStatus(0);
                valorTotal += oServico.getValor();
                o2.add(oServico);
            }
            mOServicoDao.insert(o2);
            //double valorGeral = mOServicoDao.getValorByIdOrcamentoServico(idOrcamento);
            orcamento = mOrcamento.getById(idOrcamento);
            orcamento.setValorTotal(valorTotal);
            orcamento.setStatus(0);
            mOrcamento.update(orcamento);

            //Toast.makeText(myContext.getApplicationContext(), "ORÇAMENTO ENVIADO PARA IMPRESSORA", Toast.LENGTH_SHORT).show();
            return  idOrcamento;

        }

        return null;
    }











}
