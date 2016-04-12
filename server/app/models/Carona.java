package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 27/03/16.
 */

@Entity
public class Carona extends Model {

    @Id
    public Long id;
    public static Model.Finder<Long, Carona> find = new Model.Finder<Long, Carona>(
            Long.class, Carona.class
    );

    public Horario horario;
//    private int vagas;

    @ManyToOne(cascade = CascadeType.ALL)
    public Usuario usuario;
    /*@OneToMany(mappedBy = "carona", cascade= CascadeType.ALL)
    @JsonIgnore*/
   // private List<Usuario> listaPassageiros;



    public Carona (Horario horario){
       // this.vagas = vagas;
    //    this.horario = horario;
    //    listaPassageiros = new ArrayList<Usuario>();
        this.horario = horario;
    }

/*
    public int getVagas() {
        return vagas;
    }

    public List<Usuario> getListaPassageiros() {
        return listaPassageiros;
    }
*/
 /*   public Horario getHorario(){
        return horario;
    }
/*
  //  public String getTipoCarona(){
     //   return tipoCarona;
   // }


/*    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public void setListaPassageiros(List<Usuario> listaPassageiros) {
        this.listaPassageiros = listaPassageiros;
    }
/*
    public void setHorario(Horario horario){

        this.horario = horario;
    }*/

 /*   public String getData() {

        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHora(){
        return hora;
    }

    public void setHora(String hora){
        this.hora = hora;
    }*/


    public Horario getHorario(){
        return horario;
    }

    public void setHorario(Horario horario){
        this.horario = horario;
    }

}
