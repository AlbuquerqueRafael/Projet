package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rafael on 10/04/16.
 */

@Entity
public class Endereco extends Model {

    @Id
    @Constraints.Min(100)
    private Long id;

    @Constraints.Required
    private String rua;

    @Constraints.Required
    private String bairro;


    public Endereco(String rua, String bairro){
        this.rua = rua;
        this.bairro = bairro;
    }


    public String getRua(){
        return rua;
    }

    public void setRua(String rua){
        this.rua = rua;
    }

    public String getBairro(){
        return rua;
    }

    public void setBairro(String bairro){
        this.bairro = bairro;
    }
}
