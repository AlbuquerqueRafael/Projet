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
    protected long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Horario horario;

    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario motorista;

    @ManyToOne(cascade = CascadeType.ALL)
    private Endereco endereco;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<Usuario> listaPassageiros;

    private int vagas;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Rota> rota;

    private TipoCarona tipo;

    public static Model.Finder<Long,Carona> find = new Model.Finder<Long,Carona>(Long.class, Carona.class);

    public Carona (Usuario motorista, Horario horario, int vagas, List<Rota> rota,  Endereco endereco, TipoCarona tipo){
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
        this.rota = new ArrayList<Rota>();
        this.horario = horario;
        this.listaPassageiros = new ArrayList<Usuario>();
    }
    //necessário para limitar as informações que o cliente deve receber
    public Carona(Horario horario, TipoCarona tipo, Endereco endereco, int vagas){
        this.horario = horario;
        this.tipo = tipo;
        this.endereco = endereco;
        this.vagas = vagas;
        this.rota = new ArrayList<Rota>();
        this.listaPassageiros = new ArrayList<Usuario>();
    }

    public Carona(Horario horario, Usuario motorista){
        this.horario = horario;
        this.motorista = motorista;
        this.rota = new ArrayList<Rota>();
        this.listaPassageiros = new ArrayList<Usuario>();
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

    public List<Rota> getRota(){
        return rota;
    }

    public void setRota(List<Rota> novaRota){
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
