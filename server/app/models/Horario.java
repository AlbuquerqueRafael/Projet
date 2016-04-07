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

    private String data;
    private String hora;
    private String tipo;

    @ManyToOne(cascade= CascadeType.ALL)
    @JsonIgnore
    public Usuario usuario;

    public static Model.Finder<Long, Horario> find = new Model.Finder<Long, Horario>(
            Long.class, Horario.class
    );

    public Horario(String tipo, String dia_hora, String hora) {
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

