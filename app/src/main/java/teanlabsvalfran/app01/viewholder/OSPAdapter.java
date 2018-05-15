package teanlabsvalfran.app01.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import teanlabsvalfran.app01.R;
import teanlabsvalfran.app01.models.OSPersonalizado;

/**
 * Created by Valfran on 26/12/2017.
 */

public class OSPAdapter extends RecyclerView.Adapter<OSPAdapter.OSPAdapterViewHolder> {

    private List<OSPersonalizado> osps;

    public class OSPAdapterViewHolder extends RecyclerView.ViewHolder {

        //public TextView id;
        public TextView nome;
        public TextView descricao;
        public TextView valor;

        public OSPAdapterViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.tvNomeOS);
            descricao = (TextView) itemView.findViewById(R.id.tvDescricaoOS);
            valor = (TextView) itemView.findViewById(R.id.tvValorOS);
        }
    }// FIM UsuarioViewHolder

    public OSPAdapter(List<OSPersonalizado> osps) {
        this.osps = osps;
    }


    @Override
    public OSPAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_orcamento_servico, parent, false);
        return new OSPAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OSPAdapterViewHolder holder, int position) {
        OSPersonalizado osp = osps.get(position);
        holder.nome.setText(""+osp.getNomeServico());
        holder.descricao.setText(""+osp.getDescricao());
        holder.valor.setText(""+osp.getValor());
    }

    @Override
    public int getItemCount() {
        return osps.size();
    }












}
