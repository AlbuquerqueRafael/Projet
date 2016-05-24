package models;


import com.avaje.ebean.Finder;
import com.avaje.ebean.Model;
import com.avaje.ebean.text.StringFormatter;
import sun.rmi.runtime.Log;

import javax.persistence.*;
import play.data.validation.Constraints;
import play.db.ebean.*;
import com.avaje.ebean.Model;
import java.sql.*;

/**
 * Created by rafael on 22/05/16.
 */

@Entity
public class Autenticador extends Model{

    @Id
    private Long id;

    private Long id_Usuario;
    private Timestamp inicio;
    private String authToken;

    public static Model.Finder<Long,Autenticador> find = new Model.Finder<Long,Autenticador>(Long.class, Autenticador.class);

    public Autenticador(Long id_Usuario, Timestamp inicio, String authToken){
        this.id_Usuario = id_Usuario;
        this.inicio = inicio;
        this.authToken = authToken;
    }

    public Autenticador(){

    }

    public Long getId_Usuario(){
        return id_Usuario;
    }

    public Timestamp getInicio(){
        return  inicio;
    }

    public String getAuthToken() { return  authToken; }

}
