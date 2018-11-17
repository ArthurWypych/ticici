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

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static batatacosmica.UniversoAzul.CreateParentThread.getBitmapAsByteArray;

public class CreatePost extends AppCompatActivity {

    EditText edtComment;
    Button btnPostar, btnUpload;
    TextView tvUrl;
    ImageView targetImage;

    Integer Cod;

    DbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        getSupportActionBar().hide();

        dbHelper=new DbHelper(this);

        Bundle b = getIntent().getExtras();
        Cod = b.getInt("Cod");

        setObj();
        setButtons();


    }

    private void setObj() {
        edtComment = findViewById(R.id.editText12);
        btnPostar = findViewById(R.id.button12);
        btnUpload = findViewById(R.id.button13);
        tvUrl = findViewById(R.id.textView38);
        targetImage = findViewById(R.id.imageView17);
    }

    private void setButtons() {
        btnUpload.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 0);
        });

        btnPostar.setOnClickListener(v -> {
            String coment;
            Bitmap bitmap;

            bitmap=((BitmapDrawable)targetImage.getDrawable()).getBitmap();
            coment=edtComment.getText().toString();
            insertAll(coment,bitmap);

        });


    }

    private void insertAll(String Comment,Bitmap Image){
        Date currentTime=Calendar.getInstance().getTime();
        db=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Comentario",Comment);
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate1 = df1.format(currentTime);
        contentValues.put("Time", formattedDate1);
        contentValues.put("Cod_User",CurrentUser.id);
        contentValues.put("Cod_Thread",Cod);
        byte[] data = getBitmapAsByteArray(Image);
        contentValues.put("Imagem",data);
        Long newRowId=db.insert("Posts",null,contentValues);

        if (newRowId ==-1) {
            Toast.makeText(getBaseContext(),"Erro ao Postar",Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(getBaseContext(),"Post Criado com sucesso!",Toast.LENGTH_LONG).show();
            Intent i=new Intent(this,InsideTheThread.class);
            Bundle b= new Bundle();
            b.putInt("Cod",Cod);
            i.putExtras(b);
            startActivity(i);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if(requestCode==RESULT_OK){
        Uri targetUri = data.getData();
        tvUrl.setText(targetUri.toString());
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
            targetImage.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
