package models;

import com.avaje.ebean.Model;
import jdk.nashorn.internal.ir.annotations.Ignore;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 27/03/16.
 */

@Entity
@DiscriminatorValue(value = "F")
public class Motorista extends Usuario{

    private String vagas;

    public Motorista(String nome, String matricula, String telefone, String bairro, String email, String rua,
                     String senha, String vagas) {
        super(nome,matricula,telefone,bairro, email,rua,senha);

        this.vagas = vagas;//avagasagas;
     //   this.caronas = new ArrayList<Carona>();
  //      this.solicitacoes = new ArrayList<Solicitacao>();
    }

    public String getVagas() {
        return vagas;
    }

    public void setVagas(String vagas) {
        this.vagas = vagas;

    }






}
