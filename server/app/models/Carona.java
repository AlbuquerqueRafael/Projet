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
    private List<String> rota;    


    public Carona (Usuario motorista, Horario horario, int vagas, List<String> rota, Endereco endereco){
        this.horario = horario;
        this.motorista = motorista;
        this.listaPassageiros = new ArrayList<Usuario>();
        this.rota = rota;
        this.vagas = vagas;
        this.endereco = endereco;
    }

    public Carona(){
        this.listaPassageiros = new ArrayList<Usuario>();
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
}
