package models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import models.enums.TipoCarona;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mafra on 27/03/16.
 */

public class Carona {


    private Horario horario;
    @JsonSerialize
    private Usuario motorista;
    private Endereco endereco;
    private List<Usuario> listaPassageiros;
    private int vagas;
    private List<String> rota;    
    private TipoCarona tipo;

    public Carona (Usuario motorista, Horario horario, int vagas, List<String> rota, Endereco endereco, TipoCarona tipo){
        this.horario = horario;
        this.listaPassageiros = new ArrayList<Usuario>();
        this.rota = rota;
        this.vagas = vagas;
        this.endereco = endereco;
        this.tipo = tipo;
        this.motorista = motorista;
    }

    public Carona(){
        this.listaPassageiros = new ArrayList<Usuario>();
    }

    public Carona(Horario horario, int vagas, Endereco endereco, TipoCarona tipo){
        this.vagas = vagas;
        this.endereco = endereco;
        this.tipo = tipo;
        this.horario = horario;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas){
        this.vagas = vagas;
    }

    public void novoPassageiro(Usuario usr){
        listaPassageiros.add(usr);
    }

    public List<Usuario> getListaPassageiros() {
        return listaPassageiros;
    }

    public void setListaPassageiros(List<Usuario> listaPassageiros) {
        this.listaPassageiros = listaPassageiros;
    }

    public void setMotorista(Usuario motorista){
        this.motorista = motorista;
    }

    public Usuario getMotorista(){
        return motorista;
    }

    public Horario getHorario(){
        return horario;
    }

    public void setHorario(Horario horario){
        this.horario = horario;
    }

    public List<String> getRota(){
        return rota;
    }

    public void setRota(List<String> novaRota){
        this.rota = novaRota;
    }

    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }

    public Endereco getEndereco(){
        return endereco;
    }

    public TipoCarona getTipo(){
        return tipo;
    }

    public void setTipo(TipoCarona tipo){
        this.tipo = tipo;
    }


    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Carona)){
            return false;
        }

        Carona carona = (Carona) obj;


        return this.getTipo().equals(carona.getTipo()) && this.getHorario().equals(carona.getHorario())
                && this.getMotorista().equals(carona.getMotorista());

    }

}
