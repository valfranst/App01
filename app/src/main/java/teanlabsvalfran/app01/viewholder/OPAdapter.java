package teanlabsvalfran.app01.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import teanlabsvalfran.app01.R;
import teanlabsvalfran.app01.models.OrcamentoPersonalizado;

/**
 * Created by Valfran on 26/12/2017.
 */

public class OPAdapter extends RecyclerView.Adapter<OPAdapter.OPAdapterViewHolder> {

    private List<OrcamentoPersonalizado> ops;

    public class OPAdapterViewHolder extends RecyclerView.ViewHolder {

        //public TextView id;
        public TextView placa;
        public TextView data;
        public TextView valortotal;

        public OPAdapterViewHolder(View itemView) {
            super(itemView);
            placa = (TextView) itemView.findViewById(R.id.tvPlaca);
            data = (TextView) itemView.findViewById(R.id.tvData);
            valortotal = (TextView) itemView.findViewById(R.id.tvValorTotal);
        }
    }// FIM UsuarioViewHolder

    public OPAdapter(List<OrcamentoPersonalizado> ops) {
        this.ops = ops;
    }


    @Override
    public OPAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_orcamento, parent, false);
        return new OPAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OPAdapterViewHolder holder, int position) {
        OrcamentoPersonalizado op = ops.get(position);
        holder.placa.setText(""+op.getPlaca());
        holder.data.setText(""+op.getData());
        holder.valortotal.setText(""+op.getValor());
    }

    @Override
    public int getItemCount() {
        return ops.size();
    }












}
