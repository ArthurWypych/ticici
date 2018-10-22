package batatacosmica.UniversoAzul;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        intent =new Intent(Splash.this,MainActivity.class);

        new Handler().postDelayed(() -> startActivity(intent),2000);
    }
}
