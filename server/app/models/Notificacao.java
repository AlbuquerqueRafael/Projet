package models;

import javax.persistence.*;
import play.data.validation.Constraints;
import play.db.ebean.*;
import com.avaje.ebean.Model;





@Entity
public class Notificacao extends Model{

	private static final long serialVersionUID = 1L;

    @Id
    public long id;


    @Constraints.Required
    private String message;


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





}