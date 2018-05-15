package teanlabsvalfran.app01.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import teanlabsvalfran.app01.AppDatabase;
import teanlabsvalfran.app01.R;
import teanlabsvalfran.app01.TelasActivity;
import teanlabsvalfran.app01.models.Veiculo;
import teanlabsvalfran.app01.models.VeiculoDao;
import teanlabsvalfran.app01.viewholder.RecyclerTouchListener;
import teanlabsvalfran.app01.viewholder.VeiculoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tela01Fragment extends Fragment {

    View rootView;
    private FragmentActivity myContext;

    private AppDatabase mDb;
    private VeiculoDao mVeiculoDao;

    private List<Veiculo> veiculos2 = new ArrayList<>();
    private RecyclerView recyclerViewVeiculo;
    private VeiculoAdapter veiculoAdapter;

    private EditText txtPlaca;
    private Button btPesquisar;
    private ImageView imgPlaca;





    public Tela01Fragment() {
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
        rootView = inflater.inflate(R.layout.fragment_tela01, container, false);

        ((TelasActivity)getActivity()).clearDados();

        mDb = AppDatabase.getDatabase(myContext.getApplication());
        mVeiculoDao = mDb.veiculoDao();
        veiculos2 = mVeiculoDao.getAll();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        txtPlaca = (EditText) rootView.findViewById(R.id.idPlacaT1);
        txtPlaca.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        btPesquisar = (Button) rootView.findViewById(R.id.btPesquisarT1);
        imgPlaca = (ImageView) rootView.findViewById(R.id.imgPlacaT1);
        imgPlaca.setVisibility(View.INVISIBLE);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        txtPlaca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 3){
                    txtPlaca.append("-");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() == 0){
                    imgPlaca.setVisibility(View.INVISIBLE);
                }else if(editable.length() > 0){
                    imgPlaca.setVisibility(View.VISIBLE);
                }
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        imgPlaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPlaca.setText("");
                veiculos2 = mVeiculoDao.getAll();
                criarlist();
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        btPesquisar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String teste = txtPlaca.getText().toString();

                if(TextUtils.isEmpty(teste)) {
                    txtPlaca.setError("Preechimento da placa obrigatório...");
                    return;
                }else if(!TextUtils.isEmpty(teste) && veiculos2.size() > 0){
                    List<Veiculo> vs2 = new ArrayList<>();
                    Veiculo v2 = new Veiculo();
                    for(int i = 0; i < veiculos2.size(); i++){
                        v2 = veiculos2.get(i);
                        if(v2.getPlaca().contains(teste)){
                            vs2.add(v2);
                        }
                    }
                    veiculos2 = vs2;
                    criarlist();
                    btPesquisar.setFocusable(true);

                    if(vs2.size() == 0){
                        Toast.makeText(myContext.getApplicationContext(), "Não há dados para o filtro atual", Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        // Toast.makeText(myContext.getApplicationContext(), a, Toast.LENGTH_SHORT).show();


        criarlist();





        return rootView;



    }


    public void criarlist(){

        //***************************************  RECICLEVIEW Veiculos
        recyclerViewVeiculo = (RecyclerView) rootView.findViewById(R.id.rvVeiculo);
        veiculoAdapter = new VeiculoAdapter(veiculos2);
        RecyclerView.LayoutManager veiculoLayoutManager = new LinearLayoutManager(myContext.getApplicationContext());
        recyclerViewVeiculo.setLayoutManager(veiculoLayoutManager);

        recyclerViewVeiculo.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerViewVeiculo.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVeiculo.addItemDecoration(new DividerItemDecoration(myContext, LinearLayoutManager.VERTICAL));
        recyclerViewVeiculo.setAdapter(veiculoAdapter);
        recyclerViewVeiculo.addOnItemTouchListener(new RecyclerTouchListener(myContext.getApplicationContext(), recyclerViewVeiculo, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Veiculo veiculo = veiculos2.get(position);
                ((TelasActivity)getActivity()).getTela02(veiculo.getId());

            }

            public void onLongClick(View view, int position) {

            }
        }));

    }












}
