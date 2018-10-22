package batatacosmica.UniversoAzul;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    EditText EdtUser;
    EditText EdtSenha;
    Button btnLogin;
    Button btnCadastro;
    Intent intent;


    //public class CurrentUser{public static int id;}

    DbHelper mDbHelper;

    SQLiteDatabase db;

    boolean vaiLogar=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        EdtUser=(EditText)findViewById(R.id.editText);
        EdtSenha=(EditText)findViewById(R.id.editText2);
        btnLogin=(Button)findViewById(R.id.button);
        btnCadastro=(Button)findViewById(R.id.button2);

        mDbHelper= new DbHelper(this);
        db=mDbHelper.getWritableDatabase();


       btnCadastro.setOnClickListener(view -> {
           intent=new Intent(MainActivity.this,Cadastro_Activity.class);
           startActivity(intent);
         });


       btnLogin.setOnClickListener(view -> {
           String userInput=EdtUser.getText().toString();
           String senhaInput=EdtSenha.getText().toString();


           String userAux, senhaAux,tipo_Conta;
           Integer QtdPosts;



           Cursor idCursor,userPasswordCursor;


           idCursor=db.rawQuery("SELECT Cod_User FROM USERS",null);
           idCursor.moveToFirst();

           if(idCursor != null && idCursor.moveToFirst()){

           for(int i=1; i<=idCursor.getCount(); i++){
               if(idCursor.moveToFirst()){
                   //String a[]={"Username","Senha"};

                   //userPasswordCursor = db.query("Users",a,"Cod_User="+i,null,null,null,null,"1");
                   userPasswordCursor = db.rawQuery("SELECT Username,Senha,Tipo_Conta,Qtd_Original_Posts FROM USERS WHERE Cod_User=" + i, null);
                   userPasswordCursor.moveToFirst();

                   userAux = userPasswordCursor.getString(userPasswordCursor.getColumnIndex("Username"));
                   senhaAux = userPasswordCursor.getString(userPasswordCursor.getColumnIndex("Senha"));
                   tipo_Conta=userPasswordCursor.getString(userPasswordCursor.getColumnIndex("Tipo_Conta"));
                   QtdPosts=userPasswordCursor.getInt(userPasswordCursor.getColumnIndex("Qtd_Original_Posts"));

                   checkLogin(userAux,senhaAux,i,userInput,senhaInput,tipo_Conta,QtdPosts);
                   idCursor.moveToNext();
               }
               else {
                   //String a[]={"Username","Senha"};
                   userPasswordCursor = db.rawQuery("SELECT Username,Senha,Tipo_Conta,Qtd_Original_Posts FROM USERS WHERE Cod_User=" + i, null);
                   //userPasswordCursor = db.query("Users",a,"Cod_User="+i,null,null,null,null,"1");
                   userPasswordCursor.moveToFirst();
                   userAux = userPasswordCursor.getString(userPasswordCursor.getColumnIndex("Username"));
                   senhaAux = userPasswordCursor.getString(userPasswordCursor.getColumnIndex("Senha"));
                   tipo_Conta=userPasswordCursor.getString(userPasswordCursor.getColumnIndex("Tipo_Conta"));
                   QtdPosts=userPasswordCursor.getInt(userPasswordCursor.getColumnIndex("Qtd_Original_Posts"));
                   Log.i("UTIL DEMAIS","A porra do user: "+userAux);
                   checkLogin(userAux,senhaAux,i,userInput,senhaInput,tipo_Conta,QtdPosts);
                   idCursor.moveToNext();
               }
           }

               if(vaiLogar=true){
                   Toast.makeText(getBaseContext(),"Usuário ou senha incorretos",Toast.LENGTH_LONG).show();

               }




                  // Log.i("UTIL DEMAIS","A porra do id: "+ id.toString());
                  // Log.i("UTIL DEMAIS","A porra do user: "+userAux);
                  // Log.i("UTIL DEMAIS","A porra da senha: "+senhaAux);
                  // Log.i("UTIL DEMAIS","A porra do email: "+email);
          }


       });
    }

    public void checkLogin(String userAux,String senhaAux,int id,String userInput,String senhaInput,String Tipo_Conta,Integer QtdPosts){


            if (userAux.equals(userInput) && senhaAux.equals(senhaInput)) {
                intent = new Intent(MainActivity.this, NewsFeed.class);
                vaiLogar=true;
                CurrentUser.id = id;
                CurrentUser.Username=userAux;
                CurrentUser.QtdPosts=QtdPosts;
                CurrentUser.Tipo_Conta=Tipo_Conta;
                startActivity(intent);
            }
            else{
                vaiLogar=false;
            }
        }







}
