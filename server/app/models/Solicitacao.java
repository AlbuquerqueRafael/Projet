package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import play.data.validation.Constraints;
import play.db.ebean.*;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * Created by Mafra on 11/04/2016
 */

@Entity
public class Solicitacao extends Model{

     private static final long serialVersionUID = 1L;


    @Id
    public long id;

    @Constraints.Required
    @JsonSerialize
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario passageiro;


    @Constraints.Required
    @JsonProperty
    @ManyToOne(cascade = CascadeType.ALL)
    private Carona carona;

    public Solicitacao (Carona c, Usuario passageiro){
        
        this.carona = c;
        this.passageiro = passageiro;

    }

    //necessário para mapeação JSON
    public Solicitacao(){

    }

    public Carona getCarona() {
        return carona;
    }

    public Usuario getPassageiro() {
        return passageiro;
    }

    public void setCarona(Carona carona){
        this.carona = carona;
    }

    public void setPassageiro(Usuario passageiro){
        this.passageiro = passageiro;
    }


    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Solicitacao)){
            return false;
        }

        Solicitacao solicitacao = (Solicitacao) obj;


        return this.getPassageiro().equals(solicitacao.getPassageiro());

    }

    
}