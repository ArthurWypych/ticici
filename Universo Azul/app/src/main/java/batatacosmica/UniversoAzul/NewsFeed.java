package batatacosmica.UniversoAzul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class NewsFeed extends AppCompatActivity {

    //TextView teste;
    Button btnConfig,btnNavigator;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        getSupportActionBar().hide();
        //teste=findViewById(R.id.textViewTeste);
        //teste.setText(String.valueOf(CurrentUser.id));

        btnConfig=findViewById(R.id.button5);
        btnNavigator=findViewById(R.id.button4);

        btnNavigator.setOnClickListener(v -> {
            intent=new Intent(NewsFeed.this,GambiarraDrawer.class);
            startActivity(intent);
        });

        btnConfig.setOnClickListener(v -> {
            intent=new Intent(NewsFeed.this,Settings.class);
            startActivity(intent);
        });
    }
}
