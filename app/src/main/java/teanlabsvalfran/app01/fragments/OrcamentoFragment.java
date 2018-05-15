package teanlabsvalfran.app01.fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import teanlabsvalfran.app01.AppDatabase;
import teanlabsvalfran.app01.R;
import teanlabsvalfran.app01.TelasActivity;
import teanlabsvalfran.app01.models.Orcamento;
import teanlabsvalfran.app01.models.OrcamentoDao;
import teanlabsvalfran.app01.models.OrcamentoPersonalizado;
import teanlabsvalfran.app01.models.VeiculoDao;
import teanlabsvalfran.app01.viewholder.OPAdapter;
import teanlabsvalfran.app01.viewholder.RecyclerTouchListener;


public class OrcamentoFragment extends Fragment {


    private View rootView;
    private FragmentActivity myContext;

    private AppDatabase mDb;
    private VeiculoDao mVeiculoDao;
    private OrcamentoDao mOrcamento;

    private Orcamento orcamento;
    private List<Orcamento> orcamentos;
    private OPAdapter opAdapter;
    private OrcamentoPersonalizado op;
    List<OrcamentoPersonalizado> ops;
    List<OrcamentoPersonalizado> ops2;

    private EditText txtData;
    private EditText txtPlaca;
    private Button btPesquisar;
    private Button btVoltar;
    private ImageView imgData;
    private ImageView imgPlaca;
    private ImageView imgSelectData;
    private DatePickerDialog datePickerDialog;

    private DateFormat df;



    public OrcamentoFragment() {
        // Required empty public constructor
    }
    public void onAttach(Activity Telas) {
        myContext = (FragmentActivity) Telas;
        super.onAttach(Telas);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_orcamento, container, false);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        mDb = AppDatabase.getDatabase(myContext.getApplication());
        mVeiculoDao = mDb.veiculoDao();
        mOrcamento = mDb.orcamentoDao();

        txtData = (EditText) rootView.findViewById(R.id.idData);
        txtPlaca = (EditText) rootView.findViewById(R.id.idPlaca);
        imgData = (ImageView) rootView.findViewById(R.id.imgData);
        imgPlaca = (ImageView) rootView.findViewById(R.id.imgPlaca);
        imgSelectData = (ImageView) rootView.findViewById(R.id.imgSelectData);

        txtPlaca.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        imgPlaca.setVisibility(View.INVISIBLE);
        imgData.setVisibility(View.INVISIBLE);
        df = new SimpleDateFormat("dd/MM/yyyy kk:mm");
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        txtData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 2){
                    txtData.append("/");
                }else if(charSequence.length() == 5){
                    txtData.append("/");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() == 0){
                    imgData.setVisibility(View.INVISIBLE);
                }else if(editable.length() > 0){
                    imgData.setVisibility(View.VISIBLE);
                }
            }
        });
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
        imgData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtData.setText("");
                txtData.clearFocus();
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        imgPlaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPlaca.setText("");
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        imgSelectData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtData.setText("");
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(myContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        String dia = "", mes = "";
                        if(dayOfMonth < 10){
                            dia = "0"+ dayOfMonth;
                        }else{
                            dia = ""+ dayOfMonth;
                        }
                        if(monthOfYear < 10){
                            mes = "0"+(monthOfYear + 1);
                        }else{
                            mes = ""+ (monthOfYear + 1);
                        }
                        txtData.append(dia+"/"+mes+"/"+year);
                       /* txtData.append(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);*/
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        btPesquisar = (Button) rootView.findViewById(R.id.btPesquisar);
        btPesquisar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String teste = txtData.getText().toString();
                String teste2 = txtPlaca.getText().toString();

                if(TextUtils.isEmpty(teste) && TextUtils.isEmpty(teste2)) {

                    iniciarlist();
                    opAdapter.notifyDataSetChanged();

                }else if(!TextUtils.isEmpty(teste) && !TextUtils.isEmpty(teste2)){ //os 2 preenchidos
                    iniciarlist(teste, teste2);

                }else if(!TextUtils.isEmpty(teste) || !TextUtils.isEmpty(teste2)){//so data preenchido
                   String dado = teste+teste2;
                    iniciarlist(dado);
                }
                btPesquisar.setFocusable(true);



            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        btVoltar = (Button) rootView.findViewById(R.id.btVoltar2);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((TelasActivity)getActivity()).getTela01();
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        iniciarlist();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        return rootView;

    }


    public void criarList(){
        RecyclerView recyclerOP = (RecyclerView) rootView.findViewById(R.id.rvOrcamento);
        opAdapter = new OPAdapter(ops2);
        RecyclerView.LayoutManager opLayoutManager = new LinearLayoutManager(myContext.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerOP.setLayoutManager(opLayoutManager);
        recyclerOP.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerOP.setItemAnimator(new DefaultItemAnimator());
        recyclerOP.addItemDecoration(new DividerItemDecoration(myContext, LinearLayoutManager.HORIZONTAL));
        recyclerOP.setAdapter(opAdapter);
        recyclerOP.addOnItemTouchListener(new RecyclerTouchListener(myContext.getApplicationContext(), recyclerOP, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                OrcamentoPersonalizado op = ops.get(position);
                String idOrcamento = op.getIdOrcamento();
                ((TelasActivity)getActivity()).getOSPFragment(idOrcamento);
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }






    public void iniciarlist(){//lista completa
        ops = new ArrayList();
        orcamentos = mOrcamento.getAll();
        for(int i = 0; i < orcamentos.size(); i++){
            op = new OrcamentoPersonalizado();
            orcamento = orcamentos.get(i);
            int idVeiculo = orcamento.getVeiculoId();
            op.setData(df.format(orcamento.getDataAbertura()));
            op.setPlaca(mVeiculoDao.getPlacaById(idVeiculo));
            op.setValor(orcamento.getValorTotal());
            op.setIdOrcamento(orcamento.getOrcamentoId());
            ops.add(op);
        }
        ops2 = new ArrayList();
        ops2 = ops;
        criarList();
    }

    public void iniciarlist(String dado){//lista por data
        ops2 = new ArrayList<>();
        String placa, data;
        for(int i = 0; i < ops.size(); i++){
            op = ops.get(i);
            placa = op.getPlaca();
            data = op.getData();
            if(placa.contains(dado) || data.contains(dado)){
                ops2.add(op);
            }
        }
        if(ops2.size() == 0){
            Toast.makeText(myContext.getApplicationContext(), "Não há dados para o filtro atual", Toast.LENGTH_SHORT).show();
        }
        criarList();
        //opAdapter.notifyDataSetChanged();
    }
    public void iniciarlist(String data, String placa){//lista por data e placa
        ops2 = new ArrayList<>();
        String placa1, data1;
        for(int i = 0; i < ops.size(); i++){
            op = ops.get(i);
            placa1 = op.getPlaca();
            data1 = op.getData();
            if(placa1.contains(placa) && data1.contains(data)){
                ops2.add(op);
            }
        }
        if(ops2.size() == 0){
            Toast.makeText(myContext.getApplicationContext(), "Não há dados para o filtro atual", Toast.LENGTH_SHORT).show();
        }
        criarList();
        //opAdapter.notifyDataSetChanged();
    }








}



