package teanlabsvalfran.app01.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import teanlabsvalfran.app01.R;
import teanlabsvalfran.app01.models.Veiculo;

/**
 * Created by Valfran on 26/12/2017.
 */

public class VeiculoAdapter extends RecyclerView.Adapter<VeiculoAdapter.VeiculoViewHolder> {

    private List<Veiculo> veiculos;

    public class VeiculoViewHolder extends RecyclerView.ViewHolder {

        public TextView placa;
        public TextView veiculo;
        public TextView cliente;
        public TextView idVeiculo;

        public VeiculoViewHolder(View itemView) {
            super(itemView);

            placa = (TextView) itemView.findViewById(R.id.placa);
            veiculo = (TextView) itemView.findViewById(R.id.veiculo);
            cliente = (TextView) itemView.findViewById(R.id.cliente);
            idVeiculo = (TextView) itemView.findViewById(R.id.idVeiculo);
        }
    }// FIM VeiculoViewHolder

    public VeiculoAdapter(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
        //notifyDataSetChanged();
    }


    @Override
    public VeiculoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lst_veiculo, parent, false);
        return new VeiculoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VeiculoViewHolder holder, int position) {
        Veiculo veiculo = veiculos.get(position);
        holder.idVeiculo.setText(String.valueOf(veiculo.getId()));
        holder.placa.setText(veiculo.getPlaca());
        holder.veiculo.setText(veiculo.getModelo());
        holder.cliente.setText(veiculo.getCliente());
    }

    @Override
    public int getItemCount() {
        return veiculos.size();
    }












}
