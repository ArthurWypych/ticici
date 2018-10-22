package batatacosmica.UniversoAzul;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;

public class CreateProfessionalsThread extends AppCompatActivity {

    TextView tvUrl;
    Button btnUpload,btnCriar;
    EditText edtTitulo,edtComent;
    ImageView targetImage;

    DbHelper dbHelper=new DbHelper(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_professionals_thread);
        getSupportActionBar().hide();

        tvUrl=findViewById(R.id.textView22);
        btnUpload=findViewById(R.id.button10);
        btnCriar=findViewById(R.id.button11);
        edtTitulo=findViewById(R.id.editText9);
        edtComent=findViewById(R.id.editText10);
        targetImage = findViewById(R.id.imageView9);

        btnCriar.setOnClickListener(v -> {
            String titulo,coment;
            Bitmap bitmap;


            bitmap=((BitmapDrawable)targetImage.getDrawable()).getBitmap();
            titulo=edtTitulo.getText().toString();
            coment=edtComent.getText().toString();
            insertAll(titulo,coment,bitmap);

        });

        btnUpload.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if(requestCode==RESULT_OK){
        Uri targetUri=data.getData();
        tvUrl.setText(targetUri.toString());
        Bitmap bitmap;
        try{
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
            targetImage.setImageBitmap(bitmap);
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //}
        //else{
        // Toast.makeText(this,"Não foi possivel carregar a imagem.",Toast.LENGTH_LONG).show();
        //}
    }

    private void insertAll(String titulo,String coment, Bitmap bitmap)
    {
        Date currentTime=Calendar.getInstance().getTime();
        db=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Titulo",titulo);
        contentValues.put("Comentario",coment);
        contentValues.put("Tipo_Thread","Professional");
        contentValues.put("Time",currentTime.toString());
        contentValues.put("Cod_User",CurrentUser.id);
        byte[] data = getBitmapAsByteArray(bitmap);
        contentValues.put("Imagem",data);
        Long newRowId=db.insert("Threads",null,contentValues);

        if (newRowId ==-1) {
            Toast.makeText(getBaseContext(),"Erro ao criar Thread",Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(getBaseContext(),"Thread Criada com sucesso!",Toast.LENGTH_LONG).show();
            Intent i=new Intent(CreateProfessionalsThread.this,ParentsBoard.class);
            startActivity(i);

        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
