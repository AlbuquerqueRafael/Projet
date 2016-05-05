package models;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by Mafra on 11/04/2016
 */

public class Solicitacao {

    private Usuario passageiro;
    @JsonProperty
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