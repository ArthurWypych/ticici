package batatacosmica.UniversoAzul;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

public class Cadastro_Activity extends AppCompatActivity {

    EditText edtUsername, edtPassword,edtConfirmPassword,edtEmail;
    ProgressBar mProgressBar;
    Switch SwTipoConta;
    CheckBox ShrekBox;
    Button btnCriar;

    DbHelper mDbHelper;

    SQLiteDatabase db;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity);
        getSupportActionBar().hide();
        edtUsername= findViewById(R.id.editText3);
        edtPassword= findViewById(R.id.editText4);
        edtConfirmPassword= findViewById(R.id.editText6);
        edtEmail= findViewById(R.id.editText5);
        mProgressBar = findViewById(R.id.progressBar);
        SwTipoConta= findViewById(R.id.switch1);
        ShrekBox= findViewById(R.id.checkBox);
        btnCriar= findViewById(R.id.button3);

        mDbHelper= new DbHelper(this);
        db=mDbHelper.getWritableDatabase();

        btnCriar.setOnClickListener(view -> {
            exibirProgress(true);
            String Username=edtUsername.getText().toString();
            String Password=edtPassword.getText().toString();
            String ConfirmPassword=edtConfirmPassword.getText().toString();
            String Email=edtEmail.getText().toString();
            String z="";
            if(Username.equals("")||Password.equals("")||ConfirmPassword.equals("")||Email.equals(""))
            {
                z="Preencha todos os campos";
            }

            else{
                if(!Password.equals(ConfirmPassword)){
                    z="As senhas não conferem... ";
                }

                else {
                    if(ShrekBox.isChecked()) {
                        try {
                            ContentValues contentValues=new ContentValues();
                            contentValues.put(mDbHelper.Username,Username);
                            contentValues.put(mDbHelper.Senha,Password);
                            contentValues.put(mDbHelper.Email,Email);
                            String Tipo =SwitchTest();
                            contentValues.put(mDbHelper.Tipo_Conta,Tipo);
                            contentValues.put(mDbHelper.Qtd_Original_Posts,0);

                            Long newRowId=db.insert(mDbHelper.UserTable,null,contentValues);
                            limparBangs();

                            if (newRowId ==-1) {
                                z= "Erro ao inserir registro";
                            }
                            else {
                                z = "Cadastrado com sucesso! Bem vindo(a) ao Universo Azul!";
                            }

                        } catch (Exception e) {
                            z = "Exceptions: " + e;

                        }

                    }
                    else{
                        z="Por favor, aceite os termos e condições.";
                    }
                }
            }
            exibirProgress(false);
            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();

        });


    }

    private void exibirProgress(boolean exibir) {
        mProgressBar.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }

    private String SwitchTest(){
        String Tipo;
        if(SwTipoConta.isChecked()){
            Tipo="Professional";
        }
        else {
            Tipo="Parents";
        }

        return Tipo;
    }

    public void limparBangs(){
        edtUsername.setText("");
        edtPassword.setText("");
        edtConfirmPassword.setText("");
        edtEmail.setText("");
    }




}
