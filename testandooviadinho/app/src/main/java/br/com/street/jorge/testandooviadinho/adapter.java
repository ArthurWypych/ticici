package br.com.street.jorge.testandooviadinho;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.holder> {

    public static ClickRecyclerView_Interface clickRecyclerViewInterface;
    Context mctx;
    private List<Model> mList;

    public adapter(Context ctx, List<Model> list, ClickRecyclerView_Interface clickRecyclerViewInterface) {
        this.mctx = ctx;
        this.mList = list;
        this.clickRecyclerViewInterface = clickRecyclerViewInterface;
    }
    @Override
    public holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new holder(itemView);
    }

    @Override
    public void onBindViewHolder(holder viewHolder, int i) {
        Model model = mList.get(i);

        viewHolder.textView.setText(model.getviado());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    protected class holder extends RecyclerView.ViewHolder {

        protected TextView textView;

        public holder(final View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);

            //Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    clickRecyclerViewInterface.onCustomClick(mList.get(getLayoutPosition()));

                }
            });
        }
    }

}
