package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Mafra on 27/03/16.
 */

public class Horario{


    private String aula; // 8-10, 10-12, 14-16, 16-18
    private DiaDaSemana dia;
    private TipoCarona tipo;

    public Horario(String aula, DiaDaSemana dia, TipoCarona tipo) {
        this.aula = aula;
        this.dia = dia;
        this.tipo = tipo;
    }

   public DiaDaSemana getDia(){
        return dia;
   }

   public TipoCarona getTipo (){
        return tipo;
   }

   public String getAula (){
        return aula;
   }

   public void setDia(DiaDaSemana dia){
        this.dia = dia;
   }

   public void setTipo(TipoCarona tipo){
        this.tipo = tipo;
   }

   public void setAula(String aula){
        this.aula = aula;
   }

   public boolean equals (Object obj){
      boolean resp = false;

      if (obj instanceof Horario){
        Horario other = (Horario) obj;

        resp = other.getTipo() == getTipo() && other.getDia() == getDia() && other.getAula().equals(getAula());
      }
      
      return resp;
   }
}

