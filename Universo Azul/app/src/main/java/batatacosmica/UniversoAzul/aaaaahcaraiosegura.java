package batatacosmica.UniversoAzul;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class aaaaahcaraiosegura extends RecyclerView.ViewHolder
{

    public TextView tvtitle;
    public TextView tvnome;
    public TextView tvdata;
    public TextView tvcomment;
    public ImageView iviamgem;
    //public Cursor cursor;
    private String title;
    private String nome;
    private String data;
    private String comment;
    private Bitmap imagem;



    public aaaaahcaraiosegura (@NonNull final View itemView, final Context context) {
        super(itemView);

        tvtitle = (TextView) itemView.findViewById(R.id.textView26);
        tvnome = (TextView) itemView.findViewById(R.id.textView23);
        tvdata = (TextView) itemView.findViewById(R.id.textView25);
        tvcomment = (TextView) itemView.findViewById(R.id.textView24);
        iviamgem = (ImageView) itemView.findViewById(R.id.imageView11);
        //cursor=cuck;


    }






}
