package batatacosmica.UniversoAzul;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InsideTheThread extends AppCompatActivity {

    Cursor cursor, cursorImagem, cursorUser;
    DbHelper mDbHelper;
    SQLiteDatabase db;

    TextView tvUsername,tvTitulo,tvTime,tvComment;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_the_thread);
        getSupportActionBar().hide();

        Bundle b= getIntent().getExtras();
        Integer Cod_Thread=b.getInt("Cod");
        String User=b.getString("User");

        tvTitulo=findViewById(R.id.textView27);
        tvUsername=findViewById(R.id.textView28);
        tvComment=findViewById(R.id.textView29);
        tvTime=findViewById(R.id.textView30);
        imageView=findViewById(R.id.imageView14);

        mDbHelper= new DbHelper(this);
        db=mDbHelper.getWritableDatabase();
        cursor=db.rawQuery("SELECT Titulo,Comentario,Time,Imagem FROM Threads WHERE Cod_Thread="+Cod_Thread,null);
        cursorUser=db.rawQuery("SELECT Username FROM Users WHERE Username='"+User+"'",null);
        cursorImagem=db.rawQuery("SELECT Imagem FROM Threads WHERE Cod_Thread="+Cod_Thread,null);
        cursor.moveToFirst();
        cursorImagem.moveToFirst();
        cursorUser.moveToFirst();
        if(cursor!=null && cursorUser!=null && cursorImagem !=null && cursorImagem.moveToFirst() && cursor.moveToFirst() && cursorUser.moveToFirst()) {
            do {
                tvTitulo.setText(cursor.getString(cursor.getColumnIndex("Titulo")));
                tvComment.setText(cursor.getString(cursor.getColumnIndex("Comentario")));
                tvTime.setText(cursor.getString(cursor.getColumnIndex("Time")));
                byte[] imagem = cursor.getBlob(cursor.getColumnIndex("Imagem"));
                Bitmap ImagemReal=BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                imageView.setImageBitmap(ImagemReal);
                tvUsername.setText(cursorUser.getString(cursorUser.getColumnIndex("Username")));
            }while(cursor.moveToNext() && cursorUser.moveToNext() && cursorImagem.moveToNext());
        }

    }
}
