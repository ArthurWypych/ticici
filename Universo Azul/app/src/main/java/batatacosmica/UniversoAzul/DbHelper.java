package batatacosmica.UniversoAzul;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.Nullable;


public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(@Nullable Context context) {
        super(context,NOME_BANCO,null,VERSAO);
    }




    public static final String NOME_BANCO = "Main.db";

    public static final String UserTable = "Users";
    public static final String Cod_User = "Cod_User";
    public static final String Username = "Username";
    public static final String Senha = "Senha";
    public static final String Tipo_Conta = "Tipo_Conta";
    public static final String Email = "Email";
    public static final String Qtd_Original_Posts = "Qtd_Original_Posts";
//<!------------------------------------------------------------

    public static final String ThreadTable = "Threads";
    public static final String Cod_Thread = "Cod_Thread";
    public static final String Titulo = "Titulo";
    public static final String ComentarioThread = "Comentario";
    public static final String Tipo_Thread = "Tipo_Thread";
    public static final String ThreadTime = "Time";
    public static final String Cod_UserThreadFk = "Cod_User";
    public static final String ThreadImagem = "Imagem";
//<!------------------------------------------------------------

    public static final String PostTable = "Posts";
    public static final String Cod_Post = "Cod_Post";
    public static final String ComentarioPost = "Comentario";
    public static final String PostTime = "Time";
    public static final String Cod_UserPostFk = "Cod_User";
    public static final String PostImagem = "Imagem";
    public static final String Cod_ThreadPostFk = "Cod_Thread";
//<!------------------------------------------------------------


    private static final String ReplyTable = "Replies";
    private static final String Cod_Reply = "Cod_Reply";
    private static final String ComentarioReply = "Comentario";
    private static final String ReplyTime = "Time";
    private static final String Cod_UserReplyFk = "Cod_User";
    private static final String ReplyImagem = "Imagem";
    private static final String Cod_PostReplyFk = "Cod_Post";
//<!------------------------------------------------------------

    private static final int VERSAO = 1;





    @Override
    public void onCreate(SQLiteDatabase db) {

        String Users = "CREATE TABLE "+UserTable+"("
                + Cod_User + " integer primary key autoincrement,"
                + Username + " text not null unique,"
                + Senha + " text not null,"
                + Email + " text,"
                + Tipo_Conta + " text not null,"
                + Qtd_Original_Posts + " text"
                +")";



        String Thread = "CREATE TABLE "+ThreadTable+"("
                + Cod_Thread + " integer primary key autoincrement,"
                + Titulo + " text not null,"
                + ComentarioThread + " text not null,"
                + Tipo_Thread + " text not null,"
                + Cod_UserThreadFk + " integer not null,"
                + ThreadTime + " text,"
                + ThreadImagem + " blob,"
                + "Foreign key ("+Cod_UserThreadFk+") REFERENCES Users(Cod_User)"
                +")";

        String Post = "CREATE TABLE "+PostTable+"("
                + Cod_Post + " integer primary key autoincrement,"
                + ComentarioPost + " text not null,"
                + Cod_ThreadPostFk + " text not null,"
                + Cod_UserPostFk + " integer not null,"
                + PostTime + " text,"
                + PostImagem + " blob,"
                + "Foreign key "+"("+Cod_ThreadPostFk+") REFERENCES Threads(Cod_Thread),"
                + "Foreign key "+"("+Cod_UserPostFk+") REFERENCES Users(Cod_User)"
                +")";

        String Reply = "CREATE TABLE "+ReplyTable+"("
                + Cod_Reply + " integer primary key autoincrement,"
                + ComentarioReply + " text not null,"
                + ReplyImagem + " text,"
                + ReplyTime + " text,"
                + Cod_UserReplyFk + " integer not null,"
                + Cod_PostReplyFk + " integer not null,"
                + "Foreign key "+"("+Cod_UserReplyFk+") REFERENCES Users(Cod_User),"
                + "Foreign key "+"("+Cod_PostReplyFk+") REFERENCES Posts(Cod_Post)"
                +")";

        db.execSQL(Users);
        db.execSQL(Thread);
        db.execSQL(Post);
        db.execSQL(Reply);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i!=i1) {
            db.execSQL("DROP TABLE IF EXISTS " + UserTable);
            db.execSQL("DROP TABLE IF EXISTS " + ThreadTable);
            db.execSQL("DROP TABLE IF EXISTS " + PostTable);
            db.execSQL("DROP TABLE IF EXISTS " + ReplyTable);
            onCreate(db);
        }

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
        }
        else{
            db.execSQL("PRAGMA foreign_keys=ON");
        }
        //super.onConfigure(db); -> CASO DE MERDA.TXT
    }


}
