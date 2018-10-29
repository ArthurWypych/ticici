package batatacosmica.UniversoAzul;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class ParentsBoard extends AppCompatActivity {

    DbHelper mDbHelper;
    Cursor cursor;
    SQLiteDatabase db;


    Button btnConfig,btnNavigator;
    Intent intent;
    FloatingActionButton newThread;
    RecyclerView liil;

    LayoutInflater factory = LayoutInflater.from(this);
    View viadinhogay = factory.inflate(R.layout.item_viado, null);





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_board);
        getSupportActionBar().hide();

        mDbHelper= new DbHelper(this);
        db=mDbHelper.getWritableDatabase();

        btnConfig=findViewById(R.id.buttonB);
        btnNavigator=findViewById(R.id.buttonA);
        newThread=findViewById(R.id.floatingActionButton2);
        liil=findViewById(R.id.ParentsLista);


        cursor=db.rawQuery("SELECT Titulo,Comentario,Time,Imagem FROM Threads WHERE Tipo_Thread='Parents' ",null);

        improvise_adapt_overcome BearGrylls=new improvise_adapt_overcome((List<ParentsBoard>) liil);
        aaaaahcaraiosegura SIGURA = new aaaaahcaraiosegura(viadinhogay,this,cursor);

        for (int i=0;i>=cursor.getCount();i++){
            BearGrylls.onBindViewHolder(SIGURA,i);
        }




        btnNavigator.setOnClickListener(v -> {
            intent=new Intent(ParentsBoard.this,GambiarraDrawer.class);
            startActivity(intent);
        });

        btnConfig.setOnClickListener(v -> {
            intent=new Intent(ParentsBoard.this,Settings.class);
            startActivity(intent);
        });

        newThread.setOnClickListener(v -> {
            intent=new Intent(ParentsBoard.this,CreateParentThread.class);
            startActivity(intent);

        });

    }
}
