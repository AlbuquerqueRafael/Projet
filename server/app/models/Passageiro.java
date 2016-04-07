package models;

import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 06/04/16.
 */
@Entity
@DiscriminatorValue("P")
public class Passageiro extends Usuario{

    public Passageiro (String nome, String matricula, String telefone, String bairro, String email, String rua, String senha){
        super(nome, matricula, telefone, bairro, email, rua, senha);
    }


}
