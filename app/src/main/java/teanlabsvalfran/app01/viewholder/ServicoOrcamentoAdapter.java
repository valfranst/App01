package teanlabsvalfran.app01.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import teanlabsvalfran.app01.R;
import teanlabsvalfran.app01.models.Servico;

/**
 * Created by Valfran on 26/12/2017.
 */

public class ServicoOrcamentoAdapter extends RecyclerView.Adapter<ServicoOrcamentoAdapter.ServicoOrcamentoViewHolder> {

    private List<Servico> servicos;

    public class ServicoOrcamentoViewHolder extends RecyclerView.ViewHolder {

        public TextView id;
        public TextView nome;
        public TextView valor;

        public ServicoOrcamentoViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.idServicoOrcamento);
            nome = (TextView) itemView.findViewById(R.id.nomeServicoOrcamento);
            valor = (TextView) itemView.findViewById(R.id.valorServicoOrcamento);


        }
    }// FIM UsuarioViewHolder

    public ServicoOrcamentoAdapter(List<Servico> servicos) {
        this.servicos = servicos;
    }


    @Override
    public ServicoOrcamentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lst_servico_orcamento, parent, false);
        return new ServicoOrcamentoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ServicoOrcamentoViewHolder holder, int position) {
        Servico servico = servicos.get(position);
        holder.id.setText("Id: "+servico.getId());
        holder.nome.setText("Serviço: "+servico.getDescricao());
        holder.valor.setText("Valor: "+servico.getPreco());
    }

    @Override
    public int getItemCount() {
        return servicos.size();
    }












}
