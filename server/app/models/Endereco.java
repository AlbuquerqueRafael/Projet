package models;

import javax.persistence.*;
import play.data.validation.Constraints;
import play.db.ebean.*;
import com.avaje.ebean.Model;

/**
 * Created by rafael on 10/04/16.
 */

@Entity
public class Endereco extends Model{


    private static final long serialVersionUID = 1L;

    @Id
    protected Long id;

    private String rua;

    private String bairro;


    public Endereco(String rua, String bairro){
        this.rua = rua;
        this.bairro = bairro;
    }

    //necessário para mapeação JSON
    public Endereco(){

    }

    public String getRua(){
        return rua;
    }

    public void setRua(String rua){
        this.rua = rua;
    }

    public String getBairro(){
        return bairro;
    }

    public void setBairro(String bairro){
        this.bairro = bairro;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Endereco)){
            return false;
        }

        Endereco end = (Endereco) obj;

        return end.getBairro().equals(this.getBairro());

    }
}
