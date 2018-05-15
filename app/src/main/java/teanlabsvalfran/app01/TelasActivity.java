package teanlabsvalfran.app01;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import teanlabsvalfran.app01.Remoto.Chamar;
import teanlabsvalfran.app01.fragments.DialogFragment;
import teanlabsvalfran.app01.fragments.OSPFragment;
import teanlabsvalfran.app01.fragments.OrcamentoFragment;
import teanlabsvalfran.app01.fragments.Tela01Fragment;
import teanlabsvalfran.app01.fragments.Tela02Fragment;
import teanlabsvalfran.app01.models.Orcamento;
import teanlabsvalfran.app01.models.OrcamentoServico;
import teanlabsvalfran.app01.models.Servico;
import teanlabsvalfran.app01.models.ServicoDao;

public class TelasActivity extends BaseActivity {

    FragmentTransaction ft;
    Fragment fragment;
    public static Context context;

    private static Orcamento orcamento = null;
    private static List<OrcamentoServico> oServicos = null;
    private static List<Servico> servicos = null;
    private static List<SparseBooleanArray> sparseBooleanArray = null;
    private SharedPreferences idDevice;
    public static final String mypreference = "mypref";
    public static String idNow;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telas);
        context = TelasActivity.this;

        /*Povoar pv = Povoar.getInstance(this);
        pv.prepareoData();*/

        gerarId();
        getTela01();
    }

    private void gerarId() {
        //idDevice = idDevice(mypreference, Context.MODE_PRIVATE);

        idDevice = getSharedPreferences(mypreference, MODE_PRIVATE);
        String restoredText = idDevice.getString("idDevice", null);
        if (restoredText == null) {
            String id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            SharedPreferences.Editor editor = getSharedPreferences(mypreference, MODE_PRIVATE).edit();
            editor.putString("idDevice", id);
            editor.apply();
        }
        idNow = idDevice.getString("idDevice", null);//"No name defined" is the default value.
        //Toast.makeText(context.getApplicationContext(), idNow, Toast.LENGTH_SHORT).show();
    }

    public void getTela01(){
        fragment = new Tela01Fragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public void getTela02(int idVeiculo){
        fragment = new Tela02Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("idVeiculo", idVeiculo);
        fragment.setArguments(bundle);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public void getTela02(int idVeiculo, int idServico, int idCategoria){
        fragment = new Tela02Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("idVeiculo", idVeiculo);
        bundle.putInt("idServico", idServico);
        bundle.putInt("idCategoria", idCategoria);
        fragment.setArguments(bundle);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public void getTela02(int idVeiculo, int idServico, int idCategoria, int catPosition){
        fragment = new Tela02Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("idVeiculo", idVeiculo);
        bundle.putInt("idServico", idServico);
        bundle.putInt("idCategoria", idCategoria);
        bundle.putInt("catPosition", catPosition);
        fragment.setArguments(bundle);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public void getDialogFragmento(int idVeiculo, int idServico, int idCategoria, int catPosition, int servPosition){
        fragment = new DialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("idVeiculo", idVeiculo);
        bundle.putInt("idServico", idServico);
        bundle.putInt("idCategoria", idCategoria);
        bundle.putInt("catPosition", catPosition);
        bundle.putInt("servPosition", servPosition);
        fragment.setArguments(bundle);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public void getOrcamentoFragment(){
        fragment = new OrcamentoFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public void getOSPFragment(String idOrcamento){
        fragment = new OSPFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idOrcamento", idOrcamento);
        fragment.setArguments(bundle);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public boolean isonline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if( (netInfo != null) && (netInfo.isConnectedOrConnecting()) && (netInfo.isAvailable()) ){
            return  true;
        }else
            return  false;

    }
    public boolean isWifi(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        boolean isWiFi = netInfo.getType() == ConnectivityManager.TYPE_WIFI;
        if(isWiFi){
            return  true;
        }else
            return  false;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.atualizar) {



            if(isonline()){


                if(!isWifi()){

                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
                    builder.setTitle("INFORMAÇÃO");
                    builder.setMessage("A conexão atual pode não ser suficiente para atualizar os dados do aplicativo. Se possível conecte-se a uma rede WIFI...");

                    // add the buttons
                    builder.setPositiveButton("ENTENDI", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                    // create and show the alert dialog
                    android.support.v7.app.AlertDialog dialog = builder.create();
                    dialog.show();
                }



                final Chamar ch = new Chamar();
                showProgressDialog();
                Thread mThread = new Thread() {
                    @Override
                    public void run() {
                        ch.getTodos();
                        getTela01();
                        hideProgressDialog();
                    }
                };
                mThread.start();
            }else {
                Toast.makeText(this.getApplicationContext(), "SEM CONEXÃO COM A INTERNET", Toast.LENGTH_SHORT).show();
                getTela01();
            }

            return true;



        }else if (id == R.id.orcamentoE) {
            getOrcamentoFragment();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }



    public void clearDados(){
        orcamento = null;
        oServicos = null;
        servicos = null;
        sparseBooleanArray = null;
        AppDatabase mDb = AppDatabase.getDatabase(this.getApplication());
        ServicoDao mServicoDao = mDb.servicoDao();
        int i = mServicoDao.getCount();
        if(i > 0) {
            mServicoDao.resetServicos();
        }

    }

    public static Orcamento getOrcamento() {
        return orcamento;
    }
    public static void setOrcamento(Orcamento orcamento) {
        TelasActivity.orcamento = orcamento;
    }

    public static List<OrcamentoServico> getoServicos() {
        return oServicos;
    }
    public static void setoServicos(List<OrcamentoServico> oServicos) {
        TelasActivity.oServicos = oServicos;
    }


    public static List<Servico> getServicos() {
        return servicos;
    }
    public static void setServicos(List<Servico> servicos) {
        TelasActivity.servicos = servicos;
    }

    public static List<SparseBooleanArray> getSparseBooleanArray() {
        return sparseBooleanArray;
    }
    public static void setSparseBooleanArray(List<SparseBooleanArray> sparseBooleanArray) {
        TelasActivity.sparseBooleanArray = sparseBooleanArray;
    }
    public static SparseBooleanArray getSelects(int position){
        SparseBooleanArray sp = TelasActivity.sparseBooleanArray.get(position);
        return sp;
    }
    public static void setSelects(SparseBooleanArray sp, int position){
        TelasActivity.sparseBooleanArray.set(position, sp);

    }




}//fim classe
