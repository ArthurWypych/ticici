package batatacosmica.UniversoAzul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    TextView TvUser,TvOp,TvTipo;
    Button MEUBUTAO;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();

        TvUser=findViewById(R.id.textView10);
        TvOp=findViewById(R.id.textView11);
        TvTipo=findViewById(R.id.textView12);
        MEUBUTAO=findViewById(R.id.button9);

        TvUser.setText(CurrentUser.Username);
        TvOp.setText(String.valueOf(CurrentUser.QtdPosts));
        TvTipo.setText(CurrentUser.Tipo_Conta);

        MEUBUTAO.setOnClickListener(v -> {
            CurrentUser.id=0;
            CurrentUser.Username="";
            CurrentUser.Tipo_Conta="";
            CurrentUser.QtdPosts=0;
            intent=new Intent(Settings.this,MainActivity.class);
            startActivity(intent);

        });
    }
}
