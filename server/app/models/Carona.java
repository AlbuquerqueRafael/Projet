package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mafra on 27/03/16.
 */

public class Carona {


    private Horario horario;
    private Usuario motorista;
    private Endereco endereco;
    private List<Usuario> listaPassageiros;
    private int vagas;



    public Carona (Usuario motorista, Horario horario, int vagas){
        this.horario = horario;
        this.motorista = motorista;
        //this.listaPassageiros = new ArrayList<Usuario>();
        this.vagas = vagas;
    }

    public Carona(){
        //this.listaPassageiros = new ArrayList<Usuario>();
    }


    public int getVagas() {
        return vagas;
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

    public void setMotorista(Usuario usuario){
        this.motorista = usuario;
    }

    public Horario getHorario(){
        return horario;
    }

    public void setHorario(Horario horario){
        this.horario = horario;
    }

    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }

    public Endereco getEndereco(){
        return endereco;
    }
}
