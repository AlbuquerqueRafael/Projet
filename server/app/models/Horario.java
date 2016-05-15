package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import play.data.validation.Constraints;
import play.db.ebean.*;
import com.avaje.ebean.Model;

/**
 * Created by Mafra on 27/03/16.
 */

@Entity
public class Horario extends Model{

    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    @Constraints.Required
    private String aula; // 8-10, 10-12, 14-16, 16-18

    @Constraints.Required
    private DiaDaSemana dia;

    public Horario(String aula, models.DiaDaSemana dia) {
        this.aula = aula;
        this.dia = dia;
    }

    //necessário para mapeação JSON
    public Horario(){

    }

   public models.DiaDaSemana getDia(){
        return dia;
   }

   public String getAula (){
        return aula;
   }

   public void setDia(models.DiaDaSemana dia){
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

