package batatacosmica.UniversoAzul;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class ProfessionalsBoard extends AppCompatActivity {

    Button btnConfig,btnNavigator;
    Intent intent;
    FloatingActionButton newThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professionals_board);
        getSupportActionBar().hide();

        btnConfig=findViewById(R.id.buttonD);
        btnNavigator=findViewById(R.id.buttonC);
        newThread=findViewById(R.id.floatingActionButton);

        btnNavigator.setOnClickListener(v -> {
            intent=new Intent(ProfessionalsBoard.this,GambiarraDrawer.class);
            startActivity(intent);
        });

        btnConfig.setOnClickListener(v -> {
            intent=new Intent(ProfessionalsBoard.this,Settings.class);
            startActivity(intent);
        });

        newThread.setOnClickListener(v -> {
            intent=new Intent(ProfessionalsBoard.this,CreateParentThread.class);
            startActivity(intent);

        });
    }
}
