package batatacosmica.UniversoAzul;

import android.graphics.Bitmap;

public class model {

    public model(){}

    String TituloModel;
    String UsernameModel;
    String CommentModel;
    String DataModel;
    Integer Cod_ThreadModel;
    Bitmap Image;

    public String getTitulo() {
        return TituloModel;
    }

    public void setTitulo(String titulo) {
        TituloModel = titulo;
    }

    public String getUsername() {
        return UsernameModel;
    }

    public void setUsername(String username) {
        this.UsernameModel = username;
    }

    public String getComment() {
        return CommentModel;
    }

    public void setComment(String comment) {
        this.CommentModel = comment;
    }

    public String getData() {
        return DataModel;
    }

    public void setData(String data) {
        this.DataModel = data;
    }
    public Bitmap getImage(){
        return Image;
    }
    public void setImage(Bitmap Image){
        this.Image = Image;
    }

    public Integer getCod_Thread() {
        return Cod_ThreadModel;
    }

    public void setCod_Thread(Integer cod_Thread) {
        this.Cod_ThreadModel = cod_Thread;
    }
}
