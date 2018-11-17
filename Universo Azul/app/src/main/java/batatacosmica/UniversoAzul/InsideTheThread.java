package batatacosmica.UniversoAzul;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InsideTheThread extends AppCompatActivity {

    Cursor cursor, cursorUser;
    DbHelper mDbHelper;
    SQLiteDatabase db;

    TextView tvUsername,tvTitulo,tvTime,tvComment;
    TextView tvUser1,tvComment1,tvTime1;
    TextView tvUser2,tvComment2,tvTime2;
    ImageView imageView,image1,image2;
    FloatingActionButton newPost;
    View v1,v2;

    Integer Cod_Thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_the_thread);
        getSupportActionBar().hide();

        Bundle b= getIntent().getExtras();
        Cod_Thread=b.getInt("Cod");
        String User=b.getString("User");

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

    }

    public void setGaybiarra(){
        Cursor cursor;
        cursor=db.rawQuery("SELECT Comentario,Imagem,Time FROM Posts WHERE Cod_Thread="+Cod_Thread,null);
        cursor.moveToFirst();

        if(cursor !=null && cursor.moveToFirst() ) {

            do {

                if (tvUser1.getText().equals("Username") && tvComment1.getText().equals("Comment") && tvTime1.getText().equals("Data")) {

                    tvUser1.setText(cursor.getString(cursor.getColumnIndex("Username")));
                    tvComment1.setText(cursor.getString(cursor.getColumnIndex("Comentario")));
                    tvTime1.setText(cursor.getString(cursor.getColumnIndex("Time")));
                    byte[] imagem = cursor.getBlob(cursor.getColumnIndex("Imagem"));
                    Bitmap ImagemReal = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                    image1.setImageBitmap(ImagemReal);
                    tvUser1.setVisibility(View.VISIBLE);
                    tvComment1.setVisibility(View.VISIBLE);
                    tvTime1.setVisibility(View.VISIBLE);
                    v1.setVisibility(View.VISIBLE);
                } else {
                    tvUser2.setText(cursor.getString(cursor.getColumnIndex("Username")));
                    tvComment2.setText(cursor.getString(cursor.getColumnIndex("Comentario")));
                    tvTime2.setText(cursor.getString(cursor.getColumnIndex("Time")));
                    byte[] imagem = cursor.getBlob(cursor.getColumnIndex("Imagem"));
                    Bitmap ImagemReal = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                    image2.setImageBitmap(ImagemReal);
                    tvUser2.setVisibility(View.VISIBLE);
                    tvComment2.setVisibility(View.VISIBLE);
                    tvTime2.setVisibility(View.VISIBLE);
                    v2.setVisibility(View.VISIBLE);

                }
            }while(cursor.moveToNext());
        }

    }

    private void setButtonAndObjects(){

        //tvTitulo=findViewById(R.id.textView27);
        //tvUsername=findViewById(R.id.textView28);
        //tvComment=findViewById(R.id.textView29);
        //tvTime=findViewById(R.id.textView30);
        //imageView=findViewById(R.id.imageView14);
        //newPost=findViewById(R.id.floatingActionButton3);
        //tvUser1=findViewById(R.id.textView31);
        //tvUser2=findViewById(R.id.textView32);
        //tvComment1=findViewById(R.id.textView35);
        //tvComment2=findViewById(R.id.textView36);
        //tvTime1=findViewById(R.id.textView33);
        //tvTime2=findViewById(R.id.textView34);
        //image1=findViewById(R.id.imageView15);
        //image2=findViewById(R.id.imageView16);
        //v1=findViewById(R.id.view2);
        //v2=findViewById(R.id.view3);



        newPost.setOnClickListener(v -> {
            Intent intent=new Intent(this,CreatePost.class);
            Bundle bu =new Bundle();
            bu.putInt("Cod",Cod_Thread);
            intent.putExtras(bu);
            startActivity(intent);
        });
    }

}
