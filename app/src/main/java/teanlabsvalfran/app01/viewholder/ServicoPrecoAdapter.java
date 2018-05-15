package teanlabsvalfran.app01.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import teanlabsvalfran.app01.R;
import teanlabsvalfran.app01.models.ServicoPreco;

/**
 * Created by Valfran on 26/12/2017.
 */

public class ServicoPrecoAdapter extends RecyclerView.Adapter<ServicoPrecoAdapter.ServicoPrecoViewHolder> {

    private List<ServicoPreco> servs;

    public class ServicoPrecoViewHolder extends RecyclerView.ViewHolder {

        public TextView descricao;
        public TextView valor;

        public ServicoPrecoViewHolder(View itemView) {
            super(itemView);

            descricao = (TextView) itemView.findViewById(R.id.descricaoServPreco);
            valor = (TextView) itemView.findViewById(R.id.valorServPreco);
        }
    }// FIM VeiculoViewHolder

    public ServicoPrecoAdapter(List<ServicoPreco> servs) {
        this.servs = servs;
        //notifyDataSetChanged();
    }


    @Override
    public ServicoPrecoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lst_servicopreco, parent, false);
        return new ServicoPrecoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ServicoPrecoViewHolder holder, int position) {
        ServicoPreco serv = servs.get(position);
        holder.descricao.setText(serv.getDescricao());
        holder.valor.setText(""+serv.getValor());
    }

    @Override
    public int getItemCount() {
        return servs.size();
    }












}
