package batatacosmica.UniversoAzul;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ProfessionalsBoard extends AppCompatActivity implements intermunicipal {

    Button btnConfig,btnNavigator;
    Intent intent;
    FloatingActionButton newThread;

    DbHelper mDbHelper;
    Cursor cursor, cursorImagem;
    SQLiteDatabase db;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager garotogay;
    adapter adapter;
    private List<model> listamodelo = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professionals_board);
        getSupportActionBar().hide();

        mDbHelper= new DbHelper(this);
        db=mDbHelper.getWritableDatabase();

        cursor=db.rawQuery("SELECT Cod_thread,Titulo,Comentario,Time FROM Threads WHERE Tipo_Thread='Professional' ",null);
        cursorImagem=db.rawQuery("SELECT Imagem FROM THREADS WHERE Tipo_Thread='Professional'",null);
        cursorImagem.moveToFirst();
        cursor.moveToFirst();
        setButtons();
        listenerButtons();
        model viadinho = new model();
        setRecyclerView();

        if(cursor != null && cursor.moveToFirst() && cursorImagem!=null && cursorImagem.moveToFirst()) {


            //for (int i = 1; i >= cursor.getCount(); i++) {
            do {
                viadinho.setCod_Thread(cursor.getInt(cursor.getColumnIndex("Cod_Thread")));
                viadinho.setUsername(CurrentUser.Username);
                viadinho.setTitulo(cursor.getString(cursor.getColumnIndex("Titulo")));
                viadinho.setComment(cursor.getString(cursor.getColumnIndex("Comentario")));
                viadinho.setData(cursor.getString(cursor.getColumnIndex("Time")));
                byte[] imagem = cursorImagem.getBlob(cursorImagem.getColumnIndex("Imagem"));
                //Bitmap ImagemReal = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                Bitmap[] ImagemReal=new Bitmap[10];
                ImagemReal[CurrentUser.imageCounter] = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                viadinho.setImage(ImagemReal[CurrentUser.imageCounter]);




                listamodelo.add(viadinho);
                //adapter.notifyItemInserted(adapter.getItemCount());
                //adapter.notifyDataSetChanged();
                //listamodelo.clear();
                CurrentUser.imageCounter++;




            }while(cursor.moveToNext() && cursorImagem.moveToNext());
            //}

            //listamodelo.clear();
            //setRecyclerView();
        }




    }

    public void setRecyclerView(){

        mRecyclerView = (RecyclerView) findViewById(R.id.ProfessionalsLista);
        garotogay = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(garotogay);


        adapter = new adapter(this, listamodelo, this::onCustomClick);
        mRecyclerView.setAdapter(adapter);
    }

    public void setButtons(){
        btnConfig=findViewById(R.id.buttonD);
        btnNavigator=findViewById(R.id.buttonC);
        newThread=findViewById(R.id.floatingActionButton);

    }

    public void listenerButtons(){
        btnNavigator.setOnClickListener(v -> {
            intent=new Intent(ProfessionalsBoard.this,GambiarraDrawer.class);
            startActivity(intent);
        });

        btnConfig.setOnClickListener(v -> {
            intent=new Intent(ProfessionalsBoard.this,Settings.class);
            startActivity(intent);
        });

        newThread.setOnClickListener(v -> {
            intent=new Intent(ProfessionalsBoard.this,CreateProfessionalsThread.class);
            startActivity(intent);

        });
    }


    public void onCustomClick(Object object) {
        model viadinho = (model) object;
        Integer Cooc = viadinho.getCod_Thread();
        String User=viadinho.getUsername();
        Intent intent = new Intent(this,InsideTheThread.class);
        Bundle b= new Bundle();
        b.putInt("Cod",Cooc);
        b.putString("User",User);
        intent.putExtras(b);
        startActivity(intent);
    }
}
