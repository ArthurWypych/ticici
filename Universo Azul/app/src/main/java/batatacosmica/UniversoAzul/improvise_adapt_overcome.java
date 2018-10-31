package batatacosmica.UniversoAzul;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class improvise_adapt_overcome extends RecyclerView.Adapter<aaaaahcaraiosegura>{

    //private List<ParentsBoard> thread;
    Cursor cursor;

    public improvise_adapt_overcome(Cursor cursor){
        this.cursor = cursor;
    }





    @NonNull
    @Override

    public aaaaahcaraiosegura onCreateViewHolder(@NonNull ViewGroup ViiV, int i){
        LayoutInflater layoutInflater=LayoutInflater.from(ViiV.getContext());
        View view=layoutInflater.inflate(R.layout.item_viado,ViiV,false);
        aaaaahcaraiosegura siguradero = new aaaaahcaraiosegura(view,ViiV.getContext());

        return siguradero;

    }

    @Override
    public void onBindViewHolder (@NonNull aaaaahcaraiosegura aaaaahcaraiosegura, final int position){


        if((cursor != null) && (cursor.getCount() > 0))
        {
            //ParentsBoard parents = cursor.moveToPosition(position);
            //Cursor cursor= aaaaahcaraiosegura.cursor;
            String titulo, comment, data;
            byte[] imagem;
            Bitmap imagemReal;
            Drawable imageRealVerdade;
            cursor.moveToPosition(position);
            titulo=cursor.getString(cursor.getColumnIndex("Titulo"));
            comment=cursor.getString(cursor.getColumnIndex("Comentario"));
            data=cursor.getString(cursor.getColumnIndex("Time"));
            imagem=cursor.getBlob(cursor.getColumnIndex("Imagem"));
            imagemReal= BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
            imageRealVerdade=new BitmapDrawable(Resources.getSystem(),imagemReal);


            //aaaaahcaraiosegura.tvnome.setText();
            aaaaahcaraiosegura.tvtitle.setText(titulo);
            aaaaahcaraiosegura.tvcomment.setText(comment);
            aaaaahcaraiosegura.tvdata.setText(data);
            aaaaahcaraiosegura.iviamgem.setImageBitmap(imagemReal);
        }

    }

    @Override
    public int getItemCount() { return 0;}

}
