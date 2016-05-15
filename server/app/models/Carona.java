package models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import models.enums.TipoCarona;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import play.data.validation.Constraints;
import play.db.ebean.*;
import com.avaje.ebean.Model;

/**
 * Created by Mafra on 27/03/16.
 */

@Entity
public class Carona extends Model{

    private static final long serialVersionUID = 1L;

    @Id
    public long id;

    @Constraints.Required
    private Horario horario;

    @Constraints.Required
    @JsonSerialize
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario motorista;

    @Constraints.Required
    private Endereco endereco;

    @Constraints.Required
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Usuario> listaPassageiros;

    @Constraints.Required
    private int vagas;

    @Constraints.Required
    private List<String> rota;    

    @Constraints.Required
    private TipoCarona tipo;

    public static Model.Finder<Long,Carona> find = new Model.Finder<Long,Carona>(Long.class, Carona.class);

    public Carona (Usuario motorista, Horario horario, int vagas, List<String> rota, Endereco endereco, TipoCarona tipo){
        this.horario = horario;
        this.listaPassageiros = new ArrayList<Usuario>();
        this.rota = rota;
        this.vagas = vagas;
        this.endereco = endereco;
        this.tipo = tipo;
        this.motorista = motorista;
    }

    //necessário para mapeação JSON
    public Carona(){
        this.listaPassageiros = new ArrayList<Usuario>();
    }

    //necessário para limitar as informações que o cliente deve receber
    public Carona(Horario horario, int vagas, Usuario motorista, TipoCarona tipo){
        this.vagas = vagas;
        this.motorista = motorista;
        this.tipo = tipo;
        this.horario = horario;
    }
    //necessário para limitar as informações que o cliente deve receber
    public Carona(Horario horario, TipoCarona tipo, Endereco endereco, int vagas){
        this.horario = horario;
        this.tipo = tipo;
        this.endereco = endereco;
        this.vagas = vagas;
    }

    public Carona(Horario horario, Usuario motorista){
        this.horario = horario;
        this.motorista = motorista;
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
