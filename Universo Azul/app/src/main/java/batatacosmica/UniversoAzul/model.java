package batatacosmica.UniversoAzul;

import android.graphics.Bitmap;

public class model {

    public model(){}

    String Titulo;
    String Username;
    String Comment;
    String Data;
    Integer Cod_Thread;
    Bitmap Image;

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        this.Comment = comment;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        this.Data = data;
    }
    public Bitmap getImage(){
        return Image;
    }
    public void setImage(Bitmap Image){
        this.Image = Image;
    }

    public Integer getCod_Thread() {
        return Cod_Thread;
    }

    public void setCod_Thread(Integer cod_Thread) {
        Cod_Thread = cod_Thread;
    }
}
