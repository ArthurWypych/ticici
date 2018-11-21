package batatacosmica.UniversoAzul;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InsideTheThread extends AppCompatActivity {

    Cursor cursor, cursorUser, cursorgay, cursorCaralho,cursorGayUser,cursorGayUserPORRA;
    DbHelper mDbHelper;
    SQLiteDatabase db;

    TextView tvUsername,tvTitulo,tvTime,tvComment;
    TextView tvUser1,tvComment1,tvTime1;
    TextView tvUser2,tvComment2,tvTime2;
    ImageView imageView,image1,image2;
    FloatingActionButton newPost;
    View v1,v2;

    Integer Cod_Thread;
    String User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_the_thread);
        getSupportActionBar().hide();

        Bundle b= getIntent().getExtras();
        Cod_Thread=b.getInt("Cod");
        User=b.getString("User");

        setButtonAndObjects();



        mDbHelper= new DbHelper(this);
        db=mDbHelper.getWritableDatabase();
        cursor=db.rawQuery("SELECT Titulo,Comentario,Time,Imagem FROM Threads WHERE Cod_Thread="+Cod_Thread,null);
        cursorUser=db.rawQuery("SELECT Username FROM Users WHERE Username='"+User+"'",null);
        cursor.moveToFirst();
        cursorUser.moveToFirst();
        if(cursor!=null && cursorUser!=null  && cursor.moveToFirst() && cursorUser.moveToFirst()) {
            do {
                tvTitulo.setText(cursor.getString(cursor.getColumnIndex("Titulo")));
                tvComment.setText(cursor.getString(cursor.getColumnIndex("Comentario")));
                tvTime.setText(cursor.getString(cursor.getColumnIndex("Time")));
                byte[] imagem = cursor.getBlob(cursor.getColumnIndex("Imagem"));
                Bitmap ImagemReal=BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                imageView.setImageBitmap(ImagemReal);
                tvUsername.setText(cursorUser.getString(cursorUser.getColumnIndex("Username")));
            }while(cursor.moveToNext() && cursorUser.moveToNext());
        }

        setGaybiarra();
        PUTAQUEPARIUDECARALHO();
        Log.e("PORRA",tvComment2.getText().toString());

    }

    public void setGaybiarra(){
        cursorgay=db.rawQuery("SELECT Comentario,Imagem,Time,Cod_User FROM Posts WHERE Cod_Thread="+Cod_Thread,null);
        cursorgay.moveToFirst();
        boolean primeiroViado=false;

        if(cursorgay !=null && cursorgay.moveToFirst()) {

            do {

                if (!primeiroViado) {

                    tvComment1.setText(cursorgay.getString(cursorgay.getColumnIndex("Comentario")));
                    tvTime1.setText(cursorgay.getString(cursorgay.getColumnIndex("Time")));
                    byte[] imagem = cursorgay.getBlob(cursorgay.getColumnIndex("Imagem"));
                    Bitmap ImagemReal = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                    image1.setImageBitmap(ImagemReal);
                    tvUser1.setVisibility(View.VISIBLE);
                    tvComment1.setVisibility(View.VISIBLE);
                    tvTime1.setVisibility(View.VISIBLE);
                    v1.setVisibility(View.VISIBLE);
                    cursorGayUser=db.rawQuery("SELECT Username FROM Users WHERE Cod_User="+cursorgay.getString(cursorgay.getColumnIndex("Cod_User")),null);
                    cursorGayUser.moveToFirst();
                    if(cursorGayUser!=null && cursorGayUser.moveToFirst()) {
                        do {
                            tvUser1.setText(cursorGayUser.getString(cursorGayUser.getColumnIndex("Username")));
                            tvUser1.setVisibility(View.VISIBLE);
                        }while(cursorGayUser.moveToNext());
                    }
                    primeiroViado=true;
                } //else {
                    //if(primeiroViado) {
                        //tvUser2.setText(cursorgay.getString(cursorgay.getColumnIndex("Username")));
                      //  tvComment2.setText(cursorgay.getString(cursorgay.getColumnIndex("Comentario")));
                      //  tvTime2.setText(cursorgay.getString(cursorgay.getColumnIndex("Time")));
                      //  byte[] imagem = cursorgay.getBlob(cursorgay.getColumnIndex("Imagem"));
                      //  Bitmap ImagemReal = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                      //  image2.setImageBitmap(ImagemReal);
                      //  tvUser2.setVisibility(View.VISIBLE);
                      //  tvComment2.setVisibility(View.VISIBLE);
                      //  tvTime2.setVisibility(View.VISIBLE);
                      //  v2.setVisibility(View.VISIBLE);
                    //}

                //}
            }while(cursor.moveToNext());
        }

    }

    public void PUTAQUEPARIUDECARALHO(){
        cursorCaralho=db.rawQuery("SELECT Comentario,Imagem,Time,Cod_User FROM Posts WHERE Cod_Thread="+Cod_Thread,null);
        cursorCaralho.moveToFirst();
        cursorCaralho.moveToNext();

        if(cursorCaralho!=null && cursorCaralho.getCount()>=2) {

            do {
                //tvUser2.setText(cursorgay.getString(cursorgay.getColumnIndex("Username")));
                tvComment2.setText(cursorCaralho.getString(cursorCaralho.getColumnIndex("Comentario")));
                tvTime2.setText(cursorCaralho.getString(cursorCaralho.getColumnIndex("Time")));
                byte[] imagem = cursorCaralho.getBlob(cursorCaralho.getColumnIndex("Imagem"));
                Bitmap ImagemReal = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                image2.setImageBitmap(ImagemReal);
                tvComment2.setVisibility(View.VISIBLE);
                tvTime2.setVisibility(View.VISIBLE);
                v2.setVisibility(View.VISIBLE);
                cursorGayUserPORRA=db.rawQuery("SELECT Username FROM Users WHERE Cod_User="+cursorCaralho.getString(cursorCaralho.getColumnIndex("Cod_User")),null);
                cursorGayUserPORRA.moveToFirst();
                if(cursorGayUserPORRA!=null && cursorGayUserPORRA.moveToFirst()) {
                    do {
                        tvUser2.setText(cursorGayUserPORRA.getString(cursorGayUserPORRA.getColumnIndex("Username")));
                        tvUser2.setVisibility(View.VISIBLE);
                    }while(cursorGayUserPORRA.moveToNext());
                }
            }while(cursorCaralho.moveToNext());
        }
    }

    private void setButtonAndObjects(){

        tvTitulo=findViewById(R.id.textView27);
        tvUsername=findViewById(R.id.textView28);
        tvComment=findViewById(R.id.textView29);
        tvTime=findViewById(R.id.textView30);
        imageView=findViewById(R.id.imageView14);
        newPost=findViewById(R.id.floatingActionButton3);
        tvUser1=findViewById(R.id.textView31);
        tvUser2=findViewById(R.id.textView32);
        tvComment1=findViewById(R.id.textView35);
        tvComment2=findViewById(R.id.textView36);
        tvTime1=findViewById(R.id.textView33);
        tvTime2=findViewById(R.id.textView34);
        image1=findViewById(R.id.imageView15);
        image2=findViewById(R.id.imageView16);
        v1=findViewById(R.id.view2);
        v2=findViewById(R.id.view3);



        newPost.setOnClickListener(v -> {
            Intent intent=new Intent(this,CreatePost.class);
            Bundle bu =new Bundle();
            bu.putInt("Cod",Cod_Thread);
            bu.putString("User", User);
            intent.putExtras(bu);
            startActivity(intent);
        });
    }

}
