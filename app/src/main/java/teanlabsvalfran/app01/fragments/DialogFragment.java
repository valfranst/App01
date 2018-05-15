package teanlabsvalfran.app01.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import teanlabsvalfran.app01.models.OrcamentoServico;
import teanlabsvalfran.app01.models.Servico;
import teanlabsvalfran.app01.models.ServicoDao;
import teanlabsvalfran.app01.models.ServicoPreco;
import teanlabsvalfran.app01.models.ServicoPrecoDao;
import teanlabsvalfran.app01.viewholder.RecyclerTouchListener;
import teanlabsvalfran.app01.viewholder.ServicoPrecoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragment extends Fragment {

    View rootView;
    private FragmentActivity myContext;

    private Button btCancelar;

    private List<ServicoPreco> servs = new ArrayList<>();
    private ServicoPrecoAdapter servAdapter;


    private AppDatabase mDb;
    private ServicoPrecoDao mServPrecoDao;
    private ServicoDao mServicoDao;

    private int idVeiculo;
    private int idServico;
    private int idCategoria;
    private int catPosition;
    private int servPosition;


    private OrcamentoServico oServico;
    private List<OrcamentoServico> oServicos;
    private SparseBooleanArray item;

    private DateFormat df;


    public DialogFragment() {
        // Required empty public constructor
    }
    public void onAttach(Activity Telas) {
        myContext = (FragmentActivity) Telas;
        super.onAttach(Telas);
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_dialog, container, false);

        Bundle bundle = getArguments();
        idVeiculo = bundle.getInt("idVeiculo");
        idServico = bundle.getInt("idServico");
        idCategoria = bundle.getInt("idCategoria");
        catPosition = bundle.getInt("catPosition");
        servPosition = bundle.getInt("servPosition");
        item = TelasActivity.getSelects(catPosition);

        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");



        mDb = AppDatabase.getDatabase(myContext.getApplication());
        mServPrecoDao = mDb.servicoPrecoDao();
        mServicoDao = mDb.servicoDao();


        btCancelar = (Button) rootView.findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ((TelasActivity)getActivity()).getTela02(idVeiculo, idServico, idCategoria);

            }
        });

        creatListServico();


        return  rootView;
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
    public Servico getServicoId(int idServico){
        List<Servico> ssA2 = TelasActivity.getServicos();
        for(int i = 0; i < ssA2.size(); i++){
            Servico sA1 = ssA2.get(i);
            if(sA1.getId() == idServico){
                return sA1;
            }
        }
        Toast.makeText(myContext.getApplicationContext(), "retur null", Toast.LENGTH_SHORT).show();
        return null;

    }
    public void creatListServico(){

        RecyclerView recyclerViewServicoPreco = (RecyclerView) rootView.findViewById(R.id.rvServicoPrecos);

        servs = mServPrecoDao.getByIdServico(idServico);

        servAdapter = new ServicoPrecoAdapter(servs);
        RecyclerView.LayoutManager ServicoPrecoLayoutManager = new LinearLayoutManager(myContext.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewServicoPreco.setLayoutManager(ServicoPrecoLayoutManager);
        recyclerViewServicoPreco.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerViewServicoPreco.setItemAnimator(new DefaultItemAnimator());
        recyclerViewServicoPreco.addItemDecoration(new DividerItemDecoration(myContext, LinearLayoutManager.HORIZONTAL));
        recyclerViewServicoPreco.setAdapter(servAdapter);
        recyclerViewServicoPreco.addOnItemTouchListener(new RecyclerTouchListener(myContext.getApplicationContext(), recyclerViewServicoPreco, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final ServicoPreco serv = servs.get(position);

                Date data = Calendar.getInstance().getTime();
                Date data2 = new Date(System.currentTimeMillis());
                String idOrcamentoServico = TelasActivity.idNow +" "+ df.format(data2);

                oServico = new OrcamentoServico();
                oServico.setOrcamentoservicoId(idOrcamentoServico);
                oServico.setServicoId(idServico);
                oServico.setServicoprecoId(serv.getId());
                oServico.setValor(serv.getValor());

                oServicos = TelasActivity.getoServicos();
                if(oServicos == null){
                    oServicos = new ArrayList<>();
                    oServicos.add(oServico);
                    TelasActivity.setoServicos(oServicos);
                    Toast.makeText(myContext.getApplicationContext(), "Serviço ADICIONADO!", Toast.LENGTH_SHORT).show();
                }else {
                    oServicos.add(oServico);
                    TelasActivity.setoServicos(oServicos);
                    Toast.makeText(myContext.getApplicationContext(), "Serviço ADICIONADO!", Toast.LENGTH_SHORT).show();

                }
                //*******************atualizar seleção para true
                item.put(servPosition, true);
                TelasActivity.setSelects(item, catPosition);


                //*******************atualizar o preço para o selecionado

                Servico s1 = mServicoDao.getById(idServico);
                s1.setPreco(String.valueOf(serv.getValor()));
                mServicoDao.update(s1);


                ((TelasActivity)getActivity()).getTela02(idVeiculo, idServico, idCategoria, catPosition);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }









}
