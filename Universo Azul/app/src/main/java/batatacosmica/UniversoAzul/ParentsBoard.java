package batatacosmica.UniversoAzul;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class ParentsBoard extends AppCompatActivity {

    Button btnConfig,btnNavigator;
    Intent intent;
    FloatingActionButton newThread;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_board);
        getSupportActionBar().hide();

        btnConfig=findViewById(R.id.buttonB);
        btnNavigator=findViewById(R.id.buttonA);
        newThread=findViewById(R.id.floatingActionButton2);
        listView=findViewById(R.id.listView);

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
