package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


/**
 * Created by Mafra on 11/04/2016
 */

public class Solicitacao {

    private Status status;
    private Usuario passageiro_da_solicitacao;
    private Carona carona;

    public Solicitacao (Carona c, Usuario passageiro){
        
        this.carona = c;
        this.passageiro_da_solicitacao = passageiro;
        this.status = Status.PENDENTE;

    }

    public Carona getCarona() {
        return carona;
    }

    public Usuario getPassageiro() {
        return passageiro_da_solicitacao;
    }
    
    public Status getStatus() {
        return status;
    }

    public boolean setStatus(Status status) {
        if (this.status == Status.PENDENTE){
            this.status = status;
            return true;
        }

        return false;
    }

    
}