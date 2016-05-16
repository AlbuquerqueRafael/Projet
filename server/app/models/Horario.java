package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import play.data.validation.Constraints;
import play.db.ebean.*;
import com.avaje.ebean.Model;
import models.enums.DiaDaSemana;

/**
 * Created by Mafra on 27/03/16.
 */

@Entity
public class Horario extends Model{

    private static final long serialVersionUID = 1L;

    @Id
    protected Long id;

    private String aula; // 8-10, 10-12, 14-16, 16-18

    private DiaDaSemana dia;

    public static Model.Finder<Long,Horario> find = new Model.Finder<Long,Horario>(Long.class, Horario.class);

    public Horario(String aula, DiaDaSemana dia) {
        this.aula = aula;
        this.dia = dia;
    }

    //necessário para mapeação JSON
    public Horario(){

    }

   public DiaDaSemana getDia(){
        return dia;
   }

   public String getAula (){
        return aula;
   }

   public void setDia(DiaDaSemana dia){
        this.dia = dia;
   }

   public void setAula(String aula){
        this.aula = aula;
   }


    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Horario)){
            return false;
        }

        Horario horario = (Horario) obj;


        return this.getAula().equals(horario.getAula()) && horario.getDia().equals(horario.getDia());

    }
}

