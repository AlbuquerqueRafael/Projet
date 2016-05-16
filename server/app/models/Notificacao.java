package models;

import javax.persistence.*;
import play.data.validation.Constraints;
import play.db.ebean.*;
import com.avaje.ebean.Model;




@Entity
public class Notificacao extends Model{

	private static final long serialVersionUID = 1L;

    @Id
    protected long id;

    private String message;


    public static Model.Finder<Long,Notificacao> find = new Model.Finder<Long,Notificacao>(Long.class, Notificacao.class);

    public Notificacao(){

    }

    public Notificacao(String message){
    	this.message = message;
    }

    public void setMessage(String message){
    	this.message = message;
    }

    public String getMessage(){
    	return message;
    }
/*
    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }*/






}