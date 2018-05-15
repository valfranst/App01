package teanlabsvalfran.app01.viewholder;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import teanlabsvalfran.app01.AppDatabase;
import teanlabsvalfran.app01.MyApplication;
import teanlabsvalfran.app01.R;
import teanlabsvalfran.app01.TelasActivity;
import teanlabsvalfran.app01.models.Servico;
import teanlabsvalfran.app01.models.ServicoPrecoDao;

/**
 * Created by Valfran on 26/12/2017.
 */

public class ServicoAdapter extends RecyclerView.Adapter<ServicoAdapter.ServicoViewHolder> {

    private List<Servico> servicos;
    private SparseBooleanArray selectedItems;
    private ServicoPrecoDao mServDao;
    private ServicoPrecoDao mSPrecoDao;
    private AppDatabase mDb;
    private int catPosition, qtd;

    public class ServicoViewHolder extends RecyclerView.ViewHolder{

        public TextView nome, preco, precoTexto;


        public ServicoViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.nomeServico);
            preco = (TextView) itemView.findViewById(R.id.precoServico);
            precoTexto = (TextView) itemView.findViewById(R.id.precoTexto);
        }
        /*@Override
        public void onClick(View view) {


            if (selectedItems.get(getAdapterPosition(), false)) {
                selectedItems.delete(getAdapterPosition());
                view.setSelected(false);
            }
            else {
                selectedItems.put(getAdapterPosition(), true);
                view.setSelected(true);
            }
            notifyDataSetChanged();



        }*/


    }// FIM UsuarioViewHolder

    public ServicoAdapter(List<Servico> servicos, int catPosition) {
        this.servicos = servicos;
        this.selectedItems = TelasActivity.getSelects(catPosition);
        this.catPosition = catPosition;
        mDb = AppDatabase.getDatabase(MyApplication.getContext());
        mServDao = mDb.servicoPrecoDao();
        mSPrecoDao = mDb.servicoPrecoDao();

    }


    @Override
    public ServicoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lst_servico, parent, false);
        return new ServicoViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ServicoViewHolder holder, int position) {
        Servico servico = servicos.get(position);
        holder.nome.setText(" "+servico.getDescricao());

        String txtPreco = "";
        txtPreco = servico.getPreco();
        if(txtPreco.equals("") || txtPreco.equals(null) ){
            qtd = mServDao.getCountByIdServico(servico.getId());
            holder.precoTexto.setText("Qtd de Preços: ");
            holder.preco.setText(String.valueOf(qtd));
        }else {
            holder.precoTexto.setText("Preço Selecionado: ");
            holder.preco.setText(servico.getPreco()+" R$");
        }

        if(selectedItems.get(position)){
            holder.itemView.setBackgroundColor(R.color.colorPrimary);
        }
    }

    @Override
    public int getItemCount() {
        return servicos.size();
    }


    public void setValues(List<Servico> servicos) {
        this.servicos = servicos;
        notifyDataSetChanged();
    }

    public void setSelectedItems(int catPosition){
        this.selectedItems = TelasActivity.getSelects(catPosition);
        notifyDataSetChanged();
    }
















}
