package batatacosmica.UniversoAzul;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class improvise_adapt_overcome extends RecyclerView.Adapter<aaaaahcaraiosegura>{

    private List<ParentsBoard> thread;

    public improvise_adapt_overcome(List<ParentsBoard> thread){
        this.thread = thread;
    }



    @NonNull
    @Override
    public aaaaahcaraiosegura onCreateViewHolder(@NonNull ViewGroup ViiV, int i){
        LayoutInflater layoutInflater=LayoutInflater.from(ViiV.getContext());
        View view=layoutInflater.inflate(R.layout.item_viado,ViiV,false);
        aaaaahcaraiosegura aaaaahcaraiosegura = new aaaaahcaraiosegura(view,ViiV.getContext());

        return aaaaahcaraiosegura;
    }

    @Override
    public void onBindViewHolder (@NonNull aaaaahcaraiosegura aaaaahcaraiosegura, final int position){

        if((thread != null) && (thread.size() > 0))
        {
            ParentsBoard parents = thread.get(position);
            aaaaahcaraiosegura.tvnome.setText(ParentsBoard.);
        }

    }

    @Override
    public int getItemCount() { return 0;}

}
