package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.enums.Status;


/**
 * Created by Mafra on 11/04/2016
 */

public class Solicitacao {

    private Status status;
    private Usuario passageiro;
    @JsonProperty
    private Carona carona;

    public Solicitacao (Carona c, Usuario passageiro){
        
        this.carona = c;
        this.passageiro = passageiro;
        this.status = Status.PENDENTE;

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
    
    public Status getStatus() {
        return status;
    }

    public void setCarona(Carona carona){
        this.carona = carona;
    }

    public void setPassageiro(Usuario passageiro){
        this.passageiro = passageiro;
    }

    public boolean setStatus(Status status) {
        if (this.status == Status.PENDENTE){
            this.status = status;
            return true;
        }

        return false;
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