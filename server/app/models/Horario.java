package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Mafra on 27/03/16.
 */

public class Horario extends Model{


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

   public TipoCarona tipo (){
        return tipo;
   }

   public String aula (){
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
}

