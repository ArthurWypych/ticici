package batatacosmica.UniversoAzul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GambiarraDrawer extends AppCompatActivity {

    Intent intent;
    Button btnNews,btnParents,btnProfessional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambiarra_drawer);
        getSupportActionBar().hide();

        btnNews=findViewById(R.id.button6);
        btnParents=findViewById(R.id.button7);
        btnProfessional=findViewById(R.id.button8);

        btnNews.setOnClickListener(v -> {
            intent=new Intent(GambiarraDrawer.this,NewsFeed.class);
            startActivity(intent);
        });

        btnParents.setOnClickListener(v -> {
            if(CurrentUser.Tipo_Conta.equals("Parents")){
                intent=new Intent(GambiarraDrawer.this,ParentsBoard.class);
                startActivity(intent);

            }
            else{
                Toast.makeText(getBaseContext(),"Area restrita aos Pais",Toast.LENGTH_LONG).show();
            }
        });

        btnProfessional.setOnClickListener(v -> {
            intent=new Intent(GambiarraDrawer.this,ProfessionalsBoard.class);
            startActivity(intent);

        });
    }
}
