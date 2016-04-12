package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * Created by rafael on 27/03/16.
 */
@Entity
public class Horario {

    @Id
    @Constraints.Min(100)
    private Long id;



    @Constraints.Required
    private String data;


    @Constraints.Required
    private String hora;


    @Constraints.Required
    private String tipo;

    public Horario(String dia_hora, String hora, String tipo) {
        this.data = dia_hora;
        this.hora = hora;
        this.tipo = tipo;
    }

    public String getData() {
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
    }
}

