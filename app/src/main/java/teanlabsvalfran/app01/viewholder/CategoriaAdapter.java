package teanlabsvalfran.app01.viewholder;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import teanlabsvalfran.app01.AppDatabase;
import teanlabsvalfran.app01.MyApplication;
import teanlabsvalfran.app01.R;
import teanlabsvalfran.app01.models.Categoria;
import teanlabsvalfran.app01.models.ServicoDao;

/**
 * Created by Valfran on 26/12/2017.
 */

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {

    private List<Categoria> categorias;
    private AppDatabase mDb;
    private ServicoDao mServicoDao;
    private int qtd;
    private int catPosition;

    public class CategoriaViewHolder extends RecyclerView.ViewHolder {

        //public TextView id;
        public TextView nome;
        public  TextView qtdServico;

        public CategoriaViewHolder(View itemView) {
            super(itemView);
            //id = (TextView) itemView.findViewById(R.id.idCategoria);
            nome = (TextView) itemView.findViewById(R.id.nomeCategoria);
            qtdServico = (TextView) itemView.findViewById(R.id.qtdServico);
        }
    }// FIM UsuarioViewHolder

    public CategoriaAdapter(List<Categoria> categorias, int catPosition) {
        this.categorias = categorias;
        this.catPosition = catPosition;
        mDb = AppDatabase.getDatabase(MyApplication.getContext());
        mServicoDao = mDb.servicoDao();
    }


    @Override
    public CategoriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lst_categoria, parent, false);
        return new CategoriaViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(CategoriaViewHolder holder, int position) {
        Categoria categoria = categorias.get(position);
        qtd = mServicoDao.getCountByCategoria(categoria.getId());
        holder.nome.setText(""+categoria.getDescricao());
        holder.qtdServico.setText(String.valueOf(qtd));
        if(catPosition == position){
            holder.itemView.setBackgroundColor(R.color.colorPrimary);
        }

    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public void setCatPosition(int catPosition){
        this.catPosition = catPosition;
        notifyDataSetChanged();
    }









}
