package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rafael on 16/05/16.
 */
@Entity
public class Rota extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    protected long id;

    private String destino;

    public Rota() {

    }

    public Rota(String destino) {
        this.destino = destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    };

}