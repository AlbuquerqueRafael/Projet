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
    
    public String getStatus() {
        return status;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    
}