package batatacosmica.UniversoAzul;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.holder> {

    public static intermunicipal clickRecyclerViewInterface;
    Context mctx;
    private List<model> mList;

    public adapter(Context ctx, List<model> list, intermunicipal clickRecyclerViewInterface) {
        this.mctx = ctx;
        this.mList = list;
        this.clickRecyclerViewInterface = clickRecyclerViewInterface;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_viado, viewGroup, false);
        return new holder(itemView);
    }
    @Override
    public void onBindViewHolder(holder viewHolder, int i) {
        model model = mList.get(i);

        viewHolder.Username.setText(model.getUsername());
        viewHolder.Title.setText(model.getUsername());
        viewHolder.Comment.setText(model.getUsername());
        viewHolder.Data.setText(model.getUsername());
        viewHolder.Image.setImageBitmap(model.getImage());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    protected class holder extends RecyclerView.ViewHolder {

        protected TextView Username;
        protected TextView Title;
        protected TextView Comment;
        protected TextView Data;
        protected ImageView Image;


        public holder(final View itemView) {
            super(itemView);

            Username = (TextView) itemView.findViewById(R.id.textView23);
            Title = (TextView) itemView.findViewById(R.id.textView26);
            Comment = (TextView) itemView.findViewById(R.id.textView24);
            Data = (TextView) itemView.findViewById(R.id.textView25);
            Image = (ImageView) itemView.findViewById(R.id.imageView11);


            //Setup the click listener
            itemView.setOnClickListener(v ->
                    clickRecyclerViewInterface.onCustomClick(mList.get(getLayoutPosition())));
        }

    }
}
