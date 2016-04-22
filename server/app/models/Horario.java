package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Mafra on 27/03/16.
 */

public class Horario{


    private String aula; // 8-10, 10-12, 14-16, 16-18
    private DiaDaSemana dia;

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

